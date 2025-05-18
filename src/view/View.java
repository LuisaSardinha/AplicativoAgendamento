package view;
import java.util.Scanner;

public class View {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n--- Sistema de Agendamento ---");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Consultar Cliente por ID");
        System.out.println("3. Listar Clientes");
        System.out.println("4. Excluir Cliente");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Insira um número inteiro:");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        return valor;
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
        scanner.nextLine(); // Limpa o buffer
        return valor;
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}