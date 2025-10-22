package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Funcionario;

public class FuncionarioDAO {
    private Connection conn;

    public FuncionarioDAO(Connection conn) {
        this.conn = conn;
    }

    public Funcionario buscarPorcpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM Funcionario WHERE cpf = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(rs.getInt("IdFuncionario"));
            funcionario.setNome(rs.getString("Nome"));
            funcionario.setcpf(rs.getString("cpf"));
            funcionario.setCargo(rs.getString("Cargo"));
            funcionario.setTelefone(rs.getString("Telefone"));
            return funcionario;
        }
        return null;
    }

    public int inserir(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionario (Nome, cpf, Cargo, Telefone) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getcpf());
        stmt.setString(3, funcionario.getCargo());
        stmt.setString(4, funcionario.getTelefone());
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }
    public Funcionario buscarPorId(int idFuncionario) {
        String sql = "SELECT * FROM Funcionario WHERE IdFuncionario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getInt("IdFuncionario"));
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setcpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("Telefone"));
              //  funcionario.setEmail(rs.getString("Email"));
                funcionario.setCargo(rs.getString("Cargo"));
                return funcionario;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar funcionário por ID: " + e.getMessage());
        }
        return null;
    }
}
