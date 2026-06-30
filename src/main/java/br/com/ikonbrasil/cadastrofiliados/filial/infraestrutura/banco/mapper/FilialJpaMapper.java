package br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.mapper;

import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.enumerador.StatusFilial;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.entidade.FilialJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class FilialJpaMapper {

    public FilialJpaEntity paraJpa(Filial filial) {
        return new FilialJpaEntity(
                filial.getId(),
                filial.getNome(),
                filial.getCodigo(),
                filial.getResponsavel(),
                filial.getLogoUrl(),
                filial.getEmailResponsavel(),
                filial.getTelefone(),
                filial.getLogradouro(),
                filial.getNumero(),
                filial.getComplemento(),
                filial.getBairro(),
                filial.getCidade(),
                filial.getEstado(),
                filial.getCep(),
                filial.getStatus().name(),
                filial.getDataCadastro(),
                filial.getDataAtualizacao()
        );
    }

    public Filial paraDominio(FilialJpaEntity entidade) {
        return new Filial(
                entidade.getId(),
                entidade.getNome(),
                entidade.getCodigo(),
                entidade.getResponsavel(),
                entidade.getLogoUrl(),
                entidade.getEmailResponsavel(),
                entidade.getTelefone(),
                entidade.getLogradouro(),
                entidade.getNumero(),
                entidade.getComplemento(),
                entidade.getBairro(),
                entidade.getCidade(),
                entidade.getEstado(),
                entidade.getCep(),
                StatusFilial.valueOf(entidade.getStatus()),
                entidade.getDataCadastro(),
                entidade.getDataAtualizacao()
        );
    }
}
