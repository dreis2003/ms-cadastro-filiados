package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.ListarFiliaisUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.saida.RepositorioFilial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarFiliais implements ListarFiliaisUseCase {

    private final RepositorioFilial repositorioFilial;

    public ListarFiliais(RepositorioFilial repositorioFilial) {
        this.repositorioFilial = repositorioFilial;
    }

    @Override
    public List<Filial> executar() {
        return repositorioFilial.listar();
    }
}
