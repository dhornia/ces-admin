package ui;

import exception.EmailAlreadyExistsException;
import exception.InvalidDataException;
import exception.UserNotFoundException;
import model.User;
import service.UserSystem;
import session.Session;

public class AdminMenu extends Menu {

    public AdminMenu(UserSystem userSystem) {
        super(userSystem);
    }

    private void findUser() {
        String email = InputReader.readInput("Email: ");

        try {
            User user = userSystem.findUserByEmail(email);
            ConsolePrinter.printUser(user);

        } catch (UserNotFoundException e) {
            ConsolePrinter.error(e.getMessage());
        }
    }

    private void listUsers() {
        ConsolePrinter.printUsers(userSystem.getAllUsers());
    }

    private void logout() {
        Session.logout();
        ConsolePrinter.success("Sesión cerrada");
    }

    private void createTester() {
        try {
            String name = InputReader.readInput("Nombre: ");
            String lastName = InputReader.readInput("Apellido: ");
            String email = InputReader.readInput("Correo: ");
            String password = InputReader.readInput("Contraseña: ");
            String level = InputReader.readInput("Nivel(Tester Junior, Tester Senior, Tester Líder): ");
            String country = InputReader.readInput("País: ");

            userSystem.createTester(name, lastName, email, password, country, level);
            ConsolePrinter.success("Usuario creado con éxito");

        } catch (InvalidDataException e) {
            ConsolePrinter.error("No se pudo crear el usuario: " + e.getMessage());

        } catch (EmailAlreadyExistsException e) {
            ConsolePrinter.error(e.getMessage());
        }
    }

    @Override
    public boolean show() {
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
                findUser();
                break;

            case 2:
                listUsers();
                break;

            case 3:
                createTester();
                break;

            case 4:
                logout();
                break;
        }
        return true;
    }
}