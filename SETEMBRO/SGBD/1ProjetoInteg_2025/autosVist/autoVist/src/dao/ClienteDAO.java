package dao;

import model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exception.ValidacaoException;

public class ClienteDAO {

	public int inserir(Cliente cliente) throws ValidacaoException {
		validar(cliente);

		String sql = "INSERT INTO Cliente (Nome, CPF, Telefone, Email) VALUES (?, ?, ?, ?)";

		try (Connection conn = ConexaoDB.conectar();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getTelefone());
			stmt.setString(4, cliente.getEmail());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				cliente.setIdCliente(rs.getInt(1));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir cliente: " + e.getMessage(), e);
		}
		return cliente.getIdCliente();
	}
	
	public Cliente buscarPorLogin(String login) {
	    String sql = """
	        SELECT c.* FROM Cliente c
	        JOIN Usuario u ON u.IdCliente = c.IdCliente
	        WHERE u.Login = ?
	    """;

	    try (Connection conn = ConexaoDB.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, login);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                Cliente cliente = new Cliente();
	                cliente.setIdCliente(rs.getInt("IdCliente"));
	                cliente.setNome(rs.getString("Nome"));
	                cliente.setCpf(rs.getString("CPF"));
	                cliente.setTelefone(rs.getString("Telefone"));
	                cliente.setEmail(rs.getString("Email"));
	                return cliente;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}

	
	public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM Cliente WHERE CPF = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setEmail(rs.getString("Email"));
                return cliente;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente por CPF: " + e.getMessage());
        }

        return null;
    }

	public Cliente buscarPorId(int idCliente) throws ValidacaoException{
	    String sql = "SELECT * FROM Cliente WHERE IdCliente = ?";

	    try (Connection conn = ConexaoDB.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, idCliente);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            Cliente cliente = new Cliente();
	            cliente.setIdCliente(rs.getInt("IdCliente"));
	            cliente.setNome(rs.getString("Nome"));
	            cliente.setCpf(rs.getString("CPF"));
	            cliente.setTelefone(rs.getString("Telefone"));
	            cliente.setEmail(rs.getString("Email"));
	            return cliente;
	        }

	    } catch (SQLException e) {
	        throw new RuntimeException("Erro ao buscar cliente: " + e.getMessage(), e);
	    }

	    return null;
	}


					

	public List<Cliente> listar() throws ValidacaoException {
		List<Cliente> lista = new ArrayList<>();
		String sql = "SELECT * FROM Cliente";

		try (Connection conn = ConexaoDB.conectar();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("IdCliente"));
				cliente.setNome(rs.getString("Nome"));
				cliente.setCpf(rs.getString("CPF"));
				cliente.setTelefone(rs.getString("Telefone"));
				cliente.setEmail(rs.getString("Email"));
				lista.add(cliente);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
		}

		return lista;
	}

	private void validar(Cliente cliente) throws ValidacaoException {
		if (cliente.getNome() == null || cliente.getNome().isBlank()) {
			throw new ValidacaoException("Nome do cliente é obrigatório.");
		}
		if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
			throw new ValidacaoException("CPF do cliente é obrigatório.");
		}
		// Você pode adicionar validações de formato, duplicidade, etc.
	}
	public void excluirPorCpf(String cpf) {
	    String sql = "DELETE FROM Cliente WHERE Cpf = ?";

	    try (Connection conn = ConexaoDB.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, cpf);
	        stmt.executeUpdate();

	    } catch (SQLException e) {
	        System.err.println("Erro ao excluir cliente por CPF: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	public boolean atualizar(Cliente cliente) {
	    String sql = "UPDATE Cliente SET Nome = ?, Telefone = ?, Email = ? WHERE CPF = ?";

	    try (Connection conn = ConexaoDB.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, cliente.getNome());
	        stmt.setString(2, cliente.getTelefone());
	        stmt.setString(3, cliente.getEmail());
	        stmt.setString(4, cliente.getCpf());

	        int linhasAfetadas = stmt.executeUpdate();
	        return linhasAfetadas > 0;

	    } catch (SQLException e) {
	        System.err.println("Erro ao atualizar cliente: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}


}
