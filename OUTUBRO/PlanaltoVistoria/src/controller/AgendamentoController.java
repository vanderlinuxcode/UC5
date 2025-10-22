package controller;

import dao.AgendamentoDAO;
import model.Agendamento;
import model.Cliente;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendamentoController {
    private AgendamentoDAO agendamentoDAO;

    public AgendamentoController(AgendamentoDAO agendamentoDAO) {
        this.agendamentoDAO = agendamentoDAO;
    }

    public boolean agendarVistoria(Cliente cliente, String dataStr, String horaStr) {
        try {
            LocalDate data = LocalDate.parse(dataStr);
            LocalTime hora = LocalTime.parse(horaStr);

            Agendamento agendamento = new Agendamento();
            agendamento.setIdCliente(cliente.getIdCliente());
            agendamento.setData(data);
            agendamento.setHorario(hora);
            agendamento.setStatus("Agendado");

            return agendamentoDAO.inserir(agendamento);
        } catch (Exception e) {
            System.err.println("Erro ao agendar vistoria: " + e.getMessage());
            return false;
        }
    }
}
