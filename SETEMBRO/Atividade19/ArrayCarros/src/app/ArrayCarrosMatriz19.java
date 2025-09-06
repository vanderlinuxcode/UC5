package app;

import java.util.Scanner;

public class ArrayCarrosMatriz19 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int quantidadeCarros = 3;
        String[][] carros = new String[quantidadeCarros][4]; // Marca, Modelo, Ano, Cor
        double[] precos = new double[quantidadeCarros];

        // Entrada de dados
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

        // Exibe todos os carros
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

        // Mostrar carro mais caro
        mostrarCarroMaisCaro(carros, precos);

        scanner.close();
    }

    // Pesquisa por ano (igual ou mais recente)
    public static void pesquisarPorAno(String[][] carros, double[] precos, String ano) {
        System.out.println("\n=== Carros do ano " + ano + " ou mais recentes ===");
        boolean encontrado = false;
        int anoInt = Integer.parseInt(ano);

        for (int i = 0; i < carros.length; i++) {
            int anoCarro = Integer.parseInt(carros[i][2]);
            if (anoCarro >= anoInt) {
                System.out.printf("Marca: %-10s | Modelo: %-10s | Ano: %-4s | Cor: %-7s | Preço: R$ %.2f\n",
                    carros[i][0], carros[i][1], carros[i][2], carros[i][3], precos[i]);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum carro encontrado do ano " + ano + " ou mais recente.");
        }
    }

    // Pesquisa por cor
    public static void pesquisarPorCor(String[][] carros, double[] precos, String cor) {
        System.out.println("\n=== Carros da cor " + cor + " ===");
        boolean encontrado = false;

        for (int i = 0; i < carros.length; i++) {
            if (carros[i][3].equalsIgnoreCase(cor)) {
                System.out.printf("Marca: %-10s | Modelo: %-10s | Ano: %-4s | Cor: %-7s | Preço: R$ %.2f\n",
                    carros[i][0], carros[i][1], carros[i][2], carros[i][3], precos[i]);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum carro encontrado da cor " + cor);
        }
    }

    // Mostrar o carro mais caro
    public static void mostrarCarroMaisCaro(String[][] carros, double[] precos) {
        double maiorPreco = precos[0];
        int indiceMaisCaro = 0;

        for (int i = 1; i < precos.length; i++) {
            if (precos[i] > maiorPreco) {
                maiorPreco = precos[i];
                indiceMaisCaro = i;
            }
        }

        System.out.println("\n=== Carro mais caro do estoque ===");
        System.out.printf("Marca: %-10s | Modelo: %-10s | Ano: %-4s | Cor: %-7s | Preço: R$ %.2f\n",
            carros[indiceMaisCaro][0], carros[indiceMaisCaro][1], carros[indiceMaisCaro][2],
            carros[indiceMaisCaro][3], maiorPreco);
    }
}
