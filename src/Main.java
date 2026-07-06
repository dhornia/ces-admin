import service.UserSystem;
import session.Session;
import ui.*;

public class Main {

    public static void main(String[] args) {

        UserSystem userSystem = UserSystem.getInstance();
        userSystem.seed();

        boolean running = true;

        while (running) {
            Menu menu;
            ConsolePrinter.printHeader();

            if (Session.isLogged()) {
                menu = new AdminMenu(userSystem);
            } else {
                menu = new PublicMenu(userSystem);
            }
            running = menu.show();
        }
        InputReader.close();
    }
}