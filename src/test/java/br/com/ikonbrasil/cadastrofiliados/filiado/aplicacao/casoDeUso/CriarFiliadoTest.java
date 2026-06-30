package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.excecao.ExcecaoDeDominio;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.CriarFiliadoUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.saida.RepositorioFiliado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.saida.RepositorioFilial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.enumerador.StatusFilial;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CriarFiliadoTest {

    private final RepositorioFiliado repositorioFiliado = mock(RepositorioFiliado.class);
    private final RepositorioFilial repositorioFilial = mock(RepositorioFilial.class);
    private final CriarFiliado criarFiliado = new CriarFiliado(repositorioFiliado, repositorioFilial);

    @Test
    void deveCriarFiliadoEmFilialAtiva() {
        UUID filialId = UUID.randomUUID();
        Filial filial = new Filial(filialId, "Dojo Centro", "CENTRO", null, "Curitiba", "PR", StatusFilial.ATIVA, null, null);
        when(repositorioFilial.buscarPorId(filialId)).thenReturn(Optional.of(filial));
        when(repositorioFiliado.salvar(any(Filiado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Filiado filiado = criarFiliado.executar(comando(filialId));

        assertThat(filiado.getId()).isNotNull();
        assertThat(filiado.getCpf()).isEqualTo("12345678900");
        assertThat(filiado.getFilialId()).isEqualTo(filialId);
        assertThat(filiado.estaAtivo()).isTrue();
    }

    @Test
    void naoDeveCriarFiliadoEmFilialInativa() {
        UUID filialId = UUID.randomUUID();
        Filial filial = new Filial(filialId, "Dojo Centro", "CENTRO", null, "Curitiba", "PR", StatusFilial.INATIVA, null, null);
        when(repositorioFilial.buscarPorId(filialId)).thenReturn(Optional.of(filial));

        assertThatThrownBy(() -> criarFiliado.executar(comando(filialId)))
                .isInstanceOf(ExcecaoDeDominio.class)
                .hasMessage("Filial inativa nao pode receber novos filiados");
    }

    private static CriarFiliadoUseCase.ComandoCriarFiliado comando(UUID filialId) {
        return new CriarFiliadoUseCase.ComandoCriarFiliado(
                "Joao Nakamura",
                null,
                LocalDate.of(1990, 1, 10),
                "123.456.789-00",
                null,
                "joao@email.com",
                null,
                null,
                null,
                null,
                null,
                filialId
        );
    }
}
