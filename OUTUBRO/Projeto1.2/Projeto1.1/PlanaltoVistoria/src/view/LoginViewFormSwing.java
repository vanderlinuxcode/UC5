package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


import controller.UsuarioLoginController;
import model.UsuarioModel;

public class LoginViewFormSwing extends JFrame {
	private static final long serialVersionUID = 1L;
	// Campos de entrada
    private JTextField cpfField;
    private JPasswordField senhaField;
    private UsuarioLoginController loginController;
    

    // Mapa com usuários válidos simulados
   // private Map<String, String> usuariosValidos = new HashMap<>();

    public LoginViewFormSwing(java.sql.Connection conn) {
    	this.loginController = new UsuarioLoginController(conn);
        // Define título da janela
        setTitle("Login - Sistema de Clientes");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Define tamanho e posicionamento
        setSize(420, 250);
        setLocationRelativeTo(null); // centraliza na tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // impede redimensionamento

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
        JLabel titulo = new JLabel("Planalto Vistoria LTDA");
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
    public JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // espaçamento entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Rótulo e campo de usuário
        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        cpfField = new JTextField(20);

        // Rótulo e campo de senha
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        senhaField = new JPasswordField(20);

        // Posiciona os componentes no grid
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(cpfLabel, gbc);
        gbc.gridx = 1;
        panel.add(cpfField, gbc);

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
        btnEntrar.setForeground(Color.BLACK); // texto branco
        btnEntrar.setFocusPainted(false);
        btnEntrar.setPreferredSize(new Dimension(100, 35));

        // Ação ao clicar no botão
        btnEntrar.addActionListener(this::autenticarUsuario);

        panel.add(btnEntrar);
        return panel;
    }
    
    private void autenticarUsuario(ActionEvent e) {
        String cpf = cpfField.getText().replaceAll("[^\\d]", "");
        String senha = new String(senhaField.getPassword());

        boolean sucesso = loginController.loginGUI(cpf, senha);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "✅ Login realizado com sucesso!");
            dispose();
            // abrir painel principal
            UsuarioModel usuario = loginController.getUsuarioLogado(); // você precisa guardar isso no loginController
            PainelPrincipalView painel = new PainelPrincipalView(usuario.getNome(), usuario.getTipo());
            painel.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "❌ CPF ou senha incorretos.");
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
}
}