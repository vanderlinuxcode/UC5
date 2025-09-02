package lanchonete;

import java.util.Scanner;

public class controleLanches {

	public static void main(String[] args) {
		Scanner pdv = new Scanner(System.in);
		Produto[] cardapio = { 
				new Hamburguer(11, "X-Burguer, Cachorro-Quente", 12.50, TipoHamburguer.VEGETARIANO), 
				new Hamburguer(12, "X-Salada, X-Egg, X-Bacon", 13.00, TipoHamburguer.VEGANO),
				new Hamburguer(13, "X-Tudo, X-Filé, X-Tudo(Duplo)", 15.00, TipoHamburguer.TRADICIONAL), 
				new Bebida(21, "Água, Refri, Suco", 5.00, Tamanho.PEQUENO), 
				new Bebida(22, "Água, Refri, Suco", 7.00, Tamanho.MÉDIO),
				new Bebida(23, "Água, Refri, Suco", 8.00, Tamanho.GRANDE), 
				new Fritura(31, "Batata, Coxinha, Bacon", 12.00, Peso.PEQUENO), 
				new Fritura(32, "Batata, Coxinha, Bacon", 15.00,Peso.MÉDIO ),
				new Fritura(33, "Batata, Coxinha, Bacon", 13.50, Peso.GRANDE), 
		};
			
		System.out.println("========================================================================");
		System.out.println("================================= CARDÁPIO =============================");
		System.out.println("========================================================================");
		
		// Listando com exibição for each cada estrutura do vetor
		for (Produto X : cardapio) {
			X.exibirInfo();
		}
		
		System.out.println("========================================================================");
		//System.out.println("Tamanho do cardápio: " + cardapio.length);
		
		Pedido pedido = new Pedido();
		System.out.println("Número do pedido gerado: " + pedido.getNumero());

		char continuar;
		do {
		    System.out.print("Digite o código do produto desejado:");
		    int codigo = pdv.nextInt();
		    boolean encontrado = false;

		    for (Produto p : cardapio) {
		        if (p.getCodigo() == codigo) {
		            pedido.adicionarProduto(p);
		            System.out.println("Produto adicionado!");
		            encontrado = true;
		            break;
		        }
		    }

		    if (!encontrado) {
		        System.out.println("Produto não encontrado.");
		    }

		    System.out.print("Deseja adicionar outro produto? (s/n)");
		    continuar = pdv.next().charAt(0);
		} while (continuar == 's' || continuar == 'S');

		pedido.exibirPedido();

		
		pdv.close();
	}
}
