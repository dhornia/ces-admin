package ui;

import exception.InvalidMenuOptionException;

import java.util.Scanner;

public class InputReader {
    private static Scanner scanner = new Scanner(System.in);

    public static String readInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static int readMenuOption(int min, int max) {
        try {
            int option = Integer.parseInt(readInput("\nSeleccione una opción: "));

            if (option < min || option > max) {
                throw new InvalidMenuOptionException("Opción inválida");
            }

            return option;

        } catch (NumberFormatException e) {
            ConsolePrinter.error("Debe ingresar un número");

        } catch (InvalidMenuOptionException e) {
            ConsolePrinter.error(e.getMessage());
        }

        return -1;
    }

    public static void close() {
        scanner.close();
    }
}
