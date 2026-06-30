package br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.mapper;

import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.AtualizarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada.CriarFilialUseCase;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.dto.entrada.AtualizarFilialRequest;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.dto.entrada.CriarFilialRequest;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.rest.dto.saida.FilialResponse;
import org.springframework.stereotype.Component;

@Component
public class FilialRestMapper {

    public CriarFilialUseCase.ComandoCriarFilial paraComando(CriarFilialRequest request) {
        return new CriarFilialUseCase.ComandoCriarFilial(
                request.nome(),
                request.codigo(),
                request.responsavel(),
                request.logoUrl(),
                request.emailResponsavel(),
                request.telefone(),
                request.logradouro(),
                request.numero(),
                request.complemento(),
                request.bairro(),
                request.cidade(),
                request.estado(),
                request.cep()
        );
    }

    public AtualizarFilialUseCase.ComandoAtualizarFilial paraComando(AtualizarFilialRequest request) {
        return new AtualizarFilialUseCase.ComandoAtualizarFilial(
                request.nome(),
                request.codigo(),
                request.responsavel(),
                request.logoUrl(),
                request.emailResponsavel(),
                request.telefone(),
                request.logradouro(),
                request.numero(),
                request.complemento(),
                request.bairro(),
                request.cidade(),
                request.estado(),
                request.cep()
        );
    }

    public FilialResponse paraResponse(Filial filial) {
        return new FilialResponse(
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
}
