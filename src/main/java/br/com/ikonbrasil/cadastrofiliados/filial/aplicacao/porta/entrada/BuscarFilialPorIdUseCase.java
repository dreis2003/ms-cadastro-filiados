package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada;

import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;

import java.util.UUID;

public interface BuscarFilialPorIdUseCase {

    Filial executar(UUID id);
}
