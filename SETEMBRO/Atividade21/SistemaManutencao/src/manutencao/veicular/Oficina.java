package manutencao.veicular;

import java.util.ArrayList;
import java.util.Scanner;

public class Oficina {

    public static void main(String[] args) {

        int opcao = 0;
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> veiculosEmManutencao = new ArrayList<>();

        String[] servicos = {"Troca de óleo", "Alinhamento e Balanceamento", "Revisão Geral", "Troca de Pneus"};
        double[] precos = {150.00, 120.00, 350.00, 400.00};

        while (opcao != 3) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Novo Orçamento");
            System.out.println("2. Ver Veículos em Manutenção");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome quebra de linha

            if (opcao == 1) {
                System.out.println("\n--- Novo Orçamento ---");
                System.out.print("Informe o modelo do veículo: ");
                String modeloVeiculo = scanner.nextLine();

                double valorTotal = 0.0;

                System.out.println("\nServiços Disponíveis:");
                for (int i = 0; i < servicos.length; i++) {
                    System.out.println((i + 1) + ". " + servicos[i] + " - R$ " + precos[i]);
                }

                System.out.print("Quantos serviços você deseja adicionar? ");
                int numServicos = scanner.nextInt();

                for (int i = 0; i < numServicos; i++) {
                    System.out.print("Digite o número do serviço " + (i + 1) + ": ");
                    int escolhaServico = scanner.nextInt();
                   
                    if (escolhaServico > 0 && escolhaServico <= servicos.length) {
                    	System.out.println(servicos[escolhaServico - 1]); // ---> Lógica reaproveitável escolhaServico
                        valorTotal += precos[escolhaServico - 1];
                    } else {
                        System.out.println("Opção inválida. Serviço não adicionado.");
                    }
                }
                if (valorTotal > 500.00) {
                    double desconto = valorTotal * 0.10;
                    valorTotal -= desconto;
                    System.out.println("\nDesconto de 10% aplicado! (R$ " + desconto + ")");
                }
                System.out.println("O valor total do orçamento para o " + modeloVeiculo + " é: R$ " + valorTotal);
                veiculosEmManutencao.add(modeloVeiculo);

            } else if (opcao == 2) {
                System.out.println("\n--- Veículos em Manutenção ---");
                if (veiculosEmManutencao.isEmpty()) {
                    System.out.println("Nenhum veículo em manutenção no momento.");
                } else {
                    for (String veiculo : veiculosEmManutencao) {
                        System.out.println("- " + veiculo);
                    }
                }

            } else if (opcao == 3) {
                System.out.println("Sistema encerrado. Obrigado!");

            } else {
                System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }

        scanner.close();
    }
}
