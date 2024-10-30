-- Criação da tabela usuario
CREATE TABLE usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    pontos INT NOT NULL
);

-- Criação da tabela selo
CREATE TABLE selo (
    id_selo SERIAL PRIMARY KEY,
    nome VARCHAR(30) NOT NULL,
    imagem VARCHAR(200) NOT NULL
);

-- Criação da tabela selo_usuario (associativa)
CREATE TABLE selo_usuario (
    id_usuario INT NOT NULL,
    id_selo INT NOT NULL,
    timestamp DATE NOT NULL,
    PRIMARY KEY (id_usuario, id_selo),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_selo) REFERENCES selo(id_selo)
);

-- Criação da tabela categoria_acessibilidade
CREATE TABLE categoria_acessibilidade (
    categoria_ac_id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    grupo VARCHAR(50) NOT NULL
);

-- Criação da tabela tipo_estabelecimento
CREATE TABLE tipo_estabelecimento (
    id_tipo_estabeleci SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

-- Criação da tabela estabelecimento
CREATE TABLE estabelecimento (
    id_estabelecimento SERIAL PRIMARY KEY,
    id_tipo_estabeleci INT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    latitude INT NOT NULL,
    longitude INT NOT NULL,
    imagem VARCHAR(200),
    endereco VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_tipo_estabeleci) REFERENCES tipo_estabelecimento(id_tipo_estabeleci)
);

-- Criação da tabela tipo_ocorrencia
CREATE TABLE tipo_ocorrencia (
    id_tipo_ocorrencia SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    icone VARCHAR(200) NOT NULL
);

-- Criação da tabela ocorrencia
CREATE TABLE ocorrencia (
    id_ocorrencia SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_tipo_ocorrencia INT NOT NULL,
    latitude INT NOT NULL,
    longitude INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_tipo_ocorrencia) REFERENCES tipo_ocorrencia(id_tipo_ocorrencia)
);

-- Criação da tabela votos (associativa)
CREATE TABLE votos (
    id_usuario INT NOT NULL,
    id_ocorrencia INT NOT NULL,
    PRIMARY KEY (id_usuario, id_ocorrencia),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_ocorrencia) REFERENCES ocorrencia(id_ocorrencia)
);

