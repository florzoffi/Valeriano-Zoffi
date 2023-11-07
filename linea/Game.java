package linea;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws Exception {
        System.out.println("Dimensiones?");
        Scanner scanner = new Scanner(System.in);
        int base = promptAsInt(scanner, "Base? ");
        int altura = promptAsInt(scanner, "Altura? ");
        char estrategia = promptAsChar(scanner, "Estrategia de Juego: A, B o C? ");

        Line game = new Line(base, altura, estrategia);
        System.out.println(game.show());

        while (!game.finished()) {
            game.playRedAt(promptAsInt(scanner, "Rojas? "));
            System.out.println(game.show());

            if (!game.finished()) {
                game.playBlueAt(promptAsInt(scanner, "Azul? "));
                System.out.println(game.show());
            }
        }
    }

    private static int promptAsInt(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    private static char promptAsChar(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.next().charAt(0);
    }
}
