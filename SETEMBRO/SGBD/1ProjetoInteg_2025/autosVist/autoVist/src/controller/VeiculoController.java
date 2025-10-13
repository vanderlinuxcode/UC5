package controller;

import dao.VeiculoDAO;
import model.Veiculo;
import exception.ValidacaoException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VeiculoController {
    private final VeiculoDAO dao;

    public VeiculoController() throws ValidacaoException {
        this.dao = new VeiculoDAO();
    }

    public void cadastrarVeiculo(String placa, String marca, String modelo, String ano, String numeroChassi, int clienteId) throws ValidacaoException {
        Map<String, String> erros = validarDados(placa, marca, modelo, ano, numeroChassi, clienteId);

        if (!erros.isEmpty()) {
            throw new ValidacaoException(erros);
        }

        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(placa.trim().toUpperCase());
        veiculo.setMarca(marca.trim());
        veiculo.setModelo(modelo.trim());
        veiculo.setAno(ano.trim());
        veiculo.setNumeroChassi(numeroChassi.trim().toUpperCase());
        veiculo.setIdCliente(clienteId);

        dao.inserir(veiculo);
    }
    
    public boolean atualizarVeiculo(Veiculo veiculo) throws ValidacaoException {
        Map<String, String> erros = validarDados(
            veiculo.getPlaca(),
            veiculo.getMarca(),
            veiculo.getModelo(),
            veiculo.getAno(),
            veiculo.getNumeroChassi(),
            veiculo.getIdCliente()
        );

        if (!erros.isEmpty()) {
            throw new ValidacaoException(erros);
        }

        return dao.atualizar(veiculo);
    }

    public void excluirVeiculo(int idVeiculo) throws ValidacaoException {
        dao.excluir(idVeiculo);
    }

    public List<Veiculo> listarVeiculos() {
        return dao.listar();
    }

    public Veiculo buscarPorId(int idVeiculo) throws ValidacaoException {
        return dao.buscarPorId(idVeiculo);
    }

    private Map<String, String> validarDados(String placa, String marca, String modelo, String ano, String numeroChassi, int clienteId) {
        Map<String, String> erros = new LinkedHashMap<>();

        if (placa == null || !placa.matches("^[A-Z]{3}\\d{4}$")) {
            erros.put("placa", "Placa inválida. Use o formato AAA1234.");
        }

        if (marca == null || marca.isBlank()) {
            erros.put("marca", "Marca é obrigatória.");
        }

        if (modelo == null || modelo.isBlank()) {
            erros.put("modelo", "Modelo é obrigatório.");
        }

        if (ano == null || !ano.matches("^\\d{4}$")) {
            erros.put("ano", "Ano inválido. Use 4 dígitos.");
        }

        if (numeroChassi == null || !numeroChassi.matches("^[A-Z0-9]{17}$")) {
            erros.put("numeroChassi", "Número do chassi inválido. Deve conter 17 caracteres alfanuméricos.");
        }

        if (clienteId <= 0) {
            erros.put("clienteId", "Cliente associado inválido.");
        }

        return erros;
    }
}
