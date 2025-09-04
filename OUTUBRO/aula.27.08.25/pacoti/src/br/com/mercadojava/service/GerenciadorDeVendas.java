package br.com.mercadojava.service;

import br.com.mercadojava.model.Venda;
import java.util.ArrayList;
import java.util.List;

 // Classe de serviço que gerencia o histórico de vendas. 
public class GerenciadorDeVendas {

	// Lista que armazena todas as vendas realizadas
	private List<Venda> historicoDeVendas = new ArrayList<>();

	// Registra uma nova venda no sistema.
	public void registrarVenda(Venda venda) {
		historicoDeVendas.add(venda);
	// System.out.println("Venda registrada com sucesso!");
	}

	// Exibe todas as vendas realizadas até o momento.
	public void exibirHistorico() {
		System.out.println("=== HISTÓRICO DE VENDAS ===");
		if (historicoDeVendas.isEmpty()) {
			System.out.println("Nenhuma venda registrada.");
		} else {
			System.out.println("============================");
			for (Venda venda : historicoDeVendas) {
				venda.exibirResumo();
			}
		}
		System.out.println("============================");
	}

	// informa a lista de vendas
	public List<Venda> getHistoricoDeVendas() {
		return historicoDeVendas;
	}
}
