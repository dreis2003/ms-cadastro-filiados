package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.controlador;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.AcessoNegadoRegraNegocioException;
import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RegraDeNegocioException;
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
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.entrada.AtualizarFiliadoRequest;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.entrada.AtualizarFotoPerfilFiliadoRequest;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.entrada.CriarFiliadoRequest;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.saida.FiliadoResponse;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.saida.FiliadoResumoPublicoResponse;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.mapper.FiliadoRestMapper;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.saida.RepositorioFiliado;
import br.com.ikonbrasil.cadastrofiliados.seguranca.infraestrutura.autenticacao.AutenticacaoAtual;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/filiados")
@Tag(name = "Filiados", description = "Cadastro e consulta de filiados")
@SecurityRequirement(name = "bearerAuth")
public class FiliadoController {

    private final CriarFiliadoUseCase criarFiliadoUseCase;
    private final CriarAutocadastroFiliadoUseCase criarAutocadastroFiliadoUseCase;
    private final AtualizarFiliadoUseCase atualizarFiliadoUseCase;
    private final AtualizarFotoPerfilFiliadoUseCase atualizarFotoPerfilFiliadoUseCase;
    private final BuscarFiliadoPorIdUseCase buscarFiliadoPorIdUseCase;
    private final ListarFiliadosUseCase listarFiliadosUseCase;
    private final ListarFiliadosPendentesUseCase listarFiliadosPendentesUseCase;
    private final AprovarFiliadoPendenteUseCase aprovarFiliadoPendenteUseCase;
    private final AtivarFiliadoUseCase ativarFiliadoUseCase;
    private final InativarFiliadoUseCase inativarFiliadoUseCase;
    private final FiliadoRestMapper filiadoRestMapper;
    private final RepositorioFiliado repositorioFiliado;

    public FiliadoController(
            CriarFiliadoUseCase criarFiliadoUseCase,
            CriarAutocadastroFiliadoUseCase criarAutocadastroFiliadoUseCase,
            AtualizarFiliadoUseCase atualizarFiliadoUseCase,
            AtualizarFotoPerfilFiliadoUseCase atualizarFotoPerfilFiliadoUseCase,
            BuscarFiliadoPorIdUseCase buscarFiliadoPorIdUseCase,
            ListarFiliadosUseCase listarFiliadosUseCase,
            ListarFiliadosPendentesUseCase listarFiliadosPendentesUseCase,
            AprovarFiliadoPendenteUseCase aprovarFiliadoPendenteUseCase,
            AtivarFiliadoUseCase ativarFiliadoUseCase,
            InativarFiliadoUseCase inativarFiliadoUseCase,
            FiliadoRestMapper filiadoRestMapper,
            RepositorioFiliado repositorioFiliado
    ) {
        this.criarFiliadoUseCase = criarFiliadoUseCase;
        this.criarAutocadastroFiliadoUseCase = criarAutocadastroFiliadoUseCase;
        this.atualizarFiliadoUseCase = atualizarFiliadoUseCase;
        this.atualizarFotoPerfilFiliadoUseCase = atualizarFotoPerfilFiliadoUseCase;
        this.buscarFiliadoPorIdUseCase = buscarFiliadoPorIdUseCase;
        this.listarFiliadosUseCase = listarFiliadosUseCase;
        this.listarFiliadosPendentesUseCase = listarFiliadosPendentesUseCase;
        this.aprovarFiliadoPendenteUseCase = aprovarFiliadoPendenteUseCase;
        this.ativarFiliadoUseCase = ativarFiliadoUseCase;
        this.inativarFiliadoUseCase = inativarFiliadoUseCase;
        this.filiadoRestMapper = filiadoRestMapper;
        this.repositorioFiliado = repositorioFiliado;
    }

    @PostMapping("/publico/filiais/{filialId}/autocadastro")
    @Operation(summary = "Criar autocadastro publico de filiado")
    public ResponseEntity<FiliadoResponse> autocadastrar(
            @PathVariable UUID filialId,
            @Valid @RequestBody CriarFiliadoRequest request,
            HttpServletRequest httpServletRequest
    ) {
        if (!filialId.equals(request.filialId())) {
            throw new RegraDeNegocioException("Filial do link publico nao corresponde ao cadastro enviado");
        }

        Filiado filiado = criarAutocadastroFiliadoUseCase.executar(filiadoRestMapper.paraComando(request, ipOrigem(httpServletRequest)));
        return ResponseEntity
                .created(URI.create("/api/v1/filiados/" + filiado.getId()))
                .body(filiadoRestMapper.paraResponse(filiado));
    }

