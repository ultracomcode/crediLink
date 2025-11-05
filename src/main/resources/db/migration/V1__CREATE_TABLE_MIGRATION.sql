CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    identifier UUID NOT NULL,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    cpf_cnpj VARCHAR(20) UNIQUE,
    phone VARCHAR(20),
    id_crm VARCHAR(100) UNIQUE,
    id_contrato VARCHAR(100) UNIQUE,
    status VARCHAR(20),
    role VARCHAR(20) NOT NULL
    );
CREATE INDEX idx_usuarios_email ON usuarios(email);
CREATE INDEX idx_usuarios_username ON usuarios(username);
CREATE INDEX idx_usuarios_identifier ON usuarios(identifier);

CREATE TABLE IF NOT EXISTS integration (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    token_api VARCHAR(255),
    identifier UUID NOT NULL,
    url_api VARCHAR(512),
    username VARCHAR(255),
    password VARCHAR(255),
    id_conta_banco VARCHAR(100),
    id_conta_contabil VARCHAR(100),
    id_produto VARCHAR(100),
    tipo VARCHAR(50),
    status VARCHAR(50)
);
CREATE INDEX idx_integration_identifier ON integration(identifier);
CREATE INDEX idx_integration_tipo ON integration(tipo);
CREATE INDEX idx_integration_status ON integration(status);


CREATE TABLE IF NOT EXISTS pix_transaction (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP,
    payment_at TIMESTAMP,
    expiration_seconds VARCHAR(50),
    expiration_date TIMESTAMP,
    status VARCHAR(50),
    txid VARCHAR(255),
    location VARCHAR(255),
    amount VARCHAR(50),
    key_pix VARCHAR(255),
    pix_copy_paste VARCHAR(255),
    username VARCHAR(255),
    domain VARCHAR(255),
    observation VARCHAR(255),
    user_id BIGINT,
    account_code VARCHAR(255),
    CONSTRAINT fk_pix_user FOREIGN KEY(user_id) REFERENCES usuarios(id)
);
CREATE INDEX idx_pix_status ON pix_transaction(status);
CREATE INDEX idx_pix_txid ON pix_transaction(txid);
CREATE INDEX idx_pix_user_id ON pix_transaction(user_id);


CREATE TABLE token (
    id BIGINT PRIMARY KEY,
    access_token VARCHAR(5048) NOT NULL,
    token_type VARCHAR(255),
    expires_in BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    banco_type VARCHAR(50)
);
CREATE INDEX idx_token_bank_type ON token(banco_type);
CREATE INDEX idx_token_expires_in ON token(expires_in);


