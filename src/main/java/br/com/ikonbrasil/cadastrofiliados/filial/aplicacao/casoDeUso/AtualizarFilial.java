package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RecursoNaoEncontradoException;
import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RegraDeNegocioException;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.AtualizarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.saida.RepositorioFilial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarFilial implements AtualizarFilialUseCase {

    private final RepositorioFilial repositorioFilial;

    public AtualizarFilial(RepositorioFilial repositorioFilial) {
        this.repositorioFilial = repositorioFilial;
    }

    @Override
    public Filial executar(UUID id, ComandoAtualizarFilial comando) {
        Filial filial = repositorioFilial.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Filial nao encontrada"));

        String codigoNormalizado = comando.codigo() == null ? null : comando.codigo().trim().toUpperCase();
        if (repositorioFilial.existePorCodigoEmOutraFilial(codigoNormalizado, id)) {
            throw new RegraDeNegocioException("Ja existe filial cadastrada com este codigo");
        }

        filial.alterarDados(
                comando.nome(),
                comando.codigo(),
                comando.responsavel(),
                comando.logoUrl(),
                comando.emailResponsavel(),
                comando.telefone(),
                comando.logradouro(),
                comando.numero(),
                comando.complemento(),
                comando.bairro(),
                comando.cidade(),
                comando.estado(),
                comando.cep()
        );

        return repositorioFilial.salvar(filial);
    }
}
