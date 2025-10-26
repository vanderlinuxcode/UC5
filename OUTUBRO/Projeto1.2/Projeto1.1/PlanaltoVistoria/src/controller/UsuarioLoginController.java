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
	private UsuarioLoginController loginController;
	private UsuarioModel usuarioLogado;
	
	public UsuarioLoginController(Connection conn) {
		this.usuarioDAO = new UsuarioDAO(conn);
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

	public UsuarioLoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(UsuarioLoginController loginController) {
		this.loginController = loginController;
	}
	
	public UsuarioModel getUsuarioLogado() {
	    return usuarioLogado;
	}

	public boolean loginGUI(String cpf, String senhaDigitada) {
	    try {
	        String senhaCriptografada = UsuarioDAO.criptografarSenha(senhaDigitada);
	        UsuarioModel usuario = usuarioDAO.autenticar(cpf, senhaCriptografada);

	        if (usuario != null) {
	            this.usuarioLogado = usuario; // ✅ Agora 'usuario' está definido
	            return true;
	        }

	    } catch (Exception e) {
	        System.out.println("Erro na autenticação GUI: " + e.getMessage());
	    }

	    return false;
	}

}
