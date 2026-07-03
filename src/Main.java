import exception.EmailAlreadyExistsException;
import exception.InvalidCredentialsException;
import exception.InvalidDataException;
import exception.UserNotFoundException;
import model.User;
import service.UserSystem;

import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static User currentUser;
    private static UserSystem userSystem;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        userSystem = new UserSystem();
        userSystem.seed();

        showMenu();
        scanner.close();
    }

    public static void showMenu() {
        String option = "0";

        while (!option.equals("4")) {

            System.out.println("\n=====================");
            System.out.println("     Admin CES");
            System.out.println("=====================");

            if (currentUser == null) {
                option = showPublicMenu();
            } else {
                option = showAdminMenu();
            }
        }
    }

    public static String showPublicMenu() {
        System.out.println("1 - Iniciar Sesión");
        System.out.println("2 - Registrarse");
        System.out.println("3 - Salir");

        System.out.print("Seleccione una opción: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                login();
                break;

            case "2":
                registerAdmin();
                break;

            case "3":
                System.out.println("\nSaliendo ...");
                System.out.println("Hasta luego!");
                break;

            default:
                System.out.println("Opción no válida");
        }

        return option;
    }

    public static String showAdminMenu() {
        System.out.println("\n*** Bienvenido " + currentUser.getFullName() + " ***");
        System.out.println("--- MENU ---");

        System.out.println("1 - Buscar usuario");
        System.out.println("2 - Ver usuarios");
        System.out.println("3 - Cerrar sesión");

        System.out.print("Seleccione una opción: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                findUser();
                break;

            case "2":
                listUsers();
                break;

            case "3":
                logout();
                option = "0";
                break;
        }

        return option;
    }

    public static void login() {
        try {
            System.out.println("\nIniciar Sesión Administrador ...");
            String email = read(("Email: "));
            String password = read("Contraseña: ");

            currentUser = userSystem.login(email, password);
            System.out.println("Login exitoso");

        } catch (InvalidCredentialsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void registerAdmin() {
        try {
            System.out.println("\nCrear Cuenta Administrador ...");
            String adminName = read("Nombre: ");
            String adminLastName =read("Apellido: ");
            String adminEmail =read("Correo: ");
            String adminPassword =read("Contraseña: ");
            String adminCountry =read("País: ");

            userSystem.addUser(
                    adminName,
                    adminLastName,
                    adminEmail,
                    adminPassword,
                    adminCountry
            );
            System.out.println("Usuario creado con éxito");

        } catch (EmailAlreadyExistsException e) {
            System.out.println("Email en uso. " + e.getMessage());

        } catch (InvalidDataException e) {
            System.out.println("Datos inválidos. " + e.getMessage());
        }
    }

    public static void findUser() {
        String searchEmail = read("Email: ");

        try {
            User user = userSystem.findUserByEmail(searchEmail);

            System.out.println("\n+-------------- DATOS DEL USUARIO --------------+");
            System.out.printf("| %-15s | %-25s |\n", "Campo", "Valor");
            System.out.println("+------------------+---------------------------+");

            System.out.printf("| %-15s | %-25s |\n", "Nombre", user.getName());
            System.out.printf("| %-15s | %-25s |\n", "Apellido", user.getLastName());
            System.out.printf("| %-15s | %-25s |\n", "Email", user.getEmail());
            System.out.printf("| %-15s | %-25s |\n", "País", user.getCountry());
            System.out.printf("| %-15s | %-25s |\n", "Perfil", user.getRole());

            System.out.println("+------------------+---------------------------+");

        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listUsers() {
        System.out.println("+----+----------------------+--------------------------+----------------+---------+");
        System.out.printf("| %-2s | %-20s | %-24s | %-14s | %-7s |\n",
                "#", "Nombre", "Email", "País", "Perfil");
        System.out.println("+----+----------------------+--------------------------+----------------+---------+");

        int i = 1;

        for (User u : userSystem.getAllUsers()) {

            System.out.printf("| %-2d | %-20s | %-24s | %-14s | %-7s |\n",
                    i,
                    u.getName() + " " + u.getLastName(),
                    u.getEmail(),
                    u.getCountry(),
                    u.getRole()
            );

            i++;
        }

        System.out.println("+----+----------------------+--------------------------+----------------+---------+");
    }

    public static void logout() {
        currentUser = null;
    }

    private static String read(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}