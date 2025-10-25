package controller;

import dao.FuncionarioDAO;
import dao.UsuarioDAO;
import model.FuncionarioModel;
import model.UsuarioModel;
import util.SegurancaUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioLoginController {
    private UsuarioDAO usuarioDAO;
    private FuncionarioDAO funcionarioDAO;
    private PainelController painelController;
    public UsuarioLoginController(Connection conn, Scanner scanner) {
        this.usuarioDAO = new UsuarioDAO(conn);
        this.funcionarioDAO = new FuncionarioDAO(conn);
        this.painelController = new PainelController();
    }

    public void login(String cpf, String senhaDigitada) throws SQLException {
        String senhaCriptografada = SegurancaUtils.criptografarSenha(senhaDigitada);
        UsuarioModel usuario = usuarioDAO.autenticar(cpf, senhaCriptografada);

        if (usuario == null) {
            System.out.println("❌ CPF ou senha incorretos.");
            return;
       }
//
//        if (usuario.getTipo().equals("CLIENTE") && senhaTemporariaDetectada(usuario)) {
//            realizarTrocaDeSenha(usuario);
//        }

        redirecionarPorTipo(usuario);
    }

//    private boolean senhaTemporariaDetectada(UsuarioModel usuario) {
//        // Considera senha temporária como sendo de 6 dígitos numéricos
//        return usuario.getSenha().matches("^\\d{6}$");
//    }
//
//    private void realizarTrocaDeSenha(UsuarioModel usuario) throws SQLException {
//        System.out.println("🔐 Este é seu primeiro acesso. Defina uma nova senha.");
//        System.out.println("📌 A senha deve ter pelo menos 6 caracteres, com letras e números.");
//
//        String novaSenha;
//        while (true) {
//            System.out.print("Nova senha: ");
//            novaSenha = scanner.nextLine();
//
//            System.out.print("Confirme a nova senha: ");
//            String confirmacao = scanner.nextLine();
//
//            if (!novaSenha.equals(confirmacao)) {
//                System.out.println("❌ As senhas não coincidem. Tente novamente.");
//            } else if (!Validador.senhaValida(novaSenha)) {
//                System.out.println("⚠️ A senha deve conter letras e números, com no mínimo 6 caracteres.");
//            } else {
//                break;
//            }
//        }
//
//        String senhaCriptografada = SegurancaUtils.criptografarSenha(novaSenha);
//        boolean atualizada = false;
//		try {
//			atualizada = usuarioDAO.atualizarSenha(usuario.getIdUsuario(), senhaCriptografada);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        if (atualizada) {
//            System.out.println("✅ Senha atualizada com sucesso.");
//            usuario.setSenha(senhaCriptografada);
//        } else {
//            System.out.println("❌ Erro ao atualizar a senha.");
//        }
//    }

    private void redirecionarPorTipo(UsuarioModel usuario) throws SQLException {
        if (usuario.getTipo().equals("CLIENTE")) {
            painelController.redirecionarCliente();
        } else if (usuario.getTipo().equals("FUNCIONARIO")) {
            FuncionarioModel funcionario = funcionarioDAO.buscarPorId(usuario.getIdUsuario());
            painelController.redirecionarFuncionario(funcionario);
        } else {
            System.out.println("⚠️ Tipo de usuário não reconhecido.");
        }
    }
}
