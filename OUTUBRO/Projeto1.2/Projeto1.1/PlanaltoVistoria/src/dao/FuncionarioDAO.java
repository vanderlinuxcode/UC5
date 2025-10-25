package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.FuncionarioModel;

public class FuncionarioDAO {
    private Connection conn;

    public FuncionarioDAO(Connection conn) {
        this.conn = conn;
    }

    public FuncionarioModel buscarPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE idFuncionario = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            FuncionarioModel f = new FuncionarioModel();
            f.setIdFuncionario(rs.getInt("idFuncionario"));
            f.setCargo(rs.getString("cargo"));
            return f;
        }
        return null;
    }
}
