package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.saida;

import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositorioFilial {

    Filial salvar(Filial filial);

    Optional<Filial> buscarPorId(UUID id);

    Optional<Filial> buscarPorCodigo(String codigo);

    List<Filial> listar();

    boolean existePorCodigo(String codigo);

    boolean existePorCodigoEmOutraFilial(String codigo, UUID filialId);
}
