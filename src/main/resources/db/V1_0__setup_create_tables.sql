CREATE DATABASE IF NOT EXISTS videostudiodb;

USE videostudiodb;

CREATE TABLE IF NOT EXISTS usuario (
    id int auto_increment  primary key,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    uuid BINARY(16) NOT NULL default(UNHEX(REPLACE(UUID(), '-', '')))
);

CREATE UNIQUE INDEX usuario_username_uk
    ON usuario (username);

CREATE TABLE IF NOT EXISTS video (
    id int auto_increment  primary key,
    nome VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL,
    uuid BINARY(16) NOT NULL default(UNHEX(REPLACE(UUID(), '-', '')))
);
