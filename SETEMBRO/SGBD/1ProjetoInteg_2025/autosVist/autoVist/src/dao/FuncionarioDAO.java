package dao;

import model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.ValidacaoException;

public class FuncionarioDAO {
    public int inserir(Funcionario funcionario) {
        String sql = "INSERT INTO Funcionario (Nome, Cpf, Cargo, Telefone) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getCargo());
            stmt.setString(4, funcionario.getTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return 0;
    }

    // 🔹 READ — Listar todos os funcionários
    public List<Funcionario> listar() throws ValidacaoException, Exception {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Funcionario";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(construirFuncionario(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar funcionários: " + e.getMessage(), e);
        }

        return lista;
    }

    // 🔹 READ — Buscar funcionário por ID
    public Funcionario buscarPorId(int idFuncionario) throws Exception {
        String sql = "SELECT * FROM Funcionario WHERE IdFuncionario = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirFuncionario(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar funcionário: " + e.getMessage(), e);
        }

        return null;
    }

    // 🔹 UPDATE — Atualizar funcionário existente
    public boolean atualizar(Funcionario funcionario) throws ValidacaoException {
        String sql = "UPDATE Funcionario SET Nome = ?, Cpf = ?, Cargo = ?, Telefone = ? WHERE IdFuncionario = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getCargo());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setInt(5, funcionario.getIdFuncionario());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionário: " + e.getMessage(), e);
        }
    }

    // 🔹 DELETE — Excluir funcionário pelo ID
    public void excluir(int idFuncionario) throws ValidacaoException {
        String sql = "DELETE FROM Funcionario WHERE IdFuncionario = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir funcionário: " + e.getMessage(), e);
        }
    }

    // 🔹 Verifica se funcionário existe
    public boolean existeFuncionario(int idFuncionario) throws ValidacaoException {
        String sql = "SELECT COUNT(*) FROM Funcionario WHERE IdFuncionario = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar existência do funcionário: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // 🔹 Método privado para construir objeto Funcionario
    private Funcionario construirFuncionario(ResultSet rs) throws ValidacaoException, Exception {
        return new Funcionario(
            rs.getInt("IdFuncionario"),
            rs.getString("Nome"),
            rs.getString("Cpf"),
            rs.getString("Cargo"),
            rs.getString("Telefone")
        );
    }
}
