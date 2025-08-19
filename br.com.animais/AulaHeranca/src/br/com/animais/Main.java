package br.com.animais;

public class Main {

	public static void main(String[] args) {
		//Cria uma instância de classe cachorro
		Cachorro meuCachorro = new Cachorro("Rex",5);
		System.out.println("Nome: " + meuCachorro.nome);
		System.out.println("Idade: " + meuCachorro.idade);
		meuCachorro.fazerSom();
		
		System.out.println("----------------------------------------");
		
		//Cria uma instância de classe gato
		Gato meuGato = new Gato("Frajola",2);
		System.out.println("Nome: " + meuGato.nome);
		System.out.println("Idade: " + meuGato.idade);
		meuGato.fazerSom();
		
		System.out.println("----------------------------------------");
		
		//Cria uma instância de classe Leão
		Leao meuLeao = new Leao("Rei da Selva",2);
		System.out.println("Nome: " + meuLeao.nome);
		System.out.println("Idade: " + meuLeao.idade);
		meuLeao.fazerSom();
		
		System.out.println("----------------------------------------");

		//Cria uma instância de classe Galinha
		Galinha minhaGalinha = new Galinha("Crislândia",5);
		System.out.println("Nome: " + minhaGalinha.nome);
		System.out.println("Idade: " + minhaGalinha.idade);
		minhaGalinha.fazerSom();
		
		System.out.println("----------------------------------------");

		//Cria uma instância de classe Bode
		Bode meuBode = new Bode("Juventino",7);
		System.out.println("Nome: " + meuBode.nome);
		System.out.println("Idade: " + meuBode.idade);
		meuBode.fazerSom();
	}

}
