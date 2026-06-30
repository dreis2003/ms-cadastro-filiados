package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RecursoNaoEncontradoException;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.BuscarFilialPorIdUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.saida.RepositorioFilial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarFilialPorId implements BuscarFilialPorIdUseCase {

    private final RepositorioFilial repositorioFilial;

    public BuscarFilialPorId(RepositorioFilial repositorioFilial) {
        this.repositorioFilial = repositorioFilial;
    }

    @Override
    public Filial executar(UUID id) {
        return repositorioFilial.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Filial nao encontrada"));
    }
}
