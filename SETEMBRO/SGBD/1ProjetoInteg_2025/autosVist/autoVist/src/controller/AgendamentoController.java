package controller;

import java.util.List;

import dao.AgendamentoDAO;
import exception.ValidacaoException;
import model.Agendamento;

public class AgendamentoController {
    public final AgendamentoDAO dao;

    public AgendamentoController() {
        this.dao = new AgendamentoDAO();
    }

    public void agendar(Agendamento agendamento) throws ValidacaoException {
        dao.inserir(agendamento);
    }
    public List<Agendamento> buscarAgendamentosPorCliente(int idCliente) {
        return dao.buscarPorCliente(idCliente);
    }

    

    public int cadastrarAgendamento(Agendamento agendamento) {
        AgendamentoDAO dao = new AgendamentoDAO();
        try {
            return dao.inserir(agendamento);
        } catch (ValidacaoException e) {
            System.err.println("❌ Erro ao cadastrar agendamento: " + e.getMessage());
            return -1;
            
}
    }
}
