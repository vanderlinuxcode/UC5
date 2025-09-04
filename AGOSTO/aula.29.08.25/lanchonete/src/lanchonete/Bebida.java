package lanchonete;

// Subclasse ou classe filha
public class Bebida extends Produto {
	// Declaração de atributos inerente a subclasse e enum
	private Tamanho tamanho; // "Pequeno", "Médio", "Grande"
	
	public Bebida(int codigo, String nome, double valor, Tamanho tamanho) {
		// Faz a chamada para o construtor da classe pai ou superclasse e o que
		// é comum a todas as subclasses
		super(codigo, nome, valor);
		this.tamanho = tamanho;
	}
	
	public Tamanho getTamanho(){
		return tamanho;
	}
	
	@Override
	public void exibirInfo() {
		System.out.printf("ID: %d | %-29s | (%-11s) | R$%-5.2f | %dml\n", 
		getCodigo(), getNome(), tamanho.toString(), getValor(), tamanho.getvolumeMl());
	}
	
}
