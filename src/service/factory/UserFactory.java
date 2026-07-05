package service.factory;

import model.Admin;
import model.Tester;
import model.User;

public class UserFactory {

    public static User createAdmin(String name, String lastName,
                                   String email, String password,
                                   String country) {

        return new Admin(name, lastName, email, password, country);
    }

    public static User createTester(String name, String lastName,
                                    String email, String password,
                                    String country, String level) {

        return new Tester(name, lastName, email, password, country, level);
    }
}