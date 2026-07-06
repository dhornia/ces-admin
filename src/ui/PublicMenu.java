package ui;

import exception.InvalidDataException;
import exception.UserNotFoundException;
import model.User;
import service.UserSystem;
import exception.InvalidCredentialsException;
import exception.EmailAlreadyExistsException;
import session.Session;

public class PublicMenu extends Menu {

    public PublicMenu(UserSystem userSystem) {
        super(userSystem);
    }

    private void login() {
        try {
            System.out.println("\nIniciar Sesión Administrador ...");
            String email = InputReader.readInput(("Email: "));
            String password = InputReader.readInput("Contraseña: ");

            User user = userSystem.login(email, password);
            Session.login(user);
            ConsolePrinter.success("Login exitoso");

        } catch (UserNotFoundException | InvalidCredentialsException e) {
            ConsolePrinter.error(e.getMessage());

        }
    }

    private void register() {
        try {
            String name = InputReader.readInput("Nombre: ");
            String lastName = InputReader.readInput("Apellido: ");
            String email = InputReader.readInput("Correo: ");
            String password = InputReader.readInput("Contraseña: ");
            String country = InputReader.readInput("País: ");

            userSystem.createAdmin(name, lastName, email, password, country);
            ConsolePrinter.success("Usuario creado con éxito");

        } catch (InvalidDataException e) {
            ConsolePrinter.error("Datos inválidos: " + e.getMessage());

        } catch (EmailAlreadyExistsException e) {
            ConsolePrinter.error(e.getMessage());
        }
    }

    @Override
    public boolean show() {
        ConsolePrinter.menuHeader();
        System.out.println("1) Iniciar Sesión");
        System.out.println("2) Registrarse");
        System.out.println("3) Salir");

        try {
            int option = InputReader.readMenuOption(1, 3);

            switch (option) {
                case 1:
                    login();
                    break;

                case 2:
                    register();
                    break;

                case 3:
                    System.out.println("\nSaliendo ...");
                    return false;
            }

        } catch (NumberFormatException e) {
            ConsolePrinter.error("Debe ingresar un número");
        }

        return true;
    }
}