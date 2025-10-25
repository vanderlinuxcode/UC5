package controller;

import java.util.Scanner;

import dao.UsuarioDAO;
import model.UsuarioModel;
import util.SegurancaUtils;
import util.Validador;

public class CadastroClienteController {
    private UsuarioDAO usuarioDAO;
    private Scanner scanner;

    public CadastroClienteController(UsuarioDAO usuarioDAO, Scanner scanner) {
        this.usuarioDAO = usuarioDAO;
        this.scanner = scanner;
    }
    
    public void cadastrarNovoCliente() {
        try {
            UsuarioModel novoCliente = new UsuarioModel();

            System.out.println("\n=== Cadastro de Novo Cliente ===");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            if (!Validador.nomeValido(nome)) {
                System.out.println("❌ Nome inválido.");
                return;
            }
            novoCliente.setNome(nome);

            System.out.print("E-mail: ");
            String email = scanner.nextLine();
            if (!Validador.emailValido(email)) {
                System.out.println("❌ E-mail inválido.");
                return;
            }
            novoCliente.setEmail(email);

            System.out.print("CPF: ");
            String cpf = scanner.nextLine().replaceAll("[^\\d]", ""); // remove tudo que não for número

//            if (!Validador.cpfValido(cpf)) {
//                System.out.println("❌ CPF inválido.");
//                System.out.println("CPF limpo: " + cpf);
//
//                return;
//            }

            novoCliente.setCpf(cpf);
                      
                       
            System.out.print("Senha: ");
            String senha = scanner.nextLine();
            if (senha.isBlank()) {
                System.out.println("❌ Senha não pode estar em branco.");
                return;
            }
           

            System.out.print("Confirme a senha: ");
            String confirmacao = scanner.nextLine();
                       
            if (!senha.equals(confirmacao)) {
                System.out.println("❌ As senhas não coincidem.");
                return;
            }
            
//            if (!Validador.senhaValida(senha)) {
//                System.out.println("⚠️ A senha deve ter pelo menos 6 caracteres, com letras e números.");
//                return;
//            }
            
            String senhaCriptografada = SegurancaUtils.criptografarSenha(senha);
            novoCliente.setSenha(senhaCriptografada);
           
            boolean sucesso = usuarioDAO.cadastrarCliente(novoCliente);
            if (sucesso) {
                System.out.println("✅ Cliente cadastrado com sucesso.");
            } else {
                System.out.println("❌ Falha ao cadastrar cliente.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }    
}
