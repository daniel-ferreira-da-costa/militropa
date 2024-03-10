-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO usuario(perfil, login, senha)
VALUES 
    (2, 'daniel90', 'mvJiSec3uZLUqe825+2/3gsotQ5Ar+XalO7KWvyv/3KJaUVQQ3o7aiPHavKLuSxUitM/BVb3c4SQ00XCH61DcQ=='),
    (1, 'heitor85', 'rR4uLvAZvTMqPrYpYDkdcpYFVCjK/jA9R5dFA680S5/NonVk++TiBP6E/DUXgFyWkzMqpsqC+mszw1fycViklg=='),
    (1, 'henrique95', 'm8qzYJ9CPzALDOeZhoXfvjvotP5Mgio+Pwp3gULAKYE1IkQ8Yv6pKGKwDcbXFo87Kjyb+OUkdPXSI3MXMVGe8A==');

INSERT INTO endereco(numero, cep, cidade, estado, logradouro, nome, bairro, complemento)
VALUES 
    (123, '77000-000', 'Palmas', 'TO', 'Rua das Flores', 'Casa Azul', 'Centro', 'kitnet 4'),
    (456, '77001-001', 'Gurupi', 'TO', 'Avenida dos Bandeirantes', 'Casa Verde', 'Jardim Bela Vista', 'Esquina com a Rua Principal'),
    (789, '77002-002', 'Araguaína', 'TO', 'Travessa das Oliveiras', 'Casa Amarela', 'Setor Norte', 'Ao lado da praça');

INSERT INTO pessoa (data_nascimento, cpf, telefone, nome)
VALUES 
    ('1990-05-15', '123.456.789-10', '(11) 98765-4321', 'Daniel'),
    ('1985-10-20', '987.654.321-10', '(11) 98765-4322', 'Heitor'),
    ('1995-03-25', '456.789.123-10', '(11) 98765-4323', 'Henrique');

INSERT INTO usuario_pessoa(id_pessoa, id_usuario)
VALUES 
	(1, 1),
    (2, 2),
    (3, 3);

INSERT INTO pessoa_endereco(id_endereco, id_pessoa)
VALUES 
    (1, 1),
    (2, 2),
    (3, 3);