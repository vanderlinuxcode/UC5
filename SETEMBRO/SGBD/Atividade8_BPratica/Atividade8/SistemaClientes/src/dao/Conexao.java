
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static final String URL = "jdbc:mysql://172.16.9.133:3306/clientes_db";
	private static final String USUARIO = "root";
	private static final String SENHA = "linux";

	public static Connection conectar() throws SQLException {
		try {
			// System.out.println("Conectado com sucesso");
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (SQLException e) {
			//System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
	        throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
			//return null;
		}
	}

	public static boolean testarConexao() {
		try (Connection conn = conectar()) {
			return conn != null && !conn.isClosed();
		} catch (SQLException e) {
			return false;
		}
	}
}