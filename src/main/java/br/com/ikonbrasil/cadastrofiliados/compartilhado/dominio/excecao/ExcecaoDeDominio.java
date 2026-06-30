package br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.excecao;

public class ExcecaoDeDominio extends RuntimeException {

    public ExcecaoDeDominio(String mensagem) {
        super(mensagem);
    }
}
