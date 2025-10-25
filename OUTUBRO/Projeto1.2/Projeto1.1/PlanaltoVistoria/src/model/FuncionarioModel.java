package model;

public class FuncionarioModel {
    private int idFuncionario;
    private String cargo; // VISTORIADOR, CONTABIL, GERENTE

    private UsuarioModel usuario;

    // Getters e Setters
    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public UsuarioModel getUsuario() { return usuario; }
    public void setUsuario(UsuarioModel usuario) { this.usuario = usuario; }
}
