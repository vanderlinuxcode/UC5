package view;

import java.util.Scanner;

public class LoginViewConsole implements LoginView {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String[] solicitarCredenciais() {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        return new String[]{login, senha};
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public String solicitarTipoUsuario() {
        System.out.println("Selecione o tipo de usuário:");
        System.out.println("1 - Externo");
        System.out.println("2 - Cliente");
        System.out.println("3 - Funcionario");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // limpa o buffer

        return switch (opcao) {
            case 1 -> "Externo";
            case 2 -> "Cliente";
            case 3 -> "Funcionario";
            default -> {
                System.out.println("Tipo inválido. Usando 'Externo' por padrão.");
                yield "Externo";
            }
        };
    }
}
