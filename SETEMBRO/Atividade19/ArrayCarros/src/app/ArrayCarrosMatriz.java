package app; // Define o pacote onde a classe está localizada

import java.util.Scanner; // Importa a classe Scanner para entrada de dados pelo usuário

public class ArrayCarrosMatriz {

    public static void main(String[] args) {

        // Cria uma matriz de Strings com 5 linhas (carros) e 4 colunas (modelo, ano, cor, valor)
        String[][] carros = new String[5][4];

        // Preenche manualmente os dados de cada carro
        // Coluna 0 = Modelo | Coluna 1 = Ano | Coluna 2 = Cor | Coluna 3 = Valor
        carros[0][0] = "\tCivic";       carros[0][1] = "2022";  carros[0][2] = "Preto";     carros[0][3] = "R$ 89.000,00"; 
        carros[1][0] = "\tCorolla";     carros[1][1] = "2021";  carros[1][2] = "Branco";    carros[1][3] = "R$ 83.000,00";
        carros[2][0] = "\tGol";         carros[2][1] = "2019";  carros[2][2] = "Prata";     carros[2][3] = "R$ 49.000,00";
        carros[3][0] = "\tOnix";        carros[3][1] = "2020";  carros[3][2] = "Verde";     carros[3][3] = "R$ 52.000,00";
        carros[4][0] = "\tHB20";        carros[4][1] = "2023";  carros[4][2] = "Azul";      carros[4][3] = "R$ 67.000,00";

        // Exibe o cabeçalho da tabela de carros
        System.out.println("       =========== LISTA DE CARROS ========== ");
        System.out.print("\tModelo  -Ano-\t-Cor-  Valor ");

        // Percorre a matriz e imprime os dados de cada carro
        for (int i = 0; i < carros.length; i++) {
            System.out.println(""); // Quebra de linha entre os carros
            for (int j = 0; j < carros[i].length; j++) {
                System.out.print(carros[i][j] + "\t"); // Imprime cada campo com tabulação
            } 
        }
        System.out.println(""); // Quebra de linha final
        System.out.println("       =======================================");

        // Cria objeto Scanner para entrada de dados do usuário
        Scanner scanner = new Scanner(System.in);

        // Solicita ao usuário o ano desejado para pesquisa
        System.out.print("\nDigite o ano que deseja pesquisar: ");
        String anoDesejado = scanner.nextLine();

        // Solicita ao usuário a cor desejada para pesquisa
        System.out.print("\nDigite a cor que deseja pesquisar: ");
        String cor = scanner.nextLine();

        // Chama os métodos de pesquisa com os parâmetros informados
        pesquisarPorAno(carros, anoDesejado);
        pesquisarPorCor(carros, cor);
    }

    // Método que pesquisa carros por ano
    public static void pesquisarPorAno(String[][] carros, String anoDesejado) {
        System.out.println("\n=== Carros do ano " + anoDesejado + " ===");
        boolean encontrado1 = false; // Flag para verificar se algum carro foi encontrado

        // Percorre a matriz e compara o ano informado com o ano de cada carro
        for (int i = 0; i < carros.length; i++) {
            if (carros[i][1].equals(anoDesejado)) {
                // Exibe os dados do carro encontrado
                System.out.println("Modelo: " + carros[i][0].trim() +
                                   " | Cor: " + carros[i][2] +
                                   " | Valor: " + carros[i][3]);
                encontrado1 = true;
            }
        }

        // Se nenhum carro foi encontrado, exibe mensagem
        if (!encontrado1) {
            System.out.println("Nenhum carro encontrado para o ano " + anoDesejado);
        }
    }

    // Método que pesquisa carros por cor
    public static void pesquisarPorCor(String[][] carros, String cor) {
        System.out.println("\n=== Carros da cor " + cor + " ===");
        boolean encontrado = false; // Flag para verificar se algum carro foi encontrado

        // Percorre a matriz e compara a cor informada com a cor de cada carro (ignorando maiúsculas/minúsculas)
        for (int i = 0; i < carros.length; i++) {
            if (carros[i][2].trim().equalsIgnoreCase(cor)) {
                // Exibe os dados do carro encontrado
                System.out.println("Modelo: " + carros[i][0].trim() +
                                   " | Cor: " + carros[i][2] +
                                   " | Valor: " + carros[i][3]);
                encontrado = true;
            }
        }

        // Se nenhum carro foi encontrado, exibe mensagem
        if (!encontrado) {
            System.out.println("Nenhuma cor encontrada da cor " + cor);
        }
    }
}
