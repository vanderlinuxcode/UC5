package Estoque;

import java.util.Scanner;

public class SistemaDeEstoque {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Produto produto = new Produto(
            "Arroz Tipo 1",
            50,
            5.99,
            "7891234567890",
            "Alimentos"
        );

        produto.exibirInformacoes();

        // Simular venda
        System.out.print("\nDigite a quantidade para venda: ");
        int venda = scanner.nextInt();
        produto.vender(venda);
        produto.exibirEstoqueAtual();

        // Simular reposição
        System.out.print("\nDigite a quantidade para reposição: ");
        int reposicao = scanner.nextInt();
        produto.repor(reposicao);
        produto.exibirEstoqueAtual();

        scanner.close();
    }
}
