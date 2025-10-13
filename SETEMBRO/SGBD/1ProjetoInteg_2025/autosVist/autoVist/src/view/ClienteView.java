package view;

import model.Agendamento;
import model.Cliente;
import java.util.List;
import java.util.Scanner;
import model.Veiculo;
import dao.AgendamentoDAO;
import dao.VeiculoDAO;
import exception.ValidacaoException;

public class ClienteView {
	private final Scanner scanner = new Scanner(System.in);

	public void exibirMenuCliente(Cliente cliente) throws ValidacaoException {
		System.out.println("Entrou no menu do cliente");

		AgendamentoView agendamentoView = new AgendamentoView();
		VeiculoView veiculoView = new VeiculoView();

		while (true) {
			System.out.println("\n=== MENU CLIENTE ===");
			System.out.println("Bem-vindo, " + cliente.getNome());
			System.out.println("1 - Visualizar meus dados");
			System.out.println("2 - Agendar serviço");
			System.out.println("3 - Cadastrar Veículo");
			System.out.println("0 - Sair");
			System.out.print("Escolha: ");
			String opcao = scanner.nextLine();

			switch (opcao) {
			case "1" -> visualizarDados(cliente);
			case "2" -> agendamentoView.agendarServico(cliente);
			case "3" -> veiculoView.exibirMenu(cliente);
			case "0" -> {
				System.out.println("Saindo...");
				return;
			}
			default -> System.out.println("Opção inválida.");
			}
		}
	}

	private void visualizarDados(Cliente cliente) throws ValidacaoException {
		System.out.println("🧾 Seus dados:");
		System.out.printf("ID: %d%nNome: %s%nCPF: %s%nTelefone: %s%nEmail: %s%n", cliente.getIdCliente(),
				cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail());

		System.out.println("\n🚗 Veículos pertencentes a " + cliente.getNome() + ":");

		VeiculoDAO veiculoDAO = new VeiculoDAO();
		List<Veiculo> veiculosDoCliente = veiculoDAO.listar(); // lista todos

		boolean encontrou = false;
		for (Veiculo veiculo : veiculosDoCliente) {
			if (veiculo.getIdCliente() == cliente.getIdCliente()) {
				System.out.printf("- Placa: %s | Modelo: %s | Marca: %s | Ano: %s%n", veiculo.getPlaca(),
						veiculo.getModelo(), veiculo.getMarca(), veiculo.getAno());
				encontrou = true;
			}
		}
		if (!encontrou) {
			System.out.println("Nenhum veículo cadastrado.");
		}
		// Agendamentos
		System.out.println("\n📅 Agendamentos de " + cliente.getNome() + ":");
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		List<Agendamento> agendamentos = agendamentoDAO.buscarPorCliente(cliente.getIdCliente());

		if (agendamentos.isEmpty()) {
			System.out.println("Nenhum agendamento encontrado.");
		} else {
			for (Agendamento ag : agendamentos) {
				Veiculo veiculo = veiculoDAO.buscarPorId(ag.getIdVeiculo());
				String placa = (veiculo != null) ? veiculo.getPlaca() : "Desconhecida";

				System.out.printf("- ID: %d | Placa: %s | Data: %s | Serviço: %s | Pago: %s%n", ag.getIdAgendamento(),
						placa, ag.getDataAgendamento().toLocalDate(), ag.getTipoServico(),
						ag.isPagamentoRealizado() ? "Sim" : "Não");
			}
		}
	}
}
