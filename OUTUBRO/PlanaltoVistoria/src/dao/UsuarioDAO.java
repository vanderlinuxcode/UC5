package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioDAO {
    private Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }

   
    // 
    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (LoginUsuario, SenhaUsuario, TipoUsuario, IdClienteUsuario, IdFuncionarioUsuario) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario.getLoginUsuario());
        stmt.setString(2, usuario.getSenhaUsuario());
        stmt.setString(3, usuario.getTipoUsuario());
        stmt.setObject(4, usuario.getIdClienteUsuario());
        stmt.setObject(5, usuario.getIdFuncionarioUsuario());
        stmt.executeUpdate();
    }
    
    // 
    public boolean excluirPorIdCliente(int idCliente) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE IdClienteUsuario = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idCliente);
        return stmt.executeUpdate() > 0;
    }
    
    public Usuario buscarPorLogin(String login) {
        String sql = "SELECT * FROM Usuario WHERE LoginUsuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdClienteUsuario(rs.getInt("IdUsuario"));
                usuario.setLoginUsuario(rs.getString("LoginUsuario"));
                usuario.setSenhaUsuario(rs.getString("SenhaUsuario"));
                usuario.setTipoUsuario(rs.getString("TipoUsuario"));
                usuario.setIdClienteUsuario(rs.getInt("IdClienteUsuario"));
                usuario.setIdFuncionarioUsuario(rs.getInt("IdFuncionarioUsuario"));
                return usuario;
            } else {
                System.out.println("Nenhum usuário encontrado com LoginUsuario: " + login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
