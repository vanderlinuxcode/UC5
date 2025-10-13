package app;

import dao.CadastroDAO;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.UsuarioDAO;
import model.Cliente;
import model.Funcionario;
import model.Usuario;
import view.ClienteView;
import view.FuncionarioView;
import java.util.Scanner;

import javax.swing.SwingUtilities;
//import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import view.LoginFrameView;
import view.LoginViewFormSwing;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(() -> new LoginViewFormSwing().setVisible(true));
		javax.swing.SwingUtilities.invokeLater(()-> new LoginFrameView());
		try (Scanner scanner = new Scanner(System.in)) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			CadastroDAO cadastroDAO = new CadastroDAO();
			ClienteDAO clienteDAO = new ClienteDAO();
			while (true) {
				System.out.println("\n=== MENU INICIAL ===");
				System.out.println("1 - Cadastrar novo cliente");
				System.out.println("2 - Fazer login");
				System.out.println("0 - Sair");
				System.out.print("Escolha: ");
				String opcao = scanner.nextLine();

				switch (opcao) {
				case "1" -> {
					System.out.println("\n--- Cadastro de Cliente ---");

					System.out.print("Nome: ");
					String nome = scanner.nextLine();

					System.out.print("CPF (será usado como login): ");
					String cpf = scanner.nextLine();

					if (clienteDAO.buscarPorCpf(cpf) != null) {
						System.out.println("❌ CPF já cadastrado.");
						break;
					}

					System.out.print("Telefone: ");
					String telefone = scanner.nextLine();

					System.out.print("Email: ");
					String email = scanner.nextLine();

					System.out.print("Senha: ");
					String senha = scanner.nextLine();

					Cliente cliente = new Cliente();
					cliente.setNome(nome);
					cliente.setCpf(cpf);
					cliente.setTelefone(telefone);
					cliente.setEmail(email);

					Usuario usuario = new Usuario();
					usuario.setLogin(cpf); // CPF como login
					usuario.setSenha(senha);
					usuario.setTipo("cliente");

					try {
						cadastroDAO.cadastrarClienteComUsuario(cliente, usuario);
						System.out.println("✅ Cadastro realizado com sucesso!");
					} catch (Exception e) {
						System.out.println("Erro ao cadastrar: " + e.getMessage());
					}
				}

				case "2" -> {
					System.out.println("\n--- Login ---");
					System.out.print("CPF (login): ");
					String login = scanner.nextLine();

					System.out.print("Senha: ");
					String senha = scanner.nextLine();

					try {
						Usuario usuario = usuarioDAO.buscarPorLogin(login);

						if (usuario != null && usuario.getSenha().equals(senha)) {
							if ("cliente".equalsIgnoreCase(usuario.getTipo())) {
								Cliente cliente = clienteDAO.buscarPorId(usuario.getIdCliente());
								if (cliente != null) {
									ClienteView clienteView = new ClienteView();
									clienteView.exibirMenuCliente(cliente); // ✅ redireciona para menu do cliente
									return;

								} else {
									System.out.println("❌ Cliente não encontrado.");
								}
							} else if ("funcionario".equalsIgnoreCase(usuario.getTipo())) {
								Funcionario funcionario = funcionarioDAO.buscarPorId(usuario.getIdUsuario());

								if (funcionario != null) {
									FuncionarioView funcionarioView = new FuncionarioView();
									funcionarioView.exibirMenu(funcionario);
									return; // evita voltar ao menu inicial
								} else {
									System.out.println("❌ Funcionário não encontrado.");
								}

							} else {
								System.out.println("⚠️ Tipo de usuário desconhecido.");
							}
						} else {
							System.out.println("❌ CPF ou senha inválidos.");
						}
					} catch (Exception e) {
						System.out.println("Erro ao autenticar: " + e.getMessage());
					}
				}
				}
			}
		}
	}
}
