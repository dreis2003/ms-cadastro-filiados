package br.com.ikonbrasil.cadastrofiliados.seguranca.infraestrutura.banco.repositorio;

import br.com.ikonbrasil.cadastrofiliados.seguranca.infraestrutura.banco.entidade.UsuarioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioJpaEntity, UUID> {
}
