package br.com.ikonbrasil.cadastrofiliados.compartilhado.infraestrutura.excecao;

import java.time.LocalDateTime;

public record RespostaErro(
        LocalDateTime dataHora,
        int status,
        String erro,
        String mensagem,
        String caminho
) {
}
