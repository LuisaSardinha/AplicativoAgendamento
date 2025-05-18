package view;
import java.util.Scanner;

public class View {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n--- Sistema de Agendamento ---");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Consultar Cliente por ID");
        System.out.println("3. Listar Clientes");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }

    public String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.next();
    }

    public int lerInt(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextInt();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}