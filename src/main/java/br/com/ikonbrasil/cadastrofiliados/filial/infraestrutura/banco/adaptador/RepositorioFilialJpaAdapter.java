package br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.adaptador;

import br.com.ikonbrasil.cadastrofiliados.filial.aplicacao.porta.saida.RepositorioFilial;
import br.com.ikonbrasil.cadastrofiliados.filial.dominio.entidade.Filial;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.mapper.FilialJpaMapper;
import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.repositorio.FilialJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RepositorioFilialJpaAdapter implements RepositorioFilial {

    private final FilialJpaRepository filialJpaRepository;
    private final FilialJpaMapper filialJpaMapper;

    public RepositorioFilialJpaAdapter(FilialJpaRepository filialJpaRepository, FilialJpaMapper filialJpaMapper) {
        this.filialJpaRepository = filialJpaRepository;
        this.filialJpaMapper = filialJpaMapper;
    }

    @Override
    public Filial salvar(Filial filial) {
        return filialJpaMapper.paraDominio(filialJpaRepository.save(filialJpaMapper.paraJpa(filial)));
    }

    @Override
    public Optional<Filial> buscarPorId(UUID id) {
        return filialJpaRepository.findById(id).map(filialJpaMapper::paraDominio);
    }

    @Override
    public Optional<Filial> buscarPorCodigo(String codigo) {
        return filialJpaRepository.findByCodigo(codigo).map(filialJpaMapper::paraDominio);
    }

    @Override
    public List<Filial> listar() {
        return filialJpaRepository.findAll().stream()
                .map(filialJpaMapper::paraDominio)
                .toList();
    }

    @Override
    public boolean existePorCodigo(String codigo) {
        return filialJpaRepository.existsByCodigo(codigo);
    }

    @Override
    public boolean existePorCodigoEmOutraFilial(String codigo, UUID filialId) {
        return filialJpaRepository.existsByCodigoAndIdNot(codigo, filialId);
    }
}
