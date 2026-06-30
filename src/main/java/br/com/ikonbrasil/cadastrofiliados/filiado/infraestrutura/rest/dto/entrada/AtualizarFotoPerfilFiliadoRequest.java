package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.entrada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarFotoPerfilFiliadoRequest(
        @NotBlank
        @Size(max = 2000)
        String fotoPerfilUrl
) {
}
