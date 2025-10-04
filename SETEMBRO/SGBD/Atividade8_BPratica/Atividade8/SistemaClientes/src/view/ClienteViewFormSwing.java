package view;

import controller.ClienteController;
import exception.ValidacaoException;
import model.Cliente;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.event.DocumentListener;

/**
 * Interface gráfica para cadastro e gerenciamento de clientes.
 * Utiliza Swing com layout organizado, validação visual e tabela estilizada.
 */
public class ClienteViewFormSwing extends JFrame {
    private static final long serialVersionUID = 1L;
    private int clienteEmEdicaoId = -1;
    private JButton btnCadastrar;
    private JButton btnSalvarAlteracoes;

    // Controlador responsável pela lógica de negócio
    private ClienteController controller;
    
    // campos de entrada
    private JTextField nomeField, idadeField, emailField, telefoneField, cpfField;
    
    // Labels para mensagens de erro
    private JLabel nomeError, idadeError, emailError, telefoneError, cpfError;
    
    // Tabela e modelo para exibir clientes
    private JTable tabela;
    private DefaultTableModel modelo;

    /**
     * Construtor da interface principal.
     * Inicializa componentes e organiza layout.
     */
    public ClienteViewFormSwing() {
        controller = new ClienteController();
        setTitle("Cadastro de Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

     // Adiciona os painéis à janela
        add(createFormPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        carregarClientes();
      //  initComponents();
        configurarRestauracaoVisual(); // ativa restauração automática
    }
    
    private void configurarRestauracaoVisual() {
        aplicarRestauracao(nomeField, nomeError);
        aplicarRestauracao(idadeField, idadeError);
        aplicarRestauracao(emailField, emailError);
        aplicarRestauracao(telefoneField, telefoneError);
        aplicarRestauracao(cpfField, cpfError);
    }
    
//    private void initComponents() {
//        // inicialize seus campos aqui
//        nomeField = new JTextField(20);
//        nomeField.setName("nome");
//        nomeError.setForeground(Color.RED);
//        nomeField = new JTextField(20);
//        nomeField.setName("idade");
//        nomeError.setForeground(Color.RED);
        
        // repita para os demais campos...
  //  }
    private void limparCampos() {
        nomeField.setText("");
        idadeField.setText("");
        emailField.setText("");
        telefoneField.setText("");
        cpfField.setText("");

        nomeError.setText(" ");
        idadeError.setText(" ");
        emailError.setText(" ");
        telefoneError.setText(" ");
        cpfError.setText(" ");

        clienteEmEdicaoId = -1;
        btnCadastrar.setEnabled(true);
        btnSalvarAlteracoes.setEnabled(false);
    }
    
    public JPanel createFormPanel() {
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "DADOS DO CLIENTE",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 16),
                Color.DARK_GRAY
        ));

