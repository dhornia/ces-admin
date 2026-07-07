package service;

import exception.EmailAlreadyExistsException;
import exception.InvalidCredentialsException;
import exception.InvalidDataException;
import exception.UserNotFoundException;

import model.User;
import service.factory.UserFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserSystem {
    private static UserSystem instance;
    private Map<String, User> users;

    private UserSystem() {
        this.users = new HashMap<>();
    }

    public static UserSystem getInstance() {
        if (instance == null) {
            instance = new UserSystem();
        }
        return instance;
    }

    public User login(String email, String password) throws InvalidCredentialsException, UserNotFoundException {
        User user = users.get(email);

        if (user == null) {
            throw new UserNotFoundException("Usuario no encontrado");
        }

        if (!user.getRole().equals("Administrador")) {
            throw new InvalidCredentialsException("Perfil de usuario no administrador");
        }

        if (user.getPassword().equals(password)) {
            return user;
        } else throw new InvalidCredentialsException("Credenciales inválidas");
    }

    public void createAdmin(String name, String lastName, String email, String password, String country) throws EmailAlreadyExistsException, InvalidDataException {

        validateUserData(name, lastName, email, password, country);
        User user = UserFactory.createAdmin(name, lastName, email, password, country);
        users.put(email, user);
    }

    public void createTester(String name, String lastName, String email, String password, String country, String level) throws EmailAlreadyExistsException, InvalidDataException {
        validateUserData(name, lastName, email, password, country);
        validateRequiredField(level);
        User user = UserFactory.createTester(name, lastName, email, password, country, level);
        users.put(email, user);
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public User findUserByEmail(String email) throws UserNotFoundException {
        User user = users.get(email);
        if (user != null) {
            return user;
        } else throw new UserNotFoundException("Usuario no encontrado");
    }

    public void verifyEmailDoesNotExist(String email) throws EmailAlreadyExistsException {
        if (users.containsKey(email)) {
            throw new EmailAlreadyExistsException("Ya existe un usuario registrado con este correo");
        }
    }

    public void resetPassword(User user, String newPassword) throws InvalidDataException {
        validateRequiredField(newPassword);
        validatePasswordLength(newPassword);

        user.setPassword(newPassword);
    }

    public void updateProfile(User user, String name, String lastName, String email, String country) throws InvalidDataException {
        validateRequiredField(name);
        validateRequiredField(lastName);
        validateRequiredField(email);
        validateRequiredField(country);

        user.setName(name);
        user.setLastName(lastName);
        user.setLastName(email);
        user.setCountry(country);
    }

    public void seed() {
        users.put("admin@test.com", UserFactory.createAdmin("Fiorella", "Lopez", "admin@test.com", "12345", "Uruguay"));
        users.put("yaniscorrea@gmail.com", UserFactory.createAdmin("Yanis", "Correa", "yaniscorrea@gmail.com", "12345", "Uruguay"));
        users.put("leonardoperez@gmail.com", UserFactory.createAdmin("Leonardo", "Perez", "leonardoperez@gmail.com", "12345", "Uruguay"));
        users.put("user1@test.com", UserFactory.createTester("Juan", "Furtado", "user1@test.com", "12345", "Uruguay", "Tester Junior"));
        users.put("user2@test.com", UserFactory.createTester("Viviana", "Caprani", "user2@test.com", "12345", "Uruguay", "Tester Senior"));
    }

    private void validateUserData(String name, String lastName, String email, String password, String country) throws InvalidDataException, EmailAlreadyExistsException {
        validateRequiredField(name);
        validateRequiredField(lastName);
        validateRequiredField(password);
        validateRequiredField(country);
        validateEmailFormat(email);
        validatePasswordLength(password);
        verifyEmailDoesNotExist(email);
    }

    private void validateEmailFormat(String email) throws InvalidDataException {
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new InvalidDataException("El formato del correo no es válido");
        }
    }

    private void validatePasswordLength(String password) throws InvalidDataException {
        if (password.length() < 5) {
            throw new InvalidDataException("La contraseña debe tener al menos 5 caracteres");
        }
    }

    private void validateRequiredField(String value) throws InvalidDataException {
        if (value == null || value.isBlank()) {
            throw new InvalidDataException("Todos los campos son obligatorios");
        }
    }
}