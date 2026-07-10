package br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.saida;

import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.enumerador.StatusFiliado;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositorioFiliado {

    Filiado salvar(Filiado filiado);

    Optional<Filiado> buscarPorId(UUID id);

    Optional<Filiado> buscarPorCpf(String cpf);

    List<Filiado> listar();

    List<Filiado> listarPorFilial(UUID filialId);

    List<Filiado> listarPorStatus(List<StatusFiliado> status);

    List<Filiado> listarPorFilialEStatus(UUID filialId, List<StatusFiliado> status);

    boolean existePorCpf(String cpf);

    boolean existePorNumeroInternacional(String numeroInternacional);

    boolean existePorCpfEmOutroFiliado(String cpf, UUID filiadoId);

    boolean existePorNumeroInternacionalEmOutroFiliado(String numeroInternacional, UUID filiadoId);
}
