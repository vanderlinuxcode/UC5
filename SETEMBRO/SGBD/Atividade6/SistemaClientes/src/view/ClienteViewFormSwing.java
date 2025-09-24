package view;

import controller.ClienteController;
import exception.ValidacaoException;
import model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteViewFormSwing extends JFrame {
    private static final long serialVersionUID = 1L;
	private ClienteController controller;
    private JTextField nomeField, idadeField, emailField, telefoneField, cpfField;
    private JLabel nomeError, idadeError, emailError, telefoneError, cpfError;
    private JTable tabela;
    private DefaultTableModel modelo;

    public ClienteViewFormSwing() {
        controller = new ClienteController();
        setTitle("Cadastro de Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createFormPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        carregarClientes();
    }

    private JPanel createFormPanel() {
        JPanel form = new JPanel(new GridLayout(10, 2, 5, 2));
        form.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));

        nomeField = new JTextField();
        nomeError = new JLabel(" ");
        nomeError.setForeground(Color.RED);

        idadeField = new JTextField();
        idadeError = new JLabel(" ");
        idadeError.setForeground(Color.RED);

        emailField = new JTextField();
        emailError = new JLabel(" ");
        emailError.setForeground(Color.RED);

        telefoneField = new JTextField();
        telefoneError = new JLabel(" ");
        telefoneError.setForeground(Color.RED);

        cpfField = new JTextField();
        cpfError = new JLabel(" ");
        cpfError.setForeground(Color.RED);

        form.add(new JLabel("Nome:")); form.add(nomeField);
        form.add(new JLabel("")); form.add(nomeError);

        form.add(new JLabel("Idade:")); form.add(idadeField);
        form.add(new JLabel("")); form.add(idadeError);

        form.add(new JLabel("Email:")); form.add(emailField);
        form.add(new JLabel("")); form.add(emailError);

        form.add(new JLabel("Telefone:")); form.add(telefoneField);
        form.add(new JLabel("")); form.add(telefoneError);

        form.add(new JLabel("CPF:")); form.add(cpfField);
        form.add(new JLabel("")); form.add(cpfError);

        return form;
    }

    private JScrollPane createTablePanel() {
        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Idade", "Email", "Telefone", "CPF"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Clientes Cadastrados"));
        return scroll;
    }

    private JPanel createButtonPanel() {
        JPanel botoes = new JPanel(new FlowLayout());

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> cadastrarCliente());

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> atualizarCliente());

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> excluirCliente());

        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener(e -> carregarClientes());

        botoes.add(btnCadastrar);
        botoes.add(btnAtualizar);
        botoes.add(btnExcluir);
        botoes.add(btnListar);

        return botoes;
    }

    private void limparErros() {
        nomeError.setText(" ");
        idadeError.setText(" ");
        emailError.setText(" ");
        telefoneError.setText(" ");
        cpfError.setText(" ");
    }

    private Cliente montarCliente(int id) throws Exception {
        limparErros();
        boolean valido = true;

        String nome = nomeField.getText().trim();
        String idadeTexto = idadeField.getText().trim();
        String email = emailField.getText().trim();
        String telefone = telefoneField.getText().trim();
        String cpf = cpfField.getText().trim();

        if (nome.isEmpty()) {
            nomeError.setText("Nome é obrigatório.");
            valido = false;
        }

        int idade = 0;
        if (idadeTexto.isEmpty()) {
            idadeError.setText("Idade é obrigatória.");
            valido = false;
        } else {
            try {
                idade = Integer.parseInt(idadeTexto);
            } catch (NumberFormatException ex) {
                idadeError.setText("Idade deve ser um número.");
                valido = false;
            }
        }

        if (email.isEmpty()) {
            emailError.setText("Email é obrigatório.");
            valido = false;
        }

        if (telefone.isEmpty()) {
            telefoneError.setText("Telefone é obrigatório.");
            valido = false;
        }

        if (cpf.isEmpty()) {
            cpfError.setText("CPF é obrigatório.");
            valido = false;
        }

        if (!valido) throw new Exception("Preencha os campos corretamente.");

        return new Cliente(id, nome, idade, email, telefone, cpf);
    }

    private void cadastrarCliente() {
        try {
            Cliente cliente = montarCliente(0);
            controller.cadastrarCliente(cliente);
            carregarClientes();
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
        } catch (ValidacaoException ex) {
            JOptionPane.showMessageDialog(this, "Erro de validação: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Preenchimento obrigatório: " + ex.getMessage());
        }
    }


    private void atualizarCliente() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para atualizar.");
            return;
        }

        int id = (int) tabela.getValueAt(linha, 0);
        nomeField.setText((String) tabela.getValueAt(linha, 1));
        idadeField.setText(String.valueOf(tabela.getValueAt(linha, 2)));
        emailField.setText((String) tabela.getValueAt(linha, 3));
        telefoneField.setText((String) tabela.getValueAt(linha, 4));
        cpfField.setText((String) tabela.getValueAt(linha, 5));

        int confirm = JOptionPane.showConfirmDialog(this, "Deseja atualizar os dados do cliente?");
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            Cliente cliente = montarCliente(id);
            controller.atualizarCliente(cliente);
            carregarClientes();
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
        } catch (ValidacaoException ex) {
            JOptionPane.showMessageDialog(this, "Erro de validação: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage());
        }
    }

    private void excluirCliente() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
            return;
        }
        int id = (int) tabela.getValueAt(linha, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir o cliente?");
        if (confirm == JOptionPane.YES_OPTION) {
            controller.excluirCliente(id);
            carregarClientes();
            JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
        }
    }

    private void carregarClientes() {
        modelo.setRowCount(0);
        List<Cliente> clientes = controller.listarClientes();
        for (Cliente c : clientes) {
            modelo.addRow(new Object[]{
                    c.getId(), c.getNome(), c.getIdade(), c.getEmail(), c.getTelefone(), c.getCpf()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteViewFormSwing().setVisible(true));
    }
}
