-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO usuario(perfil, login, senha)
VALUES 
    (2, 'daniel', 'mvJiSec3uZLUqe825+2/3gsotQ5Ar+XalO7KWvyv/3KJaUVQQ3o7aiPHavKLuSxUitM/BVb3c4SQ00XCH61DcQ=='),
    (1, 'henrique', 'rR4uLvAZvTMqPrYpYDkdcpYFVCjK/jA9R5dFA680S5/NonVk++TiBP6E/DUXgFyWkzMqpsqC+mszw1fycViklg=='),
    (1, 'heitor', 'm8qzYJ9CPzALDOeZhoXfvjvotP5Mgio+Pwp3gULAKYE1IkQ8Yv6pKGKwDcbXFo87Kjyb+OUkdPXSI3MXMVGe8A=='),
    (1, 'empresa_a', 'm8qzYJ9CPzALDOeZhoXfvjvotP5Mgio+Pwp3gULAKYE1IkQ8Yv6pKGKwDcbXFo87Kjyb+OUkdPXSI3MXMVGe8A=='),
    (1, 'empresa_b', 'm8qzYJ9CPzALDOeZhoXfvjvotP5Mgio+Pwp3gULAKYE1IkQ8Yv6pKGKwDcbXFo87Kjyb+OUkdPXSI3MXMVGe8A=='),
    (1, 'empresa_c', 'm8qzYJ9CPzALDOeZhoXfvjvotP5Mgio+Pwp3gULAKYE1IkQ8Yv6pKGKwDcbXFo87Kjyb+OUkdPXSI3MXMVGe8A==');


INSERT INTO endereco(numero, cep, cidade, estado, logradouro, nome, bairro, complemento)
VALUES 
    (123, '77000-000', 'Palmas', 'TO', 'Rua das Flores', 'Casa Azul', 'Centro', 'kitnet 4'),
    (456, '77001-001', 'Gurupi', 'TO', 'Avenida dos Bandeirantes', 'Casa Verde', 'Jardim Bela Vista', 'Esquina com a Rua Principal'),
    (789, '77002-002', 'Araguaína', 'TO', 'Travessa das Oliveiras', 'Casa Amarela', 'Setor Norte', 'Ao lado da praça');

INSERT INTO pessoa(tipo_pessoa, cpf_cnpj, telefone, nome_fantasia, email)
VALUES 
    (0, '123.456.789-10', '(11) 98765-4321', 'Daniel', 'daniel@example.com'),
    (0, '987.654.321-98', '(11) 12345-6789', 'Henrique', 'henrique@example.com'),
    (0, '111.222.333-44', '(11) 55555-5555', 'Heitor', 'heitor@example.com'),
    (1, '12.345.678/0001-01', '(11) 33333-3333', 'Empresa A', 'empresa_a@example.com'),
    (1, '98.765.432/0001-02', '(11) 44444-4444', 'Empresa B', 'empresa_b@example.com'),
    (1, '11.222.333/0001-03', '(11) 66666-6666', 'Empresa C', 'empresa_c@example.com');

INSERT INTO pessoa_endereco(id_endereco, id_pessoa)
VALUES 
        (1, 1),
        (2, 2),
        (3, 3);

INSERT INTO usuario_pessoa(	id_pessoa, id_usuario)
VALUES 
        (1, 1),
        (2, 2),
        (3, 3),
        (4, 4),
        (5, 5),
        (6, 6);

INSERT INTO produto (peso, preco, qtdnoestoque, tipo_arma, valor, dtype, nome, tipomunicao, descricao)
VALUES 
    (2.5, 499.99, 50, 1, 999.99, 'Arma', 'Pistola 9mm', '9mm', 'Uma pistola compacta e confiável.'),
    (3.0, 799.99, 30, 3, 1499.99, 'Arma', 'Rifle de Precisão', '5.56mm', 'Um rifle de precisão para longo alcance.'),
    (1.5, 299.99, 100, 0, 599.99, 'Arma', 'Revólver Magnum', '.357 Magnum', 'Um revólver clássico com alto poder de fogo.');

