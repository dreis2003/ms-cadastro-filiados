ALTER TABLE perfis_acesso
    DROP CONSTRAINT ck_perfis_acesso_nome;

ALTER TABLE perfis_acesso
    ADD CONSTRAINT ck_perfis_acesso_nome
        CHECK (nome IN ('MATRIZ_ADMIN', 'RESPONSAVEL_MATRIZ', 'RESPONSAVEL_FILIAL'));

INSERT INTO perfis_acesso (id, nome, descricao)
VALUES ('00000000-0000-0000-0000-000000000003', 'MATRIZ_ADMIN', 'Administrador da matriz com acesso completo ao cadastro de filiais')
ON CONFLICT (nome) DO NOTHING;
