import exception.UserNotFoundException;
import model.User;
import service.UserSystem;

import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static User currentUser;
    private static  UserSystem userSystem;

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
        System.out.println("\nIniciar Sesión Administrador ...");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        User user = userSystem.login(email, password);

        if (user != null) {
            currentUser = user;
            System.out.println("Login exitoso.");
        } else {
            System.out.println("Credenciales inválidas.");
        }
    }

    public static void registerAdmin() {
        System.out.println("\nCrear Cuenta Administrador ...");

        System.out.print("Nombre: ");
        String adminName = scanner.nextLine();

        System.out.print("Apellido: ");
        String adminLastName = scanner.nextLine();

        System.out.print("Correo: ");
        String adminEmail = scanner.nextLine();

        System.out.print("Contraseña: ");
        String adminPassword = scanner.nextLine();

        System.out.print("País: ");
        String adminCountry = scanner.nextLine();

        if (adminName.isBlank() || adminLastName.isBlank() || adminEmail.isBlank()
                || adminPassword.isBlank() || adminCountry.isBlank()) {
            System.out.println("Todos los campos son obligatorios");
            return;
        }

        boolean newUser = userSystem.addUser(adminName, adminLastName, adminEmail, adminPassword, adminCountry);

        if (newUser) {
            System.out.println("Usuario registrado.");
        } else {
            System.out.println("Ya existe un usuario registrado con este correo.");
        }
    }

    public static void findUser() {
        System.out.print("Email: ");
        String searchEmail = scanner.nextLine();

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
}