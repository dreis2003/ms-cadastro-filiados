package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.entrada;

import jakarta.validation.constraints.Size;

public record EnderecoRequest(
        @Size(max = 180) String logradouro,
        @Size(max = 30) String numero,
        @Size(max = 100) String complemento,
        @Size(max = 100) String bairro,
        @Size(max = 100) String cidade,
        @Size(min = 2, max = 2) String estado,
        @Size(max = 20) String cep
) {
}
