package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.banco.adaptador;

import br.com.ikonbrasil.cadastrofiliados.filiado.aplicacao.porta.saida.RepositorioFiliado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.entidade.Filiado;
import br.com.ikonbrasil.cadastrofiliados.filiado.dominio.enumerador.StatusFiliado;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.banco.mapper.FiliadoJpaMapper;
import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.banco.repositorio.FiliadoJpaRepository;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.entidade.FilialJpaEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RepositorioFiliadoJpaAdapter implements RepositorioFiliado {

    private final FiliadoJpaRepository filiadoJpaRepository;
    private final FiliadoJpaMapper filiadoJpaMapper;
    private final EntityManager entityManager;

    public RepositorioFiliadoJpaAdapter(
            FiliadoJpaRepository filiadoJpaRepository,
            FiliadoJpaMapper filiadoJpaMapper,
            EntityManager entityManager
    ) {
        this.filiadoJpaRepository = filiadoJpaRepository;
        this.filiadoJpaMapper = filiadoJpaMapper;
        this.entityManager = entityManager;
    }

    @Override
    public Filiado salvar(Filiado filiado) {
        FilialJpaEntity filialJpaEntity = entityManager.getReference(FilialJpaEntity.class, filiado.getFilialId());
        return filiadoJpaMapper.paraDominio(filiadoJpaRepository.save(filiadoJpaMapper.paraJpa(filiado, filialJpaEntity)));
    }

    @Override
    public Optional<Filiado> buscarPorId(UUID id) {
        return filiadoJpaRepository.findById(id).map(filiadoJpaMapper::paraDominio);
    }

    @Override
    public Optional<Filiado> buscarPorCpf(String cpf) {
        return filiadoJpaRepository.findByCpf(normalizarCpf(cpf)).map(filiadoJpaMapper::paraDominio);
    }

    @Override
    public List<Filiado> listar() {
        return filiadoJpaRepository.findAll().stream().map(filiadoJpaMapper::paraDominio).toList();
    }

    @Override
    public List<Filiado> listarPorFilial(UUID filialId) {
        return filiadoJpaRepository.findByFilialId(filialId).stream().map(filiadoJpaMapper::paraDominio).toList();
    }

    @Override
    public List<Filiado> listarPorStatus(List<StatusFiliado> status) {
        return filiadoJpaRepository.findByStatusIn(status.stream().map(Enum::name).toList()).stream()
                .map(filiadoJpaMapper::paraDominio)
                .toList();
    }

    @Override
    public List<Filiado> listarPorFilialEStatus(UUID filialId, List<StatusFiliado> status) {
        return filiadoJpaRepository.findByFilialIdAndStatusIn(filialId, status.stream().map(Enum::name).toList()).stream()
                .map(filiadoJpaMapper::paraDominio)
                .toList();
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return filiadoJpaRepository.existsByCpf(normalizarCpf(cpf));
    }

    @Override
    public boolean existePorNumeroInternacional(String numeroInternacional) {
        return filiadoJpaRepository.existsByNumeroInternacional(numeroInternacional);
    }

    @Override
    public boolean existePorCpfEmOutroFiliado(String cpf, UUID filiadoId) {
        return filiadoJpaRepository.existsByCpfAndIdNot(normalizarCpf(cpf), filiadoId);
    }

    @Override
    public boolean existePorNumeroInternacionalEmOutroFiliado(String numeroInternacional, UUID filiadoId) {
        return filiadoJpaRepository.existsByNumeroInternacionalAndIdNot(numeroInternacional, filiadoId);
    }

    private static String normalizarCpf(String cpf) {
        return cpf == null ? null : cpf.replaceAll("\\D", "");
    }
}
