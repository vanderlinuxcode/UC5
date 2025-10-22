package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginViewSwing extends JFrame implements LoginView {
    private static final long serialVersionUID = 1L;
    private JTextField loginField;
    private JPasswordField senhaField;
    private JButton entrarButton;
    private LoginController loginController;

    public LoginViewSwing() {
        setTitle("Login para Agendamento");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loginField = new JTextField(15);
        senhaField = new JPasswordField(15);
        entrarButton = new JButton("Entrar");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Login:"));
        panel.add(loginField);
        panel.add(new JLabel("Senha:"));
        panel.add(senhaField);
        panel.add(new JLabel(""));
        panel.add(entrarButton);

        add(panel);

        entrarButton.addActionListener(e -> {
            String login = loginField.getText();
            String senha = new String(senhaField.getPassword());
            if (loginController != null) {
                loginController.autenticarSwing(login, senha, this);
            } else {
                JOptionPane.showMessageDialog(this, "Controller não configurado.");
            }
        });
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

	public String[] solicitarCredenciais() {
		// TODO Auto-generated method stub
		return null;
	}

	public String solicitarTipoUsuario() {
		// TODO Auto-generated method stub
		return null;
	}
}
