package lanchonete;

//Classe filha ou subclasse
public class Hamburguer extends Produto {
	
	// Declaração de atributos inerente a subclasse e enum
		private TipoHamburguer tipoHamburguer; 
		
		// tipo Vegetariano, Vegano, Tradicional da classe enum tipoHamburguer 
	public Hamburguer(int codigo, String nome, double valor, TipoHamburguer tipoHamburguer ) {
		// Faz a chamada para o construtor da classe pai ou superclasse
		super(codigo, nome, valor);
		this.tipoHamburguer = tipoHamburguer;
	}
	public TipoHamburguer getTipoHamburguer(){
		return tipoHamburguer; 
	}
	
	@Override
	public void exibirInfo() {
		System.out.printf("ID: %d | %-29s | (%-11s) | R$%-2.2f | %dmg\n", 
		getCodigo(), getNome(), tipoHamburguer.toString(), getValor(), tipoHamburguer.getescolha());
	}
}

