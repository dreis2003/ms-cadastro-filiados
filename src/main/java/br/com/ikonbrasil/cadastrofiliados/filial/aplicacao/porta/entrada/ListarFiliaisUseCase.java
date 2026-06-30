package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada;

import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;

import java.util.List;

public interface ListarFiliaisUseCase {

    List<Filial> executar();
}
