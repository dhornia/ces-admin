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
            System.out.println("\n+------------- DATOS DEL USUARIO --------------+");
            ConsolePrinter.printUser(user);

        } catch (UserNotFoundException e) {
            ConsolePrinter.error(e.getMessage());

        } catch (InvalidDataException e) {
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

    private void updateProfile() {
        try {
            User user = Session.getUser();

            System.out.println("\n+----------------- PERFIL ---------------------+");
            ConsolePrinter.printUser(user);

            System.out.println("Actualizar perfil...");
            String name = InputReader.readInput("Nombre: ");
            String lastName = InputReader.readInput("Apellido: ");
            String email = InputReader.readInput("Email: ");
            String country = InputReader.readInput("País: ");

            userSystem.updateProfile(user, name, lastName, email, country);

            ConsolePrinter.success("Perfil actualizado correctamente");

        } catch (InvalidDataException e) {
            ConsolePrinter.error(e.getMessage());
        }
    }

    private void deleteTester() {
        try {
            System.out.println("Eliminar usuario tester...");
            String email = InputReader.readInput("Email: ");

            userSystem.deleteTester(email);

            ConsolePrinter.success("Usuario eliminado correctamente");

        } catch (UserNotFoundException | InvalidDataException e) {
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
        System.out.println("4) Reiniciar contraseña");
        System.out.println("5) Mi perfil");
        System.out.println("6) Eliminar usuario tester");
        System.out.println("7) Cerrar sesión");

        int option = InputReader.readMenuOption(1, 7);

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
                resetPassword();
                break;

            case 5:
                updateProfile();
                break;

            case 6:
                deleteTester();
                break;

            case 7:
                logout();
                break;
        }
        return true;
    }
}