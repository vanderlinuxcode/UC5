package view;

import controller.FuncionarioController;
import exception.ValidacaoException;
import model.Funcionario;

import java.util.List;
import java.util.Scanner;


public class FuncionarioView {
    private final FuncionarioController controller;
    private final Scanner scanner;

    public FuncionarioView() {
        this.controller = new FuncionarioController();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu(Funcionario funcionario) throws Exception {
        while (true) {
            System.out.println("\n=== MENU FUNCIONÁRIOS ===");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar funcionário");
            System.out.println("5 - Excluir funcionário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrarFuncionario();
                case "2" -> listarAgendamentos();
                case "3" -> buscarPorId();
                case "4" -> atualizar();
                case "5" -> excluir();
                case "0" -> { return; }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

   public void cadastrarFuncionario() {
        try {
            System.out.println("\n--- Cadastro de Funcionário ---");
            Funcionario funcionario = new Funcionario();

            System.out.print("Nome: ");
            funcionario.setNome(scanner.nextLine());

            System.out.print("CPF (formato XXX.XXX.XXX-XX): ");
            funcionario.setCpf(scanner.nextLine());

            System.out.print("Cargo: ");
            funcionario.setCargo(scanner.nextLine());

            System.out.print("Telefone (formato (XX) XXXXX-XXXX): ");
            funcionario.setTelefone(scanner.nextLine());

            int id = controller.cadastrarFuncionario(funcionario);
            System.out.println("✅ Funcionário cadastrado com ID: " + id);
        } catch (ValidacaoException e) {
            e.getErros().forEach((campo, erro) -> System.out.println("❌ " + campo + ": " + erro));
        }
    }

    public void listarAgendamentos() throws ValidacaoException, Exception {
        System.out.println("\n--- Lista de Funcionários ---");
        List<Funcionario> lista = controller.listarFuncionarios();
        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            lista.forEach(System.out::println);
        }
    }
    
    public void registrarVistoria() {
        System.out.println("🔍 ID do agendamento para vistoria:");
        int idAgendamento = Integer.parseInt(scanner.nextLine());

        System.out.println("📝 Relatório da vistoria:");
        String relatorio = scanner.nextLine();

        try {
            boolean sucesso = controller.registrarVistoria(idAgendamento, relatorio);

            if (sucesso) {
                System.out.println("✅ Vistoria registrada com sucesso!");
            } else {
                System.out.println("❌ Agendamento não encontrado ou erro ao registrar vistoria.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }


    public void registrarPagamento() {
        System.out.println("💳 ID do agendamento para pagamento:");
        int idAgendamento = Integer.parseInt(scanner.nextLine());

        System.out.println("💰 Valor pago:");
        double valorPago = Double.parseDouble(scanner.nextLine());

        try {
            boolean sucesso = controller.registrarPagamento(idAgendamento, valorPago);

            if (sucesso) {
                System.out.println("✅ Pagamento registrado com sucesso!");
            } else {
                System.out.println("❌ Agendamento não encontrado ou erro ao registrar pagamento.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }



    public void buscarPorId() throws Exception {
        System.out.print("\nDigite o ID do funcionário: ");
        int id = Integer.parseInt(scanner.nextLine());

        try {
            Funcionario funcionario = controller.buscarFuncionarioPorId(id);
            System.out.println("Funcionário encontrado:");
            System.out.println(funcionario);
        } catch (ValidacaoException e) {
            e.getErros().forEach((campo, erro) -> System.out.println("❌ " + campo + ": " + erro));
        }
    }

    public void atualizar() throws Exception {
        System.out.print("\nDigite o ID do funcionário a atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        try {
            Funcionario funcionario = controller.buscarFuncionarioPorId(id);

            System.out.print("Novo nome (" + funcionario.getNome() + "): ");
            funcionario.setNome(scanner.nextLine());

            System.out.print("Novo CPF (" + funcionario.getCpf() + "): ");
            funcionario.setCpf(scanner.nextLine());

            System.out.print("Novo cargo (" + funcionario.getCargo() + "): ");
            funcionario.setCargo(scanner.nextLine());

            System.out.print("Novo telefone (" + funcionario.getTelefone() + "): ");
            funcionario.setTelefone(scanner.nextLine());

            boolean atualizado = controller.atualizarFuncionario(funcionario);
            if (atualizado) {
                System.out.println("✅ Funcionário atualizado com sucesso!");
            }
        } catch (ValidacaoException e) {
            e.getErros().forEach((campo, erro) -> System.out.println("❌ " + campo + ": " + erro));
        }
    }

    public void excluir() {
        System.out.print("\nDigite o ID do funcionário a excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        try {
            controller.excluirFuncionario(id);
            System.out.println("✅ Funcionário excluído com sucesso!");
        } catch (ValidacaoException e) {
            e.getErros().forEach((campo, erro) -> System.out.println("❌ " + campo + ": " + erro));
        }
    }
}
