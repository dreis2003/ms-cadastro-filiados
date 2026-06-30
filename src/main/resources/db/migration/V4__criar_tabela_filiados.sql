CREATE TABLE filiados (
    id UUID PRIMARY KEY,
    nome_completo VARCHAR(180) NOT NULL,
    nome_social VARCHAR(180),
    data_nascimento DATE NOT NULL,
    cpf VARCHAR(14),
    rg VARCHAR(30),
    email VARCHAR(150),
    telefone VARCHAR(30),
    sexo VARCHAR(30),
    logradouro VARCHAR(180),
    numero VARCHAR(30),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(2),
    cep VARCHAR(20),
    numero_internacional VARCHAR(80),
    status VARCHAR(20) NOT NULL,
    foto_perfil_url TEXT,
    filial_id UUID NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP,
    CONSTRAINT uk_filiados_cpf UNIQUE (cpf),
    CONSTRAINT uk_filiados_numero_internacional UNIQUE (numero_internacional),
    CONSTRAINT fk_filiados_filiais
        FOREIGN KEY (filial_id) REFERENCES filiais(id),
    CONSTRAINT ck_filiados_status CHECK (status IN ('ATIVO', 'INATIVO')),
    CONSTRAINT ck_filiados_sexo
        CHECK (sexo IS NULL OR sexo IN ('FEMININO', 'MASCULINO', 'NAO_INFORMADO'))
);

CREATE INDEX idx_filiados_filial_id ON filiados (filial_id);
CREATE INDEX idx_filiados_status ON filiados (status);
CREATE INDEX idx_filiados_nome_completo ON filiados (nome_completo);
