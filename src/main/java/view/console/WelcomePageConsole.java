package view.console;

import java.sql.SQLException;
import java.util.Scanner;

import controller.PlayController;

/**
 * @author RMNurgalieva
 */
public class WelcomePageConsole {

    public void drawWelcomePage() throws SQLException {
        int res = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Swingy!\n----------\nChoose option\n1) Create hero\n2) Load hero\n3) Exit\nType your choice\n");
            try {
                res = scanner.nextInt();
            } catch (Exception e) {
                PlayController.printError("Error argument!");
            }if (res == 1 || res == 2){
                break;
            } else if (res == 3) {
                System.exit(0);
            } else {
                System.out.println("Error argument!");
            }
        }
        if(res == 1) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            PlayController.createHeroConsole();
        }
        if(res == 2) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            PlayController.loadHeroConsole();
        }
    }
}