    @GetMapping("/publico/por-cpf")
    @Operation(summary = "Consulta publica minima de filiado por CPF")
    public ResponseEntity<FiliadoResumoPublicoResponse> buscarPublicoPorCpf(@RequestParam String cpf) {
        Filiado filiado = repositorioFiliado.buscarPorCpf(cpf)
                .orElseThrow(() -> new RegraDeNegocioException("Nao foi possivel validar os dados informados"));
        return ResponseEntity.ok(FiliadoResumoPublicoResponse.de(filiado));
    }

    @GetMapping("/publico/{id}")
    @Operation(summary = "Consulta publica minima de filiado por ID")
    public ResponseEntity<FiliadoResumoPublicoResponse> buscarPublicoPorId(@PathVariable UUID id) {
        Filiado filiado = buscarFiliadoPorIdUseCase.executar(id);
        return ResponseEntity.ok(FiliadoResumoPublicoResponse.de(filiado));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('FILIADO_CRIAR')")
    @Operation(summary = "Criar filiado")
    public ResponseEntity<FiliadoResponse> criar(
            @Valid @RequestBody CriarFiliadoRequest request,
            Authentication authentication,
            HttpServletRequest httpServletRequest
    ) {
        validarAcessoFilial(request.filialId(), AutenticacaoAtual.filialId(authentication), authentication);
        Filiado filiado = criarFiliadoUseCase.executar(filiadoRestMapper.paraComando(request, ipOrigem(httpServletRequest)));
        return ResponseEntity
                .created(URI.create("/api/v1/filiados/" + filiado.getId()))
                .body(filiadoRestMapper.paraResponse(filiado));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('FILIADO_VISUALIZAR')")
    @Operation(summary = "Buscar filiado por ID")
    public ResponseEntity<FiliadoResponse> buscarPorId(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        Filiado filiado = buscarFiliadoPorIdUseCase.executar(id);
        validarAcessoFilial(filiado.getFilialId(), AutenticacaoAtual.filialId(authentication), authentication);
        return ResponseEntity.ok(filiadoRestMapper.paraResponse(filiado));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('FILIADO_VISUALIZAR')")
    @Operation(summary = "Listar filiados")
    public ResponseEntity<List<FiliadoResponse>> listar(
            @RequestParam(name = "filialId", required = false) UUID filialId,
            Authentication authentication
    ) {
        UUID filtroFilial = definirFiltroFilial(filialId, AutenticacaoAtual.filialId(authentication), authentication);
        return ResponseEntity.ok(listarFiliadosUseCase.executar(filtroFilial).stream()
                .map(filiadoRestMapper::paraResponse)
                .toList());
    }

    @GetMapping("/pendentes")
    @PreAuthorize("hasAuthority('FILIADO_VISUALIZAR')")
    @Operation(summary = "Listar autocadastros pendentes de aprovacao")
    public ResponseEntity<List<FiliadoResponse>> listarPendentes(
            @RequestParam(name = "filialId", required = false) UUID filialId,
            Authentication authentication
    ) {
        UUID filtroFilial = definirFiltroFilial(filialId, AutenticacaoAtual.filialId(authentication), authentication);
        return ResponseEntity.ok(listarFiliadosPendentesUseCase.executar(filtroFilial).stream()
                .map(filiadoRestMapper::paraResponse)
                .toList());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('FILIADO_EDITAR')")
    @Operation(summary = "Atualizar dados cadastrais do filiado")
    public ResponseEntity<FiliadoResponse> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody AtualizarFiliadoRequest request,
            Authentication authentication,
            HttpServletRequest httpServletRequest
    ) {
        Filiado filiadoAtual = buscarFiliadoPorIdUseCase.executar(id);
        UUID filialAutorizadaId = AutenticacaoAtual.filialId(authentication);
        validarAcessoFilial(filiadoAtual.getFilialId(), filialAutorizadaId, authentication);
        validarAcessoFilial(request.filialId(), filialAutorizadaId, authentication);

        Filiado filiado = atualizarFiliadoUseCase.executar(id, filiadoRestMapper.paraComando(request, ipOrigem(httpServletRequest)));
        return ResponseEntity.ok(filiadoRestMapper.paraResponse(filiado));
    }

    @PatchMapping("/{id}/foto-perfil")
    @PreAuthorize("hasAuthority('FILIADO_EDITAR')")
    @Operation(summary = "Atualizar foto de perfil do filiado")
    public ResponseEntity<FiliadoResponse> atualizarFotoPerfil(
            @PathVariable UUID id,
            @Valid @RequestBody AtualizarFotoPerfilFiliadoRequest request,
            Authentication authentication
    ) {
        Filiado filiadoAtual = buscarFiliadoPorIdUseCase.executar(id);
        validarAcessoFilial(filiadoAtual.getFilialId(), AutenticacaoAtual.filialId(authentication), authentication);
        Filiado filiado = atualizarFotoPerfilFiliadoUseCase.executar(id, request.fotoPerfilUrl());
        return ResponseEntity.ok(filiadoRestMapper.paraResponse(filiado));
    }

    @PatchMapping("/{id}/ativar")
    @PreAuthorize("hasAuthority('FILIADO_EDITAR')")
    @Operation(summary = "Ativar filiado")
    public ResponseEntity<FiliadoResponse> ativar(@PathVariable UUID id, Authentication authentication) {
        Filiado filiadoAtual = buscarFiliadoPorIdUseCase.executar(id);
        validarAcessoFilial(filiadoAtual.getFilialId(), AutenticacaoAtual.filialId(authentication), authentication);
        return ResponseEntity.ok(filiadoRestMapper.paraResponse(ativarFiliadoUseCase.executar(id)));
    }

    @PatchMapping("/{id}/aprovar")
    @PreAuthorize("hasAuthority('FILIADO_EDITAR')")
    @Operation(summary = "Aprovar autocadastro pendente")
    public ResponseEntity<FiliadoResponse> aprovar(@PathVariable UUID id, Authentication authentication) {
        Filiado filiadoAtual = buscarFiliadoPorIdUseCase.executar(id);
        validarAcessoFilial(filiadoAtual.getFilialId(), AutenticacaoAtual.filialId(authentication), authentication);
        return ResponseEntity.ok(filiadoRestMapper.paraResponse(aprovarFiliadoPendenteUseCase.executar(id)));
    }

    @PatchMapping("/{id}/inativar")
    @PreAuthorize("hasAuthority('FILIADO_INATIVAR')")
    @Operation(summary = "Inativar filiado")
    public ResponseEntity<FiliadoResponse> inativar(@PathVariable UUID id, Authentication authentication) {
        Filiado filiadoAtual = buscarFiliadoPorIdUseCase.executar(id);
        validarAcessoFilial(filiadoAtual.getFilialId(), AutenticacaoAtual.filialId(authentication), authentication);
        return ResponseEntity.ok(filiadoRestMapper.paraResponse(inativarFiliadoUseCase.executar(id)));
    }

    private static UUID definirFiltroFilial(UUID filialSolicitadaId, UUID filialAutorizadaId, Authentication authentication) {
        if (possuiPerfil(authentication, "ROLE_MATRIZ_ADMIN")) {
            return filialSolicitadaId;
        }
        if (possuiPerfil(authentication, "ROLE_FILIAL_RESPONSAVEL") && filialAutorizadaId != null) {
            if (filialSolicitadaId != null && !filialSolicitadaId.equals(filialAutorizadaId)) {
                throw new AcessoNegadoRegraNegocioException("Usuario nao possui permissao para acessar filiados desta filial");
            }
            return filialAutorizadaId;
        }
        if (possuiPerfil(authentication, "ROLE_FILIAL_PROFESSOR") && filialAutorizadaId != null) {
            if (filialSolicitadaId != null && !filialSolicitadaId.equals(filialAutorizadaId)) {
                throw new AcessoNegadoRegraNegocioException("Usuario nao possui permissao para acessar filiados desta filial");
            }
            return filialAutorizadaId;
        }
        throw new AcessoNegadoRegraNegocioException("Usuario nao possui permissao para acessar filiados");
    }

    private static void validarAcessoFilial(UUID filialAlvoId, UUID filialAutorizadaId, Authentication authentication) {
        if (possuiPerfil(authentication, "ROLE_MATRIZ_ADMIN")) {
            return;
        }
        if (possuiPerfil(authentication, "ROLE_FILIAL_RESPONSAVEL") && filialAlvoId.equals(filialAutorizadaId)) {
            return;
        }
        if (possuiPerfil(authentication, "ROLE_FILIAL_PROFESSOR") && filialAlvoId.equals(filialAutorizadaId)) {
            return;
        }
        throw new AcessoNegadoRegraNegocioException("Usuario nao possui permissao para acessar filiados desta filial");
    }

    private static boolean possuiPerfil(Authentication authentication, String perfil) {
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> perfil.equals(authority.getAuthority()));
    }

    private static String ipOrigem(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isBlank()) {
            return forwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
