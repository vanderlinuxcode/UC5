package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pagamento {
    private int idPagamento;
    private int idCliente;
    private int idVistoria;
    private BigDecimal valor;
    private LocalDateTime dataPagamento;
    private String formaPagamento;
    private String statusPagamento;

    // 🔹 Construtor vazio
    public Pagamento() {}

    // 🔹 Construtor completo
    public Pagamento(int idPagamento, int idCliente, int idVistoria, BigDecimal valor,
                     LocalDateTime dataPagamento, String formaPagamento, String statusPagamento) {
        this.idPagamento = idPagamento;
        this.idCliente = idCliente;
        this.idVistoria = idVistoria;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.formaPagamento = formaPagamento;
        this.statusPagamento = statusPagamento;
    }

    // 🔹 Getters e Setters
    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVistoria() {
        return idVistoria;
    }

    public void setIdVistoria(int idVistoria) {
        this.idVistoria = idVistoria;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    // 🔹 toString para facilitar exibição
    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", idCliente=" + idCliente +
                ", idVistoria=" + idVistoria +
                ", valor=" + valor +
                ", dataPagamento=" + dataPagamento +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", statusPagamento='" + statusPagamento + '\'' +
                '}';
    }
}
