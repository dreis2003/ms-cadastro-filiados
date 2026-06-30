package br.com.ikonbrasil.cadastrofiliados.seguranca.infraestrutura.banco.repositorio;

import br.com.ikonbrasil.cadastrofiliados.seguranca.infraestrutura.banco.entidade.PerfilAcessoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PerfilAcessoJpaRepository extends JpaRepository<PerfilAcessoJpaEntity, UUID> {
}
