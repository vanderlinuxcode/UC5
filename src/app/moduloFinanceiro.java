package app;

import java.util.Scanner;

public class moduloFinanceiro {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);		

				// 1. Entrada de dados
        		System.out.println("\n--- Dados Produto Loja ---");
        	
		        System.out.print("Nome do produto: ");
		        String nome = entrada.nextLine();

		        System.out.print("Preço de compra: ");
		        double precoCompra = entrada.nextDouble();

		        System.out.print("Preço de venda: ");
		        double precoVenda = entrada.nextDouble();

		        System.out.print("Quantidade vendida: ");
		        int quantidade = entrada.nextInt();

		        // 2. calcule e exiba
		        System.out.println("\n--- Resultado Financeiro ---");
		        double receitaTotal = precoVenda * quantidade; 
		        
		        double custoTotal = precoCompra * quantidade;
		        System.out.printf("\nCusto Total: " + custoTotal);
		        
		        double lucroBruto = receitaTotal - custoTotal;
		        System.out.printf("\nLucro Bruto: " + lucroBruto);
		        
		        double impostoVenda = (receitaTotal * 8)/100 ;
		        System.out.printf("\nImposto sobre a Venda: " + impostoVenda);
		        
		        double lucroLiquido = lucroBruto - impostoVenda ;  
		        System.out.printf("\nLucro Líquido: " + lucroLiquido);

		          
		entrada.close();

	}

}