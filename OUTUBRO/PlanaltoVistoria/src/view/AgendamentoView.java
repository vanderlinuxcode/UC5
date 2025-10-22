package view;

import controller.AgendamentoController;
import dao.AgendamentoDAO;
import model.Cliente;

import java.sql.Connection;
import java.util.Scanner;

public class AgendamentoView {
    private Cliente cliente;
    private Scanner scanner = new Scanner(System.in);
    private AgendamentoController agendamentoController;

    public AgendamentoView(Cliente cliente) {
        this.cliente = cliente;
        Connection conn = dao.Conexao.getConnection();
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO(conn);
        this.agendamentoController = new AgendamentoController(agendamentoDAO);
    }

    public void exibirMenuAgendamento() {
        System.out.println("=== AGENDAMENTO DE VISTORIA ===");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.print("Informe a data desejada (AAAA-MM-DD): ");
        String data = scanner.nextLine();
        System.out.print("Informe o horário desejado (HH:MM): ");
        String hora = scanner.nextLine();

        boolean sucesso = agendamentoController.agendarVistoria(cliente, data, hora);
        if (sucesso) {
            System.out.println("✅ Vistoria agendada com sucesso!");
        } else {
            System.out.println("❌ Falha ao agendar vistoria.");
        }
    }
}
