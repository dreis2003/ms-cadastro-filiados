package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada;

import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;

import java.util.UUID;

public interface BuscarFiliadoPorIdUseCase {

    Filiado executar(UUID id);
}
