package main;

import controller.UsuarioLoginController;
import controller.CadastroClienteController;
import dao.ConexaoDAO;
import dao.UsuarioDAO;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = ConexaoDAO.conectar();
             Scanner scanner = new Scanner(System.in)) {

            // Inicializa os controllers
            UsuarioDAO usuarioDAO = new UsuarioDAO(conn);
            UsuarioLoginController loginController = new UsuarioLoginController(conn, scanner);
            CadastroClienteController cadastroController = new CadastroClienteController(usuarioDAO, scanner);

            boolean executando = true;

            while (executando) {
                System.out.println("\n=== Menu Principal ===");
                System.out.println("1 - Login");
                System.out.println("2 - Cadastrar novo cliente");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");
                String opcao = scanner.nextLine();

                switch (opcao) {
                    case "1":
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Senha: ");
                        String senha = scanner.nextLine();
                        loginController.login(cpf, senha);
                        break;

                    case "2":
                        cadastroController.cadastrarNovoCliente();
                        break;

                    case "0":
                        executando = false;
                        System.out.println("👋 Encerrando o sistema. Até logo!");
                        break;

                    default:
                        System.out.println("⚠️ Opção inválida. Tente novamente.");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao iniciar o sistema: " + e.getMessage());
        }
    }
}
