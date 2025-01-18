CREATE TABLE log (
    id BIGINT PRIMARY KEY,
    usuario_id INTEGER,
    resource VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP NOT NULL
);