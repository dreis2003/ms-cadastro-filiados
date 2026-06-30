CREATE TABLE perfis_acesso (
    id UUID PRIMARY KEY,
    nome VARCHAR(40) NOT NULL UNIQUE,
    descricao VARCHAR(180) NOT NULL,
    CONSTRAINT ck_perfis_acesso_nome
        CHECK (nome IN ('RESPONSAVEL_MATRIZ', 'RESPONSAVEL_FILIAL'))
);

INSERT INTO perfis_acesso (id, nome, descricao)
VALUES
    ('00000000-0000-0000-0000-000000000001', 'RESPONSAVEL_MATRIZ', 'Responsavel pela matriz com acesso a todas as filiais'),
    ('00000000-0000-0000-0000-000000000002', 'RESPONSAVEL_FILIAL', 'Responsavel de filial com acesso restrito a propria filial');
