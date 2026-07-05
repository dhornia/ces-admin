package ui;

import model.User;

import java.util.Collection;

public class ConsolePrinter {
    public static void printHeader() {
        System.out.println("\n=====================");
        System.out.println("     Admin CES");
        System.out.println("=====================");
    }

    public static void printWelcome(User user) {
        System.out.println("\n*** Bienvenido "
                + user.getFullName()
                + " ***");
    }

    public static void printUser(User user) {
        System.out.println("\n+-------------- DATOS DEL USUARIO --------------+");
        System.out.printf("| %-15s | %-25s |\n", "Campo", "Valor");
        System.out.println("+------------------+---------------------------+");

        System.out.printf("| %-15s | %-25s |\n", "Nombre", user.getName());
        System.out.printf("| %-15s | %-25s |\n", "Apellido", user.getLastName());
        System.out.printf("| %-15s | %-25s |\n", "Email", user.getEmail());
        System.out.printf("| %-15s | %-25s |\n", "País", user.getCountry());
        System.out.printf("| %-15s | %-25s |\n", "Perfil", user.getRole());

        System.out.println("+------------------+---------------------------+");
    }

    public static void printUsers(Collection<User> users) {
        System.out.println("+----+----------------------+--------------------------+----------------+---------+");
        System.out.printf("| %-2s | %-20s | %-24s | %-14s | %-7s |\n",
                "#", "Nombre", "Email", "País", "Perfil");
        System.out.println("+----+----------------------+--------------------------+----------------+---------+");

        int i = 1;

        for (User user : users) {

            System.out.printf("| %-2d | %-20s | %-24s | %-14s | %-7s |\n",
                    i,
                    user.getName() + " " + user.getLastName(),
                    user.getEmail(),
                    user.getCountry(),
                    user.getRole()
            );

            i++;
        }

        System.out.println("+----+----------------------+--------------------------+----------------+---------+");
    }
}
