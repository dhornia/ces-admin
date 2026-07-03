package model;

public class Admin extends User{
    public Admin(String name, String lastName, String email, String password, String country) {
        super(name, lastName, email, password, country);
    }

    @Override
    public String getRole() {
        return "Administrador";
    }
}
