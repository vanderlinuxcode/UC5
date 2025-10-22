package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Agendamento;

public class AgendamentoDAO {
    private Connection conn;

    public AgendamentoDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean inserir(Agendamento agendamento) {
        String sql = "INSERT INTO Agendamento (IdCliente, Data, Horario, Status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, agendamento.getIdCliente());
            stmt.setDate(2, java.sql.Date.valueOf(agendamento.getData()));
            stmt.setTime(3, java.sql.Time.valueOf(agendamento.getHorario()));
            stmt.setString(4, agendamento.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir agendamento: " + e.getMessage());
            return false;
        }
    }
}
