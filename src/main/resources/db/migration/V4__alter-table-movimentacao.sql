ALTER TABLE movimentacao 
ADD COLUMN nome_usuario VARCHAR(255) NOT NULL,
ADD COLUMN nome_produto VARCHAR(255) NOT NULL,
ADD COLUMN quantidade INTEGER NOT NULL,
ADD COLUMN valor_unitario NUMERIC(10,2) NOT NULL,
ADD COLUMN valor_total NUMERIC(10,2) NOT NULL,
ADD COLUMN estoque_atual INTEGER NOT NULL,
ADD COLUMN tipo_movimentacao VARCHAR(1) NOT NULL;
