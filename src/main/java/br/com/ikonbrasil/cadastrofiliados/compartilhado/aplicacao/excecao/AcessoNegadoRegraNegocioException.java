package br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao;

public class AcessoNegadoRegraNegocioException extends RuntimeException {

    public AcessoNegadoRegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
