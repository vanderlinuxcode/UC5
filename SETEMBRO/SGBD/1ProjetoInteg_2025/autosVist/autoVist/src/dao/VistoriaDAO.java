package dao;

import model.Vistoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VistoriaDAO {

    // ➕ Inserir nova vistoria
    public void inserir(Vistoria vistoria) {
        String sql = "INSERT INTO Vistoria (Id_Funcionario, Id_Agendamento, Data_vistoria, Itens_Verificados, Observacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, vistoria.getIdFuncionario());
            stmt.setInt(2, vistoria.getIdAgendamento());
            stmt.setTimestamp(3, Timestamp.valueOf(vistoria.getDataVistoria()));
            stmt.setString(4, vistoria.getItensVerificados());
            stmt.setString(5, vistoria.getObservacao());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    vistoria.setIdVistoria(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir vistoria: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 📋 Listar todas as vistorias
    public List<Vistoria> listar() {
        List<Vistoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM Vistoria";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(construirVistoria(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar vistorias: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // 🔍 Buscar vistoria por ID
    public Vistoria buscarPorId(int idVistoria) {
        String sql = "SELECT * FROM Vistoria WHERE Id_Vistoria = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVistoria);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirVistoria(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar vistoria por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // 🧱 Método privado para construir objeto Vistoria
    private Vistoria construirVistoria(ResultSet rs) throws SQLException {
        return new Vistoria(
            rs.getInt("Id_Vistoria"),
            rs.getInt("Id_Funcionario"),
            rs.getInt("Id_Agendamento"),
            rs.getTimestamp("Data_vistoria").toLocalDateTime(),
            rs.getString("Itens_Verificados"),
            rs.getString("Observacao")
        );
    }
}
