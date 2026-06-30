ALTER TABLE filiados DROP CONSTRAINT IF EXISTS ck_filiados_status;

ALTER TABLE filiados
    ADD CONSTRAINT ck_filiados_status
        CHECK (status IN ('ATIVO', 'INATIVO', 'PENDENTE_APROVACAO'));
