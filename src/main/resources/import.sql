-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;


INSERT INTO endereco(numero, cep, cidade, estado, logradouro, nome, bairro, complemento)
VALUES 
    (123, '77000-000', 'Palmas', 'TO', 'Rua das Flores', 'Casa Azul', 'Centro', 'kitnet 4'),
    (456, '77001-001', 'Gurupi', 'TO', 'Avenida dos Bandeirantes', 'Casa Verde', 'Jardim Bela Vista', 'Esquina com a Rua Principal'),
    (789, '77002-002', 'Araguaína', 'TO', 'Travessa das Oliveiras', 'Casa Amarela', 'Setor Norte', 'Ao lado da praça'),
    (101, '77003-003', 'Porto Nacional', 'TO', 'Rua das Águias', 'Casa Vermelha', 'Centro', 'Próximo à Escola Municipal'),
    (202, '77004-004', 'Paraíso do Tocantins', 'TO', 'Avenida das Rosas', 'Casa Rosa', 'Jardim Primavera', 'Próximo ao Supermercado'),
    (303, '77005-005', 'Colinas do Tocantins', 'TO', 'Alameda dos Ipês', 'Casa Lilás', 'Centro', 'Ao lado do Banco do Brasil'),
    (124, '77006-006', 'Palmas', 'TO', 'Rua das Flores', 'Casa Azul', 'Centro', 'kitnet 4'),
    (457, '77007-007', 'Gurupi', 'TO', 'Avenida dos Bandeirantes', 'Casa Verde', 'Jardim Bela Vista', 'Esquina com a Rua Principal'),
    (790, '77008-008', 'Araguaína', 'TO', 'Travessa das Oliveiras', 'Casa Amarela', 'Setor Norte', 'Ao lado da praça'),
    (102, '77009-009', 'Porto Nacional', 'TO', 'Rua das Águias', 'Casa Vermelha', 'Centro', 'Próximo à Escola Municipal'),
    (203, '770100', 'Paraíso do Tocantins', 'TO', 'Avenida das Rosas', 'Casa Rosa', 'Jardim Primavera', 'Próximo ao Supermercado'),
    (304, '770111', 'Colinas do Tocantins', 'TO', 'Alameda dos Ipês', 'Casa Lilás', 'Centro', 'Ao lado do Banco do Brasil');


INSERT INTO cliente (
    numeroregistro_posse_porte, cpf, nome, email, listatelefones
)
VALUES
    ('1234567890', '111.111.111-11', 'André', 'andre@example.com', '{"1234567890", "9876543210"}'),
    ('2345678901', '222.222.222-22', 'Bene', 'bene@example.com', '{"1234567891", "9876543211"}'),
    ('3456789012', '333.333.333-33', 'Hirosh', 'hirosh@example.com', '{"1234567892", "9876543212"}'),
    ('4567890123', '444.444.444-44', 'Heitor', 'heitor@example.com', '{"1234567893", "9876543213"}'),
    ('5678901234', '555.555.555-55', 'Henrique', 'henrique@example.com', '{"1234567894", "9876543214"}'),
    ('6789012345', '666.666.666-66', 'Junin', 'junin@example.com', '{"1234567895", "9876543215"}'),
    ('7890123456', '777.777.777-77', 'Maria do Socorro', 'maria@example.com', '{"1234567896", "9876543216"}'),
    ('8901234567', '888.888.888-88', 'Aline', 'aline@example.com', '{"1234567897", "9876543217"}'),
    ('9012345678', '999.999.999-99', 'Fernanda', 'fernanda@example.com', '{"1234567898", "9876543218"}');


INSERT INTO arma (
    capacidadedetiro, preco, qtdnoestoque, tipo_arma, comprimentodocano,
    modelo, numerosigma, acabamento, marca, nome, descricao, calibre,
    numerodaarma, registro_nacional_armas
)
VALUES
    (10, 999.99, 50, 1, 4.5, 'Modelo A', 'ABC123', 'Polido', 'Marca X', 'Pistola Modelo A', 'Uma pistola de qualidade com alto desempenho.', '9mm', '001', '012345'),
    (8, 1499.99, 30, 3, 22, 'Modelo B', 'DEF456', 'Fosco', 'Marca Y', 'Rifle Modelo B', 'Um rifle versátil para uso recreativo.', '5.56mm', '002', '234567'),
    (6, 599.99, 100, 0, 2.5, 'Modelo C', 'GHI789', 'Prateado', 'Marca Z', 'Revólver Modelo C', 'Um revólver clássico para defesa pessoal.', '.357 Magnum', '003', '345678'),
    (12, 1299.99, 25, 2, 18, 'Modelo D', 'JKL012', 'Fosco', 'Marca W', 'Escopeta Modelo D', 'Uma escopeta robusta para caça e tiro esportivo.', '12 gauge', '004', '456789'),
    (15, 1799.99, 20, 4, 16, 'Modelo E', 'MNO345', 'Polido', 'Marca V', 'Carabina Modelo E', 'Uma carabina leve e precisa para uso tático.', '7.62mm', '005', '567890'),
    (10, 899.99, 40, 5, 10, 'Modelo F', 'PQR678', 'Fosco', 'Marca U', 'Submetralhadora Modelo F', 'Uma submetralhadora compacta para operações especiais.', '9mm', '006', '678901'),
    (20, 1999.99, 15, 3, 20, 'Modelo G', 'STU901', 'Prateado', 'Marca T', 'Fuzil Modelo G', 'Um fuzil de assalto confiável e poderoso.', '5.56mm', '007', '789012');
  
