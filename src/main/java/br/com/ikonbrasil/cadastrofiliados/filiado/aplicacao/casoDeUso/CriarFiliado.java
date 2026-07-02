package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.casoDeUso;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RecursoNaoEncontradoException;
import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RegraDeNegocioException;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.CriarFiliadoUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.saida.RepositorioFiliado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.saida.RepositorioFilial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import org.springframework.stereotype.Service;

@Service
public class CriarFiliado implements CriarFiliadoUseCase {

    private final RepositorioFiliado repositorioFiliado;
    private final RepositorioFilial repositorioFilial;

    public CriarFiliado(RepositorioFiliado repositorioFiliado, RepositorioFilial repositorioFilial) {
        this.repositorioFiliado = repositorioFiliado;
        this.repositorioFilial = repositorioFilial;
    }

    @Override
    public Filiado executar(ComandoCriarFiliado comando) {
        Filial filial = repositorioFilial.buscarPorId(comando.filialId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Filial nao encontrada"));
        filial.validarPodeReceberNovoFiliado();

        Filiado filiado = new Filiado(
                null,
                comando.nomeCompleto(),
                comando.nomeSocial(),
                comando.dataNascimento(),
                comando.cpf(),
                comando.rg(),
                comando.email(),
                comando.telefone(),
                comando.sexo(),
                comando.alturaCm(),
                comando.pesoKg(),
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
                null,
                comando.declaracaoSaudeAceiteIp(),
                comando.endereco(),
                comando.numeroInternacional(),
                null,
                comando.fotoPerfilUrl(),
                comando.filialId(),
                null,
                null
        );

        if (filiado.getCpf() != null && repositorioFiliado.existePorCpf(filiado.getCpf())) {
            throw new RegraDeNegocioException("Ja existe filiado cadastrado com este CPF");
        }
        if (filiado.getNumeroInternacional() != null
                && repositorioFiliado.existePorNumeroInternacional(filiado.getNumeroInternacional())) {
            throw new RegraDeNegocioException("Ja existe filiado cadastrado com este numero internacional");
        }

        return repositorioFiliado.salvar(filiado);
    }
}
