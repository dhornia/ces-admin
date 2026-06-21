import model.User;
import service.UserSystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserSystem userSystem = new UserSystem();

        showMenu(scanner, userSystem);
        scanner.close();
    }

    private static void showMenu(Scanner scanner, UserSystem userSystem) {

        String option = "0";

        while (!option.equals("4")) {

            System.out.println("\n=====================");
            System.out.println("     Admin CES");
            System.out.println("=====================");

            if (userSystem.getCurrentUser() == null) {
                option = showPublicMenu(scanner, userSystem);
            } else {
                option = showAdminMenu(scanner, userSystem);
            }
        }
    }

    private static String showPublicMenu(Scanner scanner, UserSystem userSystem) {

        System.out.println("1 - Iniciar Sesión");
        System.out.println("2 - Registrarse");
        System.out.println("3 - Salir");

        System.out.print("Seleccione una opción: ");
        String option = scanner.nextLine();

        switch (option) {

            case "1":
                login(scanner, userSystem);
                break;

            case "2":
                registerAdmin(scanner, userSystem);
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

    private static String showAdminMenu(Scanner scanner, UserSystem userSystem) {

        System.out.println("\n*** Bienvenido " + userSystem.getCurrentUser().getName() + " " + userSystem.getCurrentUser().getLastName() + " ***");
        System.out.println("--- MENU ---");

        System.out.println("1 - Buscar usuario");
        System.out.println("2 - Ver usuarios");
        System.out.println("3 - Cerrar sesión");

        System.out.print("Seleccione una opción: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                findUser(scanner, userSystem);
                break;

            case "2":
                listUsers(userSystem);
                break;

            case "3":
                userSystem.logout();
                option = "0";
                break;
        }

        return option;
    }

    private static void login(Scanner scanner, UserSystem userSystem) {

        System.out.println("\nIniciar Sesión Administrador ...");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        User user = userSystem.login(email, password);

        if (user != null) {
            System.out.println("Login exitoso.");
        } else {
            System.out.println("Credenciales inválidas.");
        }
    }

    private static void registerAdmin(Scanner scanner, UserSystem userSystem) {

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

        if (userSystem.registerUser(adminName, adminLastName, adminEmail, adminPassword, adminCountry)) {
            System.out.println("Usuario registrado.");
        } else {
            System.out.println("Ya existe un usuario registrado con este correo.");
        }
    }

    private static void findUser(Scanner scanner, UserSystem userSystem) {

        System.out.print("Email: ");
        String searchEmail = scanner.nextLine();

        User user = userSystem.findUserByEmail(searchEmail);

        if (user == null) {
            System.out.println("Usuario no encontrado");
        } else {
            System.out.println("\n--- INFORMACIÓN DEL USUARIO ---");
            System.out.println("Nombre: " + user.getName());
            System.out.println("Apellido: " + user.getLastName());
            System.out.println("País: " + user.getCountry());
            System.out.println("Perfil: " + user.getRole());
        }
    }

    private static void listUsers(UserSystem userSystem) {

        System.out.println("# - Nombre - Correo - País de nacimiento - Perfil");
        System.out.println("--------------------------------------");

        int i = 1;

        for (User u : userSystem.getAllUsers()) {
            System.out.println(
                    i + " - " +
                            u.getName() + " " +
                            u.getLastName() + " - " +
                            u.getEmail() + " - " +
                            u.getCountry() + " - " +
                            u.getRole()
            );
            i++;
        }
    }
}