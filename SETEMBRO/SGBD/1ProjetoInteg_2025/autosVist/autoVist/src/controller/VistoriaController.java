package controller;

import dao.VistoriaDAO;
import exception.ValidacaoException;
import model.Vistoria;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VistoriaController {
    private final VistoriaDAO dao;

    public VistoriaController() {
        this.dao = new VistoriaDAO();
    }

    // CREATE — Cadastrar nova vistoria
    public int cadastrarVistoria(Vistoria vistoria) throws ValidacaoException {
        Map<String, String> erros = validarDados(vistoria);

        if (!erros.isEmpty()) {
            throw new ValidacaoException(erros);
        }

        dao.inserir(vistoria);
        return vistoria.getIdVistoria();
    }

    // READ — Listar todas as vistorias
    public List<Vistoria> listarVistorias() {
        return dao.listar();
    }

    // Validação dos dados da vistoria
    private Map<String, String> validarDados(Vistoria vistoria) {
        Map<String, String> erros = new LinkedHashMap<>();

        if (vistoria == null) {
            erros.put("vistoria", "Vistoria não pode ser nula.");
            return erros;
        }

        if (vistoria.getIdFuncionario() <= 0) {
            erros.put("idFuncionario", "ID do funcionário inválido.");
        }

        if (vistoria.getIdAgendamento() <= 0) {
            erros.put("idAgendamento", "ID do agendamento inválido.");
        }

        if (vistoria.getDataVistoria() == null || vistoria.getDataVistoria().isAfter(LocalDateTime.now())) {
            erros.put("dataVistoria", "Data da vistoria não pode ser futura.");
        }

        if (vistoria.getItensVerificados() == null || vistoria.getItensVerificados().isBlank()) {
            erros.put("itensVerificados", "Itens verificados são obrigatórios.");
        }

        if (vistoria.getObservacao() == null) {
            vistoria.setObservacao(""); // opcional, pode ser vazio
        }

        return erros;
    }
}