INSERT INTO funcionario (matricula, cpf, telefone, nome, email)
VALUES
    ('123456', '111.111.111-11', '123456789', 'Daniel', 'daniel@example.com'),
    ('789012', '222.222.222-22', '987654321', 'Jacare', 'jacare@example.com');

INSERT INTO usuario (perfil, login, senha)
VALUES 
    (1, 'cliente_andre', 'senha_cliente_andre'),
    (1, 'cliente_bene', 'senha_cliente_bene'),
    (1, 'cliente_hirosh', 'senha_cliente_hirosh'),
    (1, 'cliente_heitor', 'senha_cliente_heitor'),
    (1, 'cliente_henrique', 'senha_cliente_henrique'),
    (1, 'cliente_junin', 'senha_cliente_junin'),
    (1, 'cliente_maria', 'senha_cliente_maria'),
    (1, 'cliente_aline', 'senha_cliente_aline'),
    (1, 'cliente_fernanda', 'senha_cliente_fernanda'),
    (2, 'funcionario_daniel', 'senha_funcionario_daniel'),
    (2, 'funcionario_jacare', 'senha_funcionario_jacare');

INSERT INTO cliente_usuario (id_cliente, id_usuario)
VALUES 
    -- Cliente André
    (1, 1),
    -- Cliente Bene
    (2, 2),
    -- Cliente Hirosh
    (3, 3),
    -- Cliente Heitor
    (4, 4),
    -- Cliente Henrique
    (5, 5),
    -- Cliente Junin
    (6, 6),
    -- Cliente Maria do Socorro
    (7, 7),
    -- Cliente Aline
    (8, 8),
    -- Cliente Fernanda
    (9, 9);

INSERT INTO funcionario_usuario (id_funcionario, id_usuario)
VALUES 
    -- Funcionário Daniel
    (1, 10),
    -- Funcionário Jacare
    (2, 11);

INSERT INTO cliente_endereco (id_cliente, id_endereco)
VALUES 
    -- Cliente André
    (1, 1),
    -- Cliente Bene
    (2, 2),
    -- Cliente Hirosh
    (3, 3),
    -- Cliente Heitor
    (4, 4),
    -- Cliente Henrique
    (5, 5),
    -- Cliente Junin
    (6, 6),
    -- Cliente Maria do Socorro
    (7, 7),
    -- Cliente Aline
    (8, 8),
    -- Cliente Fernanda
    (9, 9);

INSERT INTO funcionario_endereco (id_endereco, id_funcionario)
VALUES 
    -- Funcionário Daniel
    (10, 1),
    -- Funcionário Jacare
    (11, 2);

INSERT INTO cartao(
    bandeira_cartao, data_vencimento, tipo_cartao, banco, cod_verificacao, nome_titular, numero)
VALUES 
    (1, '2028-04-01', 1, 'Banco do Brasil', '123', 'João Silva', '1234567890123456'),
    (2, '2028-05-01', 1, 'Bradesco', '456', 'Maria Souza', '2345678901234567'),
    (3, '2029-06-01', 1, 'Caixa Econômica Federal', '789', 'José Santos', '3456789012345678'),
    (1, '2030-07-01', 1, 'Itaú', '012', 'Ana Oliveira', '4567890123456789'),
    (2, '2031-08-01', 1, 'Santander', '345', 'Carlos Lima', '5678901234567890'),
    (3, '2032-09-01', 1, 'Nubank', '678', 'Amanda Costa', '6789012345678901'),
    (1, '2033-10-01', 1, 'Inter', '901', 'Marcos Pereira', '7890123456789012'),
    (2, '2034-11-01', 1, 'PicPay', '234', 'Laura Fernandes', '8901234567890123'),
    (3, '2035-12-01', 1, 'Banco do Nordeste', '567', 'Fernando Oliveira', '9012345678901234'),
    (1, '2036-01-01', 1, 'Banrisul', '890', 'Carla Rodrigues', '0123456789012345'),
    (2, '2037-02-01', 1, 'HSBC', '123', 'Mariana Silva', '1234567890123456'),
    (3, '2038-03-01', 1, 'BNDES', '456', 'Rafael Souza', '2345678901234567'),
    (1, '2039-04-01', 1, 'Banestes', '789', 'Camila Santos', '3456789012345678'),
    (2, '2040-05-01', 1, 'Banpará', '012', 'Guilherme Oliveira', '4567890123456789'),
    (3, '2041-06-01', 1, 'Banco Central do Brasil', '345', 'Ana Paula Lima', '5678901234567890');

INSERT INTO cliente_cartao (id_cartao, id_cliente)
VALUES 
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 1),
    (11, 2),
    (12, 3),
    (13, 4),
    (14, 5),
    (15, 6);