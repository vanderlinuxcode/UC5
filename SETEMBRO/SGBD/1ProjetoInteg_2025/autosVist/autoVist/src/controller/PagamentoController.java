package controller;

import dao.PagamentoDAO;
import exception.ValidacaoException;
import model.Pagamento;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PagamentoController {
    private final PagamentoDAO dao;

    public PagamentoController() {
        this.dao = new PagamentoDAO();
    }

    // CREATE — Cadastrar novo pagamento
    public int cadastrarPagamento(Pagamento pagamento) throws ValidacaoException, SQLException {
        Map<String, String> erros = validarDados(pagamento);

        if (!erros.isEmpty()) {
            throw new ValidacaoException(erros);
        }

        dao.inserir(pagamento);
        return pagamento.getIdPagamento();
    }

    // READ — Listar todos os pagamentos
    public List<Pagamento> listarPagamentos() throws SQLException {
        return dao.listar();
    }

    // Validação dos dados do pagamento
    private Map<String, String> validarDados(Pagamento pagamento) {
        Map<String, String> erros = new LinkedHashMap<>();

        if (pagamento == null) {
            erros.put("pagamento", "Pagamento não pode ser nulo.");
            return erros;
        }

        if (pagamento.getIdCliente() <= 0) {
            erros.put("idCliente", "ID do cliente inválido.");
        }

        if (pagamento.getIdVistoria() <= 0) {
            erros.put("idVistoria", "ID da vistoria inválido.");
        }

        if (pagamento.getValor() == null || pagamento.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            erros.put("valor", "Valor do pagamento deve ser maior que zero.");
        }

        if (pagamento.getDataPagamento() == null || pagamento.getDataPagamento().isAfter(LocalDateTime.now())) {
            erros.put("dataPagamento", "Data do pagamento não pode ser futura.");
        }

        if (pagamento.getFormaPagamento() == null || pagamento.getFormaPagamento().isBlank()) {
            erros.put("formaPagamento", "Forma de pagamento é obrigatória.");
        }

        if (pagamento.getStatusPagamento() == null || pagamento.getStatusPagamento().isBlank()) {
            erros.put("statusPagamento", "Status do pagamento é obrigatório.");
        }

        return erros;
}
}