package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.saida.RepositorioFilial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.enumerador.StatusFilial;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AtivarInativarFilialTest {

    private final RepositorioFilial repositorioFilial = mock(RepositorioFilial.class);

    @Test
    void deveAtivarFilial() {
        UUID id = UUID.randomUUID();
        Filial filial = new Filial(id, "Dojo Centro", "CENTRO", null, "Curitiba", "PR", StatusFilial.INATIVA, null, null);
        when(repositorioFilial.buscarPorId(id)).thenReturn(Optional.of(filial));
        when(repositorioFilial.salvar(any(Filial.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Filial filialAtivada = new AtivarFilial(repositorioFilial).executar(id);

        assertThat(filialAtivada.getStatus()).isEqualTo(StatusFilial.ATIVA);
    }

    @Test
    void deveInativarFilial() {
        UUID id = UUID.randomUUID();
        Filial filial = new Filial(id, "Dojo Centro", "CENTRO", null, "Curitiba", "PR", StatusFilial.ATIVA, null, null);
        when(repositorioFilial.buscarPorId(id)).thenReturn(Optional.of(filial));
        when(repositorioFilial.salvar(any(Filial.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Filial filialInativada = new InativarFilial(repositorioFilial).executar(id);

        assertThat(filialInativada.getStatus()).isEqualTo(StatusFilial.INATIVA);
    }
}
