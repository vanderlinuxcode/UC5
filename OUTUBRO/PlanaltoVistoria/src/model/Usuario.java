package model;

public class Usuario {
    private String loginUsuario;
    private String senhaUsuario;
    private String tipoUsuario;
    private Integer idClienteUsuario;
    private Integer idFuncionarioUsuario;

    public String getLoginUsuario() { return loginUsuario; }
    public void setLoginUsuario(String loginUsuario) { this.loginUsuario = loginUsuario; }

    public String getSenhaUsuario() { return senhaUsuario; }
    public void setSenhaUsuario(String senhaUsuario) { this.senhaUsuario = senhaUsuario; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public Integer getIdClienteUsuario() { return idClienteUsuario; }
    public void setIdClienteUsuario(Integer idClienteUsuario) { this.idClienteUsuario = idClienteUsuario; }

    public Integer getIdFuncionarioUsuario() { return idFuncionarioUsuario; }
    public void setIdFuncionarioUsuario(Integer idFuncionarioUsuario) { this.idFuncionarioUsuario = idFuncionarioUsuario; }
}
