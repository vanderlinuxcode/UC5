package br.com.mercadojava.model;

public class Cliente {
	// Atributos privados para encapsulamento
    private int idCliente;
    private String nomeCliente;
    private String cpfCliente;
    private String foneCliente;

    // Construtor da classe Cliente
    public Cliente(int idCliente, String nomeCliente, String cpfCliente, String foneCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.foneCliente = foneCliente;
    }
    
    // Métodos getters para acesso aos atributos
    public int getIdCliente() {
        return idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public String getFoneCliente() {
        return foneCliente;
    }

    // Retorna uma string ID e nome Cliente
    public String getNomeId() {
        return idCliente + " - " + nomeCliente;
    }
}
