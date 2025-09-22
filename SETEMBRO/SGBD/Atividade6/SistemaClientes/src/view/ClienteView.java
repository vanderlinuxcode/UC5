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
	    String nome, email, telefone, cpf;
	    int idade;

	    // Nome obrigatório
	    do {
	        System.out.print("Nome: ");
	        nome = scanner.nextLine();
	        if (nome.isEmpty()) {
	            System.out.println("Nome é obrigatório.");
	        }
	    } while (nome.isEmpty());

	    // Idade válida (> 0)
	    do {
	        System.out.print("Idade: ");
	        try {
	            idade = Integer.parseInt(scanner.nextLine());
	            if (idade <= 0) {
	                System.out.println("⚠Idade deve ser maior que zero.");
	            }
	        } catch (NumberFormatException e) {
	            idade = 0;
	            System.out.println("⚠Idade inválida. Digite um número.");
	        }
	    } while (idade <= 0);

	    // Email com "@"
	    do {
	        System.out.print("Email: ");
	        email = scanner.nextLine();
	        if (email.isEmpty() || !email.contains("@")) {
	            System.out.println("⚠Email inválido. Deve conter '@'.");
	        }
	    } while (email.isEmpty() || !email.contains("@"));

	    // Telefone obrigatório
	    do {
	        System.out.print("Telefone: ");
	        telefone = scanner.nextLine();
	        if (telefone.isEmpty()) {
	            System.out.println("Telefone é obrigatório.");
	        }
	    } while (telefone.isEmpty());

	    // CPF com 14 caracteres
	    do {
	        System.out.print("CPF (formato xxx.xxx.xxx-xx): ");
	        cpf = scanner.nextLine();
	        if (cpf.isEmpty() || cpf.length() != 14) {
	            System.out.println("CPF deve conter 14 caracteres com pontos e traço.");
	        }
	    } while (cpf.isEmpty() || cpf.length() != 14);

	    // Criação e envio do cliente
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