package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.saida;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record FiliadoResponse(
        UUID id,
        String nomeCompleto,
        String nomeSocial,
        LocalDate dataNascimento,
        String cpf,
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
        String numeroInternacional,
        String status,
        String fotoPerfilUrl,
        EnderecoResponse endereco,
        UUID filialId,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao
) {
}
