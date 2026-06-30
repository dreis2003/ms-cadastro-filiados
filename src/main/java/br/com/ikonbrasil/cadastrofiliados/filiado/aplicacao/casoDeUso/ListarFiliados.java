package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.ListarFiliadosUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.saida.RepositorioFiliado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.enumerador.StatusFiliado;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListarFiliados implements ListarFiliadosUseCase {

    private final RepositorioFiliado repositorioFiliado;

    public ListarFiliados(RepositorioFiliado repositorioFiliado) {
        this.repositorioFiliado = repositorioFiliado;
    }

    @Override
    public List<Filiado> executar(UUID filialId) {
        List<StatusFiliado> statusOficiais = List.of(StatusFiliado.ATIVO, StatusFiliado.INATIVO);
        if (filialId != null) {
            return repositorioFiliado.listarPorFilialEStatus(filialId, statusOficiais);
        }
        return repositorioFiliado.listarPorStatus(statusOficiais);
    }
}
