package dao;

import controller.Usuario;

public abstract class UsuarioDAO {
    public abstract Usuario buscarPorUsuario(String login);
}
