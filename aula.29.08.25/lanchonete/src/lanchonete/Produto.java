package lanchonete;

// Superclasse Produto
public class Produto {
	// Declaração de atributos com encapsulamento
	private int codigo;
	private String nome;
	private double valor;

	// Construtor
	public Produto(int codigo, String nome, double valor) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
	}

	// Encapsulamento privado com getters e setters.
	// Métodos de acesso
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	// Método de exibição pronto em formatação
	public void exibirInfo() {
		System.out.printf("ID: %d | %-9s | R$ %.2f\n", codigo, nome, valor);
	}

}
