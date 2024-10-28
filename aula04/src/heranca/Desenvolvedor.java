package heranca;

public class Desenvolvedor extends Funcionario {
    private String linguagemPrincipal;

    // Construtor da subclasse Desenvolvedor
    public Desenvolvedor(String nome, double salario, String linguagemPrincipal) {
        super(nome, salario); // Chama o construtor da superclasse
        this.linguagemPrincipal = linguagemPrincipal;
    }

    // Método para exibir informações específicas do desenvolvedor
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Linguagem Principal: " + linguagemPrincipal);
    }
}
