
package controller;

import dao.ClienteDAO;
import model.Cliente;
import java.util.List;

public class ClienteController {
	private ClienteDAO dao;

	public ClienteController() {
		dao = new ClienteDAO();
	}

	public void cadastrarCliente(Cliente cliente) {
		if (cliente.getNome().isEmpty() || cliente.getIdade() <= 0 || cliente.getEmail().isEmpty()
				|| cliente.getTelefone().isEmpty() || cliente.getCpf().isEmpty()) {
			System.out.println("Todos os campos são obrigatórios.");
			return;
		}
		if (!cliente.getEmail().contains("@")) {
			System.out.println("Email inválido.");
			return;
		}
		if (cliente.getCpf().length() != 14) {
			System.out.println("CPF deve conter 14 dígitos contendo ponto e traço.");
			return;
		}
		dao.inserir(cliente);
	}

	public void listarClientes() {
        List<Cliente> clientes = dao.listar();
        System.out.println("\n\n ------ CLIENTES CADASTRADOS ------- ");
        System.out.println("ID     NOME          IDADE        EMAIL              TELEFONE          CPF");
        for (Cliente c : clientes) {
            //System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome() + " | Idade: " + c.getIdade() + 
             //                  " | Email: " + c.getEmail() + " | Telefone: " + c.getTelefone() +
            //                   " | CPF: " + c.getCpf());
             System.out.printf("%d | %-15s | %-2d | %-22s | %-15s | %-15s\n", c.getId(), c.getNome(), c.getIdade(), c.getEmail(), c.getTelefone(), c.getCpf());
        }
    }

	public void atualizarCliente(Cliente cliente) {
		dao.atualizar(cliente);
	}

	public void excluirCliente(int id) {
		dao.excluir(id);
	}
}