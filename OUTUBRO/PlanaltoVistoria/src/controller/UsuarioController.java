package controller;

import dao.UsuarioDAO;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import model.Usuario;
import view.LoginView;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    public UsuarioController(UsuarioDAO usuarioDAO, ClienteDAO clienteDAO, FuncionarioDAO funcionarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void realizarLogin(LoginView loginView) {
        String[] credenciais = loginView.solicitarCredenciais();
        String login = credenciais[0];
        String senha = credenciais[1];

        Usuario usuario = usuarioDAO.buscarPorLogin(login);

        if (usuario != null && usuario.getSenhaUsuario().equals(senha)) {
            loginView.exibirMensagem("Login bem-sucedido! Tipo: " + usuario.getTipoUsuario());

            if (podeAcessarAgendamento(usuario)) {
                loginView.exibirMensagem("✅ Acesso permitido à tabela Agendamento.");
                // Aqui você pode chamar a lógica de agendamento
            } else {
                loginView.exibirMensagem("❌ Acesso negado à tabela Agendamento.");
            }
        } else {
            loginView.exibirMensagem("Login ou senha inválidos.");
        }
    }

    public boolean podeAcessarAgendamento(Usuario usuario) {
        return "Externo".equalsIgnoreCase(usuario.getTipoUsuario());
    }
}
