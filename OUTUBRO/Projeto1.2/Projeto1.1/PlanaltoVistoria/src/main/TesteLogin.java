package main;

import java.sql.Connection;

import controller.UsuarioLoginController;
import dao.ConexaoDAO;

public class TesteLogin {
    public static void main(String[] args) {
        try {
            Connection conn = ConexaoDAO.conectar(); // sua classe de conexão
            UsuarioLoginController loginController = new UsuarioLoginController(conn);
//
//            String cpf = "11122233344"; // sem pontuação
//            String senha = "senha123";
            
            String cpf = "12345678900"; // sem pontuação
            String senha = "123456";

            boolean sucesso = loginController.loginGUI(cpf, senha);

            if (sucesso) {
                System.out.println("✅ Login realizado com sucesso.");
            } else {
                System.out.println("❌ Falha no login.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
