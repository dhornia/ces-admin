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
            System.out.println();
            System.out.println("=====================");
            System.out.println("     Sistema Usuario     ");
            System.out.println("=====================");

            System.out.println("1 - Registrar usuario");
            System.out.println("2 - Login");
            System.out.println("3 - Listar usuarios");
            System.out.println("4 - Salir");

            System.out.println("---------------------");
            System.out.print("Seleccione una opción: ");

            option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("\nRegistro de usuario ...");

                    System.out.print("Nombre: ");
                    String name = scanner.nextLine();

                    System.out.print("Apellido: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Correo: ");
                    String email = scanner.nextLine();

                    System.out.print("Contraseña: ");
                    String password = scanner.nextLine();

                    System.out.print("País: ");
                    String country = scanner.nextLine();

                    User user = new User(
                            name,
                            lastName,
                            email,
                            password,
                            country
                    );

                    if (userSystem.registerUser(user)) {
                        System.out.println("Usuario registrado.");
                    } else {
                        System.out.println("Ya existe un usuario registrado con este correo.");
                    }

                    break;

                case "2":
                    System.out.println("\nLogin ...");

                    System.out.print("Correo: ");
                    String loginEmail = scanner.nextLine();

                    System.out.print("Contraseña: ");
                    String loginPassword = scanner.nextLine();

                    if (userSystem.login(loginEmail, loginPassword)) {
                        System.out.println("Login exitoso.");
                    } else {
                        System.out.println("Credenciales inválidas.");
                    }

                    break;

                case "3":
                    System.out.println("\nLista de Usuarios ...");

                    userSystem.getAllUsers();
                    break;

                case "4":
                    System.out.println("\nSaliendo ...");

                    System.out.println("Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
