
package controller;

import dao.ClienteDAO;
import model.Cliente;
import exception.ValidacaoException;
import java.util.List;

public class ClienteController {
	private ClienteDAO dao;

	public ClienteController() {
		dao = new ClienteDAO();
	}

	public void cadastrarCliente(Cliente cliente) {
		validarCliente(cliente);
		dao.inserir(cliente);
	}

	private void validarCliente(Cliente cliente) {
		if (cliente.getNome().isEmpty()) {
			throw new ValidacaoException("Nome é obrigatório.");
		}
		if (cliente.getIdade() <= 0) {
			throw new ValidacaoException("Idade deve ser maior que zero.");
		}
		if (cliente.getEmail().isEmpty() || !cliente.getEmail().contains("@")) {
			throw new ValidacaoException("Email inválido.");
		}
		if (cliente.getTelefone().isEmpty()) {
			throw new ValidacaoException("Telefone é obrigatório.");
		}
		if (!cliente.getCpf().matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
			throw new ValidacaoException("CPF deve estar no formato XXX.XXX.XXX-XX.");
		}

	}

	private String formatarCpf(String cpf) {
		cpf = cpf.replaceAll("\\D", ""); // remove tudo que não é número
		if (cpf.length() == 11) {
			return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
		}
		return cpf;
	}

	public boolean atualizarCliente(Cliente cliente) {
		cliente.setCpf(formatarCpf(cliente.getCpf())); // formata antes de validar
		validarCliente(cliente);
		return dao.atualizar(cliente);
	}

	public List<Cliente> listarClientes() {
		List<Cliente> clientes = dao.listar();
		System.out.println("\n\n --------------------- CLIENTES CADASTRADOS ------------------------- ");
		System.out.println("ID     NOME          IDADE        EMAIL              TELEFONE          CPF");
		for (Cliente c : clientes) {
			System.out.printf("%d | %-15s | %-2d | %-22s | %-15s | %-15s\n", c.getId(), c.getNome(), c.getIdade(),
					c.getEmail(), c.getTelefone(), c.getCpf());
		}
		return clientes;
	}

	public void excluirCliente(int id) {
		dao.excluir(id);
	}
}