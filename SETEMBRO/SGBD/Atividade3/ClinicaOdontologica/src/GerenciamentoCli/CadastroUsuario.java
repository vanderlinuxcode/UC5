package GerenciamentoCli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ArrayList;

public class CadastroUsuario {

	public static void cadastrarUser(String nome, String email, String senha, String telefone) {
		Connection conn = Conexao.conectar();

		if (conn != null) {
			try {
				// Verifica se o e-mail já existe
				if (emailExiste(conn, email)) {
					System.out.printf("\n  E-mail *%s* já cadastrado.",email);
					conn.close();
					return;
				}

				// SQL para inserir dados
				String sql = "INSERT INTO usuarios (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";
				String senhaCriptografada = criptografarSenha(senha);
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, nome);
				stmt.setString(2, email);
				stmt.setString(3, senhaCriptografada);
				stmt.setString(4, telefone);

				int rowsAffected = stmt.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Cadastro realizado com sucesso!");
				} else {
					System.out.println("Erro ao realizar cadastro.");
				}

				stmt.close();
				conn.close();

			} catch (SQLException infor) {
				System.err.println("Erro ao inserir dados: " + infor.getMessage());
			}
		}
	}
		public static String criptografarSenha(String senha) {
		    try {
		        MessageDigest md = MessageDigest.getInstance("SHA-256");
		        byte[] hashBytes = md.digest(senha.getBytes());

		        StringBuilder sb = new StringBuilder();
		        for (byte b : hashBytes) {
		            sb.append(String.format("%02x", b));
		        }

		        return sb.toString();

		    } catch (NoSuchAlgorithmException infor) {
		        System.err.println("Erro ao criptografar senha: " + infor.getMessage());
		        return null;
		    }
		}
	
	public static boolean emailExiste(Connection conn, String email) {
	    try {
	        String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, email);
	        ResultSet rs = stmt.executeQuery();

	        rs.next();
	        int count = rs.getInt(1);

	        rs.close();
	        stmt.close();

	        return count > 0;

	    } catch (SQLException infor) {
	        System.err.println("Erro ao verificar e-mail: " + infor.getMessage());
	        return false;
	    }
	}


	public static List<Object[]> listarUsuariosParaTabela() {
	    List<Object[]> lista = new ArrayList<>();
	    Connection conn = Conexao.conectar();

	    if (conn != null) {
	        try {
	            String sql = "SELECT id, nome, email, senha, telefone FROM usuarios";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();

	            System.out.println("\n\n  Usuários cadastrados:");

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String nome = rs.getString("nome");
	                String email = rs.getString("email");
	                String senha = rs.getString("senha");
	                String telefone = rs.getString("telefone");
	                lista.add(new Object[]{false, id, nome, email, senha, telefone});
                	System.out.println(id + "- " + nome + " | " + email + " | " + senha + " | " + telefone );
	            }
	            

	            rs.close();
	            stmt.close();
	            conn.close();

	        } catch (SQLException infor) {
	            System.err.println("Erro ao listar usuários: " + infor.getMessage());
	        }
	    }

	    return lista;
	}
	
	public static void main(String[] args) {
		// Exemplo de cadastro
		//cadastrarUser("Izabelly Reis", "belinha1@gmail.com", "123bela", "(61) 986542322");
		//listarUsuarios();
		listarUsuariosParaTabela();
	}
}
