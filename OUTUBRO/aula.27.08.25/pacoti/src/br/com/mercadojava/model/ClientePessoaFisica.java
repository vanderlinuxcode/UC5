package br.com.mercadojava.model;

// Subclasse cliente pessoa física.

public class ClientePessoaFisica extends Cliente {
	private String rg;

	public ClientePessoaFisica(int idCliente, String nomeCliente, String cpfCliente, String foneCliente, String rg) {
		super(idCliente, nomeCliente, cpfCliente, foneCliente);
		this.rg = rg;
	}

	public String getRg() {
		return rg;
	}

	@Override
	public String getNomeId() {
		return "Pessoa Física: " + super.getNomeId() + " | RG: " + rg;
	}
}