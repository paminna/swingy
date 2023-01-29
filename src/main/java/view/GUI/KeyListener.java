package view.GUI;


import controller.PlayController;
import model.Hero;
import view.console.PlayService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static view.GUI.WindowService.render;

public class KeyListener extends KeyAdapter implements ActionListener {

    @Override
    public void keyPressed(KeyEvent e) {
        WindowService frame = new WindowService();
        if (e.getKeyCode() == KeyEvent.VK_W) {
            PlayService.move(e.getKeyCode(), WindowService.getSize());
            render();
            Hero enemy = frame.returnEnemyByCoordinates(PlayController.currentHero.getX(), PlayController.currentHero.getY());
            if(enemy != null) {
                frame.getWindow().removeKeyListener(WindowService.getSignal());
                WindowService.stage_Battle(enemy);
                return;}
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            PlayService.move(e.getKeyCode(), WindowService.getSize());
            render();
            Hero enemy = frame.returnEnemyByCoordinates(PlayController.currentHero.getX(), PlayController.currentHero.getY());
            if(enemy != null) {
                frame.getWindow().removeKeyListener(WindowService.getSignal());
                WindowService.stage_Battle(enemy);
                return;
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            PlayService.move(e.getKeyCode(), WindowService.getSize());
            render();
            Hero enemy = frame.returnEnemyByCoordinates(PlayController.currentHero.getX(), PlayController.currentHero.getY());
            if(enemy != null) {
                frame.getWindow().removeKeyListener(WindowService.getSignal());
                WindowService.stage_Battle(enemy);
                return;
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            PlayService.move(e.getKeyCode(), WindowService.getSize());
            render();
            Hero enemy = frame.returnEnemyByCoordinates(PlayController.currentHero.getX(), PlayController.currentHero.getY());
            if(enemy != null) {
                frame.getWindow().removeKeyListener(WindowService.getSignal());
                WindowService.stage_Battle(enemy);
                return;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("WTF ???");
    }

}
