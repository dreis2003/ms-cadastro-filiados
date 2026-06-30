package br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.dto.saida;

import java.time.LocalDateTime;
import java.util.UUID;

public record FilialResponse(
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
}
