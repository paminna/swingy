package view.GUI;

import controller.PlayController;
import view.CreateHero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.CreateHero.current_hero;
import static view.GUI.CreateHeroGUI.createWindowHero;

public class ButtonClickListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        WindowService frame = new WindowService();
        int coordinates_start = (0 - 1) * 5 + 10;
        coordinates_start /= 2;
        if (command.equals("Create hero")) {
            PlayController.createHero(CreateHero.heroes.get(current_hero).getName(),
                    CreateHero.heroes.get(current_hero).getHeroClass(),CreateHero.heroes.get(current_hero).getAttack(),
                    CreateHero.heroes.get(current_hero).getDefence(), CreateHero.heroes.get(current_hero).getHitPoints(),
                    CreateHero.heroes.get(current_hero).getPhotoProfile(),
                    CreateHero.heroes.get(current_hero).getLeftSide(), CreateHero.heroes.get(current_hero).getRightSide(),
                    CreateHero.heroes.get(current_hero).getBack(), coordinates_start, coordinates_start);
            WindowService.startPlay();
        } else if (command.equals("Back")) {
            frame.clearWindow();
            WindowService.createWelcomeWindow();
        } else if (command.equals("Next")) {
            if(current_hero == (CreateHero.heroes.size() - 1))
                current_hero = 0;
            else
                current_hero++;
            frame.clearWindow();
            createWindowHero();
        } else if (command.equals("Prev")) {
            if(current_hero == 0)
                current_hero = (CreateHero.heroes.size() - 1);
            else
                current_hero--;
            frame.clearWindow();
            createWindowHero();
        } else {
            System.exit(0);
        }
    }
}