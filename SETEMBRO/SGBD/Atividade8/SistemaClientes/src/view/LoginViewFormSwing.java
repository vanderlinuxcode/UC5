package view;

import controller.LoginController;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LoginViewFormSwing extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField usuarioField;
    private JPasswordField senhaField;
    private LoginController loginController = new LoginController();
    private Map<String, String> usuariosValidos = new HashMap<>();

    public LoginViewFormSwing() {
        setTitle("Login - Sistema de Clientes");
        setSize(420, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        usuariosValidos.put("admin", "123456");
        usuariosValidos.put("usuario", "senha");

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
        usuarioField = new JTextField(20);
        senhaField = new JPasswordField(20);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(usuarioLabel, gbc);
        gbc.gridx = 1;
        panel.add(usuarioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(senhaLabel, gbc);
        gbc.gridx = 1;
        panel.add(senhaField, gbc);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnEntrar.setBackground(new Color(0, 120, 215));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);
        btnEntrar.setPreferredSize(new Dimension(100, 35));

        btnEntrar.addActionListener(e -> autenticar());

        panel.add(btnEntrar);
        return panel;
    }

    private void autenticar() {
        String usuario = usuarioField.getText().trim();
        String senha = new String(senhaField.getPassword());

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String senhaCorreta = usuariosValidos.get(usuario);
        if (senhaCorreta != null && senhaCorreta.equals(senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new ClienteViewFormSwing().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            senhaField.setText("");
            senhaField.requestFocusInWindow();

            System.out.println("Usuário: " + usuario);
            System.out.println("Senha: " + senha);
        }
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new LoginViewFormSwing().setVisible(true));
    }
}
