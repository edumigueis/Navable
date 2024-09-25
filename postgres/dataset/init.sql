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

-- Create table for Tipo de Ocorrência
CREATE TABLE TipoOcorrência (
    id_tipo_ocorrencia INT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    icone VARCHAR(200) NOT NULL,
    PRIMARY KEY (id_tipo_ocorrencia)
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

-- Create table for Votos
CREATE TABLE Votos (
    id_usuario INT NOT NULL,
    id_ocorrencia INT NOT NULL,
    PRIMARY KEY (id_usuario, id_ocorrencia),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY (id_ocorrencia) REFERENCES Ocorrência(id_ocorrencia)
);

-- Create table for AvaliacaoCategoria
CREATE TABLE AvaliacaoCategoria (
    categoria_ac_id INT NOT NULL,
    id_avaliacao INT NOT NULL,
    PRIMARY KEY (categoria_ac_id, id_avaliacao),
    FOREIGN KEY (categoria_ac_id) REFERENCES CategoriaAcessibilidade(categoria_ac_id),
    FOREIGN KEY (id_avaliacao) REFERENCES Avaliação(id_avaliacao)
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

--SAMPLE FOR FILE INITIATING
CREATE TABLE inflation_data (
    RegionalMember TEXT,
    Year INT,
    Inflation DECIMAL,
    Unit_of_Measurement TEXT,
    Subregion TEXT,
    Country_Code TEXT
);

COPY inflation_data
FROM '/docker-entrypoint-initdb.d/inflation.csv'
DELIMITER ','
CSV HEADER;