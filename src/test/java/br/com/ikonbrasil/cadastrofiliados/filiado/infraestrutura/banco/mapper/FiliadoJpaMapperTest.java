package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.banco.mapper;

import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.enumerador.Sexo;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.banco.entidade.FiliadoJpaEntity;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.entidade.FilialJpaEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class FiliadoJpaMapperTest {

    private final FiliadoJpaMapper mapper = new FiliadoJpaMapper();

    @Test
    void deveMapearAlturaEPesoEntreDominioEJpa() {
        UUID filialId = UUID.randomUUID();
        FilialJpaEntity filialJpa = new FilialJpaEntity(
                filialId,
                "Dojo Centro",
                "CENTRO",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                "Curitiba",
                "PR",
                null,
                "ATIVA",
                LocalDateTime.now(),
                null
        );
        Filiado filiado = new Filiado(
                UUID.randomUUID(),
                "Joao Nakamura",
                null,
                LocalDate.of(1990, 1, 10),
                null,
                null,
                "joao@email.com",
                null,
                Sexo.MASCULINO,
                170,
                new BigDecimal("96.3"),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                "Joao Nakamura",
                true,
                null,
                null,
                null,
                null,
                null,
                null,
                filialId,
                null,
                null
        );

        FiliadoJpaEntity entidade = mapper.paraJpa(filiado, filialJpa);
        Filiado dominio = mapper.paraDominio(entidade);

        assertThat(entidade.getAlturaCm()).isEqualTo(170);
        assertThat(entidade.getPesoKg()).isEqualByComparingTo("96.3");
        assertThat(dominio.getAlturaCm()).isEqualTo(170);
        assertThat(dominio.getPesoKg()).isEqualByComparingTo("96.3");
    }
}
