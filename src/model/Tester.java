package model;

public class Tester extends User {
    private String level;

    public Tester(String name, String lastName, String email, String password, String country, String level) {
        super(name, lastName, email, password, country);
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String getRole() {
        return getLevel();
    }
}