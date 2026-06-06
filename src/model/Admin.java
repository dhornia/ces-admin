package model;

public class Admin extends User{
    private String role;

    public Admin(String name, String lastName, String email, String password, String country, String role) {
        super(name, lastName, email, password, country);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
