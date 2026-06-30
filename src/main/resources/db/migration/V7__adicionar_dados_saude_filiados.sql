ALTER TABLE filiados
    ADD COLUMN tipo_sanguineo VARCHAR(3),
    ADD COLUMN data_inicio_treinamento DATE,
    ADD COLUMN nacionalidade VARCHAR(80),
    ADD COLUMN naturalidade VARCHAR(120),
    ADD COLUMN profissao VARCHAR(120),
    ADD COLUMN responsavel_nome VARCHAR(180),
    ADD COLUMN responsavel_parentesco VARCHAR(80),
    ADD COLUMN responsavel_cpf VARCHAR(14),
    ADD COLUMN responsavel_telefone VARCHAR(30),
    ADD COLUMN responsavel_email VARCHAR(150),
    ADD COLUMN dados_medicos TEXT,
    ADD COLUMN parq_pergunta_1 BOOLEAN,
    ADD COLUMN parq_pergunta_2 BOOLEAN,
    ADD COLUMN parq_pergunta_3 BOOLEAN,
    ADD COLUMN parq_pergunta_4 BOOLEAN,
    ADD COLUMN parq_pergunta_5 BOOLEAN,
    ADD COLUMN parq_pergunta_6 BOOLEAN,
    ADD COLUMN parq_pergunta_7 BOOLEAN,
    ADD COLUMN assinatura_nome VARCHAR(180),
    ADD COLUMN declaracao_saude_aceite BOOLEAN,
    ADD COLUMN declaracao_saude_aceite_em TIMESTAMP,
    ADD COLUMN declaracao_saude_aceite_ip VARCHAR(80);

ALTER TABLE filiados
    ADD CONSTRAINT ck_filiados_tipo_sanguineo
        CHECK (tipo_sanguineo IS NULL OR tipo_sanguineo IN ('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'));
