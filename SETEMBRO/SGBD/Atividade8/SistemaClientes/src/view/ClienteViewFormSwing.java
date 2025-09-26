package view;

import dao.Conexao;
import controller.ClienteController;
import exception.ValidacaoException;
import model.Cliente;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface gráfica para cadastro e gerenciamento de clientes. Utiliza Swing
 * com layout organizado, validação visual e tabela estilizada.
 */
public class ClienteViewFormSwing extends JFrame {

	private static final long serialVersionUID = 1L;
	// Controlador responsável pela lógica de negócio
	private ClienteController controller;

	// Campos de entrada
	private JTextField nomeField, idadeField, emailField, telefoneField, cpfField;

	// Labels para mensagens de erro
	private JLabel nomeError, idadeError, emailError, telefoneError, cpfError;

	// Tabela e modelo para exibir clientes
	private JTable tabela;
	private DefaultTableModel modelo;


	/**
	 * Construtor da interface principal. Inicializa componentes e organiza layout.
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
	}


	public JPanel createFormPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "DADOS DO CLIENTE",
				TitledBorder.CENTER, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 16), Color.DARK_GRAY));

		// Rótulos
		JLabel nomeLabel = new JLabel("Nome:");
		JLabel idadeLabel = new JLabel("Idade:");
		JLabel emailLabel = new JLabel("Email:");
		JLabel telefoneLabel = new JLabel("Telefone:");
		JLabel cpfLabel = new JLabel("CPF:");

		// Campos de entrada
		nomeField = new JTextField(20);
		idadeField = new JTextField(5);
		emailField = new JTextField(20);
		telefoneField = new JTextField(15);
		cpfField = new JTextField(15);

		// Mensagens de erro
		nomeError = new JLabel(" ");
		idadeError = new JLabel(" ");
		emailError = new JLabel(" ");
		telefoneError = new JLabel(" ");
		cpfError = new JLabel(" ");

		// Estilização das mensagens de erro
		Color errorColor = new Color(204, 0, 0);
		Font errorFont = new Font("SansSerif", Font.ITALIC, 12);
		for (JLabel errorLabel : new JLabel[] { nomeError, idadeError, emailError, telefoneError, cpfError }) {
			errorLabel.setForeground(errorColor);
			errorLabel.setFont(errorFont);
		}

		// Layout com alinhamento refinado
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(nomeLabel)
						.addComponent(idadeLabel).addComponent(emailLabel).addComponent(telefoneLabel)
						.addComponent(cpfLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(nomeField)
						.addComponent(idadeField).addComponent(emailField).addComponent(telefoneField)
						.addComponent(cpfField))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(nomeError)
						.addComponent(idadeError).addComponent(emailError).addComponent(telefoneError)
						.addComponent(cpfError)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(nomeLabel)
						.addComponent(nomeField).addComponent(nomeError))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(idadeLabel)
						.addComponent(idadeField).addComponent(idadeError))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(emailLabel)
						.addComponent(emailField).addComponent(emailError))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(telefoneLabel)
						.addComponent(telefoneField).addComponent(telefoneError))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(cpfLabel)
						.addComponent(cpfField).addComponent(cpfError)));

		return panel;
	}

	/**
	 * Cria o painel da tabela com rolagem, título centralizado e estilo visual.
	 */
	private JScrollPane createTablePanel() {
		modelo = new DefaultTableModel(new Object[] { "ID", "Nome", "Idade", "Email", "Telefone", "CPF" }, 0);
		tabela = new JTable(modelo);

		// Estilização da tabela
		tabela.setShowGrid(false);
		tabela.setIntercellSpacing(new Dimension(0, 0));
		tabela.setRowHeight(24);
		tabela.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		tabela.getTableHeader().setReorderingAllowed(false);

		// Alinhamento centralizado dos dados
		DefaultTableCellRenderer alinhamentoCentralizado = new DefaultTableCellRenderer();
		alinhamentoCentralizado.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tabela.getColumnCount(); i++) {
			tabela.getColumnModel().getColumn(i).setCellRenderer(alinhamentoCentralizado);
		}
	
		// Cria borda com título centralizado e fonte personalizada
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

		// Botões de ação
		JButton btnCadastrar = new JButton("Cadastrar");
		JButton btnAtualizar = new JButton("Atualizar");
		JButton btnExcluir = new JButton("Excluir");
		JButton btnListar = new JButton("Listar");

