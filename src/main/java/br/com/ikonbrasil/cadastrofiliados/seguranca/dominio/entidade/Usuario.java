package br.com.ikonbrasil.cadastrofiliados.seguranca.dominio.entidade;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.excecao.ExcecaoDeDominio;
import br.com.ikonbrasil.cadastrofiliados.seguranca.dominio.enumerador.StatusUsuario;
import br.com.ikonbrasil.cadastrofiliados.seguranca.dominio.enumerador.TipoPerfilAcesso;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Usuario {

    private final UUID id;
    private String nome;
    private String email;
    private String senhaHash;
    private TipoPerfilAcesso perfil;
    private UUID filialId;
    private StatusUsuario status;
    private final LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    public Usuario(
            UUID id,
            String nome,
            String email,
            String senhaHash,
            TipoPerfilAcesso perfil,
            UUID filialId,
            StatusUsuario status,
            LocalDateTime dataCadastro,
            LocalDateTime dataAtualizacao
    ) {
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
        this.dataCadastro = Objects.requireNonNullElseGet(dataCadastro, LocalDateTime::now);
        alterarDados(nome, email, senhaHash, perfil, filialId);
        this.status = Objects.requireNonNullElse(status, StatusUsuario.ATIVO);
        this.dataAtualizacao = dataAtualizacao;
    }

    public void alterarDados(String nome, String email, String senhaHash, TipoPerfilAcesso perfil, UUID filialId) {
        exigirTexto(nome, "Nome do usuario e obrigatorio");
        exigirTexto(email, "Email do usuario e obrigatorio");
        exigirTexto(senhaHash, "Senha do usuario e obrigatoria");
        if (perfil == null) {
            throw new ExcecaoDeDominio("Perfil de acesso e obrigatorio");
        }
        if (TipoPerfilAcesso.RESPONSAVEL_FILIAL.equals(perfil) && filialId == null) {
            throw new ExcecaoDeDominio("Responsavel de filial deve estar vinculado a uma filial");
        }

        this.nome = nome.trim();
        this.email = email.trim().toLowerCase();
        this.senhaHash = senhaHash;
        this.perfil = perfil;
        this.filialId = filialId;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public boolean podeAcessarFilial(UUID filialAlvoId) {
        if (TipoPerfilAcesso.MATRIZ_ADMIN.equals(perfil) || TipoPerfilAcesso.RESPONSAVEL_MATRIZ.equals(perfil)) {
            return true;
        }
        return filialId != null && filialId.equals(filialAlvoId);
    }

    public void ativar() {
        this.status = StatusUsuario.ATIVO;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void inativar() {
        this.status = StatusUsuario.INATIVO;
        this.dataAtualizacao = LocalDateTime.now();
    }

    private static void exigirTexto(String valor, String mensagem) {
        if (valor == null || valor.isBlank()) {
            throw new ExcecaoDeDominio(mensagem);
        }
    }
}
