package view;

import controller.AgendamentoController;
import dao.AgendamentoDAO;
import model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class AgendamentoViewSwing extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextField dataField;
    private JTextField horaField;
    private JButton agendarButton;
   
    
    public AgendamentoViewSwing(Cliente cliente) {
        setTitle("Agendamento de Vistoria");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        dataField = new JTextField(10);
        horaField = new JTextField(10);
        agendarButton = new JButton("Agendar");

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Data (AAAA-MM-DD):"));
        panel.add(dataField);
        panel.add(new JLabel("Hora (HH:MM):"));
        panel.add(horaField);
        panel.add(new JLabel(""));
        panel.add(agendarButton);

        add(panel);

        Connection conn = dao.Conexao.getConnection();
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO(conn);
        AgendamentoController controller = new AgendamentoController(agendamentoDAO);

        agendarButton.addActionListener(e -> {
            String data = dataField.getText();
            String hora = horaField.getText();
            boolean sucesso = controller.agendarVistoria(cliente, data, hora);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "✅ Vistoria agendada com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Falha ao agendar vistoria.");
            }
        });
    }
}
