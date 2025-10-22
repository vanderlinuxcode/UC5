package view;

import java.sql.Connection;

import controller.UsuarioController;
import dao.ClienteDAO;
import dao.Conexao;
import dao.FuncionarioDAO;
import dao.UsuarioDAO;

public class Main {
    public static void main(String[] args) {
        Connection conn = Conexao.getConnection(); // sua classe de conexão
        UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
        ClienteDAO clienteDAO = new ClienteDAO(conn);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(conn);

        UsuarioController usuarioController = new UsuarioController(usuarioDAO, clienteDAO, funcionarioDAO);
        LoginView loginView = new LoginViewConsole();

        usuarioController.realizarLogin(loginView);
    }
}
