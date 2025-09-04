package br.com.animais;

public class Animal {
	String nome; //Atributo: nome do animal
	int idade; //Atributo: idade do animal
	
	//Construtor: Usado para criar um novo objeto Animal
	// Ele recebe um nome e uma idade para o animal\
	
	public Animal(String nome, int idade) {
		this.nome = nome; this.idade = idade;
	}
	// Metodo: Define o comportamento do animal "Som do animal"
	public void fazerSom() {
		System.out.println("O animal faz um som: ");
	}

}
