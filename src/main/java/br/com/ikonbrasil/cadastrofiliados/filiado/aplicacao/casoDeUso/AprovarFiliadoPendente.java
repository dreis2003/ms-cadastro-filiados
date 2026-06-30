package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RecursoNaoEncontradoException;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.AprovarFiliadoPendenteUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.saida.RepositorioFiliado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AprovarFiliadoPendente implements AprovarFiliadoPendenteUseCase {

    private final RepositorioFiliado repositorioFiliado;

    public AprovarFiliadoPendente(RepositorioFiliado repositorioFiliado) {
        this.repositorioFiliado = repositorioFiliado;
    }

    @Override
    public Filiado executar(UUID id) {
        Filiado filiado = repositorioFiliado.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Filiado pendente nao encontrado"));
        filiado.aprovarCadastro();
        return repositorioFiliado.salvar(filiado);
    }
}
