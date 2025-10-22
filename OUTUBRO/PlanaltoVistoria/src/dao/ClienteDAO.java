package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cliente;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    // Método para localizar cliente no sistema
    public Cliente buscarPorcpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("IdCliente"));
            cliente.setNome(rs.getString("Nome"));
            cliente.setcpf(rs.getString("cpf"));
            cliente.setTelefone(rs.getString("Telefone"));
            cliente.setEmail(rs.getString("Email"));
            return cliente;
        }
        return null;
    }

    //Método para inserir cliente no sistema
    public int inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (Nome, cpf, Telefone, Email) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getcpf());
        stmt.setString(3, cliente.getTelefone());
        stmt.setString(4, cliente.getEmail());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }
    
    //Método para excluir cliente no sistema
    public boolean excluir(int idCliente) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE IdCliente = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idCliente);
        return stmt.executeUpdate() > 0;
}
    
    //Método buscar por id para validar acesso ****
    public Cliente buscarPorId(int idCliente) {
        String sql = "SELECT * FROM Cliente WHERE IdCliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("IdCliente"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setcpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("Telefone"));
                cliente.setEmail(rs.getString("Email"));
                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
