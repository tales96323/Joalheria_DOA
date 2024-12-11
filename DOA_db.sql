CREATE TABLE Funcionarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nif VARCHAR(20) UNIQUE NOT NULL,
    data_contratacao DATE NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    tipo VARCHAR(10) CHECK (tipo IN ('Vendedor', 'Gerente')) NOT NULL,
    metas_vendas DECIMAL(10, 2)
);

CREATE TABLE Clientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nif VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    total_gasto DECIMAL(10, 2) DEFAULT 0
);

CREATE TABLE Fornecedores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    numero_iva VARCHAR(20) UNIQUE NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    quantidade_vendida INT DEFAULT 0
);

CREATE TABLE Joias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(10) CHECK (tipo IN ('Colar', 'Brinco', 'Anel')) NOT NULL,
    material VARCHAR(50) NOT NULL,
    peso DECIMAL(10, 2) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    quantidade_em_estoque INT NOT NULL,
    comprimento DECIMAL(10, 2),
    tipo_fecho VARCHAR(50),
    tamanho INT,
    categoria VARCHAR(50) NOT NULL,
    fornecedor_id INT NOT NULL REFERENCES Fornecedores(id)
);

CREATE TABLE Pedidos (
    id SERIAL PRIMARY KEY,
    data_compra DATE NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    status VARCHAR(10) CHECK (status IN ('Entregue', 'Pago', 'Pendente', 'Cancelado')) NOT NULL,
    quantidade_pedidos INT NOT NULL,
    cliente_id INT NOT NULL REFERENCES Clientes(id),
    joias_ids INT[]
);

CREATE TABLE Pagamentos (
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    metodo_pagamento VARCHAR(30) CHECK (metodo_pagamento IN ('Cartão de Crédito', 'Transferência Bancária', 'Dinheiro')) NOT NULL,
    pedido_id INT NOT NULL REFERENCES Pedidos(id)
);


INSERT INTO Fornecedores (nome, numero_iva, telefone, endereco, quantidade_vendida)
VALUES 
    ('Fornecedor A', '1234567890', '217654321', 'Rua Comercial, 123', 50),
    ('Fornecedor B', '9876543210', '219876543', 'Rua do Comércio, 456', 30);

INSERT INTO Joias (nome, tipo, material, peso, preco, quantidade_em_estoque, comprimento, tipo_fecho, tamanho, categoria, fornecedor_id)
VALUES 
    ('Colar de Pérolas', 'Colar', 'Pérola', 50.0, 1200.00, 15, 45.0, NULL, NULL, 'Luxo', 
     (SELECT id FROM Fornecedores WHERE nome = 'Fornecedor A')),
    ('Brinco de Ouro', 'Brinco', 'Ouro', 10.0, 800.00, 20, NULL, 'Pressão', NULL, 'Casual', 
     (SELECT id FROM Fornecedores WHERE nome = 'Fornecedor B')),
    ('Anel de Diamante', 'Anel', 'Diamante', 5.0, 5000.00, 5, NULL, NULL, 18, 'Noivado', 
     (SELECT id FROM Fornecedores WHERE nome = 'Fornecedor A'));

INSERT INTO Clientes (nome, nif, email, telefone, endereco, total_gasto)
VALUES 
    ('João Almeida', '321654987', 'joao.almeida@example.com', '912345678', 'Rua das Flores, 123', 800.00),
    ('Clara Sousa', '852963741', 'clara.sousa@example.com', '932147856', 'Avenida Central, 456', 5000.00);

INSERT INTO Pedidos (data_compra, valor_total, status, quantidade_pedidos, cliente_id, joias_ids)
VALUES 
    ('2023-11-01', 2400.00, 'Pago', 2, 
     (SELECT id FROM Clientes WHERE nome = 'João Almeida'), 
     ARRAY[(SELECT id FROM Joias WHERE nome = 'Colar de Pérolas'), (SELECT id FROM Joias WHERE nome = 'Brinco de Ouro')]),
    ('2023-11-10', 5000.00, 'Entregue', 1, 
     (SELECT id FROM Clientes WHERE nome = 'Clara Sousa'), 
     ARRAY[(SELECT id FROM Joias WHERE nome = 'Anel de Diamante')]);

INSERT INTO Pagamentos (data, valor, metodo_pagamento, pedido_id)
VALUES 
    ('2023-11-01', 2400.00, 'Cartão de Crédito', 
     (SELECT id FROM Pedidos WHERE valor_total = 2400.00 AND cliente_id = (SELECT id FROM Clientes WHERE nome = 'João Almeida'))),
    ('2023-11-10', 5000.00, 'Transferência Bancária', 
     (SELECT id FROM Pedidos WHERE valor_total = 5000.00 AND cliente_id = (SELECT id FROM Clientes WHERE nome = 'Clara Sousa')));
