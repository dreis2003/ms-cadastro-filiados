package br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.repositorio;

import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.entidade.FilialJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FilialJpaRepository extends JpaRepository<FilialJpaEntity, UUID> {

    Optional<FilialJpaEntity> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);

    boolean existsByCodigoAndIdNot(String codigo, UUID id);
}
