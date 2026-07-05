package ui;

import exception.InvalidMenuOptionException;

import java.util.Scanner;

public class InputReader {
    private static Scanner scanner = new Scanner(System.in);

    public static String read(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static int readMenuOption(int min, int max) {
        try {
            int option = Integer.parseInt(read("Seleccione una opción: "));

            if (option < min || option > max) {
                throw new InvalidMenuOptionException("Opción de menú inexistente");
            }

            return option;

        } catch (NumberFormatException e) {
            System.out.println("Opción inválida. Debe ingresar un número");

        } catch (InvalidMenuOptionException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public static void close() {
        scanner.close();
    }
}
