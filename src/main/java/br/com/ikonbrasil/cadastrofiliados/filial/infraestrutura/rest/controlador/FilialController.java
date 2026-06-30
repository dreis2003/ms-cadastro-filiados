package br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.controlador;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.AcessoNegadoRegraNegocioException;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.AtualizarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.AtivarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.BuscarFilialPorIdUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.CriarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.InativarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.ListarFiliaisUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.dto.entrada.AtualizarFilialRequest;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.dto.entrada.CriarFilialRequest;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.dto.saida.FilialResponse;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.mapper.FilialRestMapper;
import br.com.ikonbrasil.cadastrofiliados.seguranca.infraestrutura.autenticacao.AutenticacaoAtual;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/filiais")
@Tag(name = "Filiais", description = "Cadastro e consulta de academias/dojos")
@SecurityRequirement(name = "bearerAuth")
public class FilialController {

    private final CriarFilialUseCase criarFilialUseCase;
    private final AtualizarFilialUseCase atualizarFilialUseCase;
    private final BuscarFilialPorIdUseCase buscarFilialPorIdUseCase;
    private final ListarFiliaisUseCase listarFiliaisUseCase;
    private final AtivarFilialUseCase ativarFilialUseCase;
    private final InativarFilialUseCase inativarFilialUseCase;
    private final FilialRestMapper filialRestMapper;

    public FilialController(
            CriarFilialUseCase criarFilialUseCase,
            AtualizarFilialUseCase atualizarFilialUseCase,
            BuscarFilialPorIdUseCase buscarFilialPorIdUseCase,
            ListarFiliaisUseCase listarFiliaisUseCase,
            AtivarFilialUseCase ativarFilialUseCase,
            InativarFilialUseCase inativarFilialUseCase,
            FilialRestMapper filialRestMapper
    ) {
        this.criarFilialUseCase = criarFilialUseCase;
        this.atualizarFilialUseCase = atualizarFilialUseCase;
        this.buscarFilialPorIdUseCase = buscarFilialPorIdUseCase;
        this.listarFiliaisUseCase = listarFiliaisUseCase;
        this.ativarFilialUseCase = ativarFilialUseCase;
        this.inativarFilialUseCase = inativarFilialUseCase;
        this.filialRestMapper = filialRestMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('MATRIZ_ADMIN')")
    @Operation(summary = "Criar filial")
    public ResponseEntity<FilialResponse> criar(@Valid @RequestBody CriarFilialRequest request) {
        Filial filial = criarFilialUseCase.executar(filialRestMapper.paraComando(request));
        return ResponseEntity
                .created(URI.create("/api/v1/filiais/" + filial.getId()))
                .body(filialRestMapper.paraResponse(filial));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MATRIZ_ADMIN')")
    @Operation(summary = "Atualizar filial")
    public ResponseEntity<FilialResponse> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody AtualizarFilialRequest request
    ) {
        Filial filial = atualizarFilialUseCase.executar(id, filialRestMapper.paraComando(request));
        return ResponseEntity.ok(filialRestMapper.paraResponse(filial));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar filial por ID")
    public ResponseEntity<FilialResponse> buscarPorId(
            @PathVariable UUID id,
            Authentication authentication
    ) {
        validarAcessoConsultaFilial(id, AutenticacaoAtual.filialId(authentication), authentication);
        return ResponseEntity.ok(filialRestMapper.paraResponse(buscarFilialPorIdUseCase.executar(id)));
    }

    @GetMapping
    @PreAuthorize("hasRole('MATRIZ_ADMIN')")
    @Operation(summary = "Listar filiais")
    public ResponseEntity<List<FilialResponse>> listar() {
        return ResponseEntity.ok(listarFiliaisUseCase.executar().stream()
                .map(filialRestMapper::paraResponse)
                .toList());
    }

    @PatchMapping("/{id}/ativar")
    @PreAuthorize("hasRole('MATRIZ_ADMIN')")
    @Operation(summary = "Ativar filial")
    public ResponseEntity<FilialResponse> ativar(@PathVariable UUID id) {
        return ResponseEntity.ok(filialRestMapper.paraResponse(ativarFilialUseCase.executar(id)));
    }

    @PatchMapping("/{id}/inativar")
    @PreAuthorize("hasRole('MATRIZ_ADMIN')")
    @Operation(summary = "Inativar filial")
    public ResponseEntity<FilialResponse> inativar(@PathVariable UUID id) {
        return ResponseEntity.ok(filialRestMapper.paraResponse(inativarFilialUseCase.executar(id)));
    }

    private static void validarAcessoConsultaFilial(UUID filialSolicitadaId, UUID filialAutorizadaId, Authentication authentication) {
        if (possuiPerfil(authentication, "ROLE_MATRIZ_ADMIN")) {
            return;
        }

        if (possuiPerfil(authentication, "ROLE_FILIAL_RESPONSAVEL") && filialSolicitadaId.equals(filialAutorizadaId)) {
            return;
        }

        if (possuiPerfil(authentication, "ROLE_FILIAL_PROFESSOR") && filialSolicitadaId.equals(filialAutorizadaId)) {
            return;
        }

        throw new AcessoNegadoRegraNegocioException("Usuario nao possui permissao para acessar esta filial");
    }

    private static boolean possuiPerfil(Authentication authentication, String perfil) {
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> perfil.equals(authority.getAuthority()));
    }
}
