package dao;

import model.Cliente;
import model.Usuario;
import java.sql.*;

public class CadastroDAO {
    public void cadastrarClienteComUsuario(Cliente cliente, Usuario usuario) throws SQLException {
        String sqlCliente = "INSERT INTO Cliente (Nome, CPF, Telefone, Email) VALUES (?, ?, ?, ?)";
        String sqlUsuario = "INSERT INTO Usuario (Login, Senha, Tipo) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoDB.conectar()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS)) {
                stmtCliente.setString(1, cliente.getNome());
                stmtCliente.setString(2, cliente.getCpf());
                stmtCliente.setString(3, cliente.getTelefone());
                stmtCliente.setString(4, cliente.getEmail());
                stmtCliente.executeUpdate();

                ResultSet rs = stmtCliente.getGeneratedKeys();
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1));
                }
            }

            try (PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario)) {
                stmtUsuario.setString(1, usuario.getLogin()); // CPF como login
                stmtUsuario.setString(2, usuario.getSenha());
                stmtUsuario.setString(3, usuario.getTipo());
                stmtUsuario.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            throw new SQLException("Erro ao cadastrar cliente e usuário: " + e.getMessage(), e);
        }
    }
}


