package main;

import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

//import view.LoginViewFormSwing;
import dao.ConexaoDAO;
import view.TelaInicialView;


public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.out.println("⚠️ Estilo Nimbus não pôde ser aplicado.");
        }

        SwingUtilities.invokeLater(() -> {
            try {
                Connection conn = ConexaoDAO.conectar();
                //LoginViewFormSwing view = new LoginViewFormSwing(conn);
                //view.setVisible(true);
              new TelaInicialView(conn).setVisible(true);
              //new LoginViewFormSwing(conn).setVisible(true); // tela de login

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao iniciar o sistema: " + ex.getMessage());
            }
        });
    }
}
