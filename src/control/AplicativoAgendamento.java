package control;
import model.Cliente;
import view.View;
import java.util.*;

public class AplicativoAgendamento {
    private List<Cliente> clientes = new ArrayList<>();

    private PriorityQueue<Cliente> filaEspera = new PriorityQueue<>(Comparator.comparingInt(Cliente::getPrioridade));
    private View view = new View();

    public void iniciar() {
        int opcao;
        do {
            opcao = view.mostrarMenu();
            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> consultarCliente();
                case 3 -> listarClientes();
                case 4 -> excluirCliente();
                case 0 -> view.exibirMensagem("Saindo...");
                default -> view.exibirMensagem("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarCliente() {
        String nome = view.lerString("Nome do Cliente: ");
        view.exibirMensagem("Telefone: ");
        String telefone = view.lerString("");
        int id = view.lerInt("ID: ");
        int prioridade = view.lerInt("Prioridade (1 - Alta, 2 - Média, 3 - Baixa): ");

        Cliente cliente = new Cliente(nome, telefone, id, prioridade);
        clientes.add(cliente);
        filaEspera.add(cliente);

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