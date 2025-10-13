package view;

import controller.AutenticacaoController;
import controller.UsuarioController;
import model.Usuario;

import java.util.Scanner;

public class AutenticacaoView {
    private final Scanner scanner = new Scanner(System.in);
    private final AutenticacaoController autenticacaoController = new AutenticacaoController();
    private final UsuarioController usuarioController = new UsuarioController();

    public Usuario autenticarOuCadastrar() {
        while (true) {
            System.out.println("\n=== AUTENTICAÇÃO ===");
            System.out.println("1 - Já tenho conta (Login)");
            System.out.println("2 - Criar nova conta");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> {
                    return realizarLogin();
                }
                case "2" -> {
                    return realizarCadastro();
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private Usuario realizarLogin() {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        try {
            Usuario usuario = autenticacaoController.autenticar(login, senha);
            System.out.println("Login realizado com sucesso!");
            return usuario;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    private Usuario realizarCadastro() {
        System.out.print("Novo login: ");
        String login = scanner.nextLine();

        System.out.print("Nova senha: ");
        String senha = scanner.nextLine();

        System.out.println("Tipo de usuário:");
        System.out.println("1 - Cliente");
        System.out.println("2 - Funcionário");
        System.out.print("Escolha uma opção: ");
        int tipoOpcao;

        try {
            tipoOpcao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Use apenas números.");
            return null;
        }

        String tipo;
        switch (tipoOpcao) {
            case 1 -> tipo = "cliente";
            case 2 -> tipo = "funcionario";
            default -> {
                System.out.println("Opção inválida. Cadastro cancelado.");
                return null;
            }
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setTipo(tipo);

        try {
            usuarioController.cadastrarUsuario(usuario);
            System.out.println("Cadastro realizado com sucesso!");
            return usuario;
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return null;
        }
    }
}
