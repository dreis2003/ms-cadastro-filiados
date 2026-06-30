package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RegraDeNegocioException;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.CriarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.saida.RepositorioFilial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import org.springframework.stereotype.Service;

@Service
public class CriarFilial implements CriarFilialUseCase {

    private final RepositorioFilial repositorioFilial;

    public CriarFilial(RepositorioFilial repositorioFilial) {
        this.repositorioFilial = repositorioFilial;
    }

    @Override
    public Filial executar(ComandoCriarFilial comando) {
        String codigoNormalizado = comando.codigo() == null ? null : comando.codigo().trim().toUpperCase();
        if (repositorioFilial.existePorCodigo(codigoNormalizado)) {
            throw new RegraDeNegocioException("Ja existe filial cadastrada com este codigo");
        }

        Filial filial = new Filial(
                null,
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
                comando.cep(),
                null,
                null,
                null
        );

        return repositorioFilial.salvar(filial);
    }
}
