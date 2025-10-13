package dao;

import model.Veiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exception.ValidacaoException;

public class VeiculoDAO {
	
	//validação no código Java para garantir que o veículo existe no banco
	 public boolean veiculoPertenceAoCliente(int idVeiculo, int idCliente) throws ValidacaoException {
	        String sql = "SELECT COUNT(*) FROM Veiculo WHERE IdVeiculo = ? AND IdCliente = ?";

	        try (Connection conn = ConexaoDB.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setInt(1, idVeiculo);
	            stmt.setInt(2, idCliente);

	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1) > 0;
	                }
	            }

	        } catch (SQLException e) {
	            System.err.println("Erro ao verificar vínculo do veículo com cliente: " + e.getMessage());
	            e.printStackTrace();
	        }

	        return false;
	    }

	
	 public int inserir(Veiculo veiculo) throws ValidacaoException {
		    String sql = "INSERT INTO Veiculo (Placa, Marca, Modelo, Ano, Numero_chassi, IdCliente) VALUES (?, ?, ?, ?, ?, ?)";

		    try (Connection conn = ConexaoDB.conectar();
		         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

		        stmt.setString(1, veiculo.getPlaca());
		        stmt.setString(2, veiculo.getMarca());
		        stmt.setString(3, veiculo.getModelo());
		        stmt.setString(4, veiculo.getAno());
		        stmt.setString(5, veiculo.getNumeroChassi());
		        stmt.setInt(6, veiculo.getIdCliente());

		        int linhasAfetadas = stmt.executeUpdate();
		        System.out.println("✅ Veículo inserido. Linhas afetadas: " + linhasAfetadas);

		        try (ResultSet rs = stmt.getGeneratedKeys()) {
		            if (rs.next()) {
		                int idGerado = rs.getInt(1);
		                veiculo.setIdVeiculo(idGerado);
		                System.out.println("🆔 ID do veículo gerado: " + idGerado);
		                return idGerado;
		            }
		        }

		    } catch (SQLException e) {
		        System.err.println("❌ Erro ao inserir veículo: " + e.getMessage());
		        e.printStackTrace();
		    }

		    return -1;
		}

	
	public Veiculo buscarPorPlaca(String placa) throws ValidacaoException {
	    String sql = "SELECT * FROM Veiculo WHERE Placa = ?";
	    try (Connection conn = ConexaoDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, placa);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            Veiculo v = new Veiculo();
	            v.setIdVeiculo(rs.getInt("IdVeiculo"));
	            v.setPlaca(rs.getString("Placa"));
	            v.setMarca(rs.getString("Marca"));
	            v.setModelo(rs.getString("Modelo"));
	            v.setAno(rs.getString("Ano"));
	            v.setNumeroChassi(rs.getString("Numero_chassi"));
	            v.setIdCliente(rs.getInt("IdCliente"));
	            return v;
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao buscar veículo: " + e.getMessage());
	    }
	    return null;
	}

   public List<Veiculo> listar() {
        List<Veiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Veiculo";
        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                    rs.getInt("IdVeiculo"),
                    rs.getString("Placa"),
                    rs.getString("Marca"),
                    rs.getString("Modelo"),
                    rs.getString("Ano"),
                    rs.getString("Numero_chassi"),
                    rs.getInt("IdCliente")
                );
                lista.add(veiculo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos: " + e.getMessage());
        }
        return lista;
    }

    public boolean atualizar(Veiculo veiculo) throws ValidacaoException {
        String sql = "UPDATE Veiculo SET Placa = ?, Marca = ?, Modelo = ?, Ano = ?, Numero_chassi = ?, IdCliente = ? WHERE IdVeiculo = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getPlaca().trim());
            stmt.setString(2, veiculo.getMarca().trim());
            stmt.setString(3, veiculo.getModelo().trim());
            stmt.setString(4, veiculo.getAno().trim());
            stmt.setString(5, veiculo.getNumeroChassi().trim());
            stmt.setInt(6, veiculo.getIdCliente());
            stmt.setInt(7, veiculo.getIdVeiculo());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar veículo: " + e.getMessage());
            return false;
        }
    }

    public void excluir(int idVeiculo) throws ValidacaoException {
        String sql = "DELETE FROM Veiculo WHERE IdVeiculo = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVeiculo);
            stmt.executeUpdate();
            System.out.println("Veículo excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir veículo: " + e.getMessage());
        }
    }

    public Veiculo buscarPorId(int idVeiculo) throws ValidacaoException {
        String sql = "SELECT * FROM Veiculo WHERE IdVeiculo = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVeiculo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Veiculo(
                    rs.getInt("IdVeiculo"),
                    rs.getString("Placa"),
                    rs.getString("Marca"),
                    rs.getString("Modelo"),
                    rs.getString("Ano"),
                    rs.getString("Numero_chassi"),
                    rs.getInt("IdCliente")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar veículo: " + e.getMessage());
        }
        return null;
    }
    
}
