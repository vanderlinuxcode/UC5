package dao;

import model.UsuarioModel;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import exception.CadastroException;


public class UsuarioDAO {
	private Connection conn;
	private UsuarioModel  usuarioLogado;

	public UsuarioDAO(Connection conn) {
		this.conn = conn;
	}

	public UsuarioModel autenticar(String cpf, String senha) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE cpf = ? AND senha = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, cpf.trim());
		stmt.setString(2, senha.trim());
		System.out.println("DEBUG SQL: cpf=" + cpf + ", senha=" + senha);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			UsuarioModel u = new UsuarioModel();
			u.setIdUsuario(rs.getInt("idUsuario"));
			u.setNome(rs.getString("nome"));
			u.setEmail(rs.getString("email"));
			u.setSenha(rs.getString("senha"));
			u.setCpf(rs.getString("cpf"));
			u.setTipo(rs.getString("tipo"));
			return u;
		}
		return null;
	}

	public boolean atualizarSenha(int idUsuario, String novaSenha) throws SQLException {
		String sql = "UPDATE usuario SET senha = ? WHERE idUsuario = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, novaSenha);
		stmt.setInt(2, idUsuario);
		int linhasAfetadas = stmt.executeUpdate();
		return linhasAfetadas > 0;
	}

	public String verificarSenhaAtual(int idUsuario) throws SQLException {
		String sql = "SELECT senha FROM usuario WHERE idUsuario = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idUsuario);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			return rs.getString("senha");
		}
		return null;
	}

	public boolean cpfExiste(String cpf) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM usuario WHERE cpf = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, cpf);
	    ResultSet rs = stmt.executeQuery();
	    if (rs.next()) {
	        return rs.getInt(1) > 0;
	    }
	    return false;
	}

	public boolean cadastrarCliente(UsuarioModel cliente) throws SQLException, CadastroException {
	    if (cpfExiste(cliente.getCpf())) {
	        throw new CadastroException("CPF já cadastrado: " + cliente.getCpf());
	    }

	    if (emailExiste(cliente.getEmail())) {
	        throw new CadastroException("E-mail já consta no sistema: " + cliente.getEmail());
	    }

	    String sql = "INSERT INTO usuario (nome, email, senha, cpf, tipo) VALUES (?, ?, ?, ?, 'CLIENTE')";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, cliente.getNome());
	    stmt.setString(2, cliente.getEmail());
	    stmt.setString(3, cliente.getSenha());
	    stmt.setString(4, cliente.getCpf());
	    int linhasAfetadas = stmt.executeUpdate();
	    return linhasAfetadas > 0;
	}

	public boolean emailExiste(String email) throws SQLException {
	    String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, email);
	    ResultSet rs = stmt.executeQuery();
	    if (rs.next()) {
	        return rs.getInt(1) > 0;
	    }
	    return false;
	}
	
	public static String criptografarSenha(String senha) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hash = md.digest(senha.getBytes(StandardCharsets.UTF_8));
	        StringBuilder hexString = new StringBuilder();
	        for (byte b : hash) {
	            hexString.append(String.format("%02x", b));
	        }
	        return hexString.toString();
	    } catch (NoSuchAlgorithmException e) {
	        throw new RuntimeException("Erro ao criptografar senha", e);
	    }
	}
	public UsuarioModel getUsuarioLogado() {
	    return usuarioLogado;
	}
}
