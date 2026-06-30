package br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade;

import br.com.ikonbrasil.cadastrofiliados.filial.dominio.enumerador.StatusFilial;
import br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.excecao.ExcecaoDeDominio;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FilialTest {

    @Test
    void deveCriarFilialAtivaPorPadrao() {
        Filial filial = new Filial(
                null,
                "Matriz Brasil",
                "MATRIZ",
                "Responsavel",
                "Sao Paulo",
                "SP",
                null,
                null,
                null
        );

        assertThat(filial.getId()).isNotNull();
        assertThat(filial.getStatus()).isEqualTo(StatusFilial.ATIVA);
        assertThat(filial.estaAtiva()).isTrue();
    }

    @Test
    void deveInativarFilial() {
        Filial filial = new Filial(null, "Matriz Brasil", "MATRIZ", null, "Sao Paulo", "SP", null, null, null);

        filial.inativar();

        assertThat(filial.getStatus()).isEqualTo(StatusFilial.INATIVA);
        assertThat(filial.estaAtiva()).isFalse();
    }

    @Test
    void deveNormalizarCodigoEEstado() {
        Filial filial = new Filial(null, "Matriz Brasil", "matriz", null, "Sao Paulo", "sp", null, null, null);

        assertThat(filial.getCodigo()).isEqualTo("MATRIZ");
        assertThat(filial.getEstado()).isEqualTo("SP");
    }

    @Test
    void naoDeveCriarFilialSemNome() {
        assertThatThrownBy(() -> new Filial(null, " ", "MATRIZ", null, "Sao Paulo", "SP", null, null, null))
                .isInstanceOf(ExcecaoDeDominio.class)
                .hasMessage("Nome da filial e obrigatorio");
    }

    @Test
    void naoDeveCriarFilialComEstadoInvalido() {
        assertThatThrownBy(() -> new Filial(null, "Matriz Brasil", "MATRIZ", null, "Sao Paulo", "SAO", null, null, null))
                .isInstanceOf(ExcecaoDeDominio.class)
                .hasMessage("Estado da filial deve possuir 2 caracteres");
    }

    @Test
    void filialInativaNaoPodeReceberNovoFiliado() {
        Filial filial = new Filial(null, "Dojo Centro", "CENTRO", null, "Curitiba", "PR", StatusFilial.INATIVA, null, null);

        assertThatThrownBy(filial::validarPodeReceberNovoFiliado)
                .isInstanceOf(ExcecaoDeDominio.class)
                .hasMessage("Filial inativa nao pode receber novos filiados");
    }
}
