package app;

public class ArraysCarros {

	public static void main(String[] args) {
		
		// 1. Criando um array de String para armazenar nomes de carros.
		String[] carros = new String[5];
		
		// 2. Inserindo Carros no array
		carros[0] = "Fusca";
		carros[1] = "Civic";
		carros[2] = "Corolla";
		carros[3] = "Gol";
		carros[4] = "HB 20";
		
		// 3. Exibindo informações
		System.out.println("Planalto Vistorias");
		System.out.println("Lista de Carros Cadastrados");
		
		for(int i = 0; i < carros.length; i++){
			System.out.println("Posição " + i + ": " + carros[i]);
		}
		
		// 4. Acessar posições do array
		System.out.println("\ncarro na posição 2 é: " + carros[2]);
		System.out.println("carro na posição 4 é: " + carros[4]);


		// 5. Exemplo com for-each
		System.out.println("\nUsando for-each");
		
		for (String carro: carros){
			System.out.println(carro);
		}
	}
}
		