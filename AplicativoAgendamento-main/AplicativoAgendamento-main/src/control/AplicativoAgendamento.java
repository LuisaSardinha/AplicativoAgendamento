package control;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.Cliente;
import model.Agendamento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Servico;
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

        if (servicos.isEmpty()) {
            view.exibirMensagem("Nenhum serviço disponível. Cadastre um serviço antes de agendar.");
            return;
        }

        view.exibirMensagem("--- Serviços Disponíveis ---");
        for (int i = 0; i < servicos.size(); i++) {
            view.exibirMensagem(i + " - " + servicos.get(i));
        }

        int indiceServico = view.lerInt("Escolha o número do serviço: ");

        if (indiceServico < 0 || indiceServico >= servicos.size()) {
            view.exibirMensagem("Serviço inválido.");
            return;
        }

        String dataStr = view.lerString("Informe a data do agendamento (dd/MM/yyyy HH:mm): ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            Date data = sdf.parse(dataStr);
            Servico servicoEscolhido = servicos.get(indiceServico);
            Agendamento agendamento = new Agendamento(cliente, data, servicoEscolhido);
            agendamentos.add(agendamento);
            view.exibirMensagem("Agendamento realizado: " + agendamento);
        } catch (ParseException e) {
            view.exibirMensagem("Data inválida. Utilize o formato dd/MM/yyyy HH:mm.");
        }
    }


    private List<Cliente> clientes = new ArrayList<>();
    private List<Servico> servicos = new ArrayList<>();

    private PriorityQueue<Cliente> filaEspera = new PriorityQueue<>(Comparator.comparingInt(Cliente::getPrioridade));
    private View view = new View();
    private List<Agendamento> agendamentos = new ArrayList<>();

    public void iniciar() {
        carregarClientesDoArquivo();
        int opcao;
        do {
            opcao = view.mostrarMenu();
            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> consultarCliente();
                case 3 -> listarClientes();
                case 4 -> excluirCliente();
                case 5 -> agendarCliente();
                case 6 -> cadastrarServico(); // NOVA OPÇÃO
                case 7 -> excluirAgendamento();
                case 0 -> view.exibirMensagem("Saindo...");
                default -> view.exibirMensagem("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarServico() {
        String nome = view.lerString("Nome do serviço: ");
        int duracao = view.lerInt("Duração (em minutos): ");
        double valor = Double.parseDouble(view.lerString("Valor (ex: 99.90): "));

        Servico servico = new Servico(nome, duracao, valor);
        servicos.add(servico);
        view.exibirMensagem("Serviço cadastrado: " + servico);
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
    private void carregarClientesDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
            String linha;
            String nome = null;
            String telefone = null;
            int id = -1;
            int prioridade = -1;

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("ID: ")) {
                    id = Integer.parseInt(linha.substring(4).trim());
                } else if (linha.startsWith("Nome: ")) {
                    nome = linha.substring(6).trim();
                } else if (linha.startsWith("Telefone: ")) {
                    telefone = linha.substring(10).trim();
                } else if (linha.startsWith("Prioridade: ")) {
                    prioridade = Integer.parseInt(linha.substring(12).trim());
                } else if (linha.startsWith("---------------")) {
                    if (nome != null && telefone != null && id != -1 && prioridade != -1) {
                        Cliente cliente = new Cliente(nome, telefone, id, prioridade);
                        clientes.add(cliente);
                        filaEspera.add(cliente);
                    }
                    nome = null;
                    telefone = null;
                    id = -1;
                    prioridade = -1;
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar clientes do arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erro de formato no arquivo de clientes: " + e.getMessage());
        }
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
    private void excluirAgendamento() {
        if (agendamentos.isEmpty()) {
            view.exibirMensagem("Nenhum agendamento para excluir.");
            return;
        }

        view.exibirMensagem("\n--- Agendamentos Atuais ---");
        for (int i = 0; i < agendamentos.size(); i++) {
            view.exibirMensagem((i + 1) + " - " + agendamentos.get(i));
        }

        int escolha = view.lerInt("Informe o número do agendamento que deseja excluir: ");

        if (escolha < 1 || escolha > agendamentos.size()) {
            view.exibirMensagem("Opção inválida.");
            return;
        }

        Agendamento removido = agendamentos.remove(escolha - 1);
        view.exibirMensagem("Agendamento removido com sucesso:\n" + removido);
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
            atualizarArquivoClientes();
        } else {
            view.exibirMensagem("Cliente não encontrado.");
        }
    }
    private void atualizarArquivoClientes(){
        try (FileWriter writer= new FileWriter("clientes.txt")) {
            for (Cliente cliente : clientes){
                writer.write("ID: " + cliente.getId() + "\n");
                writer.write("Nome: " + cliente.getNome() + "\n");
                writer.write("Telefone: " + cliente.getTelefone() + "\n");
                writer.write("Prioridade: " + cliente.getPrioridade() + "\n");
                writer.write("---------------\n");
            }
        } catch (IOException e){
            System.err.println("Erro ao atualizar o arquivo de clientes: " + e.getMessage());
        }
    }




    public static void main(String[] args) {
        new AplicativoAgendamento().iniciar();
    }
}
