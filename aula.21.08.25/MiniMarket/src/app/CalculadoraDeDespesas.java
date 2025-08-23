package app;

import java.util.Scanner;

public class CalculadoraDeDespesas {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double totalDespesas = 0;
        int contador = 0;
        double media = 0;
        double restante = 0;

        System.out.println("Bem-vindo(a) ao Gerenciador de Despesas Mensais!");
        System.out.print("Informe quanto você tem disponível para gastar: R$ ");
        double meuDinheiro = scanner.nextDouble();
        restante = meuDinheiro;

        System.out.println("\nDigite o valor de cada despesa.");
        System.out.println("Quando terminar, digite 0 para encerrar.\n");

        double valorDespesa = -1; // Inicializa com valor diferente de 0;

        while (valorDespesa != 0) {
            System.out.printf("Saldo disponível: R$ %.2f\n", restante);
            System.out.print("Digite o valor da despesa (ou 0 para finalizar): R$ ");
            valorDespesa = scanner.nextDouble();

            if (valorDespesa == 0) {
                // não faz nada, apenas sai do loop na próxima verificação
            } else if (valorDespesa < 0) {
                System.out.println("Valor inválido. Por favor, digite um valor positivo.\n");
            } else if (valorDespesa > restante) {
                System.out.println("Você não tem grana suficiente para essa despesa!\n");
            } else {
                totalDespesas += valorDespesa;
                contador++;
                restante = meuDinheiro - totalDespesas;
            }
        }

        System.out.println("\n---------------------------------------");
        if (contador > 0) {
            media = totalDespesas / contador;
            System.out.printf("Você registrou %d despesa(s).\n", contador);
            System.out.printf("Total gasto: R$ %.2f\n", totalDespesas);
            System.out.printf("Média das despesas: R$ %.2f\n", media);
            System.out.printf("Saldo restante: R$ %.2f\n", restante);
        } else {
            System.out.println("Nenhuma despesa foi registrada.");
        }
        System.out.println("---------------------------------------");

        scanner.close();
    }
}
