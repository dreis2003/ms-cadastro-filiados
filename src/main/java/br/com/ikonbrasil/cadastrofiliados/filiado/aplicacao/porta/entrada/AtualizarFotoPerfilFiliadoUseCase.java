package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.entrada;

import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;

import java.util.UUID;

public interface AtualizarFotoPerfilFiliadoUseCase {

    Filiado executar(UUID id, String fotoPerfilUrl);
}
