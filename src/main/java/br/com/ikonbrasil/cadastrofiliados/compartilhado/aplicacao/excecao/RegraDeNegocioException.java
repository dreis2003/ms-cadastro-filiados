package br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao;

public class RegraDeNegocioException extends RuntimeException {

    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }
}
