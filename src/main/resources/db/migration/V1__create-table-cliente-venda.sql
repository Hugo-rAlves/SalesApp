CREATE TABLE IF NOT EXISTS cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    localizacao VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS venda (
    id SERIAL PRIMARY KEY,
    cliente_cnpj VARCHAR(255) NOT NULL,
    data DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_cnpj) REFERENCES cliente(cnpj)
);

CREATE INDEX idx_venda_cliente_cnpj ON venda(cliente_cnpj);