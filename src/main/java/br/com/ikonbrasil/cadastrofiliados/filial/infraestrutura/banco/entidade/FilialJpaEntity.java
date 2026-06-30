package br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "filiais")
public class FilialJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(length = 150)
    private String responsavel;

    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @Column(name = "email_responsavel", length = 150)
    private String emailResponsavel;

    @Column(length = 30)
    private String telefone;

    @Column(length = 180)
    private String logradouro;

    @Column(length = 30)
    private String numero;

    @Column(length = 100)
    private String complemento;

    @Column(length = 100)
    private String bairro;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(length = 20)
    private String cep;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    protected FilialJpaEntity() {
    }

    public FilialJpaEntity(
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
            String status,
            LocalDateTime dataCadastro,
            LocalDateTime dataAtualizacao
    ) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.responsavel = responsavel;
        this.logoUrl = logoUrl;
        this.emailResponsavel = emailResponsavel;
        this.telefone = telefone;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.status = status;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
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

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
}
