CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    product_type VARCHAR(50),
    price DECIMAL(19, 2),
    stock INTEGER
);