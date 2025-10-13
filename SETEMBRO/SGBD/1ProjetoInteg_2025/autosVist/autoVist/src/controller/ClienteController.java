package controller;

import exception.ValidacaoException;
import model.Cliente;
import java.util.LinkedHashMap;
import java.util.Map;
import dao.ClienteDAO;

public class ClienteController {
	private final ClienteDAO dao = new ClienteDAO();

	public int cadastrarCliente(Cliente cliente) throws ValidacaoException {
	    Map<String, String> erros = validar(cliente);
	    if (!erros.isEmpty()) {
	        throw new ValidacaoException(erros);
	    }
	    dao.inserir(cliente);
	    return cliente.getIdCliente();
	}

		
	public Cliente buscarClientePorCpf(String cpf) throws ValidacaoException {
		if (cpf == null || cpf.isBlank()) {
			throw new ValidacaoException(Map.of("cpf", "CPF não pode ser vazio."));
		}
		Cliente cliente = dao.buscarPorCpf(cpf);
		if (cliente == null) {
			throw new ValidacaoException(Map.of("cpf", "Cliente não encontrado."));
		}
		return cliente;
	}

	public void removerClientePorCpf(String cpf) throws ValidacaoException {
		if (cpf == null || cpf.isBlank()) {
			throw new ValidacaoException(Map.of("cpf", "CPF não pode ser vazio."));
		}
		dao.excluirPorCpf(cpf);
	}

	public void atualizarCliente(Cliente cliente) throws ValidacaoException {
		Map<String, String> erros = validar(cliente);
		if (!erros.isEmpty()) {
			throw new ValidacaoException(erros);
		}
		dao.atualizar(cliente);
	}

	private Map<String, String> validar(Cliente cliente) {
		Map<String, String> erros = new LinkedHashMap<>();

		if (cliente.getNome() == null || cliente.getNome().isBlank()) {
			erros.put("nome", "Nome é obrigatório.");
		}
		if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
			erros.put("cpf", "CPF é obrigatório.");
		}
		if (cliente.getTelefone() == null || cliente.getTelefone().isBlank()) {
			erros.put("telefone", "Telefone é obrigatório.");
		}
		if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
			erros.put("email", "Email é obrigatório.");
		}

		return erros;
	}
}
