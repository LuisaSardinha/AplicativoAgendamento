import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Cliente;
import model.Agendamento;
import model.Sistema;

public class TelaAgendarCliente extends JFrame {
    public TelaAgendarCliente() {
        setTitle("Agendar Cliente");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Cliente cliente = Sistema.filaEspera.poll(); // pega e remove o primeiro da fila

        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Nenhum cliente na fila.");
            dispose();
            return;
        }

        JLabel lblInfo = new JLabel("Cliente: " + cliente.getNome());
        JLabel lblData = new JLabel("Data e Hora (dd/MM/yyyy HH:mm):");
        JTextField txtDataHora = new JTextField();
        JButton btnConfirmar = new JButton("Confirmar");

        setLayout(new GridLayout(0, 1, 5, 5));
        add(lblInfo);
        add(lblData);
        add(txtDataHora);
        add(btnConfirmar);

        btnConfirmar.addActionListener(e -> {
            try {
                String dataHoraStr = txtDataHora.getText().trim();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date dataHora = sdf.parse(dataHoraStr);

                Agendamento agendamento = new Agendamento(cliente, dataHora);
                Sistema.agendamentos.add(agendamento);

                JOptionPane.showMessageDialog(this, "Agendamento realizado:\n" + agendamento);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Data inválida. Use formato dd/MM/yyyy HH:mm");
            }
        });

        setVisible(true);
    }
}
