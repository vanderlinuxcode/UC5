package app;

//Classe principal para executar o programa
public class Main {
	public static void main(String[] args) {
		
		// Criando o primeiro objeto Aluno
		Aluno aluno1 = new Aluno();
		aluno1.nome = "Maria";
		aluno1.idade = 20;
		aluno1.curso = "Engenharia de Software";
		
		// Criando o segundo objeto Aluno
		Aluno aluno2 = new Aluno();
		aluno2.nome = "Vander";
		aluno2.idade = 35;
		aluno2.curso = "Sistemas de Informações";
		
		// Chamando o método para exibir as informações
		aluno1.exibirInformacoes();
		aluno2.exibirInformacoes();		
	}
}

//Classe Aluno
class Aluno{
	// Atributos
	String nome;
	int idade;
	String curso;
	
	// Método para exibir informações do aluno
	void exibirInformacoes() {
		System.out.println("Nome: " + nome);
		System.out.println("Idade: " + idade);
		System.out.println("Curso: " + curso);
		System.out.println("-------------------------------");
	}
}