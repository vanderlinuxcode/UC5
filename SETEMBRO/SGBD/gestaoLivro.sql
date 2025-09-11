-- 1- Criando Arquivo /BD
create database gestaoLivro;
-- 2- Acessando o Banco
use gestaoLivro;
-- 3- Criando Tabelas
create table usuario(
	matr int primary key,
	nome varchar(100),
	end varchar(255)
);
create table livros(
	cod int primary key,
	titulo varchar(255),
	edicao varchar(50)
);
create table autor(
	cod int primary key,
    nome varchar(100),
    nacion varchar(50)
);
create table assunto(
	cod int primary key,
    nome varchar(100)
);