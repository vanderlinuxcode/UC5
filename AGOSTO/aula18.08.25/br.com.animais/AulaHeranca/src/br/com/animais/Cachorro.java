package br.com.animais;

public class Cachorro extends Animal {
	public Cachorro(String nome, int idade) {
		super(nome, idade);
	}
	@Override
	public void fazerSom() {
		System.out.println("O cachorro late: Au Au! ");
	}
	
}
