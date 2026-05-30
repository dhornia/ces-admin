import model.User;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, User> users = new HashMap<>();

        User user = new User(
                "Darlen",
                "Hornia",
                "darlen@gmail.com",
                "1234",
                "Cuba"
        );

        System.out.println(
                user.login("darlen@gmail.com", "1234")
        );

        System.out.println(register(user, users));

        System.out.println(register(user, users));

        System.out.println(users);
    }

    public static boolean register(User user, Map<String, User> users) {

        if (users.containsKey(user.getEmail())) {
            return false;
        }

        users.put(user.getEmail(), user);
        return true;
    }
}
