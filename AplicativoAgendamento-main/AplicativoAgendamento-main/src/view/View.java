package view;
import java.util.Scanner;

public class View {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Consultar Cliente");
        System.out.println("3 - Listar Clientes e Agendamentos");
        System.out.println("4 - Excluir Cliente");
        System.out.println("5 - Agendar Cliente");
        System.out.println("6 - Cadastrar Serviço"); // NOVA OPÇÃO
        System.out.println("7 - Excluir Agendamento");
        System.out.println("0 - Sair");
        return lerInt("Escolha uma opção: ");
    }


    public String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public int lerInt(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Insira um número inteiro:");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Consome a quebra de linha
        return valor;
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}
