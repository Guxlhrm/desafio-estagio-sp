import java.util.Scanner;

public class Inverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a palavra que deseja inverter:");
        String original = scanner.nextLine();

        scanner.close();

        String invertida = inverterString(original);

        System.out.println("Palavra invertida: " + invertida);
    }

    private static String inverterString(String str) {
        char[] caracteres = str.toCharArray();
        int comprimento = caracteres.length;
        char[] invertida = new char[comprimento];

        for (int i = 0; i < comprimento; i++) {
            invertida[i] = caracteres[comprimento - 1 - i];
        }

        return new String(invertida);
    }
}
