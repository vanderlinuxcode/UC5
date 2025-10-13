package model;

public class Funcionario {
    private int idFuncionario;
    private String nome;
    private String cpf;
    private String cargo;
    private String telefone;

    public Funcionario() {}
    
    // 🔹 Construtor completo
    public Funcionario(int idFuncionario, String nome, String cpf, String cargo, String telefone) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.telefone = telefone;
    }

    // 🔹 Getters e Setters
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // 🔹 toString para facilitar exibição
    @Override
    public String toString() {
        return "Funcionario{" +
                "idFuncionario=" + idFuncionario +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cargo='" + cargo + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
