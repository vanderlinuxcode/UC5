package view;

import controller.AgendamentoController;
import dao.VeiculoDAO;
import model.Agendamento;
import model.Cliente;
import model.Veiculo;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;

public class AgendamentoView {
	
    @SuppressWarnings("resource")
	public AgendamentoView() {
        new AgendamentoController();
        new Scanner(System.in);
    }

    public void agendarServico(Cliente cliente) {
        System.out.println("\n--- Agendamento de Serviço ---");
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
			VeiculoDAO veiculoDAO = new VeiculoDAO();
			AgendamentoController controller = new AgendamentoController(); // Certifique-se de ter essa classe

			try {
			    // Buscar veículos do cliente
			    List<Veiculo> veiculosDoCliente = veiculoDAO.listar().stream()
			        .filter(v -> v.getIdCliente() == cliente.getIdCliente())
			        .toList();

			    if (veiculosDoCliente.isEmpty()) {
			        System.out.println("❌ Você não possui veículos cadastrados.");
			        return;
			    }

			    // Exibir veículos para seleção
			    System.out.println("Selecione um veículo:");
			    for (int i = 0; i < veiculosDoCliente.size(); i++) {
			        Veiculo v = veiculosDoCliente.get(i);
			        System.out.printf("%d - %s (%s %s, Ano %s)%n", i + 1, v.getPlaca(), v.getMarca(), v.getModelo(), v.getAno());
			    }

			    System.out.print("Escolha o número do veículo: ");
			    int escolha = Integer.parseInt(scanner.nextLine());

			    if (escolha < 1 || escolha > veiculosDoCliente.size()) {
			        System.out.println("❌ Opção inválida.");
			        return;
			    }

			    Veiculo veiculoSelecionado = veiculosDoCliente.get(escolha - 1);

			    // Coletar tipo de serviço
			    System.out.print("Tipo de serviço: ");
			    String tipoServico = scanner.nextLine();

			    // Criar agendamento
			    Agendamento agendamento = new Agendamento();
			    agendamento.setIdCliente(cliente.getIdCliente());
			    agendamento.setIdVeiculo(veiculoSelecionado.getIdVeiculo());
			    agendamento.setTipoServico(tipoServico);
			    agendamento.setDataAgendamento(LocalDateTime.now());

			    // Persistir agendamento
			    int idGerado = controller.cadastrarAgendamento(agendamento);

			    if (idGerado > 0) {
			        System.out.println("\n✅ Serviço agendado com sucesso!");
			        System.out.println("📋 Detalhes do Agendamento:");
			        System.out.println("🆔 ID do Agendamento: " + idGerado);
			        System.out.println("🚗 Veículo: " + veiculoSelecionado.getPlaca());
			        System.out.println("👤 Cliente: " + cliente.getNome());
			        System.out.println("📅 Data: " + agendamento.getDataAgendamento());
			        System.out.println("🔧 Serviço: " + agendamento.getTipoServico());
			    } else {
			        System.out.println("❌ Falha ao realizar agendamento.");
			    }

			} catch (Exception e) {
			    System.out.println("❌ Erro ao agendar serviço: " + e.getMessage());
			}
		}
    }

