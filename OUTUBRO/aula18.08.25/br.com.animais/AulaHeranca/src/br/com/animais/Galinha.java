package br.com.animais;

public class Galinha extends Animal {
	public Galinha(String nome, int idade) {
		super(nome, idade);
	}
	@Override
	public void fazerSom() {
		System.out.println("A galinha faz: Cocoricó! ");
	}
	
}
