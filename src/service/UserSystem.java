package service;

import exception.EmailAlreadyExistsException;
import exception.InvalidCredentialsException;
import exception.InvalidDataException;
import exception.UserNotFoundException;

import model.Admin;
import model.Tester;
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

    public User login(String email, String password) throws InvalidCredentialsException {
        User user = users.get(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        throw new InvalidCredentialsException("Credenciales inválidas");
    }

    public void addUser(String name,
                        String lastName,
                        String email,
                        String password,
                        String country) throws EmailAlreadyExistsException, InvalidDataException {

        if (name == null || name.isBlank()
                || lastName == null || lastName.isBlank()
                || email == null || email.isBlank()
                || password == null || password.isBlank()
                || country == null || country.isBlank()) {

            throw new InvalidDataException("Todos los campos son obligatorios.");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new InvalidDataException("El formato del correo es inválido.");
        }

        if (password.length() < 8) {
            throw new InvalidDataException("La contraseña debe tener al menos 8 caracteres.");
        }

        verifyEmailDoesNotExist(email);

        User user = UserFactory.createAdmin(name, lastName, email, password, country);
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

    public void seed() {
        users.put("admin@test.com",
                UserFactory.createAdmin("Fiorella", "Lopez", "admin@test.com", "1234", "UY"));

        users.put("user1@test.com",
                UserFactory.createTester("Juan", "Furtado", "user1@test.com", "1234", "UY", "Tester Junior"));

        users.put("user2@test.com",
                UserFactory.createTester("Viviana", "Caprani", "user2@test.com", "1234", "CU", "Tester Senior"));
    }
}