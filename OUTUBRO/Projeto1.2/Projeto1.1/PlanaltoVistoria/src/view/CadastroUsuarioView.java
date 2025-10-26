package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import dao.UsuarioDAO;
import exception.CadastroException;
import model.UsuarioModel;

public class CadastroUsuarioView extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField nomeField, emailField, cpfField;
    private JPasswordField senhaField;
    private Connection conn;

    public CadastroUsuarioView(Connection conn) {
        this.conn = conn;

        // Aplica estilo Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception ex) {
            System.out.println("⚠️ Não foi possível aplicar o estilo Nimbus.");
        }

        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Usuário");
        setSize(400, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        // Painel de campos
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        nomeField = new JTextField();
        emailField = new JTextField();
        cpfField = new JTextField();
        senhaField = new JPasswordField();

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("CPF:"));
        formPanel.add(cpfField);
        formPanel.add(new JLabel("Senha:"));
        formPanel.add(senhaField);

        add(formPanel, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCadastrar.setBackground(new Color(0, 120, 215));
        btnCadastrar.setForeground(Color.BLACK);
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setPreferredSize(new Dimension(130, 35));
        btnCadastrar.addActionListener(e -> cadastrarUsuario());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnVoltar.setBackground(new Color(0, 120, 215));
        btnVoltar.setForeground(Color.BLACK);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setPreferredSize(new Dimension(130, 35));        
        btnVoltar.addActionListener(e -> voltarParaTelaInicial());
       // btnVoltar.addActionListener(e -> voltarParaTelaAnterior("inicial"));


        buttonPanel.add(btnCadastrar);
        buttonPanel.add(btnVoltar);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void cadastrarUsuario() {
       // dispose(); // fecha a tela atual
        String nome = nomeField.getText().trim();
        String email = emailField.getText().trim();
        String cpf = cpfField.getText().replaceAll("[^\\d]", "");
        String senha = new String(senhaField.getPassword());

        if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "❌ Preencha todos os campos antes de cadastrar.",
                    "Campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setCpf(cpf);
        usuario.setSenha(UsuarioDAO.criptografarSenha(senha));

        try {
            UsuarioDAO dao = new UsuarioDAO(conn);
            dao.cadastrarCliente(usuario);
            JOptionPane.showMessageDialog(this, "✅ Cadastro realizado com sucesso!");
            dispose();
            new LoginViewFormSwing(conn).setVisible(true);
        } catch (CadastroException ex) {
            JOptionPane.showMessageDialog(this, "❌ " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void voltarParaTelaInicial() {
        dispose();
          new TelaInicialView(conn).setVisible(true);
    }
    
//    private void voltarParaTelaAnterior(String origem) {
//        dispose(); // fecha a tela atual
//
//        if ("inicial".equalsIgnoreCase(origem)) {
//        	dispose(); // fecha a tela atual
//            //new TelaInicialView(conn).setVisible(true);
//        	new LoginViewFormSwing(conn).setVisible(true);
//        } else if ("login".equalsIgnoreCase(origem)) {
//            new LoginViewFormSwing(conn).setVisible(true);
//        } else if ("cadastro".equalsIgnoreCase(origem)) {
//            new CadastroUsuarioView(conn).setVisible(true);
//        } else {
//            JOptionPane.showMessageDialog(null, "Destino desconhecido: " + origem);
//        }
//    }
}
