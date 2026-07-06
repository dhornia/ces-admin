package ui;

import exception.EmailAlreadyExistsException;
import exception.InvalidDataException;
import exception.UserNotFoundException;
import model.User;
import service.UserSystem;
import session.Session;

public class AdminMenu {

    public static void show(UserSystem userSystem) {
        System.out.println("Bienvenido " + Session.getUser().getFullName());
        ConsolePrinter.menuHeader();

        System.out.println("1) Buscar usuario");
        System.out.println("2) Listar usuarios");
        System.out.println("3) Alta usuario Tester");
        System.out.println("4) Cerrar sesión");

        System.out.println("\nSeleccione una opción:");

        int option = InputReader.readMenuOption(1, 4);

        switch (option) {
            case 1:
                findUser(userSystem);
                break;

            case 2:
                listUsers(userSystem);
                break;

            case 3:
                createTester(userSystem);
                break;

            case 4:
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
            ConsolePrinter.error(e.getMessage());
        }
    }

    private static void listUsers(UserSystem userSystem) {
        ConsolePrinter.printUsers(userSystem.getAllUsers());
    }

    private static void logout() {
        Session.logout();
    }

    private static void createTester(UserSystem userSystem) {
        try {
            String name = InputReader.read("Nombre: ");
            String lastName = InputReader.read("Apellido: ");
            String email = InputReader.read("Correo: ");
            String password = InputReader.read("Contraseña: ");
            String level = InputReader.read("Nivel(Tester Junior, Tester Senior, Tester Líder): ");
            String country = InputReader.read("País: ");

            userSystem.createTester(name, lastName, email, password, country, level);
            ConsolePrinter.success("Usuario creado con éxito");

        } catch (InvalidDataException e) {
            ConsolePrinter.error("No se pudo crear el usuario: " + e.getMessage());

        } catch (EmailAlreadyExistsException e) {
            ConsolePrinter.error(e.getMessage());
        }
    }
}