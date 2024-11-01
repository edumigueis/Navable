-- Insert data into Usuario
INSERT INTO Usuario (id_usuario, nome, email, senha, pontos) VALUES
(1, 'Maria Silva', 'maria.silva@email.com', 'senha123', 150),
(2, 'João Oliveira', 'joao.oliveira@email.com', 'senha456', 250),
(3, 'Ana Pereira', 'ana.pereira@email.com', 'senha789', 300),
(4, 'Carlos Santos', 'carlos.santos@email.com', 'senha321', 100),
(5, 'Juliana Costa', 'juliana.costa@email.com', 'senha654', 200);

-- Insert data into Selo
INSERT INTO Selo (id_selo, nome, imagem) VALUES
(1, 'Novato', 'images/selo_novato.png'),
(2, 'Ativo', 'images/selo_ativo.png'),
(3, 'Expert', 'images/selo_expert.png'),
(4, 'Contribuidor', 'images/selo_contribuidor.png'),
(5, 'Veterano', 'images/selo_veterano.png');

-- Insert data into SeloUsuario
INSERT INTO SeloUsuario (id_usuario, id_selo, timestamp) VALUES
(1, 1, '2024-01-01'),
(2, 2, '2024-01-02'),
(3, 3, '2024-01-03'),
(4, 1, '2024-01-04'),
(5, 2, '2024-01-05');

-- Insert data into CategoriaAcessibilidade
INSERT INTO CategoriaAcessibilidade (categoria_ac_id, nome, grupo) VALUES
(1, 'Mobilidade', 'Física'),
(2, 'Comunicação', 'Visual'),
(3, 'Auditiva', 'Sensório'),
(4, 'Intelectual', 'Cognitivo'),
(5, 'Autonomia', 'Comportamental');

-- Insert data into TipoEstabelecimento
INSERT INTO TipoEstabelecimento (id_tipo_estabeleci, nome) VALUES
(1, 'Restaurante'),
(2, 'Loja de Roupas'),
(3, 'Supermercado'),
(4, 'Escola'),
(5, 'Hospital');

-- Insert data into Estabelecimento
INSERT INTO Estabelecimento (id_estabelecimento, id_tipo_estabeleci, nome, latitude, longitude, imagem, endereco) VALUES
(1, 1, 'Pizzaria do João', 123456, 654321, 'images/pizzaria.jpg', 'Rua A, 123'),
(2, 2, 'Moda e Estilo', 123457, 654322, 'images/moda.jpg', 'Rua B, 234'),
(3, 3, 'Supermercado Central', 123458, 654323, 'images/supermercado.jpg', 'Rua C, 345'),
(4, 4, 'Colégio Nossa Senhora', 123459, 654324, 'images/colegio.jpg', 'Rua D, 456'),
(5, 5, 'Hospital da Cidade', 123460, 654325, 'images/hospital.jpg', 'Rua E, 567');

-- Insert data into TipoOcorrencia
INSERT INTO TipoOcorrencia (id_tipo_ocorrencia, nome, icone) VALUES
(1, 'Acessibilidade', 'images/icone_acessibilidade.png'),
(2, 'Segurança', 'images/icone_seguranca.png'),
(3, 'Higiene', 'images/icone_higiene.png'),
(4, 'Atendimento', 'images/icone_atendimento.png'),
(5, 'Infraestrutura', 'images/icone_infraestrutura.png');

-- Insert data into Ocorrencia
INSERT INTO Ocorrencia (id_ocorrencia, id_usuario, id_tipo_ocorrencia, latitude, longitude) VALUES
(1, 1, 1, 123456, 654321),
(2, 2, 2, 123457, 654322),
(3, 3, 3, 123458, 654323),
(4, 4, 4, 123459, 654324),
(5, 5, 5, 123460, 654325);

-- Insert data into Votos
INSERT INTO Votos (id_usuario, id_ocorrencia) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 3),
(5, 4);

-- Insert data into UsuarioCategoria
INSERT INTO UsuarioCategoria (categoria_ac_id, id_usuario) VALUES
(1, 1),
(2, 2),
(3, 3),
(1, 4),
(2, 5);

-- Insert data into CategoriaAceTipoEstab
INSERT INTO CategoriaAceTipoEstab (id_tipo_estabeleci, categoria_ac_id) VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 3),
(5, 4);

-- Insert data into Avaliacao
INSERT INTO Avaliacao (id_avaliacao, id_usuario, id_estabelecimento, avaliacao, nota, timestamp) VALUES
(1, 1, 1, 'Ótima experiência!', 2, '2024-01-01'),
(2, 2, 2, 'Bom atendimento.', 1, '2024-01-02'),
(3, 3, 3, 'Pode melhorar.', 0, '2024-01-03'),
(4, 4, 4, 'Excelente!', 2, '2024-01-04'),
(5, 5, 5, 'Muito bom.', 1, '2024-01-05');

-- Insert data into AvaliacaoCategoria
INSERT INTO AvaliacaoCategoria (categoria_ac_id, id_avaliacao) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

