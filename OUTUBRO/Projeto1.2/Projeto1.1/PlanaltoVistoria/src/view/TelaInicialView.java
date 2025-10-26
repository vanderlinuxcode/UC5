package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;

public class TelaInicialView extends JFrame {
    private static final long serialVersionUID = 1L;
    private Connection conn;

    public TelaInicialView(Connection conn) {
        this.conn = conn;
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Sistema de Clientes");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Planalto Vistoria LTDA", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel botoesPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        botoesPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        botoesPanel.setBackground(new Color(245, 245, 245));

        JButton btnCadastrar = new JButton("Cadastrar Usuário");
        btnCadastrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCadastrar.addActionListener(this::abrirCadastro);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLogin.addActionListener(this::abrirLogin);

        botoesPanel.add(btnCadastrar);
        botoesPanel.add(btnLogin);

        add(botoesPanel, BorderLayout.CENTER);
    }

    private void abrirCadastro(ActionEvent e) {
        dispose();
        new CadastroUsuarioView(conn).setVisible(true);        
    }

    private void abrirLogin(ActionEvent e) {
        dispose();
        new LoginViewFormSwing(conn).setVisible(true);
    }
}
