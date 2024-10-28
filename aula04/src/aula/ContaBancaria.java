package aula;

public class ContaBancaria {
    private double saldo;
    private String titular;

    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // Método para acessar o saldo da conta
    public double getSaldo() {
        return saldo;
    }

    // Método para atualizar o titular da conta
    public void setTitular(String titular) {
        this.titular = titular;
    }

    // Método para realizar um depósito
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    // Método para realizar um saque
    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }
}