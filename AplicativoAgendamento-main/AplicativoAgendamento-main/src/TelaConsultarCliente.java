import javax.swing.*;
import java.awt.*;
import model.Cliente;
import model.Sistema;

public class TelaConsultarCliente extends JFrame {
    public TelaConsultarCliente() {
        setTitle("Consultar Cliente");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblOpcao = new JLabel("Buscar por:");
        String[] opcoes = {"ID", "Nome"};
        JComboBox<String> cbOpcao = new JComboBox<>(opcoes);

        JTextField txtBusca = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        setLayout(new GridLayout(0, 1, 5, 5));
        add(lblOpcao);
        add(cbOpcao);
        add(txtBusca);
        add(btnBuscar);

        btnBuscar.addActionListener(e -> {
            String tipoBusca = (String) cbOpcao.getSelectedItem();
            String valor = txtBusca.getText().trim();
            Cliente resultado = null;

            if (tipoBusca.equals("ID")) {
                try {
                    int id = Integer.parseInt(valor);
                    for (Cliente c : Sistema.clientes) {
                        if (c.getId() == id) {
                            resultado = c;
                            break;
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID inválido.");
                    return;
                }
            } else { // busca por nome
                for (Cliente c : Sistema.clientes) {
                    if (c.getNome().equalsIgnoreCase(valor)) {
                        resultado = c;
                        break;
                    }
                }
            }

            if (resultado != null) {
                JOptionPane.showMessageDialog(this, "Cliente encontrado:\n" + resultado);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
            }
        });

        setVisible(true);
    }
}