		// Estilização dos botões
		Color verde = new Color(0, 153, 76);
		Color azul = new Color(0, 120, 215);
		Color vermelho = new Color(204, 0, 0);
		Color cinza = Color.DARK_GRAY;

		btnCadastrar.setBackground(verde);
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setFocusPainted(false);
		btnAtualizar.setBackground(azul);
		btnAtualizar.setForeground(Color.WHITE);
		btnAtualizar.setFocusPainted(false);
		btnExcluir.setBackground(vermelho);
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setFocusPainted(false);
		btnListar.setBackground(cinza);
		btnListar.setForeground(Color.WHITE);
		btnListar.setFocusPainted(false);

		// Ações dos botões
		btnCadastrar.addActionListener(e -> cadastrarCliente());
		btnAtualizar.addActionListener(e -> atualizarCliente());
		btnExcluir.addActionListener(e -> excluirCliente());
		btnListar.addActionListener(e -> carregarClientes());

		// Adiciona ao painel
		botoes.add(btnCadastrar);
		botoes.add(btnAtualizar);
		botoes.add(btnExcluir);
		botoes.add(btnListar);

		return botoes;
	}

	/**
	 * Limpa as mensagens de erro dos campos.
	 */
	private void limparErros() {
		nomeError.setText(" ");
		idadeError.setText(" ");
		emailError.setText(" ");
		telefoneError.setText(" ");
		cpfError.setText(" ");
	}

	/**
	 * Monta um objeto Cliente a partir dos dados preenchidos.
	 * 
	 * @param id ID do cliente (0 para novo)
	 * @return Cliente preenchido
	 * @throws Exception se houver erro de validação
	 */
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

		if (!valido)
			throw new Exception("Preencha os campos corretamente.");

		return new Cliente(id, nome, idade, email, telefone, cpf);
	}

	/*
	 * Realiza o cadastro de um novo cliente.
	 */
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

	/*
	 * Atualiza os dados de um cliente selecionado na tabela. Permite edição nos
	 * campos e salva após confirmação.
	 */
	private void atualizarCliente() {
		int linha = tabela.getSelectedRow();
		if (linha == -1) {
			JOptionPane.showMessageDialog(this, "Selecione um cliente para atualizar.");
			return; //usado como um controle de fluxo para interromper o método quando uma condição não é satisfeita.
		}

		// Obtém os dados da linha selecionada
		int id = (int) tabela.getValueAt(linha, 0);
		nomeField.setText((String) tabela.getValueAt(linha, 1));
		idadeField.setText(String.valueOf(tabela.getValueAt(linha, 2)));
		emailField.setText((String) tabela.getValueAt(linha, 3));
		telefoneField.setText((String) tabela.getValueAt(linha, 4));
		cpfField.setText((String) tabela.getValueAt(linha, 5));

		// Foca no campo nome para facilitar edição
		nomeField.requestFocusInWindow();
		nomeField.selectAll();

		// Confirmação de atualização
		int confirm = JOptionPane.showConfirmDialog(this, "Deseja salvar as alterações?");
		if (confirm != JOptionPane.YES_OPTION)
			return;

		try {
			// Monta o cliente com os dados editados
			Cliente cliente = montarCliente(id);
			boolean sucesso = controller.atualizarCliente(cliente);
			if (sucesso) {
			    carregarClientes(); // atualiza a tabela
			    JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
			} else {
			    JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
			}

		} catch (ValidacaoException ex) {
			JOptionPane.showMessageDialog(this, "Erro de validação: " + ex.getMessage());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage());
		}
	}

	/**
	 * Exclui o cliente selecionado na tabela após confirmação.
	 */
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

	/**
	 * Recarrega os dados da tabela com os clientes cadastrados. Validação caso o
	 * banco esteja off um popup será mostrado na tela.
	 */
	private void carregarClientes() {
		Connection conn = Conexao.conectar();
		if (conn == null) {
			JOptionPane.showMessageDialog(this,
					"Não foi possível conectar ao banco de dados.\nVerifique a conexão e tente novamente.",
					"Erro de Conexão", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			String sql = "SELECT * FROM clientes";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			modelo.setRowCount(0); // Limpa a tabela antes de carregar

			while (rs.next()) {
				modelo.addRow(new Object[] { rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"),
						rs.getString("email"), rs.getString("telefone"), rs.getString("cpf") });
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Erro ao carregar os dados dos clientes:\n" + e.getMessage(),
					"Erro de Consulta", JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 * Método principal para iniciar a aplicação.
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(() -> new ClienteViewFormSwing().setVisible(true));
	}
}

