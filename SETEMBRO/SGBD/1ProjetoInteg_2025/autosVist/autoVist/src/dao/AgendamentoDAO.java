package dao;

import model.Agendamento;
import model.Cliente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import exception.ValidacaoException;

	public class AgendamentoDAO {
		
	    private int selecionarFuncionarioAleatorio() throws ValidacaoException {
	        String sql = "SELECT IdFuncionario FROM Funcionario ORDER BY RAND() LIMIT 1";
	        try (Connection conn = ConexaoDB.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                int idFuncionario = rs.getInt("IdFuncionario");
	                System.out.println("👷 Funcionário selecionado automaticamente: ID " + idFuncionario);
	                return idFuncionario;
	            } else {
	                throw new ValidacaoException(" Nenhum funcionário disponível para agendamento.");
	            }
	        } catch (SQLException e) {
	            throw new ValidacaoException(" Erro ao selecionar funcionário: " + e.getMessage());
	        }
	    }

	    public int inserir(Agendamento agendamento) throws ValidacaoException {
	        // Seleciona funcionário automaticamente
	        agendamento.setIdFuncionario(selecionarFuncionarioAleatorio());

	        // Validação: veículo deve pertencer ao cliente
	        VeiculoDAO veiculoDAO = new VeiculoDAO();
	        if (!veiculoDAO.veiculoPertenceAoCliente(agendamento.getIdVeiculo(), agendamento.getIdCliente())) {
	            throw new ValidacaoException(" O veículo informado não pertence ao cliente.");
	        }

	        String sql = "INSERT INTO Agendamento (IdVeiculo, IdFuncionario, IdCliente, DataAgendamento, TipoServico) VALUES (?, ?, ?, ?, ?)";

	        try (Connection conn = ConexaoDB.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	            stmt.setInt(1, agendamento.getIdVeiculo());
	            stmt.setInt(2, agendamento.getIdFuncionario());
	            stmt.setInt(3, agendamento.getIdCliente());
	            stmt.setTimestamp(4, Timestamp.valueOf(agendamento.getDataAgendamento()));
	            stmt.setString(5, agendamento.getTipoServico());

	            int linhasAfetadas = stmt.executeUpdate();
	            System.out.println("✅ Linhas afetadas: " + linhasAfetadas);

	            try (ResultSet rs = stmt.getGeneratedKeys()) {
	                if (rs.next()) {
	                    int idGerado = rs.getInt(1);
	                    agendamento.setIdAgendamento(idGerado);
	                    System.out.println("🆔 Agendamento criado com ID: " + idGerado);
	                    System.out.println("\n📋 AGENDAMENTO REALIZADO:");
	                    System.out.println("🆔 ID do Agendamento: " + agendamento.getIdAgendamento());
	                    System.out.println("🚗 ID do Veículo: " + agendamento.getIdVeiculo());
	                    System.out.println("👷 ID do Funcionário Responsável: " + agendamento.getIdFuncionario());
	                    System.out.println("👤 ID do Cliente: " + agendamento.getIdCliente());
	                    System.out.println("📅 Data do Agendamento: " + agendamento.getDataAgendamento());
	                    System.out.println("🔧 Tipo de Serviço: " + agendamento.getTipoServico());

	                    return idGerado;
	                } else {
	                    System.err.println("⚠️ Nenhum ID gerado.");
	                }
	              
	            }
	        } catch (SQLIntegrityConstraintViolationException e) {
	            System.err.println(" Erro de integridade referencial.");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.err.println(" Erro ao inserir agendamento: " + e.getMessage());
	            e.printStackTrace();
	        }

	        return -1;
	    }

	    public List<Agendamento> buscarPorCliente(int cliente) {
	        List<Agendamento> lista = new ArrayList<>();
	        String sql = "SELECT * FROM Agendamento WHERE IdCliente = ? ORDER BY DataAgendamento DESC";

	        try (Connection conn = ConexaoDB.conectar();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, cliente);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                    Agendamento ag = new Agendamento();
	                    ag.setIdAgendamento(rs.getInt("IdAgendamento"));
	                    ag.setIdVeiculo(rs.getInt("IdVeiculo"));
	                    ag.setIdFuncionario(rs.getInt("IdFuncionario"));
	                    ag.setIdCliente(rs.getInt("IdCliente"));
	                    ag.setDataAgendamento(rs.getTimestamp("DataAgendamento").toLocalDateTime());
	                    ag.setTipoServico(rs.getString("TipoServico"));
	                    ag.setRelatorioVistoria(rs.getString("RelatorioVistoria"));
	                    ag.setValorPago(rs.getDouble("ValorPago"));
	                    ag.setPagamentoRealizado(rs.getBoolean("PagamentoRealizado"));
	                    lista.add(ag);
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println(" Erro ao buscar agendamentos: " + e.getMessage());
	            e.printStackTrace();
	        }

	        return lista;
	    }

	  

    //  Atualizar agendamento
    public boolean atualizar(Agendamento agendamento) {
        String sql = "UPDATE Agendamento SET RelatorioVistoria = ?, VistoriaRealizada = ?, ValorPago = ?, PagamentoRealizado = ? WHERE IdAgendamento = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, agendamento.getRelatorioVistoria());
            stmt.setBoolean(2, agendamento.isVistoriaRealizada());
            stmt.setDouble(3, agendamento.getValorPago());
            stmt.setBoolean(4, agendamento.isPagamentoRealizado());
            stmt.setInt(5, agendamento.getIdAgendamento());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar agendamento: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    // 🔍 Buscar agendamento por ID
    public Agendamento buscarPorId(int idAgendamento) throws ValidacaoException {
        String sql = "SELECT * FROM Agendamento WHERE IdAgendamento = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAgendamento);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return construirAgendamento(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar agendamento por ID: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // 📋 Listar todos os agendamentos
    public List<Agendamento> listarTodos() throws ValidacaoException {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM Agendamento";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                agendamentos.add(construirAgendamento(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar agendamentos: " + e.getMessage());
            e.printStackTrace();
        }

        return agendamentos;
    }

     // Filtrar agendamentos por data
    public List<Agendamento> filtrarPorData(LocalDate data) throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM Agendamento WHERE DATE(DataAgendamento) = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(data));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    agendamentos.add(construirAgendamento(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao filtrar agendamentos por data: " + e.getMessage());
            e.printStackTrace();
        }

        return agendamentos;
    }

    //  Método privado para construir objeto Agendamento
    public Agendamento construirAgendamento(ResultSet rs) throws SQLException {
        Agendamento agendamento = new Agendamento();
        agendamento.setIdAgendamento(rs.getInt("IdAgendamento"));
        agendamento.setIdVeiculo(rs.getInt("IdVeiculo"));
        agendamento.setIdFuncionario(rs.getInt("IdFuncionario"));
        agendamento.setIdCliente(rs.getInt("IdCliente"));
        agendamento.setDataAgendamento(rs.getTimestamp("DataAgendamento").toLocalDateTime());
        agendamento.setTipoServico(rs.getString("TipoServico"));
        agendamento.setRelatorioVistoria(rs.getString("RelatorioVistoria"));
        agendamento.setVistoriaRealizada(rs.getBoolean("VistoriaRealizada"));
        agendamento.setValorPago(rs.getDouble("ValorPago"));
        agendamento.setPagamentoRealizado(rs.getBoolean("PagamentoRealizado"));
        return agendamento;
    }

	public List<Agendamento> buscarPorCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}      
        
}
