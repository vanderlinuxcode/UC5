package dao;

import model.Pagamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    // 🔽 Inserir novo pagamento
    public void inserir(Pagamento pagamento) throws SQLException {
        String sql = "INSERT INTO Pagamento (IdCliente, IdVistoria, Valor, Data_Pagamento, Forma_Pagamento, Status_Pagamento) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, pagamento.getIdCliente());
            stmt.setInt(2, pagamento.getIdVistoria());
            stmt.setBigDecimal(3, pagamento.getValor());
            stmt.setTimestamp(4, Timestamp.valueOf(pagamento.getDataPagamento()));
            stmt.setString(5, pagamento.getFormaPagamento());
            stmt.setString(6, pagamento.getStatusPagamento());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pagamento.setIdPagamento(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir pagamento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // 📋 Listar todos os pagamentos
    public List<Pagamento> listar() throws SQLException {
        List<Pagamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pagamento";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(construirPagamento(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar pagamentos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // 🧱 Método privado para construir objeto Pagamento
    private Pagamento construirPagamento(ResultSet rs) throws SQLException {
        return new Pagamento(
            rs.getInt("IdPagamento"),
            rs.getInt("IdCliente"),
            rs.getInt("IdVistoria"),
            rs.getBigDecimal("Valor"),
            rs.getTimestamp("Data_Pagamento").toLocalDateTime(),
            rs.getString("Forma_Pagamento"),
            rs.getString("Status_Pagamento")
        );
    }
}
