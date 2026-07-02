ALTER TABLE filiados
    ADD COLUMN altura_cm INTEGER,
    ADD COLUMN peso_kg NUMERIC(5,1);

ALTER TABLE filiados
    ADD CONSTRAINT ck_filiados_altura_cm
        CHECK (altura_cm IS NULL OR altura_cm BETWEEN 40 AND 250),
    ADD CONSTRAINT ck_filiados_peso_kg
        CHECK (peso_kg IS NULL OR peso_kg BETWEEN 1.0 AND 300.0);
