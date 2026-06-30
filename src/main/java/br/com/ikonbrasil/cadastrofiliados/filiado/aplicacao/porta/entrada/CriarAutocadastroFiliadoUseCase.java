package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada;

import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;

public interface CriarAutocadastroFiliadoUseCase {

    Filiado executar(CriarFiliadoUseCase.ComandoCriarFiliado comando);
}
