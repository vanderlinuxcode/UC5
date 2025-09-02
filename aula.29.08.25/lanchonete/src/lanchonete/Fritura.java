package lanchonete;
	
// Subclasse ou classe filha
public class Fritura extends Produto {

	// declaração enum classe Peso
	private Peso peso;

	public Fritura(int codigo, String nome, double valor, Peso peso) {
		// Faz a chamada para o construtor da classe pai ou superclasse
		super(codigo, nome, valor);
		this.peso = peso;
	}
	
	public Peso getPeso(){
		return peso;
	}
	
	@Override
	public void exibirInfo() {
		System.out.printf("ID: %d | %-29s | (%-11s) | R$%-2.2f | %dmg\n", 
		getCodigo(), getNome(), peso.toString(), getValor(), peso.getpesoGr());
	}
}
