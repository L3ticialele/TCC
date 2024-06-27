CREATE TABLE usuarios(
    id int GENERATED BY DEFAULT AS IDENTITY,
	senha varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	administrador boolean NOT NULL,
	nome varchar(255) NOT NULL,
	telefone varchar(255) NOT NULL,
	CONSTRAINT PK_usuario PRIMARY KEY(id)
);

CREATE TABLE equipes(
	nome varchar(255) NOT NULL,
	senha varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	id int GENERATED BY DEFAULT AS IDENTITY,
	CONSTRAINT PK_equipe PRIMARY KEY(id),
	email varchar(255)
);

CREATE TABLE usuario_equipe(
	idUsuario INT NOT NULL,
	idEquipe INT NOT NULL,
	CONSTRAINT PK_Usuario_Equipe PRIMARY KEY (idUsuario, idEquipe),
	CONSTRAINT FK_Usuario_Equipe1 FOREIGN KEY(idUsuario) REFERENCES usuarios(id),
	CONSTRAINT FK_Usuario_Equipe2 FOREIGN KEY(idEquipe) REFERENCES equipes(id)
);

CREATE TABLE cubesat(
	id int GENERATED BY DEFAULT AS IDENTITY,
	data varchar(255) NOT NULL,
	descricao varchar(255) NOT NULL,
	nome varchar(255) NOT NULL,
	idUsuario int NOT NULL,
	idEquipe int,
	acesso varchar(255) NOT NULL,
	status varchar(255) NOT NULL,
	CONSTRAINT PK_cubesat PRIMARY KEY (id),
	CONSTRAINT FK_cubesat_usuarios FOREIGN KEY(idUsuario) REFERENCES usuarios(id),
	CONSTRAINT FK_cubesat_equipe FOREIGN KEY (idEquipe) REFERENCES equipes(id)
);

CREATE TABLE administradores(
    id int GENERATED BY DEFAULT AS IDENTITY,
    senha varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	cpf varchar(15) NOT NULL,
	nome varchar(255) NOT NULL,
	telefone varchar(255) NOT NULL,
	administrador boolean NOT NULL, 
	CONSTRAINT PK_administrador PRIMARY KEY(id),
	CONSTRAINT FK_administrador FOREIGN KEY(id) REFERENCES usuarios(id)
);

CREATE TABLE Administrador_Equipe(
	idAdministrador int NOT NULL,
	idEquipe int NOT NULL,
	CONSTRAINT PK_Administrador_Equipe PRIMARY KEY(idAdministrador, idEquipe),
	CONSTRAINT FK_Administrador_Equipe1 FOREIGN KEY (idAdministrador) REFERENCES administradores(id),
	CONSTRAINT FK_Administrador_Equipe2 FOREIGN KEY (idEquipe) REFERENCES equipes(id)
);

CREATE TABLE dados(
	cubesat int NOT NULL,
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
	id INT GENERATED BY DEFAULT AS IDENTITY,
	CONSTRAINT PK_dados PRIMARY KEY(id),
	CONSTRAINT FK_dados_cubesat FOREIGN KEY(cubesat) REFERENCES cubesat(id)
);
INSERT INTO usuarios (senha, username, email, administrador, nome, telefone) VALUES ('123', 'usu', 'usuario123@gmail.com', false, 'usuario', '2345678');
