import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    public TelaPrincipal() {
        setTitle("Sistema de Agendamento");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0, 1, 10, 10));

        // botões menu
        JButton btnInserirCliente = new JButton("Inserir Cliente na Fila");
        JButton btnAgendarCliente = new JButton("Agendar Cliente");
        JButton btnConsultar = new JButton("Consultar Cliente");
        JButton btnListarFila = new JButton("Listar Fila");
        JButton btnListarAgendamentos = new JButton("Listar Agendamentos");

        // ações botõess
        btnInserirCliente.addActionListener(e -> new TelaInserirCliente());
        btnAgendarCliente.addActionListener(evt -> new TelaAgendarCliente()); // corrigido aqui
        btnConsultar.addActionListener(e -> new TelaConsultarCliente());
        btnListarFila.addActionListener(e -> new TelaListarFila());
        btnListarAgendamentos.addActionListener(e -> new TelaListarAgendamentos());

        // Adiciona os botões ao painel
        panel.add(btnInserirCliente);
        panel.add(btnAgendarCliente);
        panel.add(btnConsultar);
        panel.add(btnListarFila);
        panel.add(btnListarAgendamentos);

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }
}