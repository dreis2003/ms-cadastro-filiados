package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RegraDeNegocioException;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.CriarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.saida.RepositorioFilial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CriarFilialTest {

    private final RepositorioFilial repositorioFilial = mock(RepositorioFilial.class);
    private final CriarFilial criarFilial = new CriarFilial(repositorioFilial);

    @Test
    void deveCriarFilialQuandoCodigoNaoExiste() {
        CriarFilialUseCase.ComandoCriarFilial comando = new CriarFilialUseCase.ComandoCriarFilial(
                "Matriz Brasil",
                "matriz",
                "Responsavel",
                "Sao Paulo",
                "SP"
        );
        when(repositorioFilial.existePorCodigo("MATRIZ")).thenReturn(false);
        when(repositorioFilial.salvar(any(Filial.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Filial filial = criarFilial.executar(comando);

        assertThat(filial.getCodigo()).isEqualTo("MATRIZ");
        assertThat(filial.estaAtiva()).isTrue();
        verify(repositorioFilial).salvar(any(Filial.class));
    }

    @Test
    void naoDeveCriarFilialComCodigoDuplicado() {
        CriarFilialUseCase.ComandoCriarFilial comando = new CriarFilialUseCase.ComandoCriarFilial(
                "Matriz Brasil",
                "MATRIZ",
                null,
                "Sao Paulo",
                "SP"
        );
        when(repositorioFilial.existePorCodigo("MATRIZ")).thenReturn(true);

        assertThatThrownBy(() -> criarFilial.executar(comando))
                .isInstanceOf(RegraDeNegocioException.class)
                .hasMessage("Ja existe filial cadastrada com este codigo");
    }
}
