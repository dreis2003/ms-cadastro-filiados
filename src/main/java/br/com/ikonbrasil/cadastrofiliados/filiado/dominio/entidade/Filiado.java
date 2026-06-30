package br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.excecao.ExcecaoDeDominio;
import br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.objetoValor.Endereco;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.enumerador.Sexo;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.enumerador.StatusFiliado;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Filiado {

    private final UUID id;
    private String nomeCompleto;
    private String nomeSocial;
    private LocalDate dataNascimento;
    private String cpf;
    private String rg;
    private String email;
    private String telefone;
    private Sexo sexo;
    private String tipoSanguineo;
    private LocalDate dataInicioTreinamento;
    private String nacionalidade;
    private String naturalidade;
    private String profissao;
    private String responsavelNome;
    private String responsavelParentesco;
    private String responsavelCpf;
    private String responsavelTelefone;
    private String responsavelEmail;
    private String dadosMedicos;
    private Boolean parqPergunta1;
    private Boolean parqPergunta2;
    private Boolean parqPergunta3;
    private Boolean parqPergunta4;
    private Boolean parqPergunta5;
    private Boolean parqPergunta6;
    private Boolean parqPergunta7;
    private String assinaturaNome;
    private Boolean declaracaoSaudeAceite;
    private LocalDateTime declaracaoSaudeAceiteEm;
    private String declaracaoSaudeAceiteIp;
    private Endereco endereco;
    private String numeroInternacional;
    private StatusFiliado status;
    private String fotoPerfilUrl;
    private UUID filialId;
    private final LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    public Filiado(
            UUID id,
            String nomeCompleto,
            String nomeSocial,
            LocalDate dataNascimento,
            String cpf,
            String rg,
            String email,
            String telefone,
            Sexo sexo,
            Endereco endereco,
            String numeroInternacional,
            StatusFiliado status,
            String fotoPerfilUrl,
            UUID filialId,
            LocalDateTime dataCadastro,
            LocalDateTime dataAtualizacao
    ) {
        this(id, nomeCompleto, nomeSocial, dataNascimento, cpf, rg, email, telefone, sexo,
                null, null, null, null, null,
                null, null, null, null, null,
                null,
                null, null, null, null, null, null, null,
                null, null, null, null,
                endereco, numeroInternacional, status, fotoPerfilUrl, filialId, dataCadastro, dataAtualizacao);
    }

    public Filiado(
            UUID id,
            String nomeCompleto,
            String nomeSocial,
            LocalDate dataNascimento,
            String cpf,
            String rg,
            String email,
            String telefone,
            Sexo sexo,
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
            Endereco endereco,
            String numeroInternacional,
            StatusFiliado status,
            String fotoPerfilUrl,
            UUID filialId,
            LocalDateTime dataCadastro,
            LocalDateTime dataAtualizacao
    ) {
        this.id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
        this.dataCadastro = Objects.requireNonNullElseGet(dataCadastro, LocalDateTime::now);
        alterarDados(nomeCompleto, nomeSocial, dataNascimento, cpf, rg, email, telefone, sexo, tipoSanguineo,
                dataInicioTreinamento, nacionalidade, naturalidade, profissao, responsavelNome, responsavelParentesco,
                responsavelCpf, responsavelTelefone, responsavelEmail, dadosMedicos, parqPergunta1, parqPergunta2,
                parqPergunta3, parqPergunta4, parqPergunta5, parqPergunta6, parqPergunta7, assinaturaNome,
                declaracaoSaudeAceite, declaracaoSaudeAceiteIp, endereco, numeroInternacional, filialId);
        this.status = Objects.requireNonNullElse(status, StatusFiliado.ATIVO);
        this.fotoPerfilUrl = normalizarTextoOpcional(fotoPerfilUrl);
        if (declaracaoSaudeAceiteEm != null) {
            this.declaracaoSaudeAceiteEm = declaracaoSaudeAceiteEm;
        }
        this.dataAtualizacao = dataAtualizacao;
    }

    public void alterarDados(
            String nomeCompleto,
            String nomeSocial,
            LocalDate dataNascimento,
            String cpf,
            String rg,
            String email,
            String telefone,
            Sexo sexo,
            Endereco endereco,
            String numeroInternacional,
            UUID filialId
    ) {
        alterarDados(nomeCompleto, nomeSocial, dataNascimento, cpf, rg, email, telefone, sexo, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, endereco,
                numeroInternacional, filialId);
    }

    public void alterarDados(
            String nomeCompleto,
            String nomeSocial,
            LocalDate dataNascimento,
            String cpf,
            String rg,
            String email,
            String telefone,
            Sexo sexo,
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
            String declaracaoSaudeAceiteIp,
            Endereco endereco,
            String numeroInternacional,
            UUID filialId
    ) {
        exigirTexto(nomeCompleto, "Nome completo do filiado e obrigatorio");
        if (dataNascimento == null) {
            throw new ExcecaoDeDominio("Data de nascimento do filiado e obrigatoria");
        }
        if (filialId == null) {
            throw new ExcecaoDeDominio("Filiado deve estar vinculado a uma filial");
        }

        this.nomeCompleto = nomeCompleto.trim();
        this.nomeSocial = normalizarTextoOpcional(nomeSocial);
        this.dataNascimento = dataNascimento;
        this.cpf = normalizarCpf(cpf);
        this.rg = normalizarTextoOpcional(rg);
        this.email = normalizarTextoOpcional(email);
        this.telefone = normalizarTextoOpcional(telefone);
        this.sexo = Objects.requireNonNullElse(sexo, Sexo.NAO_INFORMADO);
        this.tipoSanguineo = normalizarTextoOpcional(tipoSanguineo);
        this.dataInicioTreinamento = dataInicioTreinamento;
        this.nacionalidade = normalizarTextoOpcional(nacionalidade);
        this.naturalidade = normalizarTextoOpcional(naturalidade);
        this.profissao = normalizarTextoOpcional(profissao);
        this.responsavelNome = normalizarTextoOpcional(responsavelNome);
        this.responsavelParentesco = normalizarTextoOpcional(responsavelParentesco);
        this.responsavelCpf = normalizarCpf(responsavelCpf);
        this.responsavelTelefone = normalizarTextoOpcional(responsavelTelefone);
        this.responsavelEmail = normalizarTextoOpcional(responsavelEmail);
        this.dadosMedicos = normalizarTextoOpcional(dadosMedicos);
        this.parqPergunta1 = Objects.requireNonNullElse(parqPergunta1, false);
        this.parqPergunta2 = Objects.requireNonNullElse(parqPergunta2, false);
        this.parqPergunta3 = Objects.requireNonNullElse(parqPergunta3, false);
        this.parqPergunta4 = Objects.requireNonNullElse(parqPergunta4, false);
        this.parqPergunta5 = Objects.requireNonNullElse(parqPergunta5, false);
        this.parqPergunta6 = Objects.requireNonNullElse(parqPergunta6, false);
        this.parqPergunta7 = Objects.requireNonNullElse(parqPergunta7, false);
        this.assinaturaNome = normalizarTextoOpcional(assinaturaNome);
        this.declaracaoSaudeAceite = Objects.requireNonNullElse(declaracaoSaudeAceite, false);
        if (Boolean.TRUE.equals(this.declaracaoSaudeAceite)) {
            exigirTexto(this.assinaturaNome, "Assinatura da declaracao de saude e obrigatoria");
            this.declaracaoSaudeAceiteEm = LocalDateTime.now();
            this.declaracaoSaudeAceiteIp = normalizarTextoOpcional(declaracaoSaudeAceiteIp);
        }
        this.endereco = endereco;
        this.numeroInternacional = normalizarTextoOpcional(numeroInternacional);
        this.filialId = filialId;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void ativar() {
        this.status = StatusFiliado.ATIVO;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void marcarComoPendenteAprovacao() {
        this.status = StatusFiliado.PENDENTE_APROVACAO;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void aprovarCadastro() {
        if (!StatusFiliado.PENDENTE_APROVACAO.equals(this.status)) {
            throw new ExcecaoDeDominio("Somente filiado pendente pode ser aprovado");
        }
        this.status = StatusFiliado.ATIVO;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void inativar() {
        this.status = StatusFiliado.INATIVO;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void alterarFotoPerfil(String fotoPerfilUrl) {
        this.fotoPerfilUrl = normalizarTextoOpcional(fotoPerfilUrl);
        this.dataAtualizacao = LocalDateTime.now();
    }

    public boolean estaAtivo() {
        return StatusFiliado.ATIVO.equals(status);
    }

    private static void exigirTexto(String valor, String mensagem) {
        if (valor == null || valor.isBlank()) {
            throw new ExcecaoDeDominio(mensagem);
        }
    }

    private static String normalizarTextoOpcional(String valor) {
        return valor == null || valor.isBlank() ? null : valor.trim();
    }

    private static String normalizarCpf(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        return valor.replaceAll("\\D", "");
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

    public Sexo getSexo() {
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

    public Endereco getEndereco() {
        return endereco;
    }

    public String getNumeroInternacional() {
        return numeroInternacional;
    }

    public String getFotoPerfilUrl() {
        return fotoPerfilUrl;
    }

    public UUID getFilialId() {
        return filialId;
    }

    public StatusFiliado getStatus() {
        return status;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
}
