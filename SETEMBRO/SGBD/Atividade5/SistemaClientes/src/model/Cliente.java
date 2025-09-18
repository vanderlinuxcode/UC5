package model;

// Pode ser chamado também de superclasse


public class Cliente {
	//Atributos encapsulados
	private int id;
	private String nome;
	private int idade;
	private String email;
	private String telefone;
	private String cpf;
	
	//Construtor da classe
	public Cliente() {}
	
	public Cliente(int id, String nome, int idade, String email, String telefone, String cpf) {
		
		// referencia os atributos da aplicação para os métodos getters e setters
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.email = email;
		this.telefone = telefone;
		this.cpf = cpf;		
	}

	// Método getter 
	public int getId() { return id;	}
	public String getNome() { return nome; }
	public int getIdade() { return idade; }
	public String getEmail() { return email; }
	public String getTelefone() { return telefone; }
	public String getCpf() { return cpf; }

	// Método setter
	public void setId(int id) {	this.id = id; }
	public void setNome(String nome) { this.nome = nome; }
	public void setIdade(int idade) { this.idade = idade; }
	public void setEmail(String email) { this.email = email; }
	public void setTelefone(String telefone) { this.telefone = telefone; }
	public void setCpf(String cpf) { this.cpf = cpf; }
}
