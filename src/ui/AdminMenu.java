package ui;

import exception.UserNotFoundException;
import model.User;
import service.UserSystem;
import session.Session;

public class AdminMenu {

    public static void show(UserSystem userSystem) {
        ConsolePrinter.printWelcome(Session.getUser());
        System.out.println("--- MENU ---");
        System.out.println("1 - Buscar usuario");
        System.out.println("2 - Listar usuarios");
        System.out.println("3 - Cerrar sesión");

        int option = InputReader.readMenuOption(1, 3);

        switch (option) {
            case 1:
                findUser(userSystem);
                break;

            case 2:
                listUsers(userSystem);
                break;

            case 3:
                logout();
                break;
        }
    }

    private static void findUser(UserSystem userSystem) {
        String email = InputReader.read("Email: ");

        try {
            User user = userSystem.findUserByEmail(email);
            ConsolePrinter.printUser(user);

        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listUsers(UserSystem userSystem) {
        ConsolePrinter.printUsers(userSystem.getAllUsers());
    }

    private static void logout() {
        Session.logout();
    }
}