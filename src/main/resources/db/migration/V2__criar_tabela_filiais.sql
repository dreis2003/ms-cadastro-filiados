CREATE TABLE filiais (
    id UUID PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    codigo VARCHAR(50) NOT NULL,
    responsavel VARCHAR(150),
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL,
    data_atualizacao TIMESTAMP,
    CONSTRAINT uk_filiais_codigo UNIQUE (codigo),
    CONSTRAINT ck_filiais_status CHECK (status IN ('ATIVA', 'INATIVA'))
);

CREATE INDEX idx_filiais_status ON filiais (status);
CREATE INDEX idx_filiais_cidade_estado ON filiais (cidade, estado);
