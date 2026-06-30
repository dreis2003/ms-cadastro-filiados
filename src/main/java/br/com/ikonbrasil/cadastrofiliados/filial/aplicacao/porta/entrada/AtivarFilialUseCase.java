package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada;

import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;

import java.util.UUID;

public interface AtivarFilialUseCase {

    Filial executar(UUID id);
}
