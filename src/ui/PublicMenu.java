package ui;

import exception.InvalidDataException;
import exception.UserNotFoundException;
import model.User;
import service.UserSystem;
import exception.InvalidCredentialsException;
import exception.EmailAlreadyExistsException;
import session.Session;

public class PublicMenu {

    public static boolean show(UserSystem userSystem) {
        System.out.println("1 - Iniciar Sesión");
        System.out.println("2 - Registrarse");
        System.out.println("3 - Salir");

        try {
            int option = InputReader.readMenuOption(1, 3);

            switch (option) {
                case 1:
                    login(userSystem);
                    break;

                case 2:
                    register(userSystem);
                    break;

                case 3:
                    System.out.println("\nSaliendo ...");
                    return false;
            }

        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número");
        }

        return true;
    }

    public static void login(UserSystem userSystem) {
        try {
            System.out.println("\nIniciar Sesión Administrador ...");
            String email = InputReader.read(("Email: "));
            String password = InputReader.read("Contraseña: ");

            User user = userSystem.login(email, password);
            Session.login(user);
            System.out.println("Login exitoso");

        } catch (UserNotFoundException | InvalidCredentialsException e) {
            System.out.println(e.getMessage());

        }
    }

    private static void register(UserSystem userSystem) {
        try {
            String name = InputReader.read("Nombre: ");
            String lastName = InputReader.read("Apellido: ");
            String email = InputReader.read("Correo: ");
            String password = InputReader.read("Contraseña: ");
            String country = InputReader.read("País: ");

            userSystem.addUser(name, lastName, email, password, country);
            System.out.println("Usuario creado con éxito");

        } catch (InvalidDataException e) {
            System.out.println("Datos inválidos: " + e.getMessage());

        } catch (EmailAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
    }
}