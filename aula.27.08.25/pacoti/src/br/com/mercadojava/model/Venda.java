package br.com.mercadojava.model;


public class Venda {
    private Cliente cliente;
    private Produto produto;
    private int quantidade;

    public Venda(Cliente cliente, Produto produto, int quantidade) {
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double calcularTotal() {
        return produto.getPreco() * quantidade;
    }

    public void exibirResumo() {
        //System.out.println("=== RESUMO DA VENDA ===");
        System.out.printf("Cliente: %s%n", cliente.getNomeCliente());
        System.out.printf("Produto: %s%n", produto.getNomeProduto());
        System.out.printf("Quantidade: %d%n", quantidade);
        System.out.printf("Total: R$ %.2f%n", calcularTotal());
    }
}
