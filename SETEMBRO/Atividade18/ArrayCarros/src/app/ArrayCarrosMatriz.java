package app;

import java.util.Scanner;

public class ArrayCarrosMatriz {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define o número de carros
        int quantidadeCarros = 10;

        // Array bidimensional para modelo, ano e cor
        String[][] carros = new String[quantidadeCarros][4];

        // Array simples para os preços
        double[] precos = new double[quantidadeCarros];

        // Entrada de dados pelo usuário

        for (int i = 0; i < quantidadeCarros; i++) {
            System.out.println("\nCarro " + (i + 1) + ":");
            System.out.print("Marca: ");
            carros[i][0] = scanner.nextLine();
            System.out.print("Modelo: ");
            carros[i][1] = scanner.nextLine();
            System.out.print("Ano: ");
            carros[i][2] = scanner.nextLine();
            System.out.print("Cor: ");
            carros[i][3] = scanner.nextLine();
            System.out.print("Preço (apenas números): ");
            precos[i] = scanner.nextDouble();
            scanner.nextLine(); // Limpa o buffer
        }

        // Exibe os dados em formato de tabela
        System.out.println("\n=========== LISTA DE CARROS ===========");
        System.out.printf("%-10s %-10s %-6s %-10s %-7s\n", "Marca", "Modelo", "Ano", "Cor", "Preço");

        for (int i = 0; i < quantidadeCarros; i++) {
            System.out.printf("%-10s %-10s %-6s %-10s R$ %.2f\n",
            		carros[i][0], carros[i][1], carros[i][2], carros[i][3], precos[i]);
        }

        System.out.println("========================================");

        // Pesquisa por ano
        System.out.print("\nDigite o ano que deseja pesquisar: ");
        String anoDesejado = scanner.nextLine();
        pesquisarPorAno(carros, precos, anoDesejado);

        // Pesquisa por cor
        System.out.print("\nDigite a cor que deseja pesquisar: ");
        String corDesejada = scanner.nextLine();
        pesquisarPorCor(carros, precos, corDesejada);
        scanner.close();
    }

    // Pesquisa por ano
    public static void pesquisarPorAno(String[][] carros, double[] precos, String ano) {
        System.out.println("\n=== Carros do ano " + ano + " ===");

        boolean encontrado = false;

        for (int i = 0; i < carros.length; i++) {
            if (carros[i][2].equals(ano)) {
                System.out.printf("Marca: %-10s | Modelo: %-10s | Cor: %-7s | Preço: R$ %.2f\n",
                    carros[i][0], carros[i][1], carros[i][2], precos[i]);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum carro encontrado para o ano " + ano);
        }
    }

    // Pesquisa por cor
    public static void pesquisarPorCor(String[][] carros, double[] precos, String cor) {
        System.out.println("\n=== Carros da cor " + cor + " ===");

        boolean encontrado = false;

        for (int i = 0; i < carros.length; i++) {
            if (carros[i][3].equalsIgnoreCase(cor)) {
                System.out.printf("Marca: %-10s| Modelo: %-10s | Ano: %-7s | Cor: %-7s | Preço: R$ %.2f\n",
                    carros[i][0], carros[i][1], carros[i][2], carros[i][3], precos[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum carro encontrado da cor " + cor);
        }
    }
}