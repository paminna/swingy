import controller.PlayController;
import view.GUI.WindowService;

import java.sql.SQLException;

import static view.GUI.WindowService.createWelcomePage;

public class Main {

    public static void main(String args[]) throws SQLException {
        if(args.length != 1) {
            System.out.println("Error program arguments! Need argument: console/gui");
        } else {
            if(args[0].equals("gui"))
                createWelcomePage();
            else {
                PlayController.createWelcomePageConsole();
            }
        }
    }
}
