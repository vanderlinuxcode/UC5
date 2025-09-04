package app;

public class loopWhile {

	public static void main(String[] args) {

//1. Inicializamos a variável de controle do laço
		int contador = 1; // contagem crescente

		System.out.println("-------Contador Crescente--------");
		// 2. Definimos a condição: o laço continua enquanto 'contador' for menor ou
		// igual a 5
		while (contador <= 5) {

			// 3. O que queremos fazer a cada repetição? Imprimir o valor do contador.
			System.out.println("O contador agora é: " + contador);

			// 4. A etapa crucial: atualizamos a variável de controle para evitar um laço
			// infinito.
			// Aqui, incrementamos o contador em 1 a cada repetição.
			contador++;
		}

		// 5. Após o laço, podemos imprimir uma mensagem para mostrar que ele terminou.
		System.out.println("Fim da contagem crescente!");
		System.out.println("           /    ");
		System.out.println("          /     ");
		System.out.println("         /      ");
		System.out.println("        /       ");

		//Aqui o contador está valendo número maior -1 para iniciar de número maior para menor
		contador = contador - 1; 

		System.out.println("-------Contador Decrescente--------");
		while (contador >= 1) {

			System.out.println("O contador agora é: " + contador);

			//aqui decrementamos o contador em 1 a cada repetição até atender a condição de parada.
			contador--;
		}

		// 5. Após o laço, podemos imprimir uma mensagem para mostrar que ele terminou.
		System.out.println("Fim da contagem decrescente!");
	}

}
