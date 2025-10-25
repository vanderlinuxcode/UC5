package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewFormSwing extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField cpfField;
    private JPasswordField senhaField;

    public LoginViewFormSwing() {
        setTitle("Login - Sistema de Clientes");
        setSize(420, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createLoginPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JLabel titulo = new JLabel("Acesso ao Sistema");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(245, 245, 245));
        header.add(titulo, BorderLayout.CENTER);
        return header;
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        panel.setBackground(new Color(245, 245, 245));

        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField();

        JLabel senhaLabel = new JLabel("Senha:");
        senhaField = new JPasswordField();

        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(senhaLabel);
        panel.add(senhaField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));

        JButton loginButton = new JButton("Entrar");
        loginButton.setFocusPainted(false);
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticarUsuario();
            }
        });

        panel.add(loginButton);
        return panel;
    }

    private void autenticarUsuario() {
        String cpf = cpfField.getText().replaceAll("[^\\d]", "");
        String senha = new String(senhaField.getPassword());

        if (cpf.isBlank() || senha.isBlank()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Aqui você pode integrar com seu controller real
        // Exemplo fictício de validação:
        boolean autenticado = cpf.equals("12345678909") && senha.equals("senha123");

        if (autenticado) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // fecha a tela de login
            // redirecionar para painel principal...
        } else {
            JOptionPane.showMessageDialog(this, "CPF ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginViewFormSwing login = new LoginViewFormSwing();
            login.setVisible(true);
        });
    }
}
