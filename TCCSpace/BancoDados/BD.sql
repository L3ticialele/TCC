CREATE TABLE usuarios(
    Id int GENERATED BY DEFAULT AS IDENTITY,
	senha varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	cpf varchar(15) NOT NULL,
	administrador boolean NOT NULL,
	nome varchar(255) NOT NULL,
	telefone varchar(255) NOT NULL,
	CONSTRAINT PK_usuario PRIMARY KEY(Id)
);

CREATE TABLE equipes(
	nome varchar(255) NOT NULL,
	senha varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	Id int GENERATED BY DEFAULT AS IDENTITY,
	CONSTRAINT PK_equipe PRIMARY KEY(Id),
	email varchar(255)
);

CREATE TABLE usuario_equipe(
	idUsuario INT NOT NULL,
	idEquipe INT NOT NULL,
	CONSTRAINT PK_Usuario_Equipe PRIMARY KEY (idUsuario, idEquipe),
	CONSTRAINT FK_Usuario_Equipe1 FOREIGN KEY(idUsuario) REFERENCES usuarios(Id),
	CONSTRAINT FK_Usuario_Equipe2 FOREIGN KEY(idEquipe) REFERENCES equipes(Id)
);

CREATE TABLE cubesat(
	Id int GENERATED BY DEFAULT AS IDENTITY,
	dataFabricacao varchar(255) NOT NULL,
	tamanho varchar(255) NOT NULL,
	nome varchar(255) NOT NULL,
	idUsuario INT NOT NULL,
	idEquipe INT NOT NULL,
	status varchar(255) NOT NULL,
	CONSTRAINT PK_cubesat PRIMARY KEY (Id),
	CONSTRAINT FK_cubesat_usuarios FOREIGN KEY(idUsuario) REFERENCES usuarios(Id),
	CONSTRAINT FK_cubesat_equipe FOREIGN KEY (idEquipe) REFERENCES equipes(Id)
);

CREATE TABLE administradores(
    Id int GENERATED BY DEFAULT AS IDENTITY,
    senha varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	cpf varchar(15) NOT NULL,
	nome varchar(255) NOT NULL,
	telefone varchar(255) NOT NULL,
	administrador boolean NOT NULL, 
	CONSTRAINT PK_administrador PRIMARY KEY(Id),
	CONSTRAINT FK_administrador FOREIGN KEY(Id) REFERENCES usuarios(Id)
);

CREATE TABLE Administrador_Equipe(
	idAdministrador int NOT NULL,
	idEquipe int NOT NULL,
	CONSTRAINT PK_Administrador_Equipe PRIMARY KEY(idAdministrador, idEquipe),
	CONSTRAINT FK_Administrador_Equipe1 FOREIGN KEY (idAdministrador) REFERENCES administradores(Id),
	CONSTRAINT FK_Administrador_Equipe2 FOREIGN KEY (idEquipe) REFERENCES equipes(Id)
);

CREATE TABLE dados(
	idCubeSat int NOT NULL,
	acelerometroX float,
	acelerometroY float,
	acelerometroZ float,
	anguloX float, 
	anguloY float,
	anguloZ float,
	altitude float,
	bateria float,
	correnteBateria float,
	correntePlacaSolar float,
	gas1 float,
	gas2 float, 
	luz1 float,
	luz2 float,
	pontoOrvalho float,
	pressao float,
	sensorUV float,
	temperaturaExterna float,
	temperaturaInterna float,
	tensaoBateria float,
	tensaoPlacaSolar float,
	umidade float,
	velocidade float,
	velocidadeAngularX float, 
	velocidadeAngularY float,
	velocidadeAngularZ float,
	dataObtencao varchar(255),
	Id INT GENERATED BY DEFAULT AS IDENTITY,
	CONSTRAINT PK_dados PRIMARY KEY(Id),
	CONSTRAINT FK_dados_cubesat FOREIGN KEY(idCubeSat) REFERENCES cubesat(Id)
);

CREATE TABLE localizacao(
	altitude varchar(255),
	longitude varchar(255),
	idCubeSat INT NOT NULL,
	Id INT GENERATED BY DEFAULT AS IDENTITY,
	CONSTRAINT PK_localizacao PRIMARY KEY(Id),
	CONSTRAINT FK_localizacao_cubesat FOREIGN KEY(idCubeSat) REFERENCES cubesat(Id)
);
