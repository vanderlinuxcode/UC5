# GerenciamentoCli

Sistema desktop em Java para **cadastro, listagem e exclusão de usuários**, com interface gráfica (`Swing`) e persistência em banco de dados MySQL.

---

## 📦 Estrutura do Projeto

```plaintext
GerenciamentoCli/
├── CadastroUsuario.java        # Lógica de cadastro, listagem e exclusão de usuários
├── TelaCadastroUsuario.java    # Interface gráfica principal para cadastro e visualização
├── TelaExclusaoUsuarios.java   # Interface alternativa para exclusão com checkbox (opcional)
└── Conexao.java                # Gerenciador de conexão com banco de dados MySQL
```
---

## 🛠️ Requisitos

- openjdk 17.0.16
- MySQL Server (local ou remoto)
- Driver JDBC (`mysql-connector-java`)
- IDE utilizada (Eclipse)

---

## ⚙️ Configuração do Banco de Dados


## 1. Criação do banco:

Configuração do Servidor SGBD Mysql;

Linux Desktop Ubuntu 24.04 rodando em VMware® Workstation 17 Pro;

Estação Host Linux Rocky 9;

IDE de Desenvolvimento Eclipse Version: 2025-06 (4.36.0)

Build id: 20250605-1316


CREATE DATABASE cadastro_db;



## 2. Criação da tabela:

CREATE TABLE usuarios (

    id INT AUTO_INCREMENT PRIMARY KEY,   
    
    nome VARCHAR(100),
    
    email VARCHAR(100) UNIQUE,
    
    senha VARCHAR(100),
    
    telefone VARCHAR(20)
    
);

## 3. Configuração da conexão com o banco, Conexao.java:

private static final String URL = "jdbc:mysql://<IP>:3306/cadastro_db";

private static final String USUARIO = "root";

private static final String SENHA = "sua_senha";

## 4. Utilização do conector ao banco de dados MySQL
```plaintext
[vander@localhost src]$ javac -cp .:mysql-connector-j.jar GerenciamentoCli/*.java
[vander@localhost src]$ java -cp .:mysql-connector-j.jar GerenciamentoCli.TelaCadastroUsuario 
Conexão bem-sucedida!
Cadastro realizado com sucesso!
```
## 5. Diagrama de Classe Textual
```plaintext
+---------------------+
|   CadastroUsuario   |
+---------------------+
| - cadastrarUser()   |
| - listarUsuarios()  |
+---------------------+
+------------------------+
| TelaCadastroUsuario    |
+------------------------+
| - Interface Swing      |
| - Botão cadastrar      |
| - Tabela de usuários   |
+------------------------+
+------------------+
|    Conexao       |
+------------------+
| - conectar()     |
+------------------+

```

## 6. 📌 Funcionalidades

✅ Cadastro de usuários

✅ Listagem em tempo real Obs: Funcionalidade ainda em linha de comando.

✅ Interface gráfica amigável


## 7. Evoluções

🔐 Criptografia de senha

🔐 Preenchimento obrigatório de todos os campos no sistema para envio ao banco de dados.

## 8. Evoluções Futuras

🔍 Filtro de busca por nome/email

✏️ Edição de dados

📤 Exportação para CSV ou PDF

🔑 Autenticação de login

Listagem em tempo real modo gráfico


# 9. :hammer_and_wrench: Screenshot :hammer_and_wrench: 
## SGBD
<img width="1131" height="960" alt="image" src="https://github.com/user-attachments/assets/99478674-d76e-4247-852d-299ecfee66e6" />

## :computer: VMWare Servidor Linux - Banco de Dados MySQL 
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/9642092f-87ae-4767-87a6-52829f2e7e1a" />

## IDE - Eclipse
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/c14c8026-bdcd-402e-9a67-3842b539f9d6" />

## :computer: Workstation - Host
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/fd467513-b6dc-4718-9235-f31f23dafda1" />

# 10. Inspiração
https://www.songsterr.com/a/wsa/operation-ivy-the-crowd-tab-s15442

# 11. Licença
Projeto Unidade Curricular 5 Curso Técnico de Desenvolvimento em Sistemas, acadêmico, livre para modificação.

# 12. Dedicatória
✟
_"Deus do impossível, minha mãe, meu pai, meus irmãos, minha esposa, meus filhos, minha neta, minha sogra."_

:book:
Vanderli Reis

