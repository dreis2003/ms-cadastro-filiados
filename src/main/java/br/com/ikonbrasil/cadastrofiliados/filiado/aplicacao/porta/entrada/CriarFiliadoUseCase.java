package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.objetoValor.Endereco;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.enumerador.Sexo;

import java.time.LocalDate;
import java.util.UUID;

public interface CriarFiliadoUseCase {

    Filiado executar(ComandoCriarFiliado comando);

    record ComandoCriarFiliado(
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
            String fotoPerfilUrl,
            UUID filialId
    ) {
        public ComandoCriarFiliado(
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
                String fotoPerfilUrl,
                UUID filialId
        ) {
            this(nomeCompleto, nomeSocial, dataNascimento, cpf, rg, email, telefone, sexo,
                    null, null, null, null, null, null, null, null, null, null, null,
                    false, false, false, false, false, false, false,
                    nomeCompleto, true, null, endereco, numeroInternacional, fotoPerfilUrl, filialId);
        }
    }
}
