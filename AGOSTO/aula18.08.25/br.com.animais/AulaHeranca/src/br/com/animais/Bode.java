package br.com.animais;

public class Bode extends Animal {
	public Bode(String nome, int idade) {
		super(nome, idade);
	}
	@Override
	public void fazerSom() {
		System.out.println("O bode faz: Beeee! ");
	}
	
}
