import java.util.Scanner;

public class Main {
    public static void main(String []args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o número: ");
        int numero = scanner.nextInt();

        if(Fibonacci.isFibonacci(numero)){
            System.out.println(numero + " pertence a sequência de Fibonacci.");
        } else {
            System.out.println(numero + " não pertence a sequência de Fibonacci.");
        }

        scanner.close();
    }
}