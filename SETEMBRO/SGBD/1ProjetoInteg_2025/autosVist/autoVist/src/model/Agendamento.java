package model;

import java.time.LocalDateTime;

public class Agendamento {
	private int idAgendamento;
	private int idVeiculo;
	private int idFuncionario;
	private int idCliente;
	private LocalDateTime dataAgendamento;
	private String tipoServico;
	private String relatorioVistoria;
	private double valorPago;
	private boolean pagamentoRealizado;
	private boolean vistoriaRealizada;

	public Agendamento() {}
	// Construtores
	public Agendamento(int idAgendamento, int idVeiculo, int idFuncionario, int idCliente,
			LocalDateTime dataAgendamento, String tipoServico) {
		this.idAgendamento = idAgendamento;
		this.idVeiculo = idVeiculo;
		this.idFuncionario = idFuncionario;
		this.idCliente = idCliente;
		this.dataAgendamento = dataAgendamento;
		this.tipoServico = tipoServico;
	}

	// Getters e Setters
	public int getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(int idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public int getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(int idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public LocalDateTime getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDateTime dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(String tipoServico) {
		this.tipoServico = tipoServico;
	}

	public String getRelatorioVistoria() {
		return relatorioVistoria;
	}

	public void setRelatorioVistoria(String relatorioVistoria) {
		this.relatorioVistoria = relatorioVistoria;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public boolean isPagamentoRealizado() {
		return pagamentoRealizado;
	}

	public void setPagamentoRealizado(boolean pagamentoRealizado) {
		this.pagamentoRealizado = pagamentoRealizado;
	}

	public boolean isVistoriaRealizada() {
		return vistoriaRealizada;
	}

	public void setVistoriaRealizada(boolean vistoriaRealizada) {
		this.vistoriaRealizada = vistoriaRealizada;
	}
}
