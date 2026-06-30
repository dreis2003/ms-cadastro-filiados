package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada;

import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;

import java.util.List;
import java.util.UUID;

public interface ListarFiliadosPendentesUseCase {

    List<Filiado> executar(UUID filialId);
}
