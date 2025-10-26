
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
    private static final String URL = "jdbc:mysql://172.16.9.133:3306/PlanaltoVistoria";
    private static final String USUARIO = "root";
    private static final String SENHA = "linux";

    public static Connection conectar() {
        try {
            //System.out.println("Conectado com sucesso");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
}