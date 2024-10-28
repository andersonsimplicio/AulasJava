package heranca;


public class Main{

public static void main(String[] args) {
    Gerente gerente = new Gerente("Alice", 7000.0, 1500.0);
    Desenvolvedor desenvolvedor = new Desenvolvedor("Carlos", 5000.0, "Python");
    System.out.println("Informações do Gerente:");
    gerente.exibirInformacoes();

    // Exibe informações do Desenvolvedor
    System.out.println("\nInformações do Desenvolvedor:");
    desenvolvedor.exibirInformacoes();
}

}