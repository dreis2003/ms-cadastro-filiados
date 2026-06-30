package br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.banco.repositorio;

import br.com.ikonbrasil.cadastrofiliados.filiado.infraestrutura.banco.entidade.FiliadoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FiliadoJpaRepository extends JpaRepository<FiliadoJpaEntity, UUID> {

    boolean existsByCpf(String cpf);

    boolean existsByNumeroInternacional(String numeroInternacional);

    boolean existsByCpfAndIdNot(String cpf, UUID id);

    boolean existsByNumeroInternacionalAndIdNot(String numeroInternacional, UUID id);

    List<FiliadoJpaEntity> findByFilialId(UUID filialId);

    List<FiliadoJpaEntity> findByStatusIn(List<String> status);

    List<FiliadoJpaEntity> findByFilialIdAndStatusIn(UUID filialId, List<String> status);
}
