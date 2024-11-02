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
(5, 'Hospital da Cidade', 123460, 654325, 'images/hospital.jpg', 'Rua E, 567'),
(6, 'Restaurante Universitário (RU)', -22.81678209374871, -47.07163840057773, 'images/ru.jpg', 'Av. Érico Veríssimo, 50'),
(7, 'Biblioteca Central Cesar Lattes', -22.815988330376307, -47.07033717109454, 'images/biblioteca.jpg', 'R. Sérgio Buarque de Holanda, 421'),
(8, 'Faculdade de Educação Física', -22.814978014353226, -47.071722249901974, 'images/fef.jpg', 'Av. Érico Veríssimo, 701'),
(9, 'Praça do Ciclo Básico', -22.817017579114346, -47.06973453314579, 'images/praca.jpg', 'R. Sérgio Buarque de Holanda, S/N'),
(10, 'Instituto de Física Gleb Wataghin', -22.817272606585433, -47.06770885259852, 'images/ifgw.jpg', 'R. Sérgio Buarque de Holanda, 777'),
(11, 'Instituto de Computação - IC 3 / 3.5 - Unicamp', -22.813531021175827, -47.06352688811394, 'images/ic.jpg', 'R. Saturnino de Brito, 573'),
(12, 'Restaurante da Saturnino (RS)', -22.81519689405684, -47.06215842203571, 'images/rs.jpg', 'R. Saturnino de Brito'),
(13, 'Centro de Convenções', -22.814632123648288, -47.070874208355804, 'images/cc.jpg', 'Avenida Érico Veríssimo, 500'),
(14, 'Instituto de Artes - IA/Unicamp', -22.815270315044277, -47.07058299754328, 'images/ia.jpg', 'Rua Elis Regina, 50'),
(15, 'Instituto de Geociências', -22.81330657707895, -47.068970019519725, 'images/ig.jpg', 'R. Carlos Gomes, 250'),
(16, 'Faculdade de Engenharia Mecânica', -22.818723039906683, -47.06585748693785, 'images/fem.jpg', 'R. Mendeleyev, 200'),
(17, 'Faculdade de Engenharia Química', -22.82007328775776, -47.064953610547356, 'images/eq.jpg', 'Av. Albert Einstein, 500'),
(18, 'Ciclo Básico II - PB', -22.817855629693145, -47.07071132402292, 'images/pb.jpg', 'R. Sérgio Buarque de Holanda'),
(19, 'Ciclo Básico I - CB', -22.81788369830114, -47.068504959994634, 'images/cb.jpg', 'R. Josué de Castro, 1-123'),
(20, 'Instituto de Matemática, Estatística e Computação Científica (IMECC)', -22.815875501654062, -47.067793497540066, 'images/imecc.jpg', 'Cidade Universitária, Campinas') ;

-- Insert data into Ocorrencia
INSERT INTO Ocorrencia (id_usuario, id_tipo_ocorrencia, latitude, longitude) VALUES
(1, 1, 123456, 654321),
(2, 2, 123457, 654322),
(3, 3, 123458, 654323),
(4, 4, 123459, 654324),
(5, 5, 123460, 654325),
(5, 2, -22.81678209374871, -47.07163840057773), --rampa ingreme, no ru--
(2, 12, -22.81519689405684, -47.06215842203571), --superficie escorregadia, rs--
(1, 16, -22.817855629693145, -47.07071132402292), --obstaculos no caminho, pb--
(3, 7, -22.81330657707895, -47.068970019519725), --banheiro inacessivel no ig--
(4, 6, -22.81788369830114, -47.068504959994634); -- falta de sinalização no cb--

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
