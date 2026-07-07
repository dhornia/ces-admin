package ui;

import exception.InvalidDataException;
import exception.UserNotFoundException;
import model.User;
import service.UserSystem;

public abstract class Menu {
    protected UserSystem userSystem;

    protected Menu(UserSystem userSystem) {
        this.userSystem = userSystem;
    }

    public void resetPassword() {
        try {
            System.out.println("Reiniciar contraseña");

            String email = InputReader.readInput("Email: ");
            String password = InputReader.readInput("Contraseña nueva: ");
            String repeatPassword = InputReader.readInput("Repetir contraseña: ");

            User user = userSystem.findUserByEmail(email);

            if (!password.equals(repeatPassword)) {
                ConsolePrinter.error("Las contraseñas no coinciden");
                return;
            }

            userSystem.resetPassword(user, repeatPassword);

            ConsolePrinter.success("Contraseña actualizada correctamente");

        } catch (UserNotFoundException | InvalidDataException e) {
            ConsolePrinter.error(e.getMessage());
        }
    }

    public abstract boolean show();
}