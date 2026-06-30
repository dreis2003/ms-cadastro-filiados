package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.banco.entidade;

import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.entidade.FilialJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "filiados")
public class FiliadoJpaEntity {

    @Id
    private UUID id;

    @Column(name = "nome_completo", nullable = false, length = 180)
    private String nomeCompleto;

    @Column(name = "nome_social", length = 180)
    private String nomeSocial;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(unique = true, length = 14)
    private String cpf;

    @Column(length = 30)
    private String rg;

    @Column(length = 150)
    private String email;

    @Column(length = 30)
    private String telefone;

    @Column(length = 30)
    private String sexo;

    @Column(name = "tipo_sanguineo", length = 3)
    private String tipoSanguineo;

    @Column(name = "data_inicio_treinamento")
    private LocalDate dataInicioTreinamento;

    @Column(length = 80)
    private String nacionalidade;

    @Column(length = 120)
    private String naturalidade;

    @Column(length = 120)
    private String profissao;

    @Column(name = "responsavel_nome", length = 180)
    private String responsavelNome;

    @Column(name = "responsavel_parentesco", length = 80)
    private String responsavelParentesco;

    @Column(name = "responsavel_cpf", length = 14)
    private String responsavelCpf;

    @Column(name = "responsavel_telefone", length = 30)
    private String responsavelTelefone;

    @Column(name = "responsavel_email", length = 150)
    private String responsavelEmail;

    @Column(name = "dados_medicos", columnDefinition = "text")
    private String dadosMedicos;

    @Column(name = "parq_pergunta_1")
    private Boolean parqPergunta1;

    @Column(name = "parq_pergunta_2")
    private Boolean parqPergunta2;

    @Column(name = "parq_pergunta_3")
    private Boolean parqPergunta3;

    @Column(name = "parq_pergunta_4")
    private Boolean parqPergunta4;

    @Column(name = "parq_pergunta_5")
    private Boolean parqPergunta5;

    @Column(name = "parq_pergunta_6")
    private Boolean parqPergunta6;

    @Column(name = "parq_pergunta_7")
    private Boolean parqPergunta7;

    @Column(name = "assinatura_nome", length = 180)
    private String assinaturaNome;

    @Column(name = "declaracao_saude_aceite")
    private Boolean declaracaoSaudeAceite;

    @Column(name = "declaracao_saude_aceite_em")
    private LocalDateTime declaracaoSaudeAceiteEm;

    @Column(name = "declaracao_saude_aceite_ip", length = 80)
    private String declaracaoSaudeAceiteIp;

    @Column(length = 180)
    private String logradouro;

    @Column(length = 30)
    private String numero;

    @Column(length = 100)
    private String complemento;

    @Column(length = 100)
    private String bairro;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2)
    private String estado;

    @Column(length = 20)
    private String cep;

    @Column(name = "numero_internacional", unique = true, length = 80)
    private String numeroInternacional;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "foto_perfil_url", columnDefinition = "text")
    private String fotoPerfilUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "filial_id", nullable = false)
    private FilialJpaEntity filial;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    protected FiliadoJpaEntity() {
    }

    public FiliadoJpaEntity(
            UUID id,
            String nomeCompleto,
            String nomeSocial,
            LocalDate dataNascimento,
            String cpf,
            String rg,
            String email,
            String telefone,
            String sexo,
            String tipoSanguineo,
            LocalDate dataInicioTreinamento,
            String nacionalidade,
            String naturalidade,
            String profissao,
            String responsavelNome,
            String responsavelParentesco,
            String responsavelCpf,
            String responsavelTelefone,
            String responsavelEmail,
            String dadosMedicos,
            Boolean parqPergunta1,
            Boolean parqPergunta2,
            Boolean parqPergunta3,
            Boolean parqPergunta4,
            Boolean parqPergunta5,
            Boolean parqPergunta6,
            Boolean parqPergunta7,
            String assinaturaNome,
            Boolean declaracaoSaudeAceite,
            LocalDateTime declaracaoSaudeAceiteEm,
            String declaracaoSaudeAceiteIp,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String estado,
            String cep,
            String numeroInternacional,
            String status,
            String fotoPerfilUrl,
            FilialJpaEntity filial,
            LocalDateTime dataCadastro,
            LocalDateTime dataAtualizacao
    ) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.nomeSocial = nomeSocial;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.telefone = telefone;
        this.sexo = sexo;
        this.tipoSanguineo = tipoSanguineo;
        this.dataInicioTreinamento = dataInicioTreinamento;
        this.nacionalidade = nacionalidade;
        this.naturalidade = naturalidade;
        this.profissao = profissao;
        this.responsavelNome = responsavelNome;
        this.responsavelParentesco = responsavelParentesco;
        this.responsavelCpf = responsavelCpf;
        this.responsavelTelefone = responsavelTelefone;
        this.responsavelEmail = responsavelEmail;
        this.dadosMedicos = dadosMedicos;
        this.parqPergunta1 = parqPergunta1;
        this.parqPergunta2 = parqPergunta2;
        this.parqPergunta3 = parqPergunta3;
        this.parqPergunta4 = parqPergunta4;
        this.parqPergunta5 = parqPergunta5;
        this.parqPergunta6 = parqPergunta6;
        this.parqPergunta7 = parqPergunta7;
        this.assinaturaNome = assinaturaNome;
        this.declaracaoSaudeAceite = declaracaoSaudeAceite;
        this.declaracaoSaudeAceiteEm = declaracaoSaudeAceiteEm;
        this.declaracaoSaudeAceiteIp = declaracaoSaudeAceiteIp;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.numeroInternacional = numeroInternacional;
        this.status = status;
        this.fotoPerfilUrl = fotoPerfilUrl;
        this.filial = filial;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
    }

    public UUID getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public LocalDate getDataInicioTreinamento() {
        return dataInicioTreinamento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public String getProfissao() {
        return profissao;
    }

    public String getResponsavelNome() {
        return responsavelNome;
    }

    public String getResponsavelParentesco() {
        return responsavelParentesco;
    }

    public String getResponsavelCpf() {
        return responsavelCpf;
    }

    public String getResponsavelTelefone() {
        return responsavelTelefone;
    }

    public String getResponsavelEmail() {
        return responsavelEmail;
    }

    public String getDadosMedicos() {
        return dadosMedicos;
    }

    public Boolean getParqPergunta1() {
        return parqPergunta1;
    }

    public Boolean getParqPergunta2() {
        return parqPergunta2;
    }

    public Boolean getParqPergunta3() {
        return parqPergunta3;
    }

    public Boolean getParqPergunta4() {
        return parqPergunta4;
    }

    public Boolean getParqPergunta5() {
        return parqPergunta5;
    }

    public Boolean getParqPergunta6() {
        return parqPergunta6;
    }

    public Boolean getParqPergunta7() {
        return parqPergunta7;
    }

    public String getAssinaturaNome() {
        return assinaturaNome;
    }

    public Boolean getDeclaracaoSaudeAceite() {
        return declaracaoSaudeAceite;
    }

    public LocalDateTime getDeclaracaoSaudeAceiteEm() {
        return declaracaoSaudeAceiteEm;
    }

    public String getDeclaracaoSaudeAceiteIp() {
        return declaracaoSaudeAceiteIp;
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

    public String getNumeroInternacional() {
        return numeroInternacional;
    }

    public String getStatus() {
        return status;
    }

    public String getFotoPerfilUrl() {
        return fotoPerfilUrl;
    }

    public FilialJpaEntity getFilial() {
        return filial;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
}
