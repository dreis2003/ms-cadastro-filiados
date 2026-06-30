package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RecursoNaoEncontradoException;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.AtivarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.saida.RepositorioFilial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtivarFilial implements AtivarFilialUseCase {

    private final RepositorioFilial repositorioFilial;

    public AtivarFilial(RepositorioFilial repositorioFilial) {
        this.repositorioFilial = repositorioFilial;
    }

    @Override
    public Filial executar(UUID id) {
        Filial filial = repositorioFilial.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Filial nao encontrada"));
        filial.ativar();
        return repositorioFilial.salvar(filial);
    }
}
