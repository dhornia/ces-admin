import model.User;

public class Main {
    public static void main(String[] args) {

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
    }
}
