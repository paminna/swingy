package view;

import controller.PlayController;
import view.GUI.WindowService;

import java.sql.SQLException;

import static view.GUI.WindowService.createWelcomePage;

/**
 * @author RMNurgalieva
 */
public class Main {

    public static void main(String args[]) throws SQLException {
        if(args.length != 1) {
            System.out.println("Error program arguments! Need argument: console/gui");
        } else {
            if(args[0].equals("gui"))
                createWelcomePage();
            else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                PlayController.createWelcomePageConsole();
            }
        }
    }
}
