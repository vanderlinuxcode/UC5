package view;

import controller.VeiculoController;
import exception.ValidacaoException;
import model.Cliente;

import java.util.Scanner;

public class VeiculoView {
    private final Scanner scanner = new Scanner(System.in);
    private final VeiculoController veiculoController;

    public VeiculoView() {
        try {
            this.veiculoController = new VeiculoController();
        } catch (ValidacaoException e) {
            throw new RuntimeException("Erro ao inicializar VeiculoController: " + e.getMessage());
        }
    }

    public void exibirMenu(Cliente cliente) {
        System.out.println("Entrou no menu Cadastrar Veículo");

        while (true) {
            System.out.println("\n=== MENU VEÍCULO ===");
            System.out.println("1 - Cadastrar veículo");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> cadastrarVeiculoParaCliente(cliente);
                case "0" -> {
                    System.out.println("Retornando ao menu principal...");
                     return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public void cadastrarVeiculoParaCliente(Cliente cliente) {
        System.out.println("\n=== Cadastro de Veículo ===");

        System.out.print("Placa (formato AAA1234): ");
        String placa = scanner.nextLine();

        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Ano (formato 4 dígitos): ");
        String ano = scanner.nextLine();

        System.out.print("Número do Chassi (17 caracteres): ");
        String numeroChassi = scanner.nextLine();

        try {
            veiculoController.cadastrarVeiculo(placa, marca, modelo, ano, numeroChassi, cliente.getIdCliente());
            System.out.println("✅ Veículo cadastrado com sucesso!");
            
        } catch (ValidacaoException e) {
            System.out.println("❌ Erros de validação:");
            e.getErros().forEach((campo, erro) -> System.out.println(" - " + campo + ": " + erro));
        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar veículo: " + e.getMessage());
        }
    }
}
