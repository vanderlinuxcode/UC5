package app;

import java.util.Scanner;

public class sistemaDesconto {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// Entrada de dados
		System.out.print("Digite o nome do cliente: ");
		String nome = scanner.nextLine();
		
		System.out.println("Selecione o tipo de cliente: ");
		System.out.println("1 - Comum");
		System.out.println("2 - VIP");
		System.out.println("3 - Funcionario");
		System.out.println("Tipo:");
		int tipo = scanner.nextInt();
		
		System.out.print("Digite o valor da compra: R$ ");
		double valorCompra = scanner.nextDouble();
		
		// Variáveis auxiliares
		double desconto = 0;
		String tipoTexto = "";
		
		// Estrutura de decisão
		if (tipo == 1) {
			tipoTexto = "Comum";
			desconto = 0;
		}else if (tipo == 2) {
				tipoTexto = "VIP";
				desconto = valorCompra * 0.10;
		}else if (tipo == 3) {
					tipoTexto = "Funcionário";
					desconto = valorCompra * 0.20;
				}else{
					System.out.println("Tipo de cliente inválido");
					scanner.close();
					return;					
				}
							
		double valorFinal = valorCompra - desconto;
		// Saída
		System.out.println("\n--- RESUMO DA COMPRA ----");
		System.out.println("Nome do cliente: "+ nome);
		System.out.println("Tipo de cliente: "+ tipoTexto);
		System.out.printf("Valor da compra: R$ %.2f\n", valorCompra);
		System.out.printf("Desconto aplicado: R$ %.2f\n", desconto);
		System.out.printf("Valor final: R$ %.2f\n", valorFinal);
		scanner.close();
				
		}						
			
}
		
		
	


