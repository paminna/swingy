package view.console;

import static view.CreateHero.current_hero;
import static view.CreateHero.heroes;

import java.sql.SQLException;
import java.util.Scanner;

import controller.PlayController;
import view.CreateHero;

/**
 * @author RMNurgalieva
 */
public class CreateHeroConsole {

    public static void createPageHeroCreation() throws SQLException {
        int res =0;
        CreateHero hero = new CreateHero();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--- Create hero ---");
            System.out.println("Name: " + heroes.get(current_hero).getName());
            System.out.println("Class: " + heroes.get(current_hero).getHeroClass());
            System.out.println("Attack: " + heroes.get(current_hero).getAttack());
            System.out.println("Defense: " + heroes.get(current_hero).getDefence());
            System.out.println("HitPoints: " + heroes.get(current_hero).getDefence());
            System.out.println("-------------------");
            System.out.println("1) Prev hero\n2) Next hero\n3) Create\n4) Back");
            try {
                res = scanner.nextInt();
            } catch (Exception e) {
                PlayController.printError("Error argument!");
            } if(res == 1) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                if(current_hero == 0)
                    current_hero = (heroes.size() - 1);
                else
                    current_hero--;
            } else if(res == 2) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                if(current_hero == (heroes.size() - 1))
                    current_hero = 0;
                else
                    current_hero++;
            } else if(res == 3) {
                int coordinates_start = (0 - 1) * 5 + 10;
                coordinates_start /= 2;
                PlayController.createHero(hero.heroes.get(current_hero).getName(), hero.heroes.get(current_hero).getHeroClass(), hero.heroes.get(current_hero).getAttack(),
                        hero.heroes.get(current_hero).getDefence(), hero.heroes.get(current_hero).getHitPoints(), hero.heroes.get(current_hero).getPhotoProfile(), hero.heroes.get(current_hero).getLeftSide(),
                        hero.heroes.get(current_hero).getRightSide(), hero.heroes.get(current_hero).getBack(), coordinates_start, coordinates_start);
                break;
            } else if(res == 4) {
                break;
            } else
                System.out.println("Error argument!");
        }
        if(res == 3) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            PlayController.startPlay();
        } else if(res == 4) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            PlayController.createWelcomePageConsole();
        }
    }
}
