package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.FuncionarioModel;
import model.UsuarioModel;

public class FuncionarioDAO {
    private Connection conn;

    public FuncionarioDAO(Connection conn) {
        this.conn = conn;
    }

    public FuncionarioModel buscarPorId(int idUsuario) throws SQLException {
        String sql = "SELECT f.idFuncionario, f.cargo, u.nome, u.email, u.senha, u.cpf, u.tipo " +
                     "FROM funcionario f " +
                     "JOIN usuario u ON f.idFuncionario = u.idUsuario " +
                     "WHERE f.idFuncionario = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idUsuario);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            FuncionarioModel f = new FuncionarioModel();
            f.setIdFuncionario(rs.getInt("idFuncionario"));
            f.setCargo(rs.getString("cargo"));

            UsuarioModel u = new UsuarioModel();
            u.setIdUsuario(idUsuario);
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setCpf(rs.getString("cpf"));
            u.setTipo(rs.getString("tipo"));

            f.setUsuario(u); // ✅ associa o usuário ao funcionário

            return f;
        }

        return null;
    }

}
