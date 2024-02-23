-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

-- Inserindo 5 endereços no Tocantins
INSERT INTO endereco(numero, cep, cidade, estado, logradouro, nome, bairro, complemento)
VALUES
(1, '77000-000', 'Palmas', 'TO', 'Rua das Flores', 'Residência Tocantins 1', 'Centro', 'Casa 1'),
(2, '77001-000', 'Palmas', 'TO', 'Avenida Central', 'Residência Tocantins 2', 'Plano Diretor Sul', 'Apartamento 101'),
(3, '77002-000', 'Porto Nacional', 'TO', 'Rua dos Ipês', 'Residência Tocantins 3', 'Setor Jardim Bela Vista', ''),
(4, '77003-000', 'Gurupi', 'TO', 'Avenida Central', 'Residência Tocantins 4', 'Setor Central', 'Casa 2'),
(5, '77004-000', 'Araguaína', 'TO', 'Rua das Palmeiras', 'Residência Tocantins 5', 'Setor Oeste', 'Bloco A');

-- Inserindo 5 pessoas
INSERT INTO pessoa(data_nascimento, id_endereco, id_usuario, cpf, telefone, nome)
VALUES
('1985-03-12', 1, 1, '111.111.111-11', '(63) 9999-1111', 'Isabela Andrade'),
('1990-07-25', 2, 2, '222.222.222-22', '(63) 9999-2222', 'Daniel Jacare'),
('1978-12-03', 3, 3, '333.333.333-33', '(63) 9999-3333', 'Suzana Teles'),
('1989-04-18', 4, 4, '444.444.444-44', '(63) 9999-4444', 'Andre Higo'),
('1995-09-30', 5, 5, '555.555.555-55', '(63) 9999-5555', 'Luana Regia');

-- Inserindo 5 usuários
INSERT INTO usuario(perfil, login, senha)
VALUES
('admin', 'isabela.andrade', 'senha1'),
('admin', 'daniel.jacare', 'senha2'),
('usuario', 'suzana.teles', 'senha3'),
('admin', 'andre.higo', 'senha4'),
('usuario', 'luana.regia', 'senha5');

-- Inserindo 5 equipamentos militares
INSERT INTO produto(preco, qtdnoestoque, valor, nome, descricao)
VALUES
(500.00, 10, 'Equipamento 1', 'Coletes à prova de balas', 'Colete balístico nível IIIA para proteção contra armas de fogo.'),
(200.00, 20, 'Equipamento 2', 'Capacete tático', 'Capacete de proteção balística para uso militar.'),
(150.00, 15, 'Equipamento 3', 'Mochila tática', 'Mochila resistente e tática para transporte de equipamentos.'),
(1000.00, 5, 'Equipamento 4', 'Visão Noturna', 'Dispositivo de visão noturna para operações em baixa luminosidade.'),
(300.00, 8, 'Equipamento 5', 'Rádio de Comunicação', 'Rádio de comunicação tático com alcance de longa distância e criptografia segura.');
