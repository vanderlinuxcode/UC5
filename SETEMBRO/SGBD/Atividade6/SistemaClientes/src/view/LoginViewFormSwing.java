package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Tela de login simples com validação de usuário e senha.
 */
public class LoginViewFormSwing extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextField usuarioField;
    private JPasswordField senhaField;

    // Simulação de usuários válidos
    private Map<String, String> usuariosValidos = new HashMap<>();

    public LoginViewFormSwing() {
        setTitle("🔐 Login - Sistema de Clientes");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Usuários válidos (você pode adicionar mais)
        usuariosValidos.put("admin", "1234");
        usuariosValidos.put("usuario", "senha");

        add(createLoginPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
    }

    /**
     * Cria o painel com campos de usuário e senha.
     */
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Autenticação",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 14),
            Color.DARK_GRAY
        ));

        usuarioField = new JTextField();
        senhaField = new JPasswordField();

        panel.add(new JLabel("Usuário:"));
        panel.add(usuarioField);
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);

        return panel;
    }

    /**
     * Cria o painel com botão de login.
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(0, 120, 215));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);

        btnEntrar.addActionListener(e -> autenticar());

        panel.add(btnEntrar);
        return panel;
    }

    /**
     * Valida o login e abre a tela principal se for bem-sucedido.
     */
    private void autenticar() {
        String usuario = usuarioField.getText().trim();
        String senha = new String(senhaField.getPassword());

        if (usuariosValidos.containsKey(usuario) && usuariosValidos.get(usuario).equals(senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            dispose(); // fecha a tela de login
            new ClienteViewFormSwing().setVisible(true); // abre a tela principal
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.");
            senhaField.setText(""); // limpa a senha
            senhaField.requestFocusInWindow(); // foca no campo senha
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
