package service;

import exception.EmailAlreadyExistsException;
import exception.UserNotFoundException;
import model.Admin;
import model.Tester;
import model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserSystem {
    private Map<String, User> users;

    public UserSystem() {
        this.users = new HashMap<>();
    }

    public User login(String email, String password) {
        User user = users.get(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    public boolean addUser(String name, String lastName, String email, String password, String country) throws EmailAlreadyExistsException {
        if (name == null || name.isBlank()
                || lastName == null || lastName.isBlank()
                || email == null || email.isBlank()
                || password == null || password.isBlank()
                || country == null || country.isBlank()) {
            return false;
        }

        if (users.containsKey(email)) {
            throw new EmailAlreadyExistsException("Ya existe un usuario registrado con este correo.");
        }

        User user = new Admin(name, lastName, email, password, country);
        users.put(email, user);
        return true;
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

    public void userExists(String email) throws EmailAlreadyExistsException {
        if (users.containsKey(email)) {
            throw new EmailAlreadyExistsException("Ya existe un usuario registrado con este correo.");
        }
    }

    public void seed() {
        users.put("admin@test.com",
                new Admin("Fiorella", "Lopez", "admin@test.com", "1234", "UY"));

        users.put("user1@test.com",
                new Tester("Juan", "Furtado", "user1@test.com", "1234", "UY", "Tester Junior"));

        users.put("user2@test.com",
                new Tester("Viviana", "Caprani", "user2@test.com", "1234", "CU", "Tester Senior"));
    }
}