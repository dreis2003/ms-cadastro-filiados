package br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.dto.entrada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record CriarFilialRequest(
        @NotBlank
        @Size(max = 150)
        String nome,

        @NotBlank
        @Size(max = 50)
        String codigo,

        @Size(max = 150)
        String responsavel,

        @Size(max = 500)
        String logoUrl,

        @Email
        @Size(max = 150)
        String emailResponsavel,

        @Size(max = 30)
        String telefone,

        @Size(max = 180)
        String logradouro,

        @Size(max = 30)
        String numero,

        @Size(max = 100)
        String complemento,

        @Size(max = 100)
        String bairro,

        @NotBlank
        @Size(max = 100)
        String cidade,

        @NotBlank
        @Size(min = 2, max = 2)
        String estado,

        @Size(max = 20)
        String cep
) {
}
