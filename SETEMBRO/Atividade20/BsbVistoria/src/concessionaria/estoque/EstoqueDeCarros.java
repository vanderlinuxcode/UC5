
package concessionaria.estoque;
import java.util.ArrayList;
public class EstoqueDeCarros {

	public static void main(String[] args) {
		// 1. Criar um ArrayList para armazenar os modelos de carros
		ArrayList<String> estoque = new ArrayList<>();
		System.out.println("--- Gerenciador de Estoque de Carros ---");
		// 2. Adicionar novos carros ao estoque
		System.out.println("\nAdicionando carros ao estoque...");
		estoque.add("Ford Mustang");
		estoque.add("Chevrolet Camaro");
		estoque.add("Volkswagen Jetta");
		estoque.add("Honda Civic");
		estoque.add("Toyota Corolla");
		estoque.add("Fiat Palio");
		// 3. Exibir o estoque atual e o seu tamanho
		System.out.println("\nEstoque atual (" + estoque.size() + " carros):");
		System.out.println(estoque);
		// 4. Simular uma venda (removendo um carro)
		System.out.println("\nVendendo o 'Volkswagen Jetta'...");
		estoque.remove("Volkswagen Jetta"); //estoque.remove("Honda Civic");
		// 5. Exibir após a venda 
		System.out.println("\nEstoque atualizado (" + estoque.size() + " carros):");
		System.out.println(estoque);
		// 6. Simular outra venda, desta vez pelo índice
		// O Honda Civic está na posição de índice 2 (agora que o Jetta foi removido)
		System.out.println("\nVendendo o carro na posição 2 (Honda Civic)...");
		estoque.remove(2);
		// 7. Exibir o estoque final e o tamanho atualizado
		System.out.println("\nEstoque final (" + estoque.size() + " carros):");
		System.out.println(estoque);
	}
}
