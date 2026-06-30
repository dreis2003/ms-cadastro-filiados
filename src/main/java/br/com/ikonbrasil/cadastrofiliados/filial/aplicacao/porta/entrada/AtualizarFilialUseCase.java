package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada;

import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;

import java.util.UUID;

public interface AtualizarFilialUseCase {

    Filial executar(UUID id, ComandoAtualizarFilial comando);

    record ComandoAtualizarFilial(
            String nome,
            String codigo,
            String responsavel,
            String logoUrl,
            String emailResponsavel,
            String telefone,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String estado,
            String cep
    ) {
        public ComandoAtualizarFilial(String nome, String codigo, String responsavel, String cidade, String estado) {
            this(nome, codigo, responsavel, null, null, null, null, null, null, null, cidade, estado, null);
        }
    }
}
