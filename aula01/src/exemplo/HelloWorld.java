package exemplo;
import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        // Cria um objeto Scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);
        // Solicita ao usuário que insira a sua idade
        System.out.print("Digite sua idade: ");
        int idade = scanner.nextInt();
        System.out.println("Sua idade é "+String.format("%.2f", Math.sqrt(idade)));
    }
}