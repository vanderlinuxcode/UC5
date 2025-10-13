package model;

import java.time.LocalDateTime;

public class Vistoria {
    private int idVistoria;
    private int idFuncionario;
    private int idAgendamento;
    private LocalDateTime dataVistoria;
    private String itensVerificados;
    private String observacao;

    // 🔹 Construtor vazio
    public Vistoria() {}

    // 🔹 Construtor completo
    public Vistoria(int idVistoria, int idFuncionario, int idAgendamento,
                    LocalDateTime dataVistoria, String itensVerificados, String observacao) {
        this.idVistoria = idVistoria;
        this.idFuncionario = idFuncionario;
        this.idAgendamento = idAgendamento;
        this.dataVistoria = dataVistoria;
        this.itensVerificados = itensVerificados;
        this.observacao = observacao;
    }

    // 🔹 Getters e Setters
    public int getIdVistoria() {
        return idVistoria;
    }

    public void setIdVistoria(int idVistoria) {
        this.idVistoria = idVistoria;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public LocalDateTime getDataVistoria() {
        return dataVistoria;
    }

    public void setDataVistoria(LocalDateTime dataVistoria) {
        this.dataVistoria = dataVistoria;
    }

    public String getItensVerificados() {
        return itensVerificados;
    }

    public void setItensVerificados(String itensVerificados) {
        this.itensVerificados = itensVerificados;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    // 🔹 toString para facilitar exibição
    @Override
    public String toString() {
        return "Vistoria{" +
                "idVistoria=" + idVistoria +
                ", idFuncionario=" + idFuncionario +
                ", idAgendamento=" + idAgendamento +
                ", dataVistoria=" + dataVistoria +
                ", itensVerificados='" + itensVerificados + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
