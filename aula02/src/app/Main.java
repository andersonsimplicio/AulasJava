package app;

public class Main {
    public static void main(String[] args) {
        int p = 10;
        int q = 5;

        boolean igual = (p == q); System.out.println(igual);  // Igualdade
        boolean diferente = (p != q); System.out.println(diferente);  // Diferença
        boolean maiorQue = (p > q);  System.out.println(maiorQue); // Maior que
        boolean menorQue = (p < q);  System.out.println(menorQue);// Menor que
        boolean maiorOuIgual = (p >= q);  System.out.println(maiorOuIgual);// Maior ou igual a
        boolean menorOuIgual = (p <= q); System.out.println(menorOuIgual); // Menor ou igual a
    }
}
