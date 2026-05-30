package model;

public class User {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String country;

    public User(String name, String lastName, String email, String password, String country) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean login(String email, String password) {
        if ((this.email.equals(email)) && (this.password.equals(password))){
            return true;
        };
        return  false;
    }
}
