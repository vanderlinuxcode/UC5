package view;

import controller.ClienteController;
import model.Cliente;
import java.util.Scanner;

public class ClienteView {
	private ClienteController controller;
	private Scanner scanner;
	
	public ClienteView() {
		controller = new ClienteController();
		scanner = new Scanner(System.in);
	}
	
	public void exibirMenu() {
		int opcao;
		do {
			System.out.println("--- Sistema de Gerenciamento de Clientes ---");
			System.out.println("1. Cadastrar Cliente");
			System.out.println("2. Listar Clientes");
			System.out.println("3. Atualizar Cliente");
			System.out.println("4. Excluir Cliente");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine();
			
			switch (opcao) {
			case 1 -> cadastrarCliente();
			case 2 -> controller.listarClientes();
			case 3 -> atualizarCliente();
			case 4 -> excluirCliente();
			case 0 -> System.out.println("Encerrando o sistema...");
			default -> System.out.println("Opção inválida.");
			}
		} while (opcao != 0);
	}
	
	private void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer, evita gerar espaço no próximo atributo
        System.out.print("Email: ");        
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        Cliente cliente = new Cliente(0, nome, idade, email, telefone, cpf);
        controller.cadastrarCliente(cliente);
    }

    private void atualizarCliente() {
        System.out.print("ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova idade: ");
        int idade = scanner.nextInt();
        System.out.print("Novo email: ");
        String email = scanner.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Novo CPF: ");
        String cpf = scanner.nextLine();
        Cliente cliente = new Cliente(id, nome, idade, email, telefone, cpf);
        controller.atualizarCliente(cliente);
    }

    private void excluirCliente() {
        System.out.print("ID do cliente a excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.excluirCliente(id);
    }
}