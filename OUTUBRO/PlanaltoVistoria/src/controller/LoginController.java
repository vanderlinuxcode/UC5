package controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.UsuarioDAO;
import model.Cliente;
import model.Funcionario;
import model.Usuario;
import view.AgendamentoView;
import view.LoginView;

public class LoginController {
    private UsuarioDAO usuarioDAO;
    private ClienteDAO clienteDAO;
    private FuncionarioDAO funcionarioDAO;
    private LoginView loginView;

    public LoginController(UsuarioDAO usuarioDAO, ClienteDAO clienteDAO, FuncionarioDAO funcionarioDAO, LoginView loginView) {
        this.usuarioDAO = usuarioDAO;
        this.clienteDAO = clienteDAO;
        this.funcionarioDAO = funcionarioDAO;
        this.loginView = loginView;
    }

    public void autenticar() {
        String[] credenciais = loginView.solicitarCredenciais();
        String login = credenciais[0];
        String senha = credenciais[1];

        Usuario usuario = usuarioDAO.buscarPorLogin(login);

        if (usuario == null || !usuario.getSenhaUsuario().equals(senha)) {
            loginView.exibirMensagem("Login ou senha inválidos.");
            return;
        }

        switch (usuario.getTipoUsuario()) {
            case "Cliente":
                Cliente cliente = clienteDAO.buscarPorId(usuario.getIdClienteUsuario());
                loginView.exibirMensagem("Bem-vindo, " + cliente.getNome() + "! Acesso como Cliente.");
                Cliente cliente1 = clienteDAO.buscarPorId(usuario.getIdClienteUsuario());
                loginView.exibirMensagem("Vanderli Reis Informa:, " + cliente1.getNome() + "\nEsse sistema será o legado de vários outros.");
                // Direcionar para funcionalidades de cliente
                break;

            case "Funcionario":
                Funcionario funcionario = funcionarioDAO.buscarPorId(usuario.getIdFuncionarioUsuario());
                loginView.exibirMensagem("Bem-vindo, " + funcionario.getNome() + "! Acesso como Funcionário," + "Cargo: " + funcionario.getCargo());
                // Direcionar para funcionalidades administrativas
                break;

            case "Externo":
                Cliente externo = clienteDAO.buscarPorId(usuario.getIdClienteUsuario());
                loginView.exibirMensagem("Olá, " + externo.getNome() + "! Você pode agendar sua vistoria.");
                AgendamentoView agendamentoView = new AgendamentoView(externo);
                agendamentoView.exibirMenuAgendamento();
                break;
        }
    }

    public void autenticarSwing(String login, String senha, JFrame loginFrame) {
        Usuario usuario = usuarioDAO.buscarPorLogin(login);

        if (usuario == null || !usuario.getSenhaUsuario().equals(senha)) {
            JOptionPane.showMessageDialog(loginFrame, "Login ou senha inválidos.");
            return;
        }

        if ("Externo".equals(usuario.getTipoUsuario()) || "Funcionario".equals(usuario.getTipoUsuario())) {
            JOptionPane.showMessageDialog(loginFrame, "Bem-vindo, " + usuario.getLoginUsuario() + "! Acesso liberado." + usuario.getTipoUsuario());
            loginFrame.dispose();
            // abrir tela apropriada (ex: painel do gerente)
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Este acesso é exclusivo para usuários autorizados.");
        }

    }
}
