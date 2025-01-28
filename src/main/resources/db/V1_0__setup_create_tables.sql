CREATE DATABASE IF NOT EXISTS videodb;

USE videodb;

CREATE TABLE IF NOT EXISTS usuario (
    id int auto_increment  primary key,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    data_criacao datetime NOT NULL DEFAULT NOW(),
    uuid BINARY(16) NOT NULL default(UNHEX(REPLACE(UUID(), '-', '')))
);

CREATE UNIQUE INDEX usuario_email_uk
    ON usuario (email);

CREATE UNIQUE INDEX usuario_uuid_uk
    ON usuario (uuid);

CREATE TABLE IF NOT EXISTS video (
    id int auto_increment  primary key,
    usuario_id int NOT NULL,
    nome VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,
    uuid BINARY(16) NOT NULL default(UNHEX(REPLACE(UUID(), '-', ''))),
    data_criacao datetime NOT NULL DEFAULT NOW(),
    data_atualizacao datetime NULL

);

ALTER TABLE video ADD CONSTRAINT video_fk1
    FOREIGN KEY(usuario_id) REFERENCES usuario(id);