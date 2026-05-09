package model;

public class User {
    String name;
    String lastname;
    String email;
    String password;
    String country;

    public User(String name, String lastname, String email, String password, String country) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.country = country;
    }
}
