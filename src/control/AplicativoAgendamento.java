package control;
import model.Cliente;
import model.Agendamento;
import view.View;
import java.util.*;

public class AplicativoAgendamento {
    private List<Cliente> clientes = new ArrayList<>();
    private Queue<Agendamento> filaAgendamentos = new LinkedList<>();
    private View view = new View();

    public void iniciar() {
        int opcao;
        do {
            opcao = view.mostrarMenu();
            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> consultarCliente();
                case 3 -> listarClientes();
                case 0 -> view.exibirMensagem("Saindo...");
                default -> view.exibirMensagem("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarCliente() {
        String nome = view.lerString("Nome do Cliente: ");
        String telefone = view.lerString("Telefone: ");
        int id = view.lerInt("ID: ");

        Cliente cliente = new Cliente(nome, telefone, id);
        clientes.add(cliente);
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
    }

    public static void main(String[] args) {
        new AplicativoAgendamento().iniciar();
    }
}