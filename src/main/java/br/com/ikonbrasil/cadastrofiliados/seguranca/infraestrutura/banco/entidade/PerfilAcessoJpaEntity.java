package br.com.ikonbrasil.cadastrofiliados.seguranca.infraestrutura.banco.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "perfis_acesso")
public class PerfilAcessoJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true, length = 40)
    private String nome;

    @Column(nullable = false, length = 180)
    private String descricao;

    protected PerfilAcessoJpaEntity() {
    }
}
