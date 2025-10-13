package model;

public class Veiculo {
    private int idVeiculo;
    private String placa;
    private String marca;
    private String modelo;
    private String ano;
    private String numeroChassi;
    private int IdCliente;

    public Veiculo() {}
    
        // 🔹 Construtor completo
    public Veiculo(int idVeiculo, String placa, String marca, String modelo, String ano, String numeroChassi, int IdCliente) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.numeroChassi = numeroChassi;
        this.IdCliente = IdCliente;
    }

   	// 🔹 Getters e Setters
    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getNumeroChassi() {
        return numeroChassi;
    }

    public void setNumeroChassi(String numeroChassi) {
        this.numeroChassi = numeroChassi;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    // 🔹 toString para facilitar exibição
//    @Override
//    public String toString() {
//        return "Veiculo{" +
//                "idVeiculo=" + idVeiculo +
//                ", placa='" + placa + '\'' +
//                ", marca='" + marca + '\'' +
//                ", modelo='" + modelo + '\'' +
//                ", ano='" + ano + '\'' +
//                ", numeroChassi='" + numeroChassi + '\'' +
//                ", clienteId=" + clienteId +
//                '}';
//    }
	public void setIdAgendamento(int idGerado) {
		// TODO Auto-generated method stub
		
	}
	
	public int getIdFuncionario() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getTipoServico() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDataAgendamento() {
		// TODO Auto-generated method stub
		return null;
	}
}
