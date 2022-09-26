package controller;

import model.Hero;
import view.console.CreateHeroConsole;
import view.console.LoadHeroConsole;
import view.console.PlayService;
import view.console.WelcomePageConsole;

import java.sql.SQLException;

/**
 * @author RMNurgalieva
 */
public class PlayController {

    private LoadHeroConsole loadHeroConsole = new LoadHeroConsole();

    public static Hero currentHero;

    public static void printError(String text) {
        System.out.println(text);
        System.exit(-1);
    }

    public static void createHeroConsole() throws SQLException {
        CreateHeroConsole.createPageHeroCreation();
    }

    public static void loadHeroConsole() throws SQLException {
        LoadHeroConsole loadHeroConsole = new LoadHeroConsole();
        loadHeroConsole.loadHeroFromDB();
    }

    public static void createWelcomePageConsole() throws SQLException {
        WelcomePageConsole page = new WelcomePageConsole();
        page.drawWelcomePage();
    }

    public static void createHero(String name, String heroClass, int attack, int defence, int hitPoints, String photoProfile,
                                  String leftSide, String rightSide, String back, int x, int y){
        if (currentHero == null){
            currentHero = new Hero(name, heroClass, attack,defence, hitPoints, photoProfile, leftSide, rightSide, back, x, y);
        }
    }

    public static void startPlay() throws SQLException {
        PlayService playService = new PlayService();
        playService.printBattleField();
    }
}
