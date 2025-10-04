package controller;

import dao.UsuarioDAOImpl;

public class LoginController {
    private UsuarioDAOImpl usuarioDAO;

    public LoginController() {
        this.usuarioDAO = new UsuarioDAOImpl();
    }

    public boolean autenticar(String usuario, String senha) {
        Usuario u = usuarioDAO.buscarPorUsuario(usuario);
        if (u == null) return false;
        return BCrypt.checkpw(senha, u.getSenhaCriptografada());
    }
}
