package controller;

import dao.AutenticacaoDAO;
import model.Usuario;

public class AutenticacaoController {
    private final AutenticacaoDAO dao;

    public AutenticacaoController() {
        this.dao = new AutenticacaoDAO();
    }

    public Usuario autenticar(String login, String senha) throws Exception {
        if (login == null || login.isBlank()) {
            throw new Exception("Login não pode ser vazio.");
        }

        if (senha == null || senha.isBlank()) {
            throw new Exception("Senha não pode ser vazia.");
        }

        Usuario usuario = dao.autenticar(login.trim(), senha.trim());
        if (usuario == null) {
            throw new Exception("Login ou senha inválidos.");
        }

        return usuario;
    }
}
