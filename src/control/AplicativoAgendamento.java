package control;
import java.io.FileWriter;
import java.io.IOException;
import model.Cliente;
import model.Agendamento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import view.View;
import java.util.*;


public class AplicativoAgendamento {

    private void agendarCliente() {
        int id = view.lerInt("Informe o ID do cliente para agendar: ");
        Cliente cliente = consultarClientePorId(id);

        if (cliente == null) {
            view.exibirMensagem("Cliente não encontrado.");
            return;
        }

        String dataStr = view.lerString("Informe a data do agendamento (dd/MM/yyyy HH:mm): ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date data = sdf.parse(dataStr);
            Agendamento agendamento = new Agendamento(cliente, data);
            agendamentos.add(agendamento);
            view.exibirMensagem("Agendamento realizado: " + agendamento);
        } catch (ParseException e) {
            view.exibirMensagem("Data inválida. Utilize o formato dd/MM/yyyy HH:mm.");
        }
    }
    private List<Cliente> clientes = new ArrayList<>();

    private PriorityQueue<Cliente> filaEspera = new PriorityQueue<>(Comparator.comparingInt(Cliente::getPrioridade));
    private View view = new View();
    private List<Agendamento> agendamentos = new ArrayList<>();

    public void iniciar() {
        int opcao;
        do {
            opcao = view.mostrarMenu();
            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> consultarCliente();
                case 3 -> listarClientes();
                case 4 -> excluirCliente();
                case 5 -> agendarCliente();
                case 0 -> view.exibirMensagem("Saindo...");
                default -> view.exibirMensagem("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        int id = view.lerInt("ID: ");
        int prioridade = view.lerInt("Prioridade (1 - Alta, 2 - Média, 3 - Baixa): ");

        Cliente cliente = new Cliente(nome, telefone, id, prioridade);
        clientes.add(cliente);
        filaEspera.add(cliente);
        
        try (FileWriter writer = new FileWriter("clientes.txt", true)) {
            writer.write("ID: " + cliente.getId() + "\n");
            writer.write("Nome: " + cliente.getNome() + "\n");
            writer.write("Telefone: " + cliente.getTelefone() + "\n");
            writer.write("Prioridade: " + cliente.getPrioridade() + "\n");
            writer.write("---------------\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar cliente no arquivo: " + e.getMessage());
        }

        view.exibirMensagem("Cliente cadastrado: " + cliente);
    }

    private void consultarCliente() {
        int id = view.lerInt("Informe o ID do cliente: ");
        Cliente cliente = consultarClientePorId(id);
        if (cliente != null) {
            view.exibirMensagem("Consulta: " + cliente);
        } else {
            view.exibirMensagem("Cliente não encontrado.");
        }
    }

    private Cliente consultarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    private void listarClientes() {
        view.exibirMensagem(" --- Lista de Agendamentos ---");
        for (Agendamento agendamento : agendamentos) {
            view.exibirMensagem(agendamento.toString());
        }
        view.exibirMensagem("\n--- Lista de Clientes ---");
        for (Cliente cliente : clientes) {
            view.exibirMensagem(cliente.toString());
        }

        view.exibirMensagem("\n--- Fila de Espera por Prioridade ---");
        PriorityQueue<Cliente> filaTemp = new PriorityQueue<>(filaEspera);
        while (!filaTemp.isEmpty()) {
            view.exibirMensagem(filaTemp.poll().toString());
        }
    }

    private void excluirCliente() {
        int id = view.lerInt("Informe o ID do cliente a ser excluído: ");
        Cliente cliente = consultarClientePorId(id);
        if (cliente != null) {
            clientes.remove(cliente);
            filaEspera.remove(cliente);
            view.exibirMensagem("Cliente excluído: " + cliente);
        } else {
            view.exibirMensagem("Cliente não encontrado.");
        }
    }




    public static void main(String[] args) {
        new AplicativoAgendamento().iniciar();
    }
}
