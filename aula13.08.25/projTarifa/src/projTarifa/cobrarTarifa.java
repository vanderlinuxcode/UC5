package projTarifa;

import java.util.Scanner;

public class cobrarTarifa {

	public static void main(String[] args) {

		Scanner informa = new Scanner(System.in);

		//Entrada de dados
		System.out.print("Nome do Cliente: ");
		String nome = informa.nextLine();

		//Menu para escolher opção de desconto
		System.out.println("Tipo de cliente: ");
		System.out.println("A - Classe 'A'");
		System.out.println("B - Classe 'B'");
		System.out.println("C - Classe 'C'");
		System.out.print("Classe: ");
        char classe = informa.next().toUpperCase().charAt(0);

		if (classe == 'A') {
			System.out.println("Informe a Distância: ");
			double distancia = informa.nextDouble();
			System.out.println("Informe km percorrido: ");
			double kmPercorrido = informa.nextDouble();			
			
			//somatório da entrada de dados para distância e km percorrido
			double trajetoTotal = distancia + kmPercorrido;			

			// Pagamento distância e km percorrido classe A tem 10% de desconto
			double desconto = (trajetoTotal * 10) / 100;
			
			// sem o desconto valor total
	        double valorTotal = trajetoTotal;
			
	        //com aplicação do desconto	
			double 	pagarValor = trajetoTotal - desconto;		
			
			//detalhamento da operação
			System.out.println("---Resumo da Operação-------");
			System.out.printf("Classe selecionada: %s\n",classe);
			System.out.printf("Distância Percorrida: %.2f\n",distancia);
			System.out.printf("Kilometragem Percorrida: %.2f\n",kmPercorrido);
			System.out.printf("Valor total sem desconto aplicado: R$ %.2f\n",valorTotal);
			System.out.printf("Classe '%s' tem 5%% de desconto: R$ %.2f\n",classe,pagarValor);		
		
		}else if(classe == 'B' ) {
			System.out.println("Informe a Distância: ");
			double distancia = informa.nextDouble();
			System.out.println("Informe km percorrido: ");
			double kmPercorrido = informa.nextDouble();			
			
			//somatório da entrada de dados para distância e km percorrido
			double trajetoTotal = distancia + kmPercorrido;			

			// Pagamento distância e km percorrido classe B tem 5% de desconto
			double desconto = (trajetoTotal * 5) / 100;
						
			// sem o desconto valor total
			double valorTotal = trajetoTotal;
						
			//com aplicação do desconto	
			double 	pagarValor = trajetoTotal - desconto;		
			
			//Detalhamento da operação
			System.out.println("---Resumo da Operação-------");
			System.out.printf("Classe selecionada: %s\n",classe);
			System.out.printf("Distância Percorrida: %.2f\n",distancia);
			System.out.printf("Kilometragem Percorrida: %.2f\n",kmPercorrido);
			System.out.printf("Valor total sem desconto aplicado: R$ %.2f\n",valorTotal);
			System.out.printf("Classe '%s' tem 5%% de desconto: R$ %.2f\n",classe,pagarValor);		
						
		
		}else if(classe == 'C' ) {
			System.out.println("Informe a Distância: ");
			double distancia = informa.nextDouble();
			System.out.println("Informe km percorrido: ");
			double kmPercorrido = informa.nextDouble();			
			
			//somatório da entrada de dados para distância e km percorrido
			double trajetoTotal = distancia + kmPercorrido;			

			// Pagamento distância e km percorrido classe A tem 2% de desconto
			double desconto = (trajetoTotal * 2) / 100;
						
			// sem o desconto valor total
			double valorTotal = trajetoTotal;
						
			//com aplicação do desconto	
			double 	pagarValor = trajetoTotal - desconto;		
			
			//Detalhamento da operação
			System.out.println("------Resumo da Operação-------");
			System.out.printf("Classe selecionada: %s\n",classe);
			System.out.printf("Distância Percorrida: %.2f\n",distancia);
			System.out.printf("Kilometragem Percorrida: %.2f\n",kmPercorrido);
			System.out.printf("Valor total sem desconto aplicado: R$ %.2f\n",valorTotal);
			System.out.printf("Classe '%s' tem 5%% de desconto: R$ %.2f\n",classe,pagarValor);		
						
		}else {
			System.out.println("Classe inválida. Por favor, escolha 'A', 'B' ou 'C'\n");
		}
		informa.close();
	}







}



