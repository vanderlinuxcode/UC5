package view;

import javax.swing.*;
import java.awt.*;

public class PainelPrincipalView extends JFrame {
    private static final long serialVersionUID = 1L;

    public PainelPrincipalView(String nomeUsuario, String tipoUsuario) {
        setTitle("Painel Principal - " + tipoUsuario);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        getContentPane().setBackground(new Color(240, 240, 240));
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Bem-vindo, " + nomeUsuario + "!", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JLabel tipoLabel = new JLabel("Tipo de usuário: " + tipoUsuario, SwingConstants.CENTER);
        tipoLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(tipoLabel, BorderLayout.CENTER);

        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSair.setBackground(new Color(200, 0, 0));
        btnSair.setForeground(Color.WHITE);
        btnSair.setFocusPainted(false);
        btnSair.setPreferredSize(new Dimension(100, 35));
        btnSair.addActionListener(e -> System.exit(0));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.add(btnSair);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
