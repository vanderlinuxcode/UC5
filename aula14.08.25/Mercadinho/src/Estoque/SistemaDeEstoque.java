package Estoque;

	// Superclasse Produto
	public class Produto {
		
		// Declaração de atributos
	    private String nome;
	    private int quantidade;
	    private double preco;
	    private String codigoBarras;
	    private String categoria;
	    private boolean disponivel;

	    //Construtor da classe principal
	    public Produto(String nome, int quantidade, double preco, String codigoBarras, String categoria) {
	        this.nome = nome;
	        this.quantidade = quantidade;
	        this.preco = preco;
	        this.codigoBarras = codigoBarras;
	        this.categoria = categoria;
	        this.disponivel = quantidade > 0;
	    }
	    
	    // Informações que serão exibidas após primeira chamada.
	    public void exibirInformacoes() {
	        System.out.println("=== INFORMAÇÕES DO PRODUTO ===");
	        System.out.println("Nome: " + nome);
	        System.out.println("Categoria: " + categoria);
	        System.out.println("Código de Barras: " + codigoBarras);
	        System.out.println("Preço: R$" + preco);
	        System.out.println("Quantidade em estoque: " + quantidade);
	        System.out.println("Disponível para venda: " + (disponivel ? "Sim" : "Não"));
	        System.out.println("==============================");
	    }

	    public void vender(int quantidadeVendida) {
	        if (quantidadeVendida <= quantidade && quantidadeVendida > 0) {
	            quantidade -= quantidadeVendida;
	            System.out.println("Venda realizada: " + quantidadeVendida + " unidades.");
	        } else {
	            System.out.println(" Estoque insuficiente ou quantidade inválida.");
	        }
	        atualizarDisponibilidade();
	    }

	    public void repor(int quantidadeReposicao) {
	        if (quantidadeReposicao > 0) {
	            quantidade += quantidadeReposicao;
	            System.out.println("Reposição realizada: " + quantidadeReposicao + " unidades.");
	        } else {
	            System.out.println("Quantidade de reposição inválida.");
	        }
	        atualizarDisponibilidade();
	    }

	    private void atualizarDisponibilidade() {
	        disponivel = quantidade > 0;
	    }

	    public void exibirEstoqueAtual() {
	        System.out.println("Estoque atual: " + quantidade + " unidades.");
	    }
	}
