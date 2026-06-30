package br.com.ikonbrasil.cadastrofiliados.seguranca.infraestrutura.banco.entidade;

import br.com.ikonbrasil.cadastrofiliados.filial.infraestrutura.banco.entidade.FilialJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class UsuarioJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "perfil_acesso_id", nullable = false)
    private PerfilAcessoJpaEntity perfilAcesso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filial_id")
    private FilialJpaEntity filial;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    protected UsuarioJpaEntity() {
    }
}
