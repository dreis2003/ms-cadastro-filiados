package br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.excecao.ExcecaoDeDominio;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.enumerador.StatusFilial;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Filial {

    private final UUID id;
    private String nome;
    private String codigo;
    private String responsavel;
    private String logoUrl;
    private String emailResponsavel;
    private String telefone;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private StatusFilial status;
    private final LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    public Filial(
            UUID id,
            String nome,
            String codigo,
            String responsavel,
            String cidade,
            String estado,
            StatusFilial status,
            LocalDateTime dataCadastro,
            LocalDateTime dataAtualizacao
    ) {
        this(id, nome, codigo, responsavel, null, null, null, null, null, null, null, cidade, estado, null, status, dataCadastro, dataAtualizacao);
    }

    public Filial(
            UUID id,
            String nome,
            String codigo,
            String responsavel,
            String logoUrl,
            String emailResponsavel,
            String telefone,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String estado,
            String cep,
            StatusFilial status,
            LocalDateTime dataCadastro,
            LocalDateTime dataAtualizacao
    ) {
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
        this.dataCadastro = Objects.requireNonNullElseGet(dataCadastro, LocalDateTime::now);
        alterarDados(nome, codigo, responsavel, logoUrl, emailResponsavel, telefone, logradouro, numero, complemento, bairro, cidade, estado, cep);
        this.status = Objects.requireNonNullElse(status, StatusFilial.ATIVA);
        this.dataAtualizacao = dataAtualizacao;
    }

    public void alterarDados(String nome, String codigo, String responsavel, String cidade, String estado) {
        alterarDados(nome, codigo, responsavel, null, null, null, null, null, null, null, cidade, estado, null);
    }

    public void alterarDados(
            String nome,
            String codigo,
            String responsavel,
            String logoUrl,
            String emailResponsavel,
            String telefone,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String estado,
            String cep
    ) {
        exigirTexto(nome, "Nome da filial e obrigatorio");
        exigirTexto(codigo, "Codigo da filial e obrigatorio");
        exigirTexto(cidade, "Cidade da filial e obrigatoria");
        exigirTexto(estado, "Estado da filial e obrigatorio");
        if (estado.trim().length() != 2) {
            throw new ExcecaoDeDominio("Estado da filial deve possuir 2 caracteres");
        }

        this.nome = nome.trim();
        this.codigo = codigo.trim().toUpperCase();
        this.responsavel = normalizarTextoOpcional(responsavel);
        this.logoUrl = normalizarTextoOpcional(logoUrl);
        this.emailResponsavel = normalizarTextoOpcional(emailResponsavel);
        this.telefone = normalizarTextoOpcional(telefone);
        this.logradouro = normalizarTextoOpcional(logradouro);
        this.numero = normalizarTextoOpcional(numero);
        this.complemento = normalizarTextoOpcional(complemento);
        this.bairro = normalizarTextoOpcional(bairro);
        this.cidade = cidade.trim();
        this.estado = estado.trim().toUpperCase();
        this.cep = normalizarTextoOpcional(cep);
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void ativar() {
        this.status = StatusFilial.ATIVA;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void inativar() {
        this.status = StatusFilial.INATIVA;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public boolean estaAtiva() {
        return StatusFilial.ATIVA.equals(status);
    }

    public void validarPodeReceberNovoFiliado() {
        if (!estaAtiva()) {
            throw new ExcecaoDeDominio("Filial inativa nao pode receber novos filiados");
        }
    }

    private static void exigirTexto(String valor, String mensagem) {
        if (valor == null || valor.isBlank()) {
            throw new ExcecaoDeDominio(mensagem);
        }
    }

    private static String normalizarTextoOpcional(String valor) {
        return valor == null || valor.isBlank() ? null : valor.trim();
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public StatusFilial getStatus() {
        return status;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
}
