package view;

import controller.LoginController;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.UsuarioDAO;
import model.Usuario;

import java.sql.Connection;
import java.util.Scanner;

public class LoginViewAgendamento {
    private Scanner scanner = new Scanner(System.in);
    private UsuarioDAO usuarioDAO;
    private ClienteDAO clienteDAO;
    private FuncionarioDAO funcionarioDAO;

    public LoginViewAgendamento(Connection conn) {
        this.usuarioDAO = new UsuarioDAO(conn);
        this.clienteDAO = new ClienteDAO(conn);
        this.funcionarioDAO = new FuncionarioDAO(conn);
    }

    public void iniciarLoginParaAgendamento() {
        System.out.println("=== LOGIN PARA AGENDAMENTO DE VISTORIA ===");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = usuarioDAO.buscarPorLogin(login);

        if (usuario == null || !usuario.getSenhaUsuario().equals(senha)) {
            System.out.println("❌ Login ou senha inválidos.");
            return;
        }

        if (!"Externo".equals(usuario.getTipoUsuario())) {
            System.out.println("⚠️ Este acesso é exclusivo para clientes externos.");
            return;
        }

        // Direciona para o fluxo de agendamento
        LoginController loginController = new LoginController(usuarioDAO, clienteDAO, funcionarioDAO, null);
        loginController.autenticar(); // já trata o tipo "Externo" e chama AgendamentoView
    }
}
