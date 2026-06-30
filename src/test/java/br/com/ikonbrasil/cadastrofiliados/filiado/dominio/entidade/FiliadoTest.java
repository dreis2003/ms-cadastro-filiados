package br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade;

import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.enumerador.StatusFiliado;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class FiliadoTest {

    @Test
    void deveCriarFiliadoAtivoPorPadraoSemNumeroInternacional() {
        Filiado filiado = new Filiado(
                null,
                "Joao Nakamura",
                null,
                LocalDate.of(1990, 1, 10),
                null,
                null,
                "joao@email.com",
                null,
                null,
                null,
                null,
                null,
                null,
                UUID.randomUUID(),
                null,
                null
        );

        assertThat(filiado.getId()).isNotNull();
        assertThat(filiado.getStatus()).isEqualTo(StatusFiliado.ATIVO);
        assertThat(filiado.estaAtivo()).isTrue();
    }

    @Test
    void deveInativarFiliado() {
        Filiado filiado = new Filiado(null, "Joao Nakamura", null, LocalDate.of(1990, 1, 10), null, null, null, null, null, null, null, null, null, UUID.randomUUID(), null, null);

        filiado.inativar();

        assertThat(filiado.getStatus()).isEqualTo(StatusFiliado.INATIVO);
        assertThat(filiado.estaAtivo()).isFalse();
    }
}
