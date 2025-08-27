package br.com.mercadojava.model;

	// Classe Produto 
public class Produto {
    private int idProduto;
    private String nomeProduto;
    private double preco;

    public Produto(int idProduto, String nomeProduto, double preco) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public double getPreco() {
        return preco;
    }
}
