package service;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserSystem {
    private Map<String, User> users;

    public UserSystem() {
        this.users = new HashMap<>();
        loadTestUsers();
    }

    public boolean registerUser(User user) {
        if (userExists(user.getEmail())) {
            return false;
        }

        users.put(user.getEmail(), user);
        return true;
    }

    public boolean login(String email, String password) {
        User user = findUserByEmail(email);

        return user != null &&
                user.getPassword().equals(password);
    }

    public void getAllUsers() {
        System.out.println("# - Nombre - Correo - País de nacimiento");
        System.out.println("--------------------------------------");

        int i = 1;

        for (User user : users.values()) {
            System.out.println(
                    i + " - " +
                            user.getName() + " " +
                            user.getLastName() + " - " +
                            user.getEmail() + " - " +
                            user.getCountry()
            );
            i++;
        }
    }

    private boolean userExists(String email) {
        return users.containsKey(email);
    }

    private User findUserByEmail(String email) {
        return users.get(email);
    }

    private void loadTestUsers() {
        users.put("admin@test.com", new User("Fiorella", "Lopez", "admin@test.com", "1234", "UY"));
        users.put("user1@test.com", new User("Juan", "Furtado", "user1@test.com", "1234", "UY"));
        users.put("user2@test.com", new User("Viviana", "Caprani", "user2@test.com", "1234", "CU"));
    }
}