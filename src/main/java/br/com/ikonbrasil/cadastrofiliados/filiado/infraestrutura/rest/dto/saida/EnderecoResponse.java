package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.rest.dto.saida;

public record EnderecoResponse(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
}
