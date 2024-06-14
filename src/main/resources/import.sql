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

INSERT INTO arma(
	capacidadedetiro, preco, qtdnoestoque, tipo_arma, modelo, numerosigma, comprimentodocano,
    acabamento, marca, nome, descricao, calibre, numerodaarma, registro_nacional_armas)
VALUES
    (15, 2000.00, 30, 1, 'Smith & Wesson Model 686', 'SW686-001', '6"', 'Polido', 'Smith & Wesson', 'Revólver .357 Magnum', 'Um revólver clássico, conhecido por sua confiabilidade e precisão.', '.357 Magnum', 'SW001', 'RN001'),
    (30, 1500.00, 20, 4, 'Colt AR-15', 'COLTAR15-002', '16"', 'Fosco', 'Colt', 'Rifle AR-15', 'Um rifle semiautomático, amplamente utilizado para esportes de tiro e caça.', '5.56mm', 'COLT002', 'RN002'),
    (8, 800.00, 25, 2, 'Glock 19', 'GLOCK19-003', '4"', 'Prateado', 'Glock', 'Pistola Glock 19', 'Uma pistola compacta, popular entre forças policiais e civis.', '9mm', 'GLK003', 'RN003'),
    (5, 1200.00, 15, 7, 'Remington 870', 'REM870-004', '24"', 'Fosco', 'Remington', 'Escopeta Remington 870', 'Uma escopeta de bombeamento clássica, amplamente utilizada para caça e defesa.', '12 gauge', 'REM004', 'RN004'),
    (20, 1800.00, 10, 5, 'Springfield M1A', 'SPFM1A-005', '18"', 'Polido', 'Springfield Armory', 'Carabina Springfield M1A', 'Uma carabina semiautomática, derivada do M14, conhecida por sua precisão.', '7.62mm NATO', 'SPF005', 'RN005'),
    (25, 2500.00, 12, 6, 'Heckler & Koch MP5', 'HKMP5-006', '10"', 'Fosco', 'Heckler & Koch', 'Submetralhadora MP5', 'Uma submetralhadora compacta, amplamente utilizada por forças policiais e militares em todo o mundo.', '9mm', 'HCK006', 'RN006'),
    (30, 3500.00, 8, 8, 'FN SCAR', 'FNSCAR-007', '20"', 'Prateado', 'FN Herstal', 'Fuzil FN SCAR', 'Um fuzil de assalto moderno e versátil, utilizado por forças especiais em todo o mundo.', '5.56mm', 'FNS007', 'RN007'),
    (6, 1000.00, 18, 1, 'Smith & Wesson Model 637', 'SW637-008', '4"', 'Polido', 'Smith & Wesson', 'Revólver .38 Special', 'Um revólver compacto, ideal para uso como arma de backup ou defesa pessoal.', '.38 Special', 'SW002', 'RN008');


INSERT INTO funcionario (matricula, cpf, telefone, nome, email)
VALUES
    ('123456', '111.111.111-11', '123456789', 'Daniel', 'daniel@example.com'),
    ('789012', '222.222.222-22', '987654321', 'Jacare', 'jacare@example.com');

INSERT INTO usuario (perfil, login, senha)
VALUES 
-- senha_cliente_andre
    (1, 'cliente_andre', '/GOjHgpptfKVaR92/cXMp7sJi0raFpcUWzVNZj5jjwjx0Z/oi19lZx6/v+f6nOpnnYLb9fg9vGw8pUWW08ekUw=='),
-- senha_cliente_bene
    (1, 'cliente_bene', 'tHg5Av764hOiw2peQgpDxMBsweO2HAsg4znQ5kxtcNMz/vPhKldRslxOL53tWghnqAQzna1E80x6CkNJbLT/kQ=='),
-- senha_cliente_hirosh
    (1, 'cliente_hirosh', 'a55M/kc9MqJFbtr5FGF6Ru8LZSwm36pYubnZZMvyox3Tyg8wOt7NZEDKUAIqr/NFejO6r8R+eUj0NvLLlgyl2Q=='),
-- senha_cliente_heitor
    (1, 'cliente_heitor', 'bbkoG72xCIeM+5TONI15VERApwynQf256JHHvX6bkAve00r43+cXaOOVLBYk/Q3xnnjce/7/jsvSaTOHB8RadQ=='),
-- senha_cliente_henrique
    (1, 'cliente_henrique', 'VobPCVa6t0EWSzXmvjNz/1iyKtcnISau1orzafq2UZEflPtvY9IjE/XCSg4Eccw/ifM14KsdoqWy+ag1r0OWFg=='),
-- senha_cliente_junin
    (1, 'cliente_junin', 'dsBjbU2BWnA/oQtxfZ7WLv3/Wdb4vRRb+YzXqHw+qJMuZ3rUoZKX0nWOvsp+r12ZZnhNriIxNsrIS/L2ROm1Ag=='),
-- senha_cliente_maria
    (1, 'cliente_maria', 'gQvX0a7+KxRYbi5K/1hDHzq32iNHUZHwYkF2ok22XL6MJ2SvWLZlduiM8D4UZdP57jvpyU89CSiFKinzIjXw1g=='),
-- senha_cliente_aline
    (1, 'cliente_aline', 'pzn74eUzJBP32jVsBmOpL50OTrZVgI9VBF7jKLXKKkAsGRfpqnaKblr696yAzh/1ALEyuVAkmOWVyXZzDGu+SQ=='),
-- senha_cliente_fernanda
    (1, 'cliente_fernanda', 'GJT+Wolwvp3TaibJtfTCixQ6mXbWOH6Y5M3FWOYCN0Twh+kdjIn5wSD0eVKjqQbhdiue9w8mRPRgbquvOBp3fw=='),
-- senha_funcionario_daniel
    (2, 'funcionario_daniel', 'gz92jkTcveMCL5phxX4m8vcDbE7IwdfMYLEbCFjsd55e89YZ8XlOte2oYZKk9l+UgIR94tyzIt1wyI22/K1sdg=='),
-- senha_funcionario_jacare
    (2, 'funcionario_jacare', '/1TvZJJL/8eyuJ8ktCm35K2ShjAQYM9dPzdDUwU8Fk2S5UMQlZ+AJI6ruk/VRwEB+6LISFzdAOVVQvhl0Owc3w==');


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