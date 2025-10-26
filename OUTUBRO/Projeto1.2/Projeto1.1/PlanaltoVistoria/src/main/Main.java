package main;

import view.LoginViewFormSwing;
import dao.ConexaoDAO;
import javax.swing.*;
import java.sql.Connection;

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
                LoginViewFormSwing view = new LoginViewFormSwing(conn);
                view.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao iniciar o sistema: " + ex.getMessage());
            }
        });
    }
}
