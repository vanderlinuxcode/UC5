package view;

import dao.*;
import model.*;		
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrameView extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField cpfField;
    private JPasswordField senhaField;
    private JButton loginButton;
    private JButton cadastrarButton;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    @SuppressWarnings("unused")
    private final CadastroDAO cadastroDAO = new CadastroDAO();

    public LoginFrameView() {
        setTitle("🔐 Login - Sistema Vistoria Veicular V-0.1");
        setSize(420, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 245, 250));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createBevelBorder(BevelBorder.LOWERED),
            new EmptyBorder(60, 80,150, 60)
        ));

        JLabel titleLabel = new JLabel("Bem-vindo");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(40, 70, 130));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(25));

        JLabel titleLabel1 = new JLabel("Vistoria Veicular - DF");
        titleLabel1.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel1.setForeground(new Color(40, 70, 130));
        mainPanel.add(titleLabel1);
        mainPanel.add(Box.createVerticalStrut(25));

        cpfField = criarCampoEstilizado();
        senhaField = criarCampoSenhaEstilizado();

        mainPanel.add(cpfField);
        mainPanel.add(criarLabelAbaixo("CPF (login)"));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(senhaField);
        mainPanel.add(criarLabelAbaixo("Senha"));
        mainPanel.add(Box.createVerticalStrut(20));

        loginButton = criarBotaoEstilizado("Entrar", new Color(60, 130, 220));
        cadastrarButton = criarBotaoEstilizado("Cadastrar", new Color(100, 180, 100));

        loginButton.addActionListener(e -> fazerLogin());
        cadastrarButton.addActionListener(e -> abrirCadastro());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(mainPanel.getBackground());
        buttonPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        buttonPanel.add(loginButton);
        buttonPanel.add(cadastrarButton);

        mainPanel.add(buttonPanel);
        add(mainPanel);
        setVisible(true);
    }

    private JTextField criarCampoEstilizado() {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        Color corPadrao = Color.WHITE;
        Color corFoco = new Color(220, 240, 255);
        field.setBackground(corPadrao);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBackground(corFoco);
            }
            public void focusLost(FocusEvent e) {
                field.setBackground(corPadrao);
            }
        });
        return field;
    }

    private JPasswordField criarCampoSenhaEstilizado() {
        JPasswordField field = new JPasswordField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        Color corPadrao = Color.WHITE;
        Color corFoco = new Color(220, 240, 255);
        field.setBackground(corPadrao);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                field.setBackground(corFoco);
            }
            public void focusLost(FocusEvent e) {
                field.setBackground(corPadrao);
            }
        });
        return field;
    }

    private JLabel criarLabelAbaixo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(new Color(100, 100, 100));
        return label;
    }

    private JButton criarBotaoEstilizado(String texto, Color corFundo) {

        JButton button = new JButton(texto);
        button.setPreferredSize(new Dimension(110, 35));
        //button.setPreferredSize(new Dimension(50,25));
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.BLACK);
        button.setBackground(corFundo);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(corFundo.darker());
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(corFundo);
            }
        });
        return button;
    }

    private void fazerLogin() {
        String login = cpfField.getText().trim();
        String senha = new String(senhaField.getPassword());
        System.out.println("Login recebido: " + login);
        System.out.println("Senha recebida: " + senha);
      //  System.out.println("Usuário encontrado: " + usuario);
        //System.out.println("Tipo de usuário: " + usuario.getTipo());


        try {
            Usuario usuario = usuarioDAO.buscarPorLogin(login);

            if (usuario != null && usuario.getSenha().equals(senha)) {
                switch (usuario.getTipo().toLowerCase()) {
                    case "cliente" -> {
                        Cliente cliente = clienteDAO.buscarPorId(usuario.getIdCliente());
                        if (cliente != null) {
                            new ClienteView().exibirMenuCliente(cliente);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
                        }
                    }
                    case "funcionario" -> {
                        Funcionario funcionario = funcionarioDAO.buscarPorId(usuario.getIdUsuario());
                        if (funcionario != null) {
                            new FuncionarioView().exibirMenu(funcionario);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, "Funcionário não encontrado.");
                        }
                    }
                    default -> JOptionPane.showMessageDialog(this, "Tipo de usuário desconhecido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "CPF ou senha inválidos.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao autenticar: " + ex.getMessage());
        }
    }

    private void abrirCadastro() {
        JOptionPane.showMessageDialog(this, "Cadastro de cliente ainda não implementado nesta interface.");
    }
}
