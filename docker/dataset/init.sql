-- Create table for Usuario
CREATE TABLE Usuario (
    id_usuario INT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    pontos INT NOT NULL,
    PRIMARY KEY (id_usuario)
);

-- Create table for Selo
CREATE TABLE Selo (
    id_selo INT NOT NULL,
    nome VARCHAR(30) NOT NULL,
    imagem VARCHAR(200) NOT NULL,
    PRIMARY KEY (id_selo)
);

-- Create table for SeloUsuario
CREATE TABLE SeloUsuario (
    id_usuario INT NOT NULL,
    id_selo INT NOT NULL,
    timestamp DATE NOT NULL,
    PRIMARY KEY (id_usuario, id_selo),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_selo) REFERENCES Selo(id_selo)
);


-- Create table for Categoria de Acessibilidade
CREATE TABLE CategoriaAcessibilidade (
    categoria_ac_id INT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    grupo VARCHAR(50) NOT NULL,
    PRIMARY KEY (categoria_ac_id)
);

-- Create table for Tipo de Estabelecimento
CREATE TABLE TipoEstabelecimento (
    id_tipo_estabeleci INT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_tipo_estabeleci)
);

-- Create table for Estabelecimento
CREATE TABLE Estabelecimento (
    id_estabelecimento INT NOT NULL,
    id_tipo_estabeleci INT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    latitude INT NOT NULL,
    longitude INT NOT NULL,
    imagem VARCHAR(200),
    endereco VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_estabelecimento),
    FOREIGN KEY (id_tipo_estabeleci) REFERENCES TipoEstabelecimento(id_tipo_estabeleci)
);

-- Create table for Tipo de Ocorrência
CREATE TABLE TipoOcorrência (
    id_tipo_ocorrencia INT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    icone VARCHAR(200) NOT NULL,
    PRIMARY KEY (id_tipo_ocorrencia)
);

-- Create table for Ocorrência
CREATE TABLE Ocorrencia (
    id_ocorrencia INT NOT NULL,
    id_usuario INT NOT NULL,
    id_tipo_ocorrencia INT NOT NULL,
    latitude INT NOT NULL,
    longitude INT NOT NULL,
    PRIMARY KEY (id_ocorrencia),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_tipo_ocorrencia) REFERENCES TipoOcorrência(id_tipo_ocorrencia)
);

-- Create table for Votos
CREATE TABLE Votos (
    id_usuario INT NOT NULL,
    id_ocorrencia INT NOT NULL,
    PRIMARY KEY (id_usuario, id_ocorrencia),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_ocorrencia) REFERENCES Ocorrência(id_ocorrencia)
);

-- Create table for UsuarioCategoria
CREATE TABLE UsuarioCategoria (
    categoria_ac_id INT NOT NULL,
    id_usuario INT NOT NULL,
    PRIMARY KEY (categoria_ac_id, id_usuario),
    FOREIGN KEY (categoria_ac_id) REFERENCES CategoriaAcessibilidade(categoria_ac_id),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

-- Create table for CategoriaAceTipoEstab
CREATE TABLE CategoriaAceTipoEstab (
    id_tipo_estabeleci INT NOT NULL,
    categoria_ac_id INT NOT NULL,
    PRIMARY KEY (id_tipo_estabeleci, categoria_ac_id),
    FOREIGN KEY (id_tipo_estabeleci) REFERENCES TipoEstabelecimento(id_tipo_estabeleci),
    FOREIGN KEY (categoria_ac_id) REFERENCES CategoriaAcessibilidade(categoria_ac_id)
);

-- Create table for Avaliação
CREATE TABLE Avaliacaoo (
    id_avaliacao INT NOT NULL,
    id_usuario INT NOT NULL,
    id_estabelecimento INT NOT NULL,
    avaliacao VARCHAR(100),
    nota INT CHECK (nota IN (0, 1, 2)) NOT NULL,
    timestamp DATE NOT NULL,
    PRIMARY KEY (id_avaliacao),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_estabelecimento) REFERENCES Estabelecimento(id_estabelecimento)
);

-- Create table for AvaliacaoCategoria
CREATE TABLE AvaliacaoCategoria (
    categoria_ac_id INT NOT NULL,
    id_avaliacao INT NOT NULL,
    PRIMARY KEY (categoria_ac_id, id_avaliacao),
    FOREIGN KEY (categoria_ac_id) REFERENCES CategoriaAcessibilidade(categoria_ac_id),
    FOREIGN KEY (id_avaliacao) REFERENCES Avaliação(id_avaliacao)
);

-- Insert data into Selo
INSERT INTO Selo (nome, imagem) VALUES
('Novato', 'selo_novato.png'),
('Ativo', 'selo_ativo.png'),
('Expert', 'selo_expert.png'),
('Contribuidor', 'selo_contribuidor.png'),
('Veterano', 'selo_veterano.png');

-- Inserir dados na CategoriaAcessibilidade
INSERT INTO CategoriaAcessibilidade (nome, grupo) VALUES
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
INSERT INTO TipoEstabelecimento (nome) VALUES
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
INSERT INTO TipoOcorrencia (nome, icone) VALUES
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
