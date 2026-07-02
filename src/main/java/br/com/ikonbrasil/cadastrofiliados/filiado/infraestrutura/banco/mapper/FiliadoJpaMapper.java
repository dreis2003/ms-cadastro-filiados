package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.banco.mapper;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.objetoValor.Endereco;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.enumerador.Sexo;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.enumerador.StatusFiliado;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.banco.entidade.FiliadoJpaEntity;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.entidade.FilialJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class FiliadoJpaMapper {

    public FiliadoJpaEntity paraJpa(Filiado filiado, FilialJpaEntity filialJpaEntity) {
        Endereco endereco = filiado.getEndereco();
        return new FiliadoJpaEntity(
                filiado.getId(),
                filiado.getNomeCompleto(),
                filiado.getNomeSocial(),
                filiado.getDataNascimento(),
                filiado.getCpf(),
                filiado.getRg(),
                filiado.getEmail(),
                filiado.getTelefone(),
                filiado.getSexo().name(),
                filiado.getAlturaCm(),
                filiado.getPesoKg(),
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
                endereco == null ? null : endereco.logradouro(),
                endereco == null ? null : endereco.numero(),
                endereco == null ? null : endereco.complemento(),
                endereco == null ? null : endereco.bairro(),
                endereco == null ? null : endereco.cidade(),
                endereco == null ? null : endereco.estado(),
                endereco == null ? null : endereco.cep(),
                filiado.getNumeroInternacional(),
                filiado.getStatus().name(),
                filiado.getFotoPerfilUrl(),
                filialJpaEntity,
                filiado.getDataCadastro(),
                filiado.getDataAtualizacao()
        );
    }

    public Filiado paraDominio(FiliadoJpaEntity entidade) {
        Endereco endereco = new Endereco(
                entidade.getLogradouro(),
                entidade.getNumero(),
                entidade.getComplemento(),
                entidade.getBairro(),
                entidade.getCidade(),
                entidade.getEstado(),
                entidade.getCep()
        );

        return new Filiado(
                entidade.getId(),
                entidade.getNomeCompleto(),
                entidade.getNomeSocial(),
                entidade.getDataNascimento(),
                entidade.getCpf(),
                entidade.getRg(),
                entidade.getEmail(),
                entidade.getTelefone(),
                entidade.getSexo() == null ? Sexo.NAO_INFORMADO : Sexo.valueOf(entidade.getSexo()),
                entidade.getAlturaCm(),
                entidade.getPesoKg(),
                entidade.getTipoSanguineo(),
                entidade.getDataInicioTreinamento(),
                entidade.getNacionalidade(),
                entidade.getNaturalidade(),
                entidade.getProfissao(),
                entidade.getResponsavelNome(),
                entidade.getResponsavelParentesco(),
                entidade.getResponsavelCpf(),
                entidade.getResponsavelTelefone(),
                entidade.getResponsavelEmail(),
                entidade.getDadosMedicos(),
                entidade.getParqPergunta1(),
                entidade.getParqPergunta2(),
                entidade.getParqPergunta3(),
                entidade.getParqPergunta4(),
                entidade.getParqPergunta5(),
                entidade.getParqPergunta6(),
                entidade.getParqPergunta7(),
                entidade.getAssinaturaNome(),
                entidade.getDeclaracaoSaudeAceite(),
                entidade.getDeclaracaoSaudeAceiteEm(),
                entidade.getDeclaracaoSaudeAceiteIp(),
                endereco,
                entidade.getNumeroInternacional(),
                StatusFiliado.valueOf(entidade.getStatus()),
                entidade.getFotoPerfilUrl(),
                entidade.getFilial().getId(),
                entidade.getDataCadastro(),
                entidade.getDataAtualizacao()
        );
    }
}
