package view.GUI;

import controller.PlayController;
import model.Hero;
import view.CreateHero;
import view.console.PlayService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static view.CreateHero.current_hero;

/**
 * @author RMNurgalieva
 */
public class Action implements ActionListener {
    private WindowService windowService = new WindowService();
    private List<Hero> heroList = CreateHero.getHeroes();

    @Override
    public void actionPerformed(ActionEvent e) {
        final String action = e.getActionCommand();
        if (action.equals("create hero")){
            System.out.println("hero creation");
            PlayController.createHero(heroList.get(current_hero).getName(),
                    heroList.get(current_hero).getHeroClass(),heroList.get(current_hero).getAttack(),
                    heroList.get(current_hero).getDefence(), heroList.get(current_hero).getHitPoints(),
                    heroList.get(current_hero).getPhotoProfile(), heroList.get(current_hero).getLeftSide(),
                    heroList.get(current_hero).getRightSide(), heroList.get(current_hero).getBack(),
                    0, 0);
            windowService.clearWindow();
            CreateHeroGUI.createWindowHero();
        } else if (action.equals("load hero")){
            System.out.println("hero selected");
        } else if (action.equals("exit")){
            System.exit(0);
        } else if (action.equals("go to console")){
            try {
                windowService.clearWindow();
                windowService.getWindow().setVisible(false);
                PlayController.createWelcomePageConsole();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
