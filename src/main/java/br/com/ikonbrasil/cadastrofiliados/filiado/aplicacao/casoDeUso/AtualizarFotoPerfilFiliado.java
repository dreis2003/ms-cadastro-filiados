package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RecursoNaoEncontradoException;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.AtualizarFotoPerfilFiliadoUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.saida.RepositorioFiliado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarFotoPerfilFiliado implements AtualizarFotoPerfilFiliadoUseCase {

    private final RepositorioFiliado repositorioFiliado;

    public AtualizarFotoPerfilFiliado(RepositorioFiliado repositorioFiliado) {
        this.repositorioFiliado = repositorioFiliado;
    }

    @Override
    public Filiado executar(UUID id, String fotoPerfilUrl) {
        Filiado filiado = repositorioFiliado.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Filiado nao encontrado"));
        filiado.alterarFotoPerfil(fotoPerfilUrl);
        return repositorioFiliado.salvar(filiado);
    }
}
