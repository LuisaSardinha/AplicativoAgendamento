import javax.swing.*;
import java.awt.*;
import java.util.PriorityQueue;
import model.Cliente;
import model.Sistema;

public class TelaListarFila extends JFrame {
    public TelaListarFila() {
        setTitle("Fila de Espera");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea txtArea = new JTextArea();
        txtArea.setEditable(false);

        // Copia temporária da fila pra não esvaziar a original
        PriorityQueue<Cliente> filaTemp = new PriorityQueue<>(Sistema.filaEspera);
        StringBuilder sb = new StringBuilder();

        if (filaTemp.isEmpty()) {
            sb.append("Nenhum cliente na fila.");
        } else {
            int posicao = 1;
            while (!filaTemp.isEmpty()) {
                Cliente c = filaTemp.poll();
                sb.append(posicao++).append(". ").append(c).append("\n");
            }
        }

        txtArea.setText(sb.toString());
        add(new JScrollPane(txtArea), BorderLayout.CENTER);

        setVisible(true);
    }
}