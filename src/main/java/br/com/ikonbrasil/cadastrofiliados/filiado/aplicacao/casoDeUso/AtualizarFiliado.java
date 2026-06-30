package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RecursoNaoEncontradoException;
import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RegraDeNegocioException;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.AtualizarFiliadoUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.saida.RepositorioFiliado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.saida.RepositorioFilial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarFiliado implements AtualizarFiliadoUseCase {

    private final RepositorioFiliado repositorioFiliado;
    private final RepositorioFilial repositorioFilial;

    public AtualizarFiliado(RepositorioFiliado repositorioFiliado, RepositorioFilial repositorioFilial) {
        this.repositorioFiliado = repositorioFiliado;
        this.repositorioFilial = repositorioFilial;
    }

    @Override
    public Filiado executar(UUID id, ComandoAtualizarFiliado comando) {
        Filiado filiado = repositorioFiliado.buscarPorId(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Filiado nao encontrado"));
        Filial filial = repositorioFilial.buscarPorId(comando.filialId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Filial nao encontrada"));
        if (!comando.filialId().equals(filiado.getFilialId())) {
            filial.validarPodeReceberNovoFiliado();
        }

        filiado.alterarDados(
                comando.nomeCompleto(),
                comando.nomeSocial(),
                comando.dataNascimento(),
                comando.cpf(),
                comando.rg(),
                comando.email(),
                comando.telefone(),
                comando.sexo(),
                comando.tipoSanguineo(),
                comando.dataInicioTreinamento(),
                comando.nacionalidade(),
                comando.naturalidade(),
                comando.profissao(),
                comando.responsavelNome(),
                comando.responsavelParentesco(),
                comando.responsavelCpf(),
                comando.responsavelTelefone(),
                comando.responsavelEmail(),
                comando.dadosMedicos(),
                comando.parqPergunta1(),
                comando.parqPergunta2(),
                comando.parqPergunta3(),
                comando.parqPergunta4(),
                comando.parqPergunta5(),
                comando.parqPergunta6(),
                comando.parqPergunta7(),
                comando.assinaturaNome(),
                comando.declaracaoSaudeAceite(),
                comando.declaracaoSaudeAceiteIp(),
                comando.endereco(),
                comando.numeroInternacional(),
                comando.filialId()
        );

        if (filiado.getCpf() != null && repositorioFiliado.existePorCpfEmOutroFiliado(filiado.getCpf(), filiado.getId())) {
            throw new RegraDeNegocioException("Ja existe outro filiado cadastrado com este CPF");
        }
        if (filiado.getNumeroInternacional() != null
                && repositorioFiliado.existePorNumeroInternacionalEmOutroFiliado(filiado.getNumeroInternacional(), filiado.getId())) {
            throw new RegraDeNegocioException("Ja existe outro filiado cadastrado com este numero internacional");
        }

        return repositorioFiliado.salvar(filiado);
    }
}
