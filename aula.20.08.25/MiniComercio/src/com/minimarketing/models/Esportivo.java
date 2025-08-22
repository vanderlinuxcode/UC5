package com.minimarketing.models;

//classe filho ou subclasse
public class Esportivo extends Produto {

	private String corrida;

	public Esportivo (int id, String nome, double preco, String codigoDeBarra, String corrida) {
		//invoca o construtor da classe pai (Produto)
				super(id, nome, preco, codigoDeBarra);
					this.corrida = corrida;
	}	
					@Override
					public void exibirInformacoes() {
						//invoca a exibição de informações da classe pai e adiciona a garantia
						super.exibirInformacoes();
						System.out.printf("Modalidade: %s\n", corrida);
					}
					
					@Override
					public double calcularImposto() {
						//usa o método getPreco() da classe pai para calcular o imposto
						return getPreco() * 0.15;
					}		
	}

