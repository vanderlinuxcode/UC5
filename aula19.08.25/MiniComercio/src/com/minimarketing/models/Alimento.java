package com.minimarketing.models;

public class Alimento extends Produto {
	
	private String validadeMeses;
	
	public Alimento(int id, String nome, double preco, String codigoDeBarra, String validadeMeses) {
		//invoca o construtor da classe pai (Produto)
		super(id, nome, preco, codigoDeBarra);
			this.validadeMeses = validadeMeses;	
	}
	
	@Override
	public void exibirInformacoes() {
		//invoca a exibição de informações da classe pai e adiciona a garantia
		super.exibirInformacoes();
		System.out.printf("Validade: %s\n", validadeMeses);
	}
	
	@Override
	public double calcularImposto() {
		//usa o método getPreco() da classe pai para calcular o imposto
		return getPreco() * 0.15;
	}
		

}
