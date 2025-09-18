# 🧾 Sistema de Gerenciamento de Clientes - Java MVC

Este projeto é uma aplicação Java que implementa o padrão arquitetural **MVC (Model-View-Controller)**, com persistência de dados em banco MySQL. O sistema permite **cadastrar, listar, atualizar e excluir clientes**, utilizando uma estrutura modular e organizada.

---

## 🧠 Arquitetura MVC

O projeto está dividido em cinco pacotes principais:

| Pacote      | Responsabilidade                                                                 |
|-------------|-----------------------------------------------------------------------------------|
| `model`     | Representa os dados da aplicação (ex: classe `Cliente`)                          |
| `view`      | Interface com o usuário via terminal (entrada e saída de dados)                  |
| `controller`| Lógica de negócio e validações                                                   |
| `dao`       | Acesso ao banco de dados (CRUD via JDBC)                                         |
| `main`      | Ponto de entrada da aplicação (`Main.java`)                                      |

---

## 📦 Estrutura de Classes

### 🔹 `model.Cliente`
Classe que representa a entidade Cliente, com atributos encapsulados:
- `id`, `nome`, `idade`, `email`, `telefone`, `cpf`
- Construtores, getters e setters

### 🔹 `controller.ClienteController`
Controla a lógica de negócio:
- Valida dados antes de persistir
- Chama métodos do DAO
- Exibe mensagens de sucesso/erro

### 🔹 `view.ClienteView`
Interface via terminal:
- Menu interativo
- Entrada de dados via `Scanner`
- Chama métodos do controller

### 🔹 `dao.ClienteDAO`
Responsável pelas operações com o banco:
- `inserir`, `listar`, `atualizar`, `excluir`
- Utiliza `PreparedStatement` e `ResultSet`

### 🔹 `dao.Conexao`
Classe utilitária para conectar ao banco MySQL:
```java
URL: jdbc:mysql://172.16.9.xxx:3306/clientes_db
Usuário: root
Senha: ----

