package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.ListarFiliadosPendentesUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.saida.RepositorioFiliado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.enumerador.StatusFiliado;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarFiliadosPendentes implements ListarFiliadosPendentesUseCase {

    private final RepositorioFiliado repositorioFiliado;

    public ListarFiliadosPendentes(RepositorioFiliado repositorioFiliado) {
        this.repositorioFiliado = repositorioFiliado;
    }

    @Override
    public List<Filiado> executar(UUID filialId) {
        List<StatusFiliado> pendente = List.of(StatusFiliado.PENDENTE_APROVACAO);
        if (filialId != null) {
            return repositorioFiliado.listarPorFilialEStatus(filialId, pendente);
        }
        return repositorioFiliado.listarPorStatus(pendente);
    }
}
