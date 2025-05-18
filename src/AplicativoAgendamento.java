import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import java.util.*;

class Cliente {
    private String nome;
    private String telefone;
    private int id;

    public Cliente(String nome, String telefone, int id) {
        this.nome = nome;
        this.telefone = telefone;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Telefone: " + telefone;
    }
}

class Agendamento {
    private Cliente cliente;
    private Date horario;

    public Agendamento(Cliente cliente, Date horario) {
        this.cliente = cliente;
        this.horario = horario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getHorario() {
        return horario;
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente.getNome() + " | Horário: " + horario;
    }
}

public class AplicativoAgendamento {
    private List<Cliente> clientes = new ArrayList<>();
    private Queue<Agendamento> filaAgendamentos = new LinkedList<>();
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- Sistema de Agendamento ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Consultar Cliente por ID");
            System.out.println("3. Listar Clientes");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> consultarCliente();
                case 3 -> listarClientes();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    public void cadastrarCliente() {
        System.out.print("Nome do Cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = new Cliente(nome, telefone, id);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado: " + cliente);
    }

    public void consultarCliente() {
        System.out.print("Informe o ID do cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = consultarClientePorId(id);
        if (cliente != null) {
            System.out.println("Consulta: " + cliente);
        }
    }

    public Cliente consultarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        System.out.println("Cliente não encontrado.");
        return null;
    }

    public void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public static void main(String[] args) {
        AplicativoAgendamento sistema = new AplicativoAgendamento();
        sistema.menu();
    }
}
