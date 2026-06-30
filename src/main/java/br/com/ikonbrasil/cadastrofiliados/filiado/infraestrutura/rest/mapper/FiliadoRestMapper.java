package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.mapper;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.objetoValor.Endereco;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.AtualizarFiliadoUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada.CriarFiliadoUseCase;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.entrada.AtualizarFiliadoRequest;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.entrada.CriarFiliadoRequest;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.entrada.EnderecoRequest;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.saida.EnderecoResponse;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.saida.FiliadoResponse;
import org.springframework.stereotype.Component;

@Component
public class FiliadoRestMapper {

    public CriarFiliadoUseCase.ComandoCriarFiliado paraComando(CriarFiliadoRequest request, String ipOrigem) {
        return new CriarFiliadoUseCase.ComandoCriarFiliado(
                request.nomeCompleto(),
                request.nomeSocial(),
                request.dataNascimento(),
                request.cpf(),
                request.rg(),
                request.email(),
                request.telefone(),
                request.sexo(),
                request.tipoSanguineo(),
                request.dataInicioTreinamento(),
                request.nacionalidade(),
                request.naturalidade(),
                request.profissao(),
                request.responsavelNome(),
                request.responsavelParentesco(),
                request.responsavelCpf(),
                request.responsavelTelefone(),
                request.responsavelEmail(),
                request.dadosMedicos(),
                request.parqPergunta1(),
                request.parqPergunta2(),
                request.parqPergunta3(),
                request.parqPergunta4(),
                request.parqPergunta5(),
                request.parqPergunta6(),
                request.parqPergunta7(),
                request.assinaturaNome(),
                request.declaracaoSaudeAceite(),
                ipOrigem,
                paraEndereco(request.endereco()),
                request.numeroInternacional(),
                request.fotoPerfilUrl(),
                request.filialId()
        );
    }

    public AtualizarFiliadoUseCase.ComandoAtualizarFiliado paraComando(AtualizarFiliadoRequest request, String ipOrigem) {
        return new AtualizarFiliadoUseCase.ComandoAtualizarFiliado(
                request.nomeCompleto(),
                request.nomeSocial(),
                request.dataNascimento(),
                request.cpf(),
                request.rg(),
                request.email(),
                request.telefone(),
                request.sexo(),
                request.tipoSanguineo(),
                request.dataInicioTreinamento(),
                request.nacionalidade(),
                request.naturalidade(),
                request.profissao(),
                request.responsavelNome(),
                request.responsavelParentesco(),
                request.responsavelCpf(),
                request.responsavelTelefone(),
                request.responsavelEmail(),
                request.dadosMedicos(),
                request.parqPergunta1(),
                request.parqPergunta2(),
                request.parqPergunta3(),
                request.parqPergunta4(),
                request.parqPergunta5(),
                request.parqPergunta6(),
                request.parqPergunta7(),
                request.assinaturaNome(),
                request.declaracaoSaudeAceite(),
                ipOrigem,
                paraEndereco(request.endereco()),
                request.numeroInternacional(),
                request.filialId()
        );
    }

    public FiliadoResponse paraResponse(Filiado filiado) {
        return new FiliadoResponse(
                filiado.getId(),
                filiado.getNomeCompleto(),
                filiado.getNomeSocial(),
                filiado.getDataNascimento(),
                filiado.getCpf(),
                filiado.getEmail(),
                filiado.getTelefone(),
                filiado.getSexo().name(),
                filiado.getTipoSanguineo(),
                filiado.getDataInicioTreinamento(),
                filiado.getNacionalidade(),
                filiado.getNaturalidade(),
                filiado.getProfissao(),
                filiado.getResponsavelNome(),
                filiado.getResponsavelParentesco(),
                filiado.getResponsavelCpf(),
                filiado.getResponsavelTelefone(),
                filiado.getResponsavelEmail(),
                filiado.getDadosMedicos(),
                filiado.getParqPergunta1(),
                filiado.getParqPergunta2(),
                filiado.getParqPergunta3(),
                filiado.getParqPergunta4(),
                filiado.getParqPergunta5(),
                filiado.getParqPergunta6(),
                filiado.getParqPergunta7(),
                filiado.getAssinaturaNome(),
                filiado.getDeclaracaoSaudeAceite(),
                filiado.getDeclaracaoSaudeAceiteEm(),
                filiado.getDeclaracaoSaudeAceiteIp(),
                filiado.getNumeroInternacional(),
                filiado.getStatus().name(),
                filiado.getFotoPerfilUrl(),
                paraEnderecoResponse(filiado.getEndereco()),
                filiado.getFilialId(),
                filiado.getDataCadastro(),
                filiado.getDataAtualizacao()
        );
    }

    private static EnderecoResponse paraEnderecoResponse(Endereco endereco) {
        if (endereco == null) {
            return null;
        }

        return new EnderecoResponse(
                endereco.logradouro(),
                endereco.numero(),
                endereco.complemento(),
                endereco.bairro(),
                endereco.cidade(),
                endereco.estado(),
                endereco.cep()
        );
    }

    private static Endereco paraEndereco(EnderecoRequest request) {
        if (request == null) {
            return null;
        }
        return new Endereco(
                request.logradouro(),
                request.numero(),
                request.complemento(),
                request.bairro(),
                request.cidade(),
                request.estado(),
                request.cep()
        );
    }
}
