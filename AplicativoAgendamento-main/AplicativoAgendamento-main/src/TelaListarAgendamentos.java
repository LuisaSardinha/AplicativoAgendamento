import javax.swing.*;
import java.awt.*;
import model.Agendamento;
import model.Sistema;

public class TelaListarAgendamentos extends JFrame {
    public TelaListarAgendamentos() {
        setTitle("Agendamentos Confirmados");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea txtArea = new JTextArea();
        txtArea.setEditable(false);

        StringBuilder sb = new StringBuilder();
        if (Sistema.agendamentos.isEmpty()) {
            sb.append("Nenhum agendamento encontrado.");
        } else {
            int count = 1;
            for (Agendamento ag : Sistema.agendamentos) {
                sb.append(count++).append(". ").append(ag).append("\n");
            }
        }

        txtArea.setText(sb.toString());
        add(new JScrollPane(txtArea), BorderLayout.CENTER);

        setVisible(true);
    }
}