        // Rótulos
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel idadeLabel = new JLabel("Idade:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel telefoneLabel = new JLabel("Telefone:");
        JLabel cpfLabel = new JLabel("CPF:");

        // Campos de entrada
        nomeField = new JTextField(20);
        idadeField = new JTextField(5);
        emailField = new JTextField(5);
        telefoneField = new JTextField(15);
        cpfField = new JTextField(15);
        
        // Mensagens de erro
        nomeError = new JLabel(" ");
        idadeError = new JLabel(" ");
        emailError = new JLabel(" ");
        telefoneError = new JLabel(" ");
        cpfError = new JLabel(" ");
        
        // Tamanhos preferenciais
        nomeField.setPreferredSize(new Dimension(250, 28));
        idadeField.setPreferredSize(new Dimension(80, 28));
        emailField.setPreferredSize(new Dimension(250, 28));
        telefoneField.setPreferredSize(new Dimension(180, 28));
        cpfField.setPreferredSize(new Dimension(180, 28));
                
        // Estilização das mensagens de erro
        Color errorColor = new Color(204, 0, 0);
        Font errorFont = new Font("SansSerif", Font.ITALIC, 12);
        for (JLabel errorLabel : new JLabel[]{nomeError, idadeError, emailError, telefoneError, cpfError}) {
            errorLabel.setForeground(errorColor);
            errorLabel.setFont(errorFont);
            //errorLabel.setMinimumSize(new Dimension(150, 20));
        }

        GroupLayout layout = new GroupLayout(painel);
        painel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(nomeLabel)
                .addComponent(idadeLabel)
                .addComponent(emailLabel)
                .addComponent(telefoneLabel)
                .addComponent(cpfLabel))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nomeField)
                .addComponent(idadeField)
                .addComponent(emailField)
                .addComponent(telefoneField)
                .addComponent(cpfField))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nomeError)
                .addComponent(idadeError)
                .addComponent(emailError)
                .addComponent(telefoneError)
                .addComponent(cpfError))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nomeLabel)
                .addComponent(nomeField)
                .addComponent(nomeError))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(idadeLabel)
                .addComponent(idadeField)
                .addComponent(idadeError))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(emailLabel)
                .addComponent(emailField)
                .addComponent(emailError))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(telefoneLabel)
                .addComponent(telefoneField)
                .addComponent(telefoneError))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(cpfLabel)
                .addComponent(cpfField)
                .addComponent(cpfError))
        );

        return painel;
    }
    
    /**
     * Cria o painel da tabela com rolagem, título centralizado e estilo visual.
     */
    private JScrollPane createTablePanel() {
        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Idade", "Email", "Telefone", "CPF"}, 0);
        tabela = new JTable(modelo);

        // Estilização da tabela
        tabela.setShowGrid(false);
        tabela.setIntercellSpacing(new Dimension(0, 0));
        tabela.setRowHeight(24);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tabela.getTableHeader().setReorderingAllowed(false);

        // Alinhamento centralizado dos dados
        DefaultTableCellRenderer alinhamentoCentralizado = new DefaultTableCellRenderer();
        alinhamentoCentralizado.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(alinhamentoCentralizado);
        }

        // Cria borda com título centralizado e fonte personalizada para os dados inserido na tabela
        TitledBorder titulo = BorderFactory.createTitledBorder("CLIENTES CADASTRADOS");
        titulo.setTitleFont(new Font("SansSerif", Font.BOLD, 16));
        titulo.setTitleJustification(TitledBorder.CENTER);
        titulo.setTitlePosition(TitledBorder.TOP);

        // Envolve a tabela em um painel de rolagem com a borda estilizada
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(titulo);

        return scroll;  
    }
    
    /**
     * Cria o painel com botões de ação estilizados.
     */
    private JPanel createButtonPanel() {
    	JPanel botoes = new JPanel(new FlowLayout());
    	
    	//botões de ação
        btnCadastrar = new JButton("Cadastrar");      
        JButton btnEditar = new JButton("Editar");
        btnSalvarAlteracoes = new JButton("Salvar Alterações");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnListar = new JButton("Listar");

        // Estilização dos botões
        Color verde = new Color(0, 153, 76);
        Color azul = new Color(0, 120, 215);
        Color vermelho = new Color(204, 0, 0);
        Color cinza = Color.DARK_GRAY;
        
        // este campo não foi
        btnCadastrar.setBackground(verde); btnCadastrar.setForeground(Color.WHITE); btnCadastrar.setFocusPainted(false);
        btnEditar.setBackground(azul); btnEditar.setForeground(Color.WHITE); btnEditar.setFocusPainted(false);
        btnSalvarAlteracoes.setBackground(verde); btnSalvarAlteracoes.setForeground(Color.WHITE); btnSalvarAlteracoes.setFocusPainted(false);
        btnExcluir.setBackground(vermelho); btnExcluir.setForeground(Color.WHITE); btnExcluir.setFocusPainted(false);
        btnListar.setBackground(cinza); btnListar.setForeground(Color.WHITE); btnListar.setFocusPainted(false);

        
        // Ações dos botões
        btnCadastrar.addActionListener(e -> cadastrarCliente());
        btnEditar.addActionListener(e -> prepararEdicaoCliente());
        btnSalvarAlteracoes.addActionListener(e -> salvarAlteracoesCliente());
        btnExcluir.addActionListener(e -> excluirCliente());
        btnListar.addActionListener(e -> carregarClientes());

        // só ativa durante edição
        btnSalvarAlteracoes.setEnabled(false); 

        // Adiciona ao painel
        botoes.add(btnCadastrar);
        botoes.add(btnEditar);
        botoes.add(btnSalvarAlteracoes);
        botoes.add(btnExcluir);
        botoes.add(btnListar);

        return botoes;
    }

    /**
     * Limpa as mensagens de erro dos campos.
     */
    private void limparErros() {
        nomeField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        idadeField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        emailField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        telefoneField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
        cpfField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
    }

	
	  /**
     * Monta um objeto Cliente a partir dos dados preenchidos.
     * @param id ID do cliente (0 para novo)
     * @return Cliente preenchido
     * @throws Exception se houver erro de validação
     */
    private Cliente montarCliente(int id) {
    	limparErros();
        String nome = nomeField.getText().trim();
        String idadeTexto = idadeField.getText().trim();
        String email = emailField.getText().trim();
        String telefone = telefoneField.getText().trim();
        String cpf = cpfField.getText().trim();

        int idade = -1;
        try {
            idade = Integer.parseInt(idadeTexto);
        } catch (NumberFormatException ex) {
            // idade inválida será tratada na validação
        }

        return new Cliente(id, nome, idade, email, telefone, cpf);
    }

    private void cadastrarCliente() {
        try {
            String nome = nomeField.getText().trim();
            String idadeTexto = idadeField.getText().trim();
            String email = emailField.getText().trim();
            String telefone = telefoneField.getText().trim();
            String cpf = cpfField.getText().trim();

            controller.cadastrarCliente(0, nome, idadeTexto, email, telefone, cpf);

            carregarClientes();
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            limparCampos(); // Limpa os campos após cadastro efetuado
        } catch (ValidacaoException ex) {
            limparErros();
            exibirErros(ex.getErros());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage());
        }
    }
    
        
    private void aplicarRestauracao(JTextField campo, JLabel erroLabel) {
        System.out.println("Aplicando restauração para: " + campo.getName());

        campo.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { restaurar(); }
            public void removeUpdate(DocumentEvent e) { restaurar(); }
            public void changedUpdate(DocumentEvent e) { restaurar(); }

            private void restaurar() {
                campo.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                erroLabel.setText(" ");
            }
        });
    }


    
    private void prepararEdicaoCliente() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.");
            return;
        }

        clienteEmEdicaoId = (int) tabela.getValueAt(linha, 0);
        nomeField.setText((String) tabela.getValueAt(linha, 1));
        idadeField.setText(String.valueOf(tabela.getValueAt(linha, 2)));
        emailField.setText((String) tabela.getValueAt(linha, 3));
        telefoneField.setText((String) tabela.getValueAt(linha, 4));
        cpfField.setText((String) tabela.getValueAt(linha, 5));

        nomeField.requestFocusInWindow();
        nomeField.selectAll();

        btnCadastrar.setEnabled(false);
        btnSalvarAlteracoes.setEnabled(true);

        JOptionPane.showMessageDialog(this, "Modo de edição ativado. Altere os dados e clique em 'Salvar Alterações'.");
    }

    private void salvarAlteracoesCliente() {
        try {
            Cliente cliente = montarCliente(clienteEmEdicaoId);
            controller.validarCliente(cliente);
            controller.atualizarCliente(cliente);
            carregarClientes();
            limparCampos();
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
        } catch (ValidacaoException ex) {
            limparErros(); // limpa bordas e mensagens anteriores
            exibirErros(ex.getErros()); // mostra os erros nos campos corretos
            System.out.println("Erros de validação: " + ex.getErros());
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
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir o cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                controller.excluirCliente(id);
                carregarClientes();
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
            }
        }
    
        /**
         * Recarrega os dados da tabela com os clientes cadastrados.
         */	
        private void carregarClientes() {
            modelo.setRowCount(0); // Limpa a tabela antes de tentar carregar

            try {
                List<Cliente> clientes = controller.listarClientes();
                for (Cliente c : clientes) {
                    modelo.addRow(new Object[] {
                        c.getId(), c.getNome(), c.getIdade(),
                        c.getEmail(), c.getTelefone(), c.getCpf()
                    });
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(),
                    // "Erro ao conectar ao banco de dados:\n" +
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
                // Tabela permanece vazia, mas a interface continua funcional
            }
        }

        private void exibirErros(Map<String, String> erros) {
            for (Map.Entry<String, String> erro : erros.entrySet()) {
                switch (erro.getKey()) {
                    case "nome":
                        nomeError.setText(erro.getValue());
                        nomeField.setBorder(BorderFactory.createLineBorder(Color.RED));
                        break;
                    case "idade":
                        idadeError.setText(erro.getValue());
                        idadeField.setBorder(BorderFactory.createLineBorder(Color.RED));
                        break;
                    case "email":
                        emailError.setText(erro.getValue());
                        emailField.setBorder(BorderFactory.createLineBorder(Color.RED));
                        break;
                    case "telefone":
                        telefoneError.setText(erro.getValue());
                        telefoneField.setBorder(BorderFactory.createLineBorder(Color.RED));
                        break;
                    case "cpf":
                        cpfError.setText(erro.getValue());
                        cpfField.setBorder(BorderFactory.createLineBorder(Color.RED));
                        break;
                }
            }
        }

	public static void main(String[] args) {		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(() -> new ClienteViewFormSwing().setVisible(true));
	}
}

