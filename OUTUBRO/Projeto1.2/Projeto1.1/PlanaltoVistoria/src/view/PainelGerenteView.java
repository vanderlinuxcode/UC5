package view;

import javax.swing.*;
import java.awt.*;
import model.FuncionarioModel;

public class PainelGerenteView extends JFrame {
    private static final long serialVersionUID = 1L;
	private JButton btnCadastrarCliente;
    private JButton btnCadastrarVeiculo;
    private JButton btnAgendarVistoria;
    private JButton btnVisualizarPagamentos;
    private JLabel lblUsuario;

    public PainelGerenteView(FuncionarioModel funcionario) {
        setTitle("Painel de Acesso - " + funcionario.getCargo());
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents(funcionario);
        aplicarPermissoes(funcionario);
    }

    private void initComponents(FuncionarioModel funcionario) {
        // Cabeçalho com nome e CPF
        String nome = funcionario.getUsuario().getNome();
        String cpf = funcionario.getUsuario().getCpf();
        lblUsuario = new JLabel("👤 Bem-vindo, " + nome + " | CPF: " + cpf, SwingConstants.CENTER);
        lblUsuario.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblUsuario.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblUsuario, BorderLayout.NORTH);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new GridLayout(2, 2, 20, 20));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnCadastrarCliente = new JButton("Cadastrar Cliente");
        btnCadastrarVeiculo = new JButton("Cadastrar Veículo");
        btnAgendarVistoria = new JButton("Agendar Vistoria");
        btnVisualizarPagamentos = new JButton("Visualizar Pagamentos");

        painelBotoes.add(btnCadastrarCliente);
        painelBotoes.add(btnCadastrarVeiculo);
        painelBotoes.add(btnAgendarVistoria);
        painelBotoes.add(btnVisualizarPagamentos);

        add(painelBotoes, BorderLayout.CENTER);

        // Ações dos botões (substitua com suas views reais)
        btnCadastrarCliente.addActionListener(e -> System.out.println("Abrindo cadastro de cliente..."));
        btnCadastrarVeiculo.addActionListener(e -> System.out.println("Abrindo cadastro de veículo..."));
        btnAgendarVistoria.addActionListener(e -> System.out.println("Abrindo agendamento de vistoria..."));
        btnVisualizarPagamentos.addActionListener(e -> System.out.println("Abrindo painel de pagamentos..."));
    }

    private void aplicarPermissoes(FuncionarioModel funcionario) {
        String cargo = funcionario.getCargo();

        switch (cargo) {
            case "VISTORIADOR":
                btnVisualizarPagamentos.setEnabled(false);
                break;
            case "CONTABIL":
                btnCadastrarCliente.setEnabled(false);
                btnCadastrarVeiculo.setEnabled(false);
                btnAgendarVistoria.setEnabled(false);
                break;
            case "GERENTE":
                // acesso total
                break;
            default:
                JOptionPane.showMessageDialog(this, "⚠️ Cargo não reconhecido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
