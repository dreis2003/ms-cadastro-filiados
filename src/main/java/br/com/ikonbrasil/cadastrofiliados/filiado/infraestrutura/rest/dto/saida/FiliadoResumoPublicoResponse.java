package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.saida;

import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;

import java.time.LocalDate;
import java.util.UUID;

public record FiliadoResumoPublicoResponse(
        UUID id,
        UUID filialId,
        String nomeCompleto,
        LocalDate dataNascimento,
        String cpf,
        String numeroInternacional,
        String status
) {
    public static FiliadoResumoPublicoResponse de(Filiado filiado) {
        return new FiliadoResumoPublicoResponse(
                filiado.getId(),
                filiado.getFilialId(),
                filiado.getNomeCompleto(),
                filiado.getDataNascimento(),
                filiado.getCpf(),
                filiado.getNumeroInternacional(),
                filiado.getStatus().name()
        );
    }
}
