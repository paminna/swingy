package view.GUI;

import controller.PlayController;
import view.CreateHero;
import view.DBService;
import view.console.PlayService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import static controller.PlayController.currentHero;
import static view.CreateHero.current_hero;
import static view.GUI.BattleService.printHeroLose;
import static view.GUI.BattleService.printHeroWin;
import static view.GUI.CreateHeroGUI.createWindowHero;
import static view.GUI.WindowService.render;
import static view.console.PlayService.enemy;

public class ButtonClickListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        DBService dbService = new DBService();
        String command = e.getActionCommand();
        WindowService frame = new WindowService();
        int coordinates_start = (0 - 1) * 5 + 10;
        coordinates_start /= 2;
        if (command.equals("Create hero")) {
            PlayController.createHero(currentHero.getName(),
                    currentHero.getHeroClass(), currentHero.getAttack(),
                    currentHero.getDefence(), currentHero.getHitPoints(),
                    currentHero.getPhotoProfile(),
                    currentHero.getLeftSide(), currentHero.getRightSide(),
                    currentHero.getBack(), coordinates_start, coordinates_start,  currentHero.getExperience(),
                    currentHero.getLevel());
            WindowService.startPlay();
            WindowService.heroIsDB = 0;
        } else if (command.equals("Back")) {
            frame.clearWindow();
            WindowService.createWelcomeWindow();
        } else if (command.equals("Next")) {
            if (current_hero == (CreateHero.heroes.size() - 1))
                current_hero = 0;
            else
                current_hero++;
            frame.clearWindow();
            createWindowHero();
        } else if (command.equals("Prev")) {
            if (current_hero == 0)
                current_hero = (CreateHero.heroes.size() - 1);
            else
                current_hero--;
            frame.clearWindow();
            createWindowHero();
        } else if (command.equals("Save hero")) {
            dbService.saveHeroDB();
            render();
        } else if (command.equals("Battle")) {
            if (BattleService.getWinner() == 1) {
                printHeroWin();
            } else {
                printHeroLose();
            }
        } else if (command.equals("Run")) {
            Random random = new Random();
            int rand = random.nextInt(1 + 1);
            if (rand == 1) {
                if (BattleService.getWinner() == 1) {
                    printHeroWin();
                } else {
                    System.out.println("Hero lose");
                    printHeroLose();
                }
            } else {
                if (rand == 0)
                    PlayService.move(KeyEvent.VK_W, WindowService.getSize());
                else if (rand == 1)
                    PlayService.move(KeyEvent.VK_S, WindowService.getSize());
                else if (rand == 2)
                    PlayService.move(KeyEvent.VK_D, WindowService.getSize());
                else if (rand == 3)
                    PlayService.move(KeyEvent.VK_A, WindowService.getSize());
                WindowService.clearWindow();
                WindowService.startPlay();
            }
        } else if (command.equals("Finish")) {
            if (BattleService.getWinner() == 1) {
                enemy.setX(-1);
                enemy.setY(-1);
                WindowService.generateFirst = 0;
                WindowService.clearWindow();
                WindowService.createWelcomeWindow();
            } else {
                printHeroLose();
            }
        } else if (command.equals("Exit")){
            currentHero.setExperience(0);
            System.exit(0);
        } else {
            dbService.findHeroById(Integer.valueOf(command));
            WindowService.clearWindow();
            WindowService.startPlay();
        }
    }
}