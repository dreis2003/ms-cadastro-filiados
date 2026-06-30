package br.com.ikonbrasil.cadastrofiliados.compartilhado.infraestrutura.excecao;

import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.AcessoNegadoRegraNegocioException;
import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RecursoNaoEncontradoException;
import br.com.ikonbrasil.cadastrofiliados.compartilhado.aplicacao.excecao.RegraDeNegocioException;
import br.com.ikonbrasil.cadastrofiliados.compartilhado.dominio.excecao.ExcecaoDeDominio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorGlobalExcecoes {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    ResponseEntity<RespostaErro> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException excecao, HttpServletRequest request) {
        return criarResposta(HttpStatus.NOT_FOUND, excecao.getMessage(), request);
    }

    @ExceptionHandler({RegraDeNegocioException.class, ExcecaoDeDominio.class})
    ResponseEntity<RespostaErro> tratarRegraDeNegocio(RuntimeException excecao, HttpServletRequest request) {
        return criarResposta(HttpStatus.UNPROCESSABLE_ENTITY, excecao.getMessage(), request);
    }

    @ExceptionHandler(AcessoNegadoRegraNegocioException.class)
    ResponseEntity<RespostaErro> tratarAcessoNegado(AcessoNegadoRegraNegocioException excecao, HttpServletRequest request) {
        return criarResposta(HttpStatus.FORBIDDEN, excecao.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<RespostaErro> tratarValidacao(MethodArgumentNotValidException excecao, HttpServletRequest request) {
        String mensagem = excecao.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return criarResposta(HttpStatus.BAD_REQUEST, mensagem, request);
    }

    private static ResponseEntity<RespostaErro> criarResposta(HttpStatus status, String mensagem, HttpServletRequest request) {
        return ResponseEntity.status(status).body(new RespostaErro(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                mensagem,
                request.getRequestURI()
        ));
    }
}
