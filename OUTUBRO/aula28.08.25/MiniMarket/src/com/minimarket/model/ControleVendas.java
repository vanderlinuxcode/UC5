package com.minimarket.model;

import java.util.Scanner;

public class ControleVendas {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] dias = { "Segunda", "Terça", "Quarta", "quinta", "Sexta", "Sábado", "Domingo" };
		int[] vendas = new int[7];
		// Entrada de dados
		for (int i = 0; i < vendas.length; i++) {
			System.out.print("Digite as vendas de " + dias[i] + ": ");
			vendas[i] = scanner.nextInt();
		}
		// Processamento
		int total = 0, maior = vendas[0], menor = vendas[0];
		int diaMaior = 0, diaMenor = 0;

		for (int i = 0; i < vendas.length; i++) {
			total += vendas[i];
			if (vendas[i] > maior) {
				maior = vendas[i];
				diaMaior = i;
			}
			if (vendas[i] < menor) {
				menor = vendas[i];
				diaMenor = i;
			}
		}
		
		double media = total / 7.0;
		
		// Saída
		System.out.println("\nTotal de vendas na semana: " + total);
		System.out.printf("Média de vendas por dia: %.2f\n", media);
		System.out.println("Maior venda: " + maior + " (" + dias[diaMaior] + ")");
		System.out.println("Menor venda: " + menor + " (" + dias[diaMenor] + ")");
	}
}
