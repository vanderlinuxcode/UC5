package br.com.mercadojava.app;

import br.com.mercadojava.model.Cliente;
import br.com.mercadojava.model.ClientePessoaFisica;
import br.com.mercadojava.service.GerenciadorDeVendas;
import br.com.mercadojava.model.Produto;
import br.com.mercadojava.model.Venda;

public class MercadoApp {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente(10, "Vanderli", "123.321.543-54", "(61)98651-6466");
        Produto produto1 = new Produto(101, "Arroz 5kg", 22.90);
        Venda venda1 = new Venda(cliente1, produto1, 1);
        Cliente cliente2 = new ClientePessoaFisica(10, "Vanderli", "123.321.543-54", "(61)98651-6466", "MG-12.345.678");
        
        System.out.printf("ID/Nome: %s%n", cliente1.getNomeId());
        System.out.printf("CPF: %s%n", cliente1.getCpfCliente());
        System.out.printf("Telefone: %s%n", cliente1.getFoneCliente());
        System.out.println("========================");
        
        venda1.exibirResumo();

		System.out.println("============================");
        System.out.println(cliente2.getNomeId());
		System.out.println("============================");
        GerenciadorDeVendas vendas = new GerenciadorDeVendas();
        
        vendas.registrarVenda(venda1);
        vendas.exibirHistorico();
                
    }
}
