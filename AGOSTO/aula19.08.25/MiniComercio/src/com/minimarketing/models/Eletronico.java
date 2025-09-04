package com.minimarketing.models;

// classe filho ou subclasse
public class Eletronico extends Produto {
	
	private int garantiaMeses;
	
	public Eletronico(int id, String nome, double preco, String codigoDeBarras, int garantiaMeses) {
	//Invoca o construtor da classe pai (Produto)
		super(id, nome, preco, codigoDeBarras);
		this.garantiaMeses = garantiaMeses;		
	}

	@Override
	public void exibirInformacoes() {
		//Invoca a exibição de informações da classe pai e adiciona a garantia
		super.exibirInformacoes();
		System.out.println("Garantia: " + this.garantiaMeses + " meses");
	}
	
	@Override
	public double calcularImposto() {
		//Usa o método getPreco() da classe pai para calcular o imposto
		return getPreco() * 0.15;
	}	
}
