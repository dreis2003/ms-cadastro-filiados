package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RegraDeNegocioException;
import br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.excecao.ExcecaoDeDominio;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.AtualizarFiliadoUseCase;
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

class AtualizarFiliadoTest {

    private final RepositorioFiliado repositorioFiliado = mock(RepositorioFiliado.class);
    private final RepositorioFilial repositorioFilial = mock(RepositorioFilial.class);
    private final AtualizarFiliado atualizarFiliado = new AtualizarFiliado(repositorioFiliado, repositorioFilial);

    @Test
    void deveAtualizarDadosDoFiliado() {
        UUID filialId = UUID.randomUUID();
        UUID filiadoId = UUID.randomUUID();
        when(repositorioFiliado.buscarPorId(filiadoId)).thenReturn(Optional.of(filiado(filiadoId, filialId)));
        when(repositorioFilial.buscarPorId(filialId)).thenReturn(Optional.of(filial(filialId, StatusFilial.ATIVA)));
        when(repositorioFiliado.salvar(any(Filiado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Filiado filiado = atualizarFiliado.executar(filiadoId, comando(filialId, "123.456.789-00", null));

        assertThat(filiado.getNomeCompleto()).isEqualTo("Joao Silva Nakamura");
        assertThat(filiado.getCpf()).isEqualTo("12345678900");
        assertThat(filiado.getFilialId()).isEqualTo(filialId);
    }

    @Test
    void naoDeveAtualizarComCpfDeOutroFiliado() {
        UUID filialId = UUID.randomUUID();
        UUID filiadoId = UUID.randomUUID();
        when(repositorioFiliado.buscarPorId(filiadoId)).thenReturn(Optional.of(filiado(filiadoId, filialId)));
        when(repositorioFilial.buscarPorId(filialId)).thenReturn(Optional.of(filial(filialId, StatusFilial.ATIVA)));
        when(repositorioFiliado.existePorCpfEmOutroFiliado("12345678900", filiadoId)).thenReturn(true);

        assertThatThrownBy(() -> atualizarFiliado.executar(filiadoId, comando(filialId, "123.456.789-00", null)))
                .isInstanceOf(RegraDeNegocioException.class)
                .hasMessage("Ja existe outro filiado cadastrado com este CPF");
    }

    @Test
    void naoDeveTransferirParaFilialInativa() {
        UUID filialAtualId = UUID.randomUUID();
        UUID novaFilialId = UUID.randomUUID();
        UUID filiadoId = UUID.randomUUID();
        when(repositorioFiliado.buscarPorId(filiadoId)).thenReturn(Optional.of(filiado(filiadoId, filialAtualId)));
        when(repositorioFilial.buscarPorId(novaFilialId)).thenReturn(Optional.of(filial(novaFilialId, StatusFilial.INATIVA)));

        assertThatThrownBy(() -> atualizarFiliado.executar(filiadoId, comando(novaFilialId, null, null)))
                .isInstanceOf(ExcecaoDeDominio.class)
                .hasMessage("Filial inativa nao pode receber novos filiados");
    }

    private static Filiado filiado(UUID filiadoId, UUID filialId) {
        return new Filiado(
                filiadoId,
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
                filialId,
                null,
                null
        );
    }

    private static Filial filial(UUID filialId, StatusFilial status) {
        return new Filial(filialId, "Dojo Centro", "CENTRO", null, "Curitiba", "PR", status, null, null);
    }

    private static AtualizarFiliadoUseCase.ComandoAtualizarFiliado comando(UUID filialId, String cpf, String numeroInternacional) {
        return new AtualizarFiliadoUseCase.ComandoAtualizarFiliado(
                "Joao Silva Nakamura",
                null,
                LocalDate.of(1990, 1, 10),
                cpf,
                null,
                "joao.silva@email.com",
                null,
                null,
                null,
                numeroInternacional,
                filialId
        );
    }
}