-- Criação da tabela usuario_categoria (associativa)
CREATE TABLE usuario_categoria (
    categoria_ac_id INT NOT NULL,
    id_usuario INT NOT NULL,
    PRIMARY KEY (categoria_ac_id, id_usuario),
    FOREIGN KEY (categoria_ac_id) REFERENCES categoria_acessibilidade(categoria_ac_id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Criação da tabela categoria_ace_tipo_estab (associativa)
CREATE TABLE categoria_ace_tipo_estab (
    id_tipo_estabeleci INT NOT NULL,
    categoria_ac_id INT NOT NULL,
    PRIMARY KEY (id_tipo_estabeleci, categoria_ac_id),
    FOREIGN KEY (id_tipo_estabeleci) REFERENCES tipo_estabelecimento(id_tipo_estabeleci),
    FOREIGN KEY (categoria_ac_id) REFERENCES categoria_acessibilidade(categoria_ac_id)
);

-- Criação da tabela avaliacao
CREATE TABLE avaliacao (
    id_avaliacao SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_estabelecimento INT NOT NULL,
    avaliacao VARCHAR(100),
    nota INT CHECK (nota IN (0, 1, 2)) NOT NULL,
    timestamp DATE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_estabelecimento) REFERENCES estabelecimento(id_estabelecimento)
);

-- Criação da tabela avaliacao_categoria (associativa)
CREATE TABLE avaliacao_categoria (
    categoria_ac_id INT NOT NULL,
    id_avaliacao INT NOT NULL,
    PRIMARY KEY (categoria_ac_id, id_avaliacao),
    FOREIGN KEY (categoria_ac_id) REFERENCES categoria_acessibilidade(categoria_ac_id),
    FOREIGN KEY (id_avaliacao) REFERENCES avaliacao(id_avaliacao)
);

-- Insert data into Selo
INSERT INTO Selo (nome, imagem) VALUES
('Novato', 'selo_novato.png'),
('Ativo', 'selo_ativo.png'),
('Expert', 'selo_expert.png'),
('Contribuidor', 'selo_contribuidor.png'),
('Veterano', 'selo_veterano.png');

-- Inserir dados na CategoriaAcessibilidade
INSERT INTO Categoria_Acessibilidade (nome, grupo) VALUES
('Assento Sanitário Rebaixado', 'Banheiro'),
('Rampa de Acesso', 'Entrada'),
('Estacionamento Acessível', 'Entrada'),
('Entrada Sem Barreiras', 'Entrada'),
('Porta Automática', 'Entrada'),
('Elevador', 'Geral'),
('Banheiro Acessível - Privado', 'Banheiro'),
('Banheiro Acessível - Cabine', 'Banheiro'),
('Mobiliário Acessível', 'Geral'),
('Iluminação Brilhante', 'Geral'),
('Iluminação Baixa', 'Geral'),
('Acessibilidade Auditiva', 'Auditivo'),
('Sinais em Braille', 'Geral'),
('Cadeiras de Rodas Disponíveis', 'Geral'),
('Espaço Amplo', 'Geral'),
('Balcões Abaixados', 'Geral'),
('Serviço Amigo do Animal', 'Geral'),
('Quadro de Avisos em Grande Formato', 'Geral'),
('Sinalização Clara', 'Geral'),
('Sons Auditivos de Alerta', 'Auditivo'),
('Acessibilidade Digital', 'Digital'),
('Menu Digital', 'Digital'),
('Acessibilidade em Eventos', 'Geral'),
('Sistema de Loop de Audição', 'Auditivo'),
('Apoio Visual', 'Geral'),
('Área de Espera Acessível', 'Geral'),
('Tábuas de Tactile', 'Geral'),
('Acessibilidade para Deficientes Visuais', 'Geral'),
('Guias de Navegação', 'Geral'),
('Área de Descanso Acessível', 'Geral'),
('Placas Informativas em Braille', 'Geral'),
('Feedback Auditivo', 'Geral'),
('Mesas Acessíveis', 'Banheiro'),
('Protetores Auditivos Disponíveis', 'Auditivo'),
('Segurança Acessível', 'Geral'),
('Iluminação de Sinalização', 'Geral'),
('Acessibilidade para Animais de Serviço', 'Geral'),
('Suporte para Mobilidade', 'Geral'),
('Sinalização em Vários Idiomas', 'Geral'),
('Estacionamento Reservado para Deficientes', 'Entrada');

-- Insert data into TipoEstabelecimento
INSERT INTO Tipo_Estabelecimento (nome) VALUES
('Restaurante'),
('Loja de Roupas'),
('Supermercado'),
('Escola'),
('Hospital'),
('Ponto de Ônibus'),
('Cafeteria'),
('Clínica'),
('Shopping Center'),
('Biblioteca'),
('Centro de Convivência'),
('Posto de Combustível'),
('Academia'),
('Teatro'),
('Museu'),
('Parque');

-- Insert data into TipoOcorrencia
INSERT INTO Tipo_Ocorrencia (nome, icone) VALUES
('Calçada Irregular', 'images/icone_calçada_irregular.png'),
('Rampa Íngreme', 'images/icone_rampa_ingreme.png'),
('Buraco na Calçada', 'images/icone_buraco_calçada.png'),
('Guia de Cegos Mal Posicionada', 'images/icone_guia_cegos.png'),
('Acesso Bloqueado', 'images/icone_acesso_bloqueado.png'),
('Falta de Sinalização', 'images/icone_falta_sinalizacao.png'),
('Banheiro Inacessível', 'images/icone_banheiro_inacessivel.png'),
('Escadas Sem Alternativa', 'images/icone_escadas_sem_alternativa.png'),
('Assentos Inadequados', 'images/icone_assentos_inadequados.png'),
('Iluminação Insuficiente', 'images/icone_iluminacao_insuficiente.png'),
('Elevador Fora de Serviço', 'images/icone_elevador_fora_servico.png'),
('Superfície Escorregadia', 'images/icone_superficie_escorregadia.png'),
('Mobiliário Inadequado', 'images/icone_mobiliario_inadequado.png'),
('Portas Pesadas ou Difíceis de Abrir', 'images/icone_portas_pesadas.png'),
('Falta de Corrimão em Escadas', 'images/icone_falta_corrimao.png'),
('Obstáculos no Caminho', 'images/icone_obstaculos_caminho.png'),
('Dificuldade para Manobras com Cadeira de Rodas', 'images/icone_dificuldade_manobras.png');

-- Insert data into Usuario
INSERT INTO Usuario (nome, email, senha, pontos) VALUES
('Maria Silva', 'maria.silva@email.com', 'B23SMgJxhisIEWMb9OKe5g==', 150),
('João Oliveira', 'joao.oliveira@email.com', 'V7R8qoHA8GRvo1YjJesmEg==', 250),
('Ana Pereira', 'ana.pereira@email.com', '6LFFwZzPmhVrUIgArs77ew==', 300),
('Carlos Santos', 'carlos.santos@email.com', 'N+O5Pk+tJGLgyOv0z3cVWg==', 100),
('Juliana Costa', 'juliana.costa@email.com', 'rYGht6h7tw0msRAlRkDUZQ==', 200);

-- Insert data into Selo
INSERT INTO Selo (nome, imagem) VALUES
('Novato', 'selo_novato.png'),
('Ativo', 'selo_ativo.png'),
('Expert', 'selo_expert.png'),
('Contribuidor', 'selo_contribuidor.png'),
('Veterano', 'selo_veterano.png');

-- Insert data into SeloUsuario
INSERT INTO SeloUsuario (id_usuario, id_selo, timestamp) VALUES
(1, 1, '2024-01-01'),
(2, 2, '2024-01-02'),
(3, 3, '2024-01-03'),
(4, 1, '2024-01-04'),
(5, 2, '2024-01-05');

-- Insert data into Estabelecimento
INSERT INTO Estabelecimento (id_tipo_estabeleci, nome, latitude, longitude, imagem, endereco) VALUES
(1, 'Pizzaria do João', 123456, 654321, 'images/pizzaria.jpg', 'Rua A, 123'),
(2, 'Moda e Estilo', 123457, 654322, 'images/moda.jpg', 'Rua B, 234'),
(3, 'Supermercado Central', 123458, 654323, 'images/supermercado.jpg', 'Rua C, 345'),
(4, 'Colégio Nossa Senhora', 123459, 654324, 'images/colegio.jpg', 'Rua D, 456'),
(5, 'Hospital da Cidade', 123460, 654325, 'images/hospital.jpg', 'Rua E, 567');

-- Insert data into Ocorrencia
INSERT INTO Ocorrencia (id_usuario, id_tipo_ocorrencia, latitude, longitude) VALUES
(1, 1, 123456, 654321),
(2, 2, 123457, 654322),
(3, 3, 123458, 654323),
(4, 4, 123459, 654324),
(5, 5, 123460, 654325);

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
INSERT INTO Avaliacao (id_usuario, id_estabelecimento, avaliacao, nota, timestamp) VALUES
(1, 1, 'Ótima experiência!', 2, '2024-01-01'),
(2, 2, 'Bom atendimento.', 1, '2024-01-02'),
(3, 3, 'Pode melhorar.', 0, '2024-01-03'),
(4, 4, 'Excelente!', 2, '2024-01-04'),
(5, 5, 'Muito bom.', 1, '2024-01-05');

-- Insert data into AvaliacaoCategoria
INSERT INTO AvaliacaoCategoria (categoria_ac_id, id_avaliacao) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);
