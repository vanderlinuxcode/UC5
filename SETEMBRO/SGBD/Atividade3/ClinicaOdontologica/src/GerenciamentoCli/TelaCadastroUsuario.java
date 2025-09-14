package GerenciamentoCli;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroUsuario {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cadastro de Usuário");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.setLayout(new GridBagLayout());
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5); // espaçamento entre os componentes
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx =1.0;
            
            // Componentes
            JLabel lblNome = new JLabel("Nome:");
            JTextField txtNome = new JTextField(20);

            JLabel lblEmail = new JLabel("E-mail:");
            JTextField txtEmail = new JTextField(20);

            JLabel lblSenha = new JLabel("Senha:");
            JPasswordField txtSenha = new JPasswordField(20);

            JLabel lblTelefone = new JLabel("Telefone:");
            JTextField txtTelefone = new JTextField(20);

            
            JButton btnCadastrar = new JButton("Cadastrar");
            JLabel lblMensagem = new JLabel("");
            lblMensagem.setForeground(Color.RED);
            
            // Linha 1 - Nome
            gbc.gridx = 0; gbc.gridy = 0;
            frame.add(lblNome, gbc);
            gbc.gridx = 1;
            frame.add(txtNome, gbc);


            // Linha 2 - Email
            gbc.gridx = 0; gbc.gridy = 1;
            frame.add(lblEmail, gbc);
            gbc.gridx = 1;
            frame.add(txtEmail, gbc);

            // Linha 3 - Senha
            gbc.gridx = 0; gbc.gridy = 2;
            frame.add(lblSenha, gbc);
            gbc.gridx = 1;
            frame.add(txtSenha, gbc);

            // Linha 4 - Telefone
            gbc.gridx = 0; gbc.gridy = 3;
            frame.add(lblTelefone, gbc);
            gbc.gridx = 1;
            frame.add(txtTelefone, gbc);
            
         // Linha 5 - Botão
            gbc.gridx = 1; gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.CENTER;
            frame.add(btnCadastrar, gbc);

            // Linha 6 - Mensagem
            gbc.gridx = 1; gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.CENTER;
            frame.add(lblMensagem, gbc);
            
            // determina a interação de cor para dados informado e após cadastrar
           // JLabel lblMensagem = new JLabel("");

            // Ação do botão
            btnCadastrar.addActionListener(e -> {
                String nome = txtNome.getText().trim();
                String email = txtEmail.getText().trim();
                String senha = new String(txtSenha.getPassword()).trim();
                String telefone = txtTelefone.getText().trim();
                
                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || telefone.isEmpty()) {
                	lblMensagem.setForeground(Color.RED);
                    lblMensagem.setText("Preencha todos os campos.");
                } else {
                    CadastroUsuario.cadastrarUser(nome, email, senha, telefone);
                	lblMensagem.setForeground(new Color(0, 128, 0));
                    lblMensagem.setText("Cadastro efetivado!");
                    txtNome.setText("");
                    txtEmail.setText("");
                    txtSenha.setText("");
                    txtTelefone.setText("");
                }
            });

            frame.setLocationRelativeTo(null); // centraliza na tela
            frame.setVisible(true);
        });
    }
}
