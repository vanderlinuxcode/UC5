package GerenciamentoCli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://172.16.9.133:3306/cadastro_db";  // URL de conexão com o banco externo VM Ubuntu
    private static final String USUARIO = "root";  // Usuário padrão do MySQL (no XAMPP)
    private static final String SENHA = "linux";  // Senha (normalmente em branco no XAMPP)

    public static Connection conectar() {
        try {
            // Estabelecendo a conexão
            Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
           System.out.println("\n\nConexão bem-sucedida!");
            return conn;
        } catch (SQLException infor) {
            System.err.println("Erro de conexão: " + infor.getMessage());
            return null;
        }
    }
}