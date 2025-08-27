package br.com.mercadojava.service;

import br.com.mercadojava.model.Produto;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por controlar o estoque de produtos.
 */
public class GerenciadorDeEstoque {

    private Map<Produto, Integer> estoque = new HashMap<>();

    /**
     * Adiciona um produto ao estoque com a quantidade especificada.
     */
    public void adicionarProduto(Produto produto, int quantidade) {
        estoque.put(produto, estoque.getOrDefault(produto, 0) + quantidade);
    }

    /**
     * Remove uma quantidade de um produto do estoque.
     */
    public boolean removerProduto(Produto produto, int quantidade) {
        int atual = estoque.getOrDefault(produto, 0);
        if (atual >= quantidade) {
            estoque.put(produto, atual - quantidade);
            return true;
        }
        return false;
    }

    /**
     * Consulta a quantidade disponível de um produto.
     */
    public int consultarEstoque(Produto produto) {
        return estoque.getOrDefault(produto, 0);
    }

    /**
     * Exibe todos os produtos e suas quantidades no estoque.
     */
    public void exibirEstoque() {
        //System.out.println("=== ESTOQUE ATUAL ===");
        for (Map.Entry<Produto, Integer> entry : estoque.entrySet()) {
            System.out.printf("Produto: %s | Quantidade: %d%n", entry.getKey().getNomeProduto(), entry.getValue());
        }
        System.out.println("======================");
    }
}
