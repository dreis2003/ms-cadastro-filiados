package br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.controlador;

import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.AtualizarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.AtivarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.BuscarFilialPorIdUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.CriarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.InativarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.ListarFiliaisUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.mapper.FilialRestMapper;
import br.com.ikonbrasil.cadastrofiliados.seguranca.infraestrutura.configuracao.ConfiguracaoSeguranca;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilialController.class)
@Import({ConfiguracaoSeguranca.class, FilialRestMapper.class})
class FilialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CriarFilialUseCase criarFilialUseCase;

    @MockitoBean
    private AtualizarFilialUseCase atualizarFilialUseCase;

    @MockitoBean
    private BuscarFilialPorIdUseCase buscarFilialPorIdUseCase;

    @MockitoBean
    private ListarFiliaisUseCase listarFiliaisUseCase;

    @MockitoBean
    private AtivarFilialUseCase ativarFilialUseCase;

    @MockitoBean
    private InativarFilialUseCase inativarFilialUseCase;

    @Test
    void deveCriarFilialComoMatrizAdmin() throws Exception {
        Filial filial = new Filial(UUID.randomUUID(), "Matriz Brasil", "MATRIZ", "Responsavel", "Sao Paulo", "SP", null, null, null);
        when(criarFilialUseCase.executar(any())).thenReturn(filial);

        mockMvc.perform(post("/api/v1/filiais")
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_MATRIZ_ADMIN")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nome": "Matriz Brasil",
                                  "codigo": "MATRIZ",
                                  "responsavel": "Responsavel",
                                  "cidade": "Sao Paulo",
                                  "estado": "SP"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/v1/filiais/" + filial.getId()))
                .andExpect(jsonPath("$.id").value(filial.getId().toString()))
                .andExpect(jsonPath("$.codigo").value("MATRIZ"))
                .andExpect(jsonPath("$.status").value("ATIVA"));
    }

    @Test
    void naoDeveCriarFilialComoResponsavelDeFilial() throws Exception {
        mockMvc.perform(post("/api/v1/filiais")
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_FILIAL_RESPONSAVEL")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nome": "Matriz Brasil",
                                  "codigo": "MATRIZ",
                                  "cidade": "Sao Paulo",
                                  "estado": "SP"
                                }
                                """))
                .andExpect(status().isForbidden());
    }

    @Test
    void deveAtualizarFilialComoMatrizAdmin() throws Exception {
        UUID filialId = UUID.randomUUID();
        Filial filial = new Filial(filialId, "Dojo Centro", "CENTRO", "Responsavel", "Curitiba", "PR", null, null, null);
        when(atualizarFilialUseCase.executar(any(), any())).thenReturn(filial);

        mockMvc.perform(put("/api/v1/filiais/{id}", filialId)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_MATRIZ_ADMIN")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "nome": "Dojo Centro",
                                  "codigo": "CENTRO",
                                  "responsavel": "Responsavel",
                                  "cidade": "Curitiba",
                                  "estado": "PR"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(filialId.toString()))
                .andExpect(jsonPath("$.codigo").value("CENTRO"))
                .andExpect(jsonPath("$.status").value("ATIVA"));
    }

    @Test
    void deveBuscarPropriaFilialComoResponsavelDeFilial() throws Exception {
        UUID filialId = UUID.randomUUID();
        Filial filial = new Filial(filialId, "Dojo Centro", "CENTRO", null, "Curitiba", "PR", null, null, null);
        when(buscarFilialPorIdUseCase.executar(filialId)).thenReturn(filial);

        mockMvc.perform(get("/api/v1/filiais/{id}", filialId)
                        .with(jwt()
                                .jwt(token -> token.claim("filialId", filialId.toString()))
                                .authorities(new SimpleGrantedAuthority("ROLE_FILIAL_RESPONSAVEL"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(filialId.toString()))
                .andExpect(jsonPath("$.codigo").value("CENTRO"));
    }

    @Test
    void naoDeveBuscarFilialDeOutroResponsavel() throws Exception {
        UUID filialSolicitadaId = UUID.randomUUID();
        UUID filialUsuarioId = UUID.randomUUID();

        mockMvc.perform(get("/api/v1/filiais/{id}", filialSolicitadaId)
                        .with(jwt()
                                .jwt(token -> token.claim("filialId", filialUsuarioId.toString()))
                                .authorities(new SimpleGrantedAuthority("ROLE_FILIAL_RESPONSAVEL"))))
                .andExpect(status().isForbidden());
    }
}
