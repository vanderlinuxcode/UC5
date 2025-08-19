package br.com.animais;

public class Leao extends Animal {
	public Leao(String nome, int idade) {
		super(nome, idade);
	}
	@Override
	public void fazerSom() {
		System.out.println("O Leão faz: Rugir! ");
	}
	
}
