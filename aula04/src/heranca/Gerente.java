package heranca;

public class Gerente extends Funcionario {
        private double bonus;
    
        // Construtor da subclasse Gerente
        public Gerente(String nome, double salario, double bonus) {
            super(nome, salario); // Chama o construtor da superclasse
            this.bonus = bonus;
        }
    
        // Método para exibir informações específicas do gerente
        @Override
        public void exibirInformacoes() {
            super.exibirInformacoes();
            System.out.println("Bônus: " + bonus);
        }
}
    

