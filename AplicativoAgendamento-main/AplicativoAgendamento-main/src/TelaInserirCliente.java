import javax.swing.*;
import java.awt.*;
import model.Cliente;
import model.Sistema;

public class TelaInserirCliente extends JFrame {
    public TelaInserirCliente() {
        setTitle("Inserir Cliente na Fila");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();

        JLabel lblID = new JLabel("ID:");
        JTextField txtID = new JTextField();

        JButton btnSalvar = new JButton("Salvar");

        setLayout(new GridLayout(0, 1, 5, 5));
        add(lblNome);
        add(txtNome);
        add(lblTelefone);
        add(txtTelefone);
        add(lblID);
        add(txtID);
        add(btnSalvar);

        // Ação do botão
        btnSalvar.addActionListener(e -> {
            try {
                String nome = txtNome.getText().trim();
                String telefone = txtTelefone.getText().trim();
                int id = Integer.parseInt(txtID.getText().trim());

                String[] opcoes = {"1 - Alta", "2 - Média", "3 - Baixa"};
                String prioridadeStr = (String) JOptionPane.showInputDialog(this,
                        "Selecione a prioridade:", "Prioridade",
                        JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[1]);

                if (prioridadeStr == null) return;

                int prioridade = Integer.parseInt(prioridadeStr.substring(0, 1));

                Cliente cliente = new Cliente(nome, telefone, id, prioridade);
                Sistema.adicionarCliente(cliente);

                JOptionPane.showMessageDialog(this, "Cliente cadastrado: " + cliente);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}