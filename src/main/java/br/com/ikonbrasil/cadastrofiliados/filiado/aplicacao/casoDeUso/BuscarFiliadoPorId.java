package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RecursoNaoEncontradoException;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.BuscarFiliadoPorIdUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.saida.RepositorioFiliado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarFiliadoPorId implements BuscarFiliadoPorIdUseCase {

    private final RepositorioFiliado repositorioFiliado;

    public BuscarFiliadoPorId(RepositorioFiliado repositorioFiliado) {
        this.repositorioFiliado = repositorioFiliado;
    }

    @Override
    public Filiado executar(UUID id) {
        return repositorioFiliado.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Filiado nao encontrado"));
    }
}
