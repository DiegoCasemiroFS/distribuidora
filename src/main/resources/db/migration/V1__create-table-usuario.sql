CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    rua VARCHAR(255),
    numero INTEGER,
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    cep VARCHAR(20),
    tipo_usuario VARCHAR(50),
    admin BOOLEAN
);