package model;

import java.util.*;
import java.io.*;

public class Sistema {
    public static List<Cliente> clientes = new ArrayList<>();
    public static PriorityQueue<Cliente> filaEspera = new PriorityQueue<>(Comparator.comparingInt(Cliente::getPrioridade));
    public static List<Agendamento> agendamentos = new ArrayList<>();

    public static void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
        filaEspera.add(cliente);
        salvarClienteNoArquivo(cliente);
    }

    private static void salvarClienteNoArquivo(Cliente cliente) {
        try (FileWriter writer = new FileWriter("clientes.txt", true)) {
            writer.write("ID: " + cliente.getId() + "\n");
            writer.write("Nome: " + cliente.getNome() + "\n");
            writer.write("Telefone: " + cliente.getTelefone() + "\n");
            writer.write("Prioridade: " + cliente.getPrioridade() + "\n");
            writer.write("---------------\n");
        } catch (IOException e) {
            System.err.println("Erro ao salvar cliente no arquivo: " + e.getMessage());
        }
    }
}
