package view;

import java.util.Scanner;

import dao.ClienteDAO;
import exception.ValidacaoException;
import model.Cliente;
import model.Usuario;

public class MenuPrincipalView {
    private final Scanner scanner = new Scanner(System.in);
    private final ClienteView clienteView = new ClienteView(); // instância obrigatória
    private final AgendamentoView agendamentoView = new AgendamentoView(); // instância obrigatória
    private final FuncionarioView funcionarioView = new FuncionarioView();
	private ClienteDAO clienteDAO;
	private Usuario usuario;

    Cliente cliente = clienteDAO.buscarPorLogin(usuario.getLogin());

    public void exibirMenu(Usuario usuario) throws ValidacaoException, Exception {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("Bem-vindo, " + usuario.getLogin() + "!");


        switch (usuario.getTipo().toLowerCase()) {
            case "cliente" -> menuCliente(usuario);
            case "funcionario" -> menuFuncionario(usuario);
            default -> System.out.println("Tipo de usuário desconhecido: " + usuario.getTipo());
        }
    }

    private void menuCliente(Usuario usuario) {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = clienteDAO.buscarPorCpf(usuario.getLogin());

            if (cliente != null) {
                clienteView.exibirMenuCliente(cliente); // envia cliente logado
            } else {
                System.out.println("❌ Cliente vinculado ao usuário não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados do cliente: " + e.getMessage());
        }
    }

    public void menuFuncionario(Usuario usuario) throws ValidacaoException, Exception {
        while (true) {
            System.out.println("\n--- MENU FUNCIONÁRIO ---");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar agendamentos");
            System.out.println("3 - Registrar vistoria");
            System.out.println("4 - Registrar pagamento");
            System.out.println("5 - Agendar serviço"); // nova opção
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> funcionarioView.cadastrarFuncionario();
                case "2" -> funcionarioView.listarAgendamentos();
                case "3" -> funcionarioView.registrarVistoria();
                case "4" -> funcionarioView.registrarPagamento();
                case "5" -> agendamentoView.agendarServico(cliente); // chamada do método
                case "0" -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}