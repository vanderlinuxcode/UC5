package dao;

import model.Usuario;
import java.sql.*;

public class AutenticacaoDAO {

    public Usuario autenticar(String login, String senha) {
        String sql = "SELECT * FROM Usuario WHERE Login = ? AND Senha = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha); // Idealmente, use hash de senha
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("Id_Usuario"));
                u.setLogin(rs.getString("Login"));
                u.setSenha(rs.getString("Senha"));
                u.setTipo(rs.getString("Tipo"));
                return u;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autenticar usuário: " + e.getMessage(), e);
        }
        return null;
    }
}
