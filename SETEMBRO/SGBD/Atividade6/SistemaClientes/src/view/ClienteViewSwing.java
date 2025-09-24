package view;

import java.awt.*;
import javax.swing.*;
import model.Cliente;
import java.util.List;
import java.awt.event.ActionEvent;
import exception.ValidacaoException;
import controller.ClienteController;
import javax.swing.table.DefaultTableModel;


public class ClienteViewSwing extends JFrame {
    private static final long serialVersionUID = 1L;
	private ClienteController controller;
    private JTable tabela;
    private DefaultTableModel modelo;

    public ClienteViewSwing() {
        controller = new ClienteController();
        setTitle("Sistema de Clientes");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Idade", "Email", "Telefone", "CPF"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        painel.add(scroll, BorderLayout.CENTER);

        JPanel botoes = new JPanel();

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener((ActionEvent e) -> cadastrarCliente());

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener((ActionEvent e) -> atualizarCliente());

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener((ActionEvent e) -> excluirCliente());

        JButton btnListar = new JButton("Listar");
        btnListar.addActionListener((ActionEvent e) -> carregarClientes());

        botoes.add(btnCadastrar);
        botoes.add(btnAtualizar);
        botoes.add(btnExcluir);
        botoes.add(btnListar);

        painel.add(botoes, BorderLayout.SOUTH);

        add(painel);
        carregarClientes();
    }

    private void carregarClientes() {
        modelo.setRowCount(0);
        List<Cliente> clientes = controller.listarClientes();
        for (Cliente c : clientes) {
            modelo.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getIdade(),
                    c.getEmail(),
                    c.getTelefone(),
                    c.getCpf()
            });
        }
    }

    private void cadastrarCliente() {
        boolean dadosValidos = false;

        while (!dadosValidos) {
            JTextField nomeField = new JTextField();
            JTextField idadeField = new JTextField();
            JTextField emailField = new JTextField();
            JTextField telefoneField = new JTextField();
            JTextField cpfField = new JTextField();

            Object[] form = {
                "Nome:", nomeField,
                "Idade:", idadeField,
                "Email:", emailField,
                "Telefone:", telefoneField,
                "CPF:", cpfField
            };

            int option = JOptionPane.showConfirmDialog(this, form, "Cadastrar Cliente", JOptionPane.OK_CANCEL_OPTION);
            if (option != JOptionPane.OK_OPTION) {
                return;
            }

            try {
                String nome = nomeField.getText().trim();
                String idadeTexto = idadeField.getText().trim();
                String email = emailField.getText().trim();
                String telefone = telefoneField.getText().trim();
                String cpf = cpfField.getText().trim();

                if (nome.isEmpty() || idadeTexto.isEmpty() || email.isEmpty() || telefone.isEmpty() || cpf.isEmpty()) {
                    throw new IllegalArgumentException("Todos os campos são obrigatórios.");
                }

                int idade = Integer.parseInt(idadeTexto);

                Cliente cliente = new Cliente(0, nome, idade, email, telefone, cpf);
                controller.cadastrarCliente(cliente); // pode lançar ValidacaoException
                carregarClientes();
                dadosValidos = true;

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Idade deve ser um número inteiro válido.");
            } catch (IllegalArgumentException | ValidacaoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + ex.getMessage());
            }
        }
    }
        
        private void atualizarCliente() {
            int linha = tabela.getSelectedRow();
            if (linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um cliente para atualizar.");
                return;
            }

            int id = (int) tabela.getValueAt(linha, 0);
            String nomeAtual = (String) tabela.getValueAt(linha, 1);
            int idadeAtual = (int) tabela.getValueAt(linha, 2);
            String emailAtual = (String) tabela.getValueAt(linha, 3);
            String telefoneAtual = (String) tabela.getValueAt(linha, 4);
            String cpfAtual = (String) tabela.getValueAt(linha, 5);

            boolean dadosValidos = false;

            while (!dadosValidos) {
                JTextField nomeField = new JTextField(nomeAtual);
                JTextField idadeField = new JTextField(String.valueOf(idadeAtual));
                JTextField emailField = new JTextField(emailAtual);
                JTextField telefoneField = new JTextField(telefoneAtual);
                JTextField cpfField = new JTextField(cpfAtual);

                Object[] form = {
                    "Nome:", nomeField,
                    "Idade:", idadeField,
                    "Email:", emailField,
                    "Telefone:", telefoneField,
                    "CPF:", cpfField
                };

                int option = JOptionPane.showConfirmDialog(this, form, "Atualizar Cliente", JOptionPane.OK_CANCEL_OPTION);
                if (option != JOptionPane.OK_OPTION) {
                    return;
                }

                try {
                    String nome = nomeField.getText().trim();
                    String idadeTexto = idadeField.getText().trim();
                    String email = emailField.getText().trim();
                    String telefone = telefoneField.getText().trim();
                    String cpf = cpfField.getText().trim();

                    if (nome.isEmpty() || idadeTexto.isEmpty() || email.isEmpty() || telefone.isEmpty() || cpf.isEmpty()) {
                        throw new IllegalArgumentException("Todos os campos são obrigatórios.");
                    }

                    int idade = Integer.parseInt(idadeTexto);

                    Cliente cliente = new Cliente(id, nome, idade, email, telefone, cpf);
                    controller.atualizarCliente(cliente); // pode lançar ValidacaoException
                    carregarClientes();
                    dadosValidos = true;

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Idade deve ser um número inteiro válido.");
                } catch (IllegalArgumentException | ValidacaoException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente: " + ex.getMessage());
                }
            }
        }


    private void excluirCliente() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
            return;
        }
        int id = (int) tabela.getValueAt(linha, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir o cliente selecionado?");
        if (confirm == JOptionPane.YES_OPTION) {
            controller.excluirCliente(id);
            carregarClientes();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteViewSwing().setVisible(true));
    }
}
