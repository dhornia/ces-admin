package service;

import model.Admin;
import model.Tester;
import model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserSystem {

    private Map<String, User> users;
    private User currentUser;

    public UserSystem() {
        this.users = new HashMap<>();
        loadTestUsersData();
    }

    public User login(String email, String password) {
        User user = users.get(email);

        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return user;
        }

        return null;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean registerUser(String name, String lastName, String email, String password, String country) {
        if (userExists(email)) {
            return false;
        }

        User user = new Admin(name, lastName, email, password, country);
        users.put(email, user);
        return true;
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public User findUserByEmail(String email) {
        return users.get(email);
    }

    private boolean userExists(String email) {
        return users.containsKey(email);
    }

    private void loadTestUsersData() {
        users.put("admin@test.com",
                new Admin("Fiorella", "Lopez", "admin@test.com", "1234", "UY"));

        users.put("user1@test.com",
                new Tester("Juan", "Furtado", "user1@test.com", "1234", "UY", "Tester Junior"));

        users.put("user2@test.com",
                new Tester("Viviana", "Caprani", "user2@test.com", "1234", "CU", "Tester Senior"));
    }
}