CREATE TABLE usuarios (
    id UUID PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    perfil_acesso_id UUID NOT NULL,
    filial_id UUID,
    status VARCHAR(20) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP,
    CONSTRAINT uk_usuarios_email UNIQUE (email),
    CONSTRAINT fk_usuarios_perfis_acesso
        FOREIGN KEY (perfil_acesso_id) REFERENCES perfis_acesso(id),
    CONSTRAINT fk_usuarios_filiais
        FOREIGN KEY (filial_id) REFERENCES filiais(id),
    CONSTRAINT ck_usuarios_status CHECK (status IN ('ATIVO', 'INATIVO')),
    CONSTRAINT ck_usuarios_responsavel_filial_tem_filial
        CHECK (
            perfil_acesso_id <> '00000000-0000-0000-0000-000000000002'
            OR filial_id IS NOT NULL
        )
);

CREATE INDEX idx_usuarios_perfil_acesso_id ON usuarios (perfil_acesso_id);
CREATE INDEX idx_usuarios_filial_id ON usuarios (filial_id);
CREATE INDEX idx_usuarios_status ON usuarios (status);
