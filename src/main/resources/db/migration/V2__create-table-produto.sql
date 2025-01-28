CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo_produto VARCHAR(50),
    preco DECIMAL(19, 2),
    estoque INTEGER
);