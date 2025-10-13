package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import exception.ValidacaoException;
import model.Usuario;



public class UsuarioDAO {
	
	    public void inserirUsuarioFuncionario(String login, String senha, String tipo, int idFuncionario) {
	        String sql = "INSERT INTO Usuario (Login, Senha, Tipo, IdCliente) VALUES (?, ?, ?, NULL)";
	        try (Connection conn = ConexaoDB.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, login);
				stmt.setString(2, senha);
	            stmt.setString(3, tipo); // "funcionario" ou "gerente"
	            stmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	


    public void cadastrar(Usuario usuario) throws ValidacaoException {
        validar(usuario);

        String sql = "INSERT INTO Usuario (Login, Senha, Tipo, IdCliente) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getTipo());

            if (usuario.getIdCliente() != null) {
                stmt.setInt(4, usuario.getIdCliente());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar usuário: " + e.getMessage(), e);
        }
    }

    public Usuario buscarPorLogin(String login) throws ValidacaoException{
        String sql = "SELECT * FROM Usuario WHERE Login = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setLogin(rs.getString("Login"));
                usuario.setSenha(rs.getString("Senha"));
                usuario.setTipo(rs.getString("Tipo"));
                usuario.setIdCliente(rs.getInt("IdCliente"));
                return usuario;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário: " + e.getMessage(), e);
        }

        return null;
    }

    public Usuario autenticar(String login, String senha) throws ValidacaoException {
        String sql = "SELECT * FROM Usuario WHERE Login = ? AND Senha = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setLogin(rs.getString("Login"));
                usuario.setSenha(rs.getString("Senha"));
                usuario.setTipo(rs.getString("Tipo"));
                usuario.setIdCliente(rs.getInt("IdCliente"));
                return usuario;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autenticar usuário: " + e.getMessage(), e);
        }

        return null;
    }

    private void validar(Usuario usuario) throws ValidacaoException {
        if (usuario.getLogin() == null || usuario.getLogin().isBlank()) {
            throw new ValidacaoException("Login é obrigatório.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new ValidacaoException("Senha é obrigatória.");
        }
        if (usuario.getTipo() == null || usuario.getTipo().isBlank()) {
            throw new ValidacaoException("Tipo de usuário é obrigatório.");
        }
    }

}
