import service.UserSystem;
import session.Session;
import ui.AdminMenu;
import ui.ConsolePrinter;
import ui.InputReader;
import ui.PublicMenu;

public class Main {

    public static void main(String[] args) {
        UserSystem userSystem = UserSystem.getInstance();
        userSystem.seed();

        while (true) {
            ConsolePrinter.printHeader();

            if (Session.isLogged()) {
                AdminMenu.show(userSystem);
            } else {
                if (!PublicMenu.show(userSystem)) {
                    break;
                }
            }
        }

        InputReader.close();
    }
}