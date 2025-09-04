package com.minimarketing.app;

import com.minimarketing.models.Produto;
import com.minimarketing.models.Alimento;
import com.minimarketing.models.Eletronico;

public class Main {
	
	public static void main(String[] args) {
		
		//Criando da instância objeto Produto;
		System.out.println("--- PRODUTO GENÉRICO---");
		Produto produtoGenerico = new Produto(1,"Caderno", 15.00, "12345");
		produtoGenerico.exibirInformacoes();
		System.out.println("Imposto a pagar: R$" + String.format("%.2f\n", produtoGenerico.calcularImposto())); 
		
		System.out.println("--- PRODUTO ELETRÔNICO---");
		//Criando uma instância da classe Eletrônico
		Eletronico smartphone = new Eletronico(2,"Smartphone X", 2500.00, "54321",12);
		smartphone.exibirInformacoes();
		System.out.println("Imposto a pagar: R$" + String.format("%.2f\n", smartphone.calcularImposto()));

		System.out.println("--- PRODUTO Alimentício---");
		Alimento arroz = new Alimento(3, "Arroz Tio João", 25.90, "45321", "20/11/2025");
		arroz.exibirInformacoes();
		System.out.println("Imposto a pagar: R$" + String.format("%.2f\n", arroz.calcularImposto()));
		
	}

}
