package controller;

import java.sql.Connection;

import javax.swing.SwingUtilities;

import model.FuncionarioModel;
import view.PainelGerenteView;

public class PainelController {
	private final Connection conn;

	public PainelController(Connection conn) {
		this.conn = conn;
	}
	
	public Connection getConn() {
		return conn;
	}

	public void redirecionarFuncionario(FuncionarioModel funcionario) {
		if (funcionario == null) {
			System.out.println("⚠️ Funcionário não encontrado.");
			return;
		}
		
		SwingUtilities.invokeLater(() -> {
	        new PainelGerenteView(funcionario).setVisible(true);
	    });
		
		switch (funcionario.getCargo()) {
		case "VISTORIADOR":
			System.out.println("🔧 Painel do Vistoriador");
			// new PainelVistoriadorView().setVisible(true);
			break;
		case "CONTABIL":
			System.out.println("📊 Painel do Contábil");
			// new PainelContabilView().setVisible(true);
			break;
		case "GERENTE":
			System.out.println("🧭 Painel do Gerente");
			// new PainelGerenteView().setVisible(true);
			break;
		default:
			System.out.println("⚠️ Cargo não reconhecido.");
		}
	}
}
