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
    (303, '77005-005', 'Colinas do Tocantins', 'TO', 'Alameda dos Ipês', 'Casa Lilás', 'Centro', 'Ao lado do Banco do Brasil');


INSERT INTO produto (peso, preco, qtdnoestoque, tipo_arma, valor, dtype, nome, tipomunicao, descricao)
VALUES 
    (2.5, 499.99, 50, 1, 999.99, 'Arma', 'Pistola 9mm', '9mm', 'Uma pistola compacta e confiável.'),
    (3.0, 799.99, 30, 3, 1499.99, 'Arma', 'Rifle de Precisão', '5.56mm', 'Um rifle de precisão para longo alcance.'),
    (1.5, 299.99, 100, 0, 599.99, 'Arma', 'Revólver Magnum', '.357 Magnum', 'Um revólver clássico com alto poder de fogo.');

INSERT INTO pessoa(cpf, nome, email, listatelefones)
VALUES 
  ('123.456.789-00', 'Daniel', 'daniel@example.com', '{(11) 1234-5678, (11) 9876-5432}'),
  ('987.654.321-00', 'Isabela', 'isabela@example.com', '{(21) 5555-1234}'),
  ('555.444.333-00', 'Bene', 'bene@example.com', '{(31) 9999-8888}'),
  ('222.333.444-00', 'Heitor', 'heitor@example.com', '{(41) 7777-6666, (41) 3333-2222}'),
  ('999.888.777-00', 'Henrique', 'henrique@example.com', '{(51) 2222-3333}'),
  ('777.666.555-00', 'Luana', 'luana@example.com', '{(61) 9999-1111, (61) 4444-5555}');

INSERT INTO usuario(perfil, login, senha)
VALUES 
  (1, 'daniel', 'senha123'),
  (1, 'isabela', 'senha456'),
  (1, 'bene', 'senha789'),
  (1, 'heitor', 'senhaabc'),
  (1, 'henrique', 'senhadef'),
  (1, 'luana', 'senhaghi');