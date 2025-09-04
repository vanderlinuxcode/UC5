package app;

import java.util.Scanner;

public class ConsultaCarros {
    public static void main(String[] args) {
        
    	Scanner scanner = new Scanner(System.in);
        String[] carros = new String[10];

        // Preencher o array com nomes digitados pelo usuário
        System.out.println("Cadastro de 10 carros:");
        for (int i = 0; i < carros.length; i++) {
            System.out.println("Digite o nome do carro " + (i) + ": ");
            carros[i] = scanner.nextLine();
        }

        // Exibir a lista completa de carros cadastrados
        System.out.println("\nLista de carros cadastrados:");
        for (int i = 0; i < carros.length; i++) {
            System.out.println("[" + i + "] " + carros[i]);
        }

        // Pesquisar um carro pelo nome
        System.out.print("\nDigite o nome do carro para pesquisar: ");
        String pesquisa = scanner.nextLine();
        boolean encontrado = false;
        for (String carro : carros) {
            if (carro.equalsIgnoreCase(pesquisa)) {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            System.out.println("O carro \"" + pesquisa + "\" está na lista.");
        } else {
            System.out.println("O carro \"" + pesquisa + "\" não foi encontrado.");
        }

        // Mostrar quantos carros foram cadastrados
        System.out.println("\nTotal de carros cadastrados: " + carros.length);

        // Exibir o carro de uma posição específica
        System.out.print("\nDigite a posição (0 a 9) para ver o carro: ");
        int posicao = scanner.nextInt();
        
        // Limpa o buffer
        scanner.nextLine();
        
        if (posicao >= 0 && posicao < carros.length) {
            System.out.println("Carro na posição " + "["+ posicao +"]: " + carros[posicao]);
        } else {
            System.out.println("Posição inválida.");
        }

        // Exibir a lista usando for
        System.out.println("\nLista de carros usando for:");
        for (int i = 0; i < carros.length; i++) {
            System.out.println("Carro " + i + ": " + carros[i]);
        }

        // Exibir a lista usando for-each
        System.out.println("\nLista de carros usando for-each:");
        for (String carro : carros) {
            System.out.println("Carro : " + carro);
        }

        scanner.close();
    }
}
