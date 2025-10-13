package controller;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioController {
    private final UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void cadastrarUsuario(Usuario usuario) throws Exception {
        if (usuario == null) {
            throw new Exception("Usuário não pode ser nulo.");
        }

        if (usuario.getLogin() == null || usuario.getLogin().isBlank()) {
            throw new Exception("Login do usuário é obrigatório.");
        }

        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new Exception("Senha do usuário é obrigatória.");
        }

        if (usuario.getTipo() == null || usuario.getTipo().isBlank()) {
            throw new Exception("Tipo de usuário é obrigatório.");
        }

        usuarioDAO.cadastrar(usuario);
    }
}
