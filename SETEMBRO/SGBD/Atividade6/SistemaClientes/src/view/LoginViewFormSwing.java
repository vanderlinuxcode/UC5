package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LoginViewFormSwing extends JFrame {
    // Campos de entrada
    private JTextField usuarioField;
    private JPasswordField senhaField;

    // Mapa com usuários válidos simulados
    private Map<String, String> usuariosValidos = new HashMap<>();

    public LoginViewFormSwing() {
        // Define título da janela
        setTitle("Login - Sistema de Clientes");

        // Define tamanho e posicionamento
        setSize(420, 250);
        setLocationRelativeTo(null); // centraliza na tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // impede redimensionamento

        // Adiciona usuários válidos (simulação)
        usuariosValidos.put("admin", "123456");
        usuariosValidos.put("usuario", "senha");

        // Define cor de fundo da janela
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        // Adiciona os painéis à janela
        add(createHeader(), BorderLayout.NORTH);     // título
        add(createLoginPanel(), BorderLayout.CENTER); // campos
        add(createButtonPanel(), BorderLayout.SOUTH); // botão
    }

    /**
     * Cria o cabeçalho com título estilizado.
     */
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

    /**
     * Cria o painel com campos de usuário e senha.
     */
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // espaçamento entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Rótulo e campo de usuário
        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        usuarioField = new JTextField(20);

        // Rótulo e campo de senha
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        senhaField = new JPasswordField(20);

        // Posiciona os componentes no grid
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(usuarioLabel, gbc);
        gbc.gridx = 1;
        panel.add(usuarioField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(senhaLabel, gbc);
        gbc.gridx = 1;
        panel.add(senhaField, gbc);

        return panel;
    }

    /**
     * Cria o painel com botão de login.
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnEntrar.setBackground(new Color(0, 120, 215)); // azul
        btnEntrar.setForeground(Color.WHITE); // texto branco
        btnEntrar.setFocusPainted(false);
        btnEntrar.setPreferredSize(new Dimension(100, 35));

        // Ação ao clicar no botão
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

        // Verifica se o usuário e senha são válidos
        if (usuariosValidos.containsKey(usuario) && usuariosValidos.get(usuario).equals(senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // fecha a tela de login
            new ClienteViewFormSwing().setVisible(true); // abre a tela principal
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            senhaField.setText(""); // limpa o campo senha
            senhaField.requestFocusInWindow(); // foca no campo senha
        }
    }

    /**
     * Método principal para iniciar o sistema.
     */
    public static void main(String[] args) {
        // Aplica estilo visual moderno
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inicia a tela de login
        SwingUtilities.invokeLater(() -> new LoginViewFormSwing().setVisible(true));
    }
}
