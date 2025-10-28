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
import javax.swing.text.*;

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
        setTitle("Acesso ao Sistema");
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
        //carrega o método para execução da formatação original CPF
        ((AbstractDocument) cpfField.getDocument()).setDocumentFilter(new CPFDocumentFilter());


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
            @SuppressWarnings("unused")
			UsuarioModel usuario = loginController.getUsuarioLogado(); // ✅ agora está definido

            JOptionPane.showMessageDialog(this, "✅ Login realizado com sucesso!");
            dispose();

            // Se quiser abrir um painel genérico com nome/tipo:
            // new PainelPrincipalView(usuario.getNome(), usuario.getTipo()).setVisible(true);

            // Mas como o redirecionamento já é feito pelo PainelController,
            // você pode simplesmente encerrar aqui.
        } else {
            JOptionPane.showMessageDialog(this, "❌ CPF ou senha incorretos.");
        }
    }

    

    private class CPFDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string == null) return;
            replace(fb, offset, 0, string, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            Document doc = fb.getDocument();
            String currentText = doc.getText(0, doc.getLength());
            StringBuilder sb = new StringBuilder(currentText);
            sb.replace(offset, offset + length, text);

            String digits = sb.toString().replaceAll("[^\\d]", "");
            if (digits.length() > 11) digits = digits.substring(0, 11);

            StringBuilder formatted = new StringBuilder();
            for (int i = 0; i < digits.length(); i++) {
                if (i == 3 || i == 6) formatted.append('.');
                if (i == 9) formatted.append('-');
                formatted.append(digits.charAt(i));
            }

            fb.remove(0, doc.getLength());
            fb.insertString(0, formatted.toString(), attrs);
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            Document doc = fb.getDocument();
            String currentText = doc.getText(0, doc.getLength());
            StringBuilder sb = new StringBuilder(currentText);
            sb.delete(offset, offset + length);

            String digits = sb.toString().replaceAll("[^\\d]", "");
            StringBuilder formatted = new StringBuilder();
            for (int i = 0; i < digits.length(); i++) {
                if (i == 3 || i == 6) formatted.append('.');
                if (i == 9) formatted.append('-');
                formatted.append(digits.charAt(i));
            }

            fb.remove(0, doc.getLength());
            fb.insertString(0, formatted.toString(), null);
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