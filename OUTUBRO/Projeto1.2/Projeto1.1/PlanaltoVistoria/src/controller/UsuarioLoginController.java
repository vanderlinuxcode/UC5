package controller;

import dao.FuncionarioDAO;
import dao.UsuarioDAO;
import model.FuncionarioModel;
import model.UsuarioModel;
import util.SegurancaUtils;
import java.sql.Connection;
import java.sql.SQLException;

public class UsuarioLoginController {
    private UsuarioDAO usuarioDAO;
    private FuncionarioDAO funcionarioDAO;
    private PainelController painelController;
    private UsuarioModel usuarioLogado;

    public UsuarioLoginController(Connection conn) {
        this.usuarioDAO = new UsuarioDAO(conn);
        this.funcionarioDAO = new FuncionarioDAO(conn);
        this.painelController = new PainelController(conn); // ✅ inicializa o painel
    }

    /**
     * Login via terminal ou backend
     */
    public void login(String cpf, String senhaDigitada) throws SQLException {
        String senhaCriptografada = SegurancaUtils.criptografarSenha(senhaDigitada);
        UsuarioModel usuario = usuarioDAO.autenticar(cpf, senhaCriptografada);

        if (usuario == null) {
            System.out.println("❌ CPF ou senha incorretos.");
            return;
        }

        if (!usuario.getTipo().equals("FUNCIONARIO")) {
            System.out.println("🚫 Apenas funcionários podem acessar o sistema.");
            return;
        }

        redirecionarFuncionario(usuario);
    }

    /**
     * Redireciona para o painel com base no cargo
     */
    private void redirecionarFuncionario(UsuarioModel usuario) throws SQLException {
        FuncionarioModel funcionario = funcionarioDAO.buscarPorId(usuario.getIdUsuario());

        if (funcionario == null) {
            System.out.println("⚠️ Funcionário não encontrado ou sem cargo definido.");
            return;
        }

        // ✅ associa o usuário ao funcionário (caso o DAO não faça isso)
        funcionario.setUsuario(usuario);

        this.usuarioLogado = usuario;
        painelController.redirecionarFuncionario(funcionario);
    }

    /**
     * Login via interface gráfica
     */
    public boolean loginGUI(String cpf, String senhaDigitada) {
        try {
            String senhaCriptografada = UsuarioDAO.criptografarSenha(senhaDigitada);
            UsuarioModel usuario = usuarioDAO.autenticar(cpf, senhaCriptografada);

            if (usuario != null && usuario.getTipo().equals("FUNCIONARIO")) {
                FuncionarioModel funcionario = funcionarioDAO.buscarPorId(usuario.getIdUsuario());

                if (funcionario == null) {
                    System.out.println("⚠️ Funcionário não encontrado.");
                    return false;
                }

                funcionario.setUsuario(usuario); // ✅ garante que o painel tenha os dados completos
                this.usuarioLogado = usuario;
                painelController.redirecionarFuncionario(funcionario);

                return true;
            } else {
                System.out.println("🚫 Acesso negado: apenas funcionários podem logar.");
            }

        } catch (Exception e) {
            System.out.println("Erro na autenticação GUI: " + e.getMessage());
        }

        return false;
    }

    /**
     * Retorna o usuário logado para uso em outras telas
     */
    public UsuarioModel getUsuarioLogado() {
        return usuarioLogado;
    }
}
