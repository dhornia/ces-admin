package service;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserSystem {
    private Map<String, User> users;

    public UserSystem() {
        this.users = new HashMap<>();
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

    private boolean userExists(String email) {
        return users.containsKey(email);
    }

    private User findUserByEmail(String email) {
        return users.get(email);
    }
}