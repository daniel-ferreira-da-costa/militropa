-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO endereco(numero, cep, cidade, estado, logradouro, nome, bairro, complemento)
VALUES 
    (1, '77000-000', 'Palmas', 'TO', 'Avenida JK', 'Residencial Primavera', 'Centro', 'Bloco A, Ap. 101'),
    (2, '77001-000', 'Palmas', 'TO', 'Rua 13 de Maio', 'Bairro Flamboyant', 'Plano Diretor Norte', 'Casa 02'),
    (3, '77002-000', 'Palmas', 'TO', 'Quadra 205 Sul', 'Condomínio Jardins', 'Plano Diretor Sul', 'Casa 03');

INSERT INTO produto(preco, qtdnoestoque, valor, nome, descricao)
VALUES 
    (1500.00, 10, 15000.00, 'Fuzil de Assalto M4', 'Fuzil de assalto utilizado pelas forças armadas.'),
    (1200.00, 15, 18000.00, 'Pistola Glock 17', 'Pistola semi-automática de uso militar.'),
    (2500.00, 8, 20000.00, 'Lança-Granadas M203', 'Lança-granadas acoplável a rifles de assalto.');

INSERT INTO usuario(perfil, login, nome, senha, telefone)
VALUES 
    (2, 'admin123', 'Administração Geral', 'senha123', '(99) 9999-9999'),
    (1, 'usuario1', 'João Silva', 'minhasenha', '(88) 8888-8888'),
    (1, 'usuario2', 'Maria Santos', 'outrasenha', '(77) 7777-7777');
