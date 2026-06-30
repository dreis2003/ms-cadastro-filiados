package br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.objetoValor;

public record Endereco(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
}
