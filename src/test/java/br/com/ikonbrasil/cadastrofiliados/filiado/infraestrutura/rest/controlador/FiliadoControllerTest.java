package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.controlador;

import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.AprovarFiliadoPendenteUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.AtivarFiliadoUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.AtualizarFiliadoUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.AtualizarFotoPerfilFiliadoUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.BuscarFiliadoPorIdUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.CriarAutocadastroFiliadoUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.CriarFiliadoUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.InativarFiliadoUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.ListarFiliadosUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.ListarFiliadosPendentesUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.mapper.FiliadoRestMapper;
import br.com.ikonbrasil.cadastrofiliados.seguranca.infraestrutura.configuracao.ConfiguracaoSeguranca;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FiliadoController.class)
@Import({ConfiguracaoSeguranca.class, FiliadoRestMapper.class})
class FiliadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CriarFiliadoUseCase criarFiliadoUseCase;

    @MockitoBean
    private CriarAutocadastroFiliadoUseCase criarAutocadastroFiliadoUseCase;

    @MockitoBean
    private AtualizarFiliadoUseCase atualizarFiliadoUseCase;

    @MockitoBean
    private AtualizarFotoPerfilFiliadoUseCase atualizarFotoPerfilFiliadoUseCase;

    @MockitoBean
    private BuscarFiliadoPorIdUseCase buscarFiliadoPorIdUseCase;

    @MockitoBean
    private ListarFiliadosUseCase listarFiliadosUseCase;

    @MockitoBean
    private ListarFiliadosPendentesUseCase listarFiliadosPendentesUseCase;

    @MockitoBean
    private AprovarFiliadoPendenteUseCase aprovarFiliadoPendenteUseCase;

    @MockitoBean
    private AtivarFiliadoUseCase ativarFiliadoUseCase;

    @MockitoBean
    private InativarFiliadoUseCase inativarFiliadoUseCase;

    @Test
    void deveCriarFiliadoComoMatrizAdmin() throws Exception {
        UUID filialId = UUID.randomUUID();
        Filiado filiado = new Filiado(UUID.randomUUID(), "Joao Nakamura", null, LocalDate.of(1990, 1, 10), "12345678900", null, "joao@email.com", null, null, null, null, null, null, filialId, null, null);
        when(criarFiliadoUseCase.executar(any())).thenReturn(filiado);

        mockMvc.perform(post("/api/v1/filiados")
                        .with(jwt().authorities(
                                new SimpleGrantedAuthority("ROLE_MATRIZ_ADMIN"),
                                new SimpleGrantedAuthority("FILIADO_CRIAR")
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nomeCompleto": "Joao Nakamura",
                                  "dataNascimento": "1990-01-10",
                                  "cpf": "123.456.789-00",
                                  "email": "joao@email.com",
                                  "assinaturaNome": "Joao Nakamura",
                                  "declaracaoSaudeAceite": true,
                                  "filialId": "%s"
                                }
                                """.formatted(filialId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(filiado.getId().toString()))
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.filialId").value(filialId.toString()));
    }

    @Test
    void naoDeveCriarFiliadoEmOutraFilialComoResponsavel() throws Exception {
        UUID filialRequestId = UUID.randomUUID();
        UUID filialUsuarioId = UUID.randomUUID();

        mockMvc.perform(post("/api/v1/filiados")
                        .with(jwt()
                                .jwt(token -> token.claim("filialId", filialUsuarioId.toString()))
                                .authorities(
                                        new SimpleGrantedAuthority("ROLE_FILIAL_RESPONSAVEL"),
                                        new SimpleGrantedAuthority("FILIADO_CRIAR")
                                ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nomeCompleto": "Joao Nakamura",
                                  "dataNascimento": "1990-01-10",
                                  "assinaturaNome": "Joao Nakamura",
                                  "declaracaoSaudeAceite": true,
                                  "filialId": "%s"
                                }
                                """.formatted(filialRequestId)))
                .andExpect(status().isForbidden());
    }

    @Test
    void deveAtualizarFiliadoDaPropriaFilialComoResponsavel() throws Exception {
        UUID filialId = UUID.randomUUID();
        UUID filiadoId = UUID.randomUUID();
        Filiado filiadoAtual = new Filiado(filiadoId, "Joao Nakamura", null, LocalDate.of(1990, 1, 10), null, null, null, null, null, null, null, null, null, filialId, null, null);
        Filiado filiadoAtualizado = new Filiado(filiadoId, "Joao Silva Nakamura", null, LocalDate.of(1990, 1, 10), null, null, "joao@email.com", null, null, null, null, null, null, filialId, null, null);
        when(buscarFiliadoPorIdUseCase.executar(filiadoId)).thenReturn(filiadoAtual);
        when(atualizarFiliadoUseCase.executar(eq(filiadoId), any())).thenReturn(filiadoAtualizado);

        mockMvc.perform(put("/api/v1/filiados/{id}", filiadoId)
                        .with(jwt()
                                .jwt(token -> token.claim("filialId", filialId.toString()))
                                .authorities(
                                        new SimpleGrantedAuthority("ROLE_FILIAL_RESPONSAVEL"),
                                        new SimpleGrantedAuthority("FILIADO_EDITAR")
                                ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nomeCompleto": "Joao Silva Nakamura",
                                  "dataNascimento": "1990-01-10",
                                  "email": "joao@email.com",
                                  "assinaturaNome": "Joao Silva Nakamura",
                                  "declaracaoSaudeAceite": true,
                                  "filialId": "%s"
                                }
                                """.formatted(filialId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(filiadoId.toString()))
                .andExpect(jsonPath("$.nomeCompleto").value("Joao Silva Nakamura"));
    }

    @Test
    void naoDeveAtualizarFiliadoDeOutraFilialComoResponsavel() throws Exception {
        UUID filialFiliadoId = UUID.randomUUID();
        UUID filialUsuarioId = UUID.randomUUID();
        UUID filiadoId = UUID.randomUUID();
        Filiado filiadoAtual = new Filiado(filiadoId, "Joao Nakamura", null, LocalDate.of(1990, 1, 10), null, null, null, null, null, null, null, null, null, filialFiliadoId, null, null);
        when(buscarFiliadoPorIdUseCase.executar(filiadoId)).thenReturn(filiadoAtual);

        mockMvc.perform(put("/api/v1/filiados/{id}", filiadoId)
                        .with(jwt()
                                .jwt(token -> token.claim("filialId", filialUsuarioId.toString()))
                                .authorities(
                                        new SimpleGrantedAuthority("ROLE_FILIAL_RESPONSAVEL"),
                                        new SimpleGrantedAuthority("FILIADO_EDITAR")
                                ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nomeCompleto": "Joao Silva Nakamura",
                                  "dataNascimento": "1990-01-10",
                                  "assinaturaNome": "Joao Silva Nakamura",
                                  "declaracaoSaudeAceite": true,
                                  "filialId": "%s"
                                }
                                """.formatted(filialFiliadoId)))
                .andExpect(status().isForbidden());
    }

    @Test
    void deveAtualizarFotoPerfil() throws Exception {
        UUID filialId = UUID.randomUUID();
        UUID filiadoId = UUID.randomUUID();
        Filiado filiadoAtual = new Filiado(filiadoId, "Joao Nakamura", null, LocalDate.of(1990, 1, 10), null, null, null, null, null, null, null, null, null, filialId, null, null);
        Filiado filiadoAtualizado = new Filiado(filiadoId, "Joao Nakamura", null, LocalDate.of(1990, 1, 10), null, null, null, null, null, null, null, null, "https://cdn.ikon/filiado.jpg", filialId, null, null);
        when(buscarFiliadoPorIdUseCase.executar(filiadoId)).thenReturn(filiadoAtual);
        when(atualizarFotoPerfilFiliadoUseCase.executar(filiadoId, "https://cdn.ikon/filiado.jpg")).thenReturn(filiadoAtualizado);

        mockMvc.perform(patch("/api/v1/filiados/{id}/foto-perfil", filiadoId)
                        .with(jwt().authorities(
                                new SimpleGrantedAuthority("ROLE_MATRIZ_ADMIN"),
                                new SimpleGrantedAuthority("FILIADO_EDITAR")
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "fotoPerfilUrl": "https://cdn.ikon/filiado.jpg"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fotoPerfilUrl").value("https://cdn.ikon/filiado.jpg"));
    }

    @Test
    void deveInativarFiliadoComPermissao() throws Exception {
        UUID filialId = UUID.randomUUID();
        UUID filiadoId = UUID.randomUUID();
        Filiado filiadoAtual = new Filiado(filiadoId, "Joao Nakamura", null, LocalDate.of(1990, 1, 10), null, null, null, null, null, null, null, null, null, filialId, null, null);
        Filiado filiadoInativado = new Filiado(filiadoId, "Joao Nakamura", null, LocalDate.of(1990, 1, 10), null, null, null, null, null, null, null, null, null, filialId, null, null);
        filiadoInativado.inativar();
        when(buscarFiliadoPorIdUseCase.executar(filiadoId)).thenReturn(filiadoAtual);
        when(inativarFiliadoUseCase.executar(filiadoId)).thenReturn(filiadoInativado);

        mockMvc.perform(patch("/api/v1/filiados/{id}/inativar", filiadoId)
                        .with(jwt().authorities(
                                new SimpleGrantedAuthority("ROLE_MATRIZ_ADMIN"),
                                new SimpleGrantedAuthority("FILIADO_INATIVAR")
                        )))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("INATIVO"));
    }
}
