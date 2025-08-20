package com.minimarketing.models;

// Classe pai ou super classe
public class Produto {
	//Declaração de variáveis e seus atributos
	private int id;
	private String nome;
	private double preco;
	private String codigoDeBarras;
	
	//método especial para inicializar a classe
	public Produto(int id, String nome, double preco, String codigoDeBarras) {	
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.codigoDeBarras = codigoDeBarras;
			
	}
	
	//método para exibir resultado dos atributos
	public void exibirInformacoes() {
		System.out.println("Id: " + this.id);
		System.out.println("Nome: " + this.nome);
		System.out.println("Valor: R$ " + String.format("%.2f", this.preco));
		System.out.println("Código: " + this.codigoDeBarras);
	}
	
	public double calcularImposto() {
		//Impoosto padrão de 10% para produtos genéricos
		return this.preco * 0.10;
	}
	
	//métodos Getters para acessar os atributos privados
		public int getid() {
			return id;
		}		
	
	//métodos Getters para acessar os atributos privados
	public String getNome() {
		return nome;
	}
	
	public double getPreco() {
		return preco;
	}
}
	