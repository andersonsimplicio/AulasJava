package aula;

public class Main{

public static void main(String[] args) {
    ContaBancaria conta = new ContaBancaria("João", 1000.0);

    // Exibe o saldo inicial
    System.out.println("Saldo inicial: " + conta.getSaldo());

    // Realiza um depósito
    conta.depositar(500.0);
    System.out.println("Saldo após depósito de 500: " + conta.getSaldo());

    // Realiza um saque
    conta.sacar(300.0);
    System.out.println("Saldo após saque de 300: " + conta.getSaldo());

    // Tenta realizar um saque maior que o saldo disponível
    conta.sacar(1500.0);

    // Atualiza o titular da conta
    conta.setTitular("João Silva");
    System.out.println("Titular atualizado: João Silva");

    // Exibe o saldo final
    System.out.println("Saldo final: " + conta.getSaldo());
}

}