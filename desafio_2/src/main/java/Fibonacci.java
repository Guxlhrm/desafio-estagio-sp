public class Fibonacci {
    public static boolean isFibonacci(int num){
        if (num == 0 || num == 1) {
            return true;
        }

        int anterior = 0;
        int atual = 1;

        while (atual < num){
            int posterior = anterior + atual;
            anterior = atual;
            atual = posterior;
        }
        return atual == num;
    }

}