package br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.entrada;

import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;

public interface CriarFilialUseCase {

    Filial executar(ComandoCriarFilial comando);

    record ComandoCriarFilial(
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
        public ComandoCriarFilial(String nome, String codigo, String responsavel, String cidade, String estado) {
            this(nome, codigo, responsavel, null, null, null, null, null, null, null, cidade, estado, null);
        }
    }
}
