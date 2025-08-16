package lanchonete;

import java.util.Scanner;
import java.util.Random;

public class vendaLanches {

	// método throws InterrupteDexception para simular o tempo de espera
	public static void main(String[] args) throws InterruptedException {
		Scanner pdv = new Scanner(System.in);
		Random gerarComanda = new Random();

		// Gera número de comanda aleatório entre 100 e 999
		int comanda = 100 + gerarComanda.nextInt(999);

		// CARDÁPIO
		System.out.println("  =============================================== ");
		System.out.println("  |                 CARDÁPIO                     | ");
		System.out.println("  =============================================== ");
		System.out.println("  =  Hamburgueres ==== Refrescos ===== Fritura == ");
		System.out.println("  = (01) X-Salada    (02) Suco     (03) Batata  = ");
		System.out.println("  = (11) X-Egg       (21) Refri    (31) Palmito = ");
		System.out.println("  = (12) X-Tudo      (22) Água     (32) Picles  = ");
		System.out.println("  ===============================================\n");

		// Entrada de dados
		System.out.println("Número da Comanda: " + comanda);
		System.out.print("Nome: ");
		String nome = pdv.nextLine();

		System.out.print("Escolha Código Hamburguer: ");
		int codigo = pdv.nextInt();

		// Caso a entrada não satisfaça a condição do Cardápio
		if (codigo != 1 && codigo != 11 && codigo != 12) {
			System.out.println("\n Código inválido. Operação cancelada.");
			System.out.println("Por favor, reinicie o sistema e escolha um item válido do cardápio.");
			pdv.close();
			return;
		}

		String nomeHamburguer = "";
		String nomeBebida = "";
		String nomeFritura = "";
		int quantHamburg = 0;
		int quantBebida = 0;
		int quantFritura = 0;

		if (codigo == 1) {
			System.out.println("\nResumo da Comanda: ");
			System.out.println("Comanda Nº: " + comanda);
			System.out.println("Cliente: " + nome);
			System.out.println("Pedido:");
			System.out.print(" - Item " + codigo + " | ");
			nomeHamburguer = "X-Salada";
			System.out.printf("%s\n",nomeHamburguer);
			System.out.print("Quantidade: ");
			quantHamburg = pdv.nextInt();
			
			System.out.println("Escolha Refresco 02 Suco 21 Refri 22 Água: ");
			int refresco = pdv.nextInt();

			if (refresco == 2) {
				nomeBebida = "Suco";
				System.out.printf("%s\n",nomeBebida);
				System.out.print("Quantidade: ");
				quantBebida = pdv.nextInt();
				
			} else if (refresco == 21) {
				nomeBebida = "Refrigerante";
				System.out.printf("%s\n",nomeBebida);
				System.out.print("Quantidade: ");
				quantBebida = pdv.nextInt();
			
			} else {
				nomeBebida = "Água";
				System.out.printf("%s\n",nomeBebida);
				System.out.print("Quantidade: ");
				quantBebida = pdv.nextInt();
				
			}

			System.out.print("Escolha Fritura 03 Batata 31 Palmito 32 Picles: ");
			int fritura = pdv.nextInt();

			if (fritura == 3) {
				nomeFritura = "Batata";
				System.out.printf("%s\n",nomeFritura);
				System.out.print("Quantidade: ");
				quantFritura = pdv.nextInt();
				
			} else if (fritura == 31) {
				nomeFritura = "Palmito";
				System.out.printf("%s\n",nomeFritura);
				System.out.print("Quantidade: ");
				quantFritura = pdv.nextInt();
				
			} else {
				nomeFritura = "Bacon";
				System.out.printf("%s\n",nomeFritura);
				System.out.print("Quantidade: ");
				quantFritura = pdv.nextInt();
				
			}
		}

		if (codigo == 11) {
			System.out.println("\nResumo da Comanda: ");
			System.out.println("Comanda Nº: " + comanda);
			System.out.println("Cliente: " + nome);
			System.out.println("Pedido:");
			System.out.print(" - Item " + codigo + " | ");
			nomeHamburguer = "X-Egg";
			System.out.printf("%s\n",nomeHamburguer);
			System.out.print("Quantidade: ");
			quantHamburg = pdv.nextInt();

			System.out.print("Escolha Refresco 02 Suco 21 Refri 22 Água: ");
			int refresco = pdv.nextInt();

			if (refresco == 2) {
				nomeBebida = "Suco";
				System.out.printf("%s\n",nomeBebida);
				System.out.print("Quantidade: ");
			    quantBebida = pdv.nextInt();
			    
			} else if (refresco == 21) {
				nomeBebida = "Refrigerante";
				System.out.printf("%s\n",nomeBebida);
				System.out.print("Quantidade: ");
				quantBebida = pdv.nextInt();
				
			} else {
				nomeBebida = "Água";
				System.out.printf("%s\n",nomeBebida);
				System.out.print("Quantidade: ");
				quantBebida = pdv.nextInt();
			
			}

			System.out.print("Escolha Fritura 03 Batata 31 Palmito 32 Picles: ");
			int fritura = pdv.nextInt();

			if (fritura == 3) {
				nomeFritura = "Batata";
				System.out.printf("%s\n",nomeFritura);
				System.out.print("Quantidade: ");
				quantFritura = pdv.nextInt();
				
			} else if (fritura == 31) {
				nomeFritura = "Palmito";
				System.out.printf("%s\n",nomeFritura);
				System.out.print("Quantidade: ");
				quantFritura = pdv.nextInt();
				
			} else {
				nomeFritura = "Bacon";
				System.out.printf("%s\n",nomeFritura);
				System.out.print("Quantidade: ");
				quantFritura = pdv.nextInt();
			
			}
		}

		if (codigo == 12) {
			System.out.println("\nResumo da Comanda: ");
			System.out.println("Comanda Nº: " + comanda);
			System.out.println("Cliente: " + nome);
			System.out.println("Pedido:");
			System.out.print(" - Item " + codigo + " | ");
			nomeHamburguer = "X-Tudo";
			System.out.printf("%s\n",nomeHamburguer);
			System.out.print("Quantidade: ");
			quantHamburg = pdv.nextInt();

			System.out.print("Escolha Refresco 02 Suco 21 Refri 22 Água: ");
			int refresco = pdv.nextInt();

			if (refresco == 2) {
				nomeBebida = "Suco";
				System.out.printf("%s\n",nomeBebida);
				System.out.print("Quantidade: ");
				quantBebida = pdv.nextInt();
				
			} else if (refresco == 21) {
				nomeBebida = "Refrigerante";
				System.out.printf("%s",nomeBebida);
				System.out.print("Quantidade: ");
				quantBebida = pdv.nextInt();
				
			} else {
				nomeBebida = "Água";
				System.out.printf("%s",nomeBebida);
				System.out.print("Quantidade: ");
				quantBebida = pdv.nextInt();
				
			}

			System.out.print("Escolha Fritura 03 Batata 31 Palmito 32 Picles: ");
			int fritura = pdv.nextInt();

			if (fritura == 3) {
				nomeFritura = "Batata";
				System.out.printf("%s\n", nomeFritura);
				System.out.print("Quantidade: ");
				quantFritura = pdv.nextInt();
				
			} else if (fritura == 31) {
				nomeFritura = "Palmito";
				System.out.printf("%s\n", nomeFritura);
				System.out.print("Quantidade: ");
				quantFritura = pdv.nextInt();
				
			} else {
				nomeFritura = "Bacon";
				System.out.printf("%s\n", nomeFritura);
				System.out.print("Quantidade: ");
				quantFritura = pdv.nextInt();
				
			}
		}

		System.out.println("\nStatus do Pedido:");
		Thread.sleep(500);

		System.out.println(" Preparando...");
		Thread.sleep(500);

		System.out.println(" Finalizando...");
		Thread.sleep(500);

		System.out.println(" Pedido pronto para retirada!");
		Thread.sleep(500);

		System.out.println("\n=======================================");
		System.out.println(" Comanda Nº: " + comanda);
		System.out.println(" Cliente: " + nome);
		System.out.printf(" Pedido: %s (%d) %s (%d) %s (%d)\n", nomeHamburguer,quantHamburg,nomeBebida,quantBebida,nomeFritura,quantFritura);
		System.out.println(" Obrigado por escolher nossa lanchonete!");
		System.out.println("=========================================\n");

		pdv.close();
	}
}
