package app;

import java.util.Scanner;

public class ArrayCarrosMatriz {

	public static void main(String[] args) {

		// Criando um array bidimensional (5 carros x 3 informações cada)
		String[][] carros = new String[5][4];
		// Preenchendo os dados: [linha][coluna]
		// Coluna 0 = Modelo 		 | 		Coluna 1 = Ano 	  | 	Coluna 2 = Cor  	|	Coluna 3 = Valor
		carros[0][0] = "\tCivic";		carros[0][1] = "2022";	carros[0][2] = "Preto";		carros[0][3] = "R$ 89.000,00"; 
		carros[1][0] = "\tCorolla";		carros[1][1] = "2021";	carros[1][2] = "Branco";	carros[1][3] = "R$ 83.000,00";
		carros[2][0] = "\tGol";			carros[2][1] = "2019";	carros[2][2] = "Prata";		carros[2][3] = "R$ 49.000,00";
		carros[3][0] = "\tOnix";		carros[3][1] = "2020";	carros[3][2] = "Verde";	 	carros[3][3] = "R$ 52.000,00";
		carros[4][0] = "\tHB20";		carros[4][1] = "2023";	carros[4][2] = "Azul";		carros[4][3] = "R$ 67.000,00";;

		// Exibindo tabela de carros
		System.out.println("       =========== LISTA DE CARROS ========== ");
		System.out.print("\tModelo  -Ano-\t-Cor-  Valor ");
		
		for (int i = 0; i < carros.length; i++) {
			System.out.println("");
			for (int j = 0; j < carros[i].length; j++) {
				System.out.print(carros[i][j] + "\t");
			} 
		}
		System.out.println("");
		System.out.println("       =======================================");
			
//			
		Scanner scanner = new Scanner(System.in);
        System.out.print("\nDigite o ano que deseja pesquisar: ");
        String anoDesejado = scanner.nextLine();
        System.out.print("\nDigite a cor que deseja pesquisar: ");
        String cor = scanner.nextLine();
        
        pesquisarPorAno(carros,anoDesejado);
        pesquisarPorCor(carros, cor);
	}
	
	public static void pesquisarPorAno(String[][] carros, String anoDesejado) {
		System.out.println("\n=== Carros do ano " + anoDesejado + " ===");
		boolean encontrado1 = false;

            for (int i = 0; i < carros.length; i++) {
                if (carros[i][1].equals(anoDesejado)) {
                    System.out.println("Modelo: " + carros[i][0].trim() +
                                       " | Cor: " + carros[i][2] +
                                       " | Valor: " + carros[i][3]);
                    encontrado1 = true;
                }
            }        

            if (!encontrado1) {
                System.out.println("Nenhum carro encontrado para o ano " + anoDesejado);
            }
        }
            
            public static void pesquisarPorCor(String[][] carros, String cor) {
                System.out.println("\n=== Carros da cor " + cor + " ===");
                boolean encontrado = false;

                for (int i = 0; i < carros.length; i++) {
                    if (carros[i][2].trim().equalsIgnoreCase(cor)) {
                        System.out.println("Modelo: " + carros[i][0].trim()+
                        " | Cor: " + carros[i][2] +
                        " | Valor: " + carros[i][3]);
                        encontrado = true;
                    }
                }
                
                    if (!encontrado) {
                    System.out.println("Nenhuma cor encontrado da cor " + cor);
                    }
            }
}

