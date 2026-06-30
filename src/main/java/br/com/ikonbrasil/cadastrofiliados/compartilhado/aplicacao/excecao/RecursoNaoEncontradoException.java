package br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
