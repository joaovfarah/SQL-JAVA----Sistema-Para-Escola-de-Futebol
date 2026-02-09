
---+++++++++++++++++++++++++++++++++++
--CRIA AS TABELAS
---+++++++++++++++++++++++++++++++++++

--Tabela para os dados de aluno
CREATE TABLE ALUNO (
	CODALUNO	smallint NOT NULL ,
	ALUNO		varchar (40) NOT NULL ,
	SEXO		char(1) NOT NULL,
	CELULAR		char(11) NULL,
	EMAIL		varchar (40) NULL,
	CPF			char(11) NOT NULL, 
	DTNASC		date NOT NULL,
	ENDERECO	varchar(100) NOT NULL
);
go

--Tabela para os dados das matricula
CREATE TABLE MATRICULA (
	CODMATRICULA smallint NOT NULL,
	CODTURMA	 smallint NOT NULL,
	CODALUNO	 smallint NOT NULL ,
	MENSALIDADE	 numeric(8,2) NOT NULL,
	DTMATRIC	 date  NOT NULL,
	HABILIDADE   smallint NULL,
);
go

--Tabela para os dados de turma
CREATE TABLE TURMA (
	CODTURMA	  smallint NOT NULL,
	CODTREINADOR  smallint NOT NULL, 
	CODDATA   	  smallint NOT NULL,
	NOMETURMA 	  varchar(20) NOT NULL
);
go

--Tabela para os dados de data
CREATE TABLE DATA (
	CODDATA       smallint NOT NULL,
	DIADASEMANA	  varchar(30) NOT NULL
);
go

--Tabela para os dados de treinador
CREATE TABLE TREINADOR (
	CODTREINADOR  smallint NOT NULL,
	TREINADOR     varchar(30) NOT NULL, 
	CELULAR		char(11) NULL,
	EMAIL		varchar (40) NULL,
	CPF			char(11) NOT NULL,
	ENDERECO	varchar(100) NOT NULL
);
go


---+++++++++++++++++++++++++++++++++++
--CRIA AS CONSTRAINTS
---+++++++++++++++++++++++++++++++++++

--CRIA AS PKs
ALTER TABLE aluno ADD CONSTRAINT aluno_codaluno_PK PRIMARY KEY (codaluno);
go
ALTER TABLE matricula ADD CONSTRAINT matriucula_codmatricula_PK PRIMARY KEY (codmatricula) ;
go
ALTER TABLE turma ADD CONSTRAINT turma_codturma_PK PRIMARY KEY (codturma);
go
ALTER TABLE data ADD CONSTRAINT data_coddata PRIMARY KEY (coddata) ;
go
ALTER TABLE treinador ADD CONSTRAINT treinador_codtreinador PRIMARY KEY (codtreinador);
go

--CRIA A FK
ALTER TABLE matricula ADD CONSTRAINT matricula_codturma_FK FOREIGN KEY (codturma) REFERENCES turma (codturma);
go
ALTER TABLE matricula ADD CONSTRAINT matricula_codaluno_FK FOREIGN KEY (codaluno) REFERENCES aluno (codaluno);
go
ALTER TABLE turma ADD CONSTRAINT turma_codtreinador_FK FOREIGN KEY (codtreinador) REFERENCES treinador (codtreinador);
go
ALTER TABLE turma ADD CONSTRAINT turma_coddata_FK FOREIGN KEY (coddata) REFERENCES data (coddata);
go

--CRIA AS UNIQUEs
ALTER TABLE aluno ADD CONSTRAINT aluno_cpf_uq UNIQUE (cpf);
go
ALTER TABLE treinador ADD CONSTRAINT treinador_cpf_uq UNIQUE (cpf);
go

--CRIA O CHECK
ALTER TABLE aluno ADD CONSTRAINT aluno_sexo_ck CHECK (sexo = 'F' or sexo = 'M');
go
ALTER TABLE matricula ADD CONSTRAINT matricula_habilidade_ck CHECK (habilidade >= 1 or habilidade <= 5);
go
ALTER TABLE data ADD CONSTRAINT data_diadasemana_ck CHECK (diadasemana = 'segunda, quarta, sexta' or diadasemana = '
');
go

--CRIA O DEFAULT
ALTER TABLE matricula ADD CONSTRAINT matricula_habilidade_df DEFAULT (0) FOR habilidade;
go