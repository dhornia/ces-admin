import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Map<String, User> users = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        String option = "0";

        while (!option.equals("3")) {

            System.out.println("\n*** MENU ***");
            System.out.println("1 - Registrar usuario");
            System.out.println("2 - Login");
            System.out.println("3 - Salir");
            System.out.print("Seleccione una opción: ");

            option = scanner.next();

            switch (option) {

                case "1":
                    registerUser(scanner, users);
                    break;

                case "2":
                    loginUser(scanner, users);
                    break;

                case "3":
                    System.out.println("Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        }

        scanner.close();
    }

    public static void registerUser(
            Scanner scanner,
            Map<String, User> users) {

        scanner.nextLine();

        System.out.print("Nombre: ");
        String name = scanner.nextLine();

        System.out.print("Apellido: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Country: ");
        String country = scanner.nextLine();

        if (name.isBlank() || lastName.isBlank() || email.isBlank()
                || password.isBlank() || country.isBlank()) {
            System.out.println("Todos los campos son obligatorios");
            return;
        }

        User user = new User(
                name,
                lastName,
                email,
                password,
                country
        );

        if (users.containsKey(user.getEmail())) {
            System.out.println("El correo ya existe");
        } else {
            users.put(user.getEmail(), user);
            System.out.println("Usuario registrado");
        }
    }

    public static void loginUser(
            Scanner scanner,
            Map<String, User> users) {

        scanner.nextLine();

        System.out.print("Correo: ");
        String email = scanner.nextLine();

        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        User user = users.get(email);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Credenciales inválidas");
        }
    }
}
