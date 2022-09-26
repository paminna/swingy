package view.GUI;

import controller.PlayController;
import model.Hero;
import view.CreateHero;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.CreateHero.current_hero;

public class CreateHeroGUI {

    public static void crateHero() {
//        CreateHero hero = new CreateHero();
        createWindowHero(CreateHero.getHeroes().get(CreateHero.getCurrent_hero()));
    }

    public static void createWindowHero(Hero hero){
        WindowService frame = new WindowService();
        frame.createBackGround("/Users/regina/Desktop/swingy/src/main/resources/hero_create.jpg");

        JPanel button_panel = new JPanel();
        button_panel.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel.setLayout(new FlowLayout());

        JButton createHero = frame.createBotton("Create hero", 20, 100);
        createHero.setActionCommand("Create hero");
        createHero.addActionListener(new ButtonClickListener());
        button_panel.add(createHero);

        JButton back = frame.createBotton("Back", 16, 150);
        back.setActionCommand("Back");
        back.addActionListener(new ButtonClickListener());
        button_panel.add(back);

        JPanel button_panel_np = new JPanel();
        button_panel_np.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel_np.setLayout(new FlowLayout());

        JButton prev = frame.createBotton("Prev", 16, 200);
        prev.setActionCommand("Prev");
        prev.addActionListener(new ButtonClickListener());
        button_panel_np.add(prev);

        JButton next = frame.createBotton("Next", 16, 250);
        next.setActionCommand("Next");
        next.addActionListener(new ButtonClickListener());
        button_panel_np.add(next);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.createText("Create your hero", 22, 100);
        frame.getWindow().add(text, c);
        frame.add_empty_text(2, 0);

        c.gridy = 3;
        ImageIcon imageIcon = new ImageIcon(CreateHero.heroes.get(current_hero).getPhotoProfile()); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(220, 220,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        frame.getWindow().add(new JLabel(imageIcon), c);

        frame.add_empty_text(4, 0);
        c.gridy = 5;
        frame.getWindow().add(button_panel_np, c);

        frame.add_empty_text(6, 0);
        c.gridy = 7;
        text = frame.createText("Name: " + CreateHero.heroes.get(current_hero).getName(), 0, 0);
        frame.getWindow().add(text, c);
        frame.add_empty_text(8, 0);

        c.gridy = 9;
        text = frame.createText("Class: " + CreateHero.heroes.get(current_hero).getHeroClass(), 0, 0);
        frame.getWindow().add(text, c);
        frame.add_empty_text(10, 0);

        c.gridy = 11;
        text = frame.createText("Attack: " + CreateHero.heroes.get(current_hero).getAttack(), 0, 0);
        frame.getWindow().add(text, c);
        frame.add_empty_text(12, 0);

        c.gridy = 13;
        text = frame.createText("Defence: " + CreateHero.heroes.get(current_hero).getDefence(), 0, 0);
        frame.getWindow().add(text, c);
        frame.add_empty_text(14, 0);

        c.gridy = 15;
        text = frame.createText("HitPoints: " + CreateHero.heroes.get(current_hero).getHitPoints(), 0, 0);
        frame.getWindow().add(text, c);
        frame.add_empty_text(16, 0);

        c.gridy = 17;
        frame.getWindow().add(button_panel, c);

        frame.getWindow().setVisible(true);
    }

    private static class ButtonClickListener implements ActionListener {
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
//                GameController.getInstance().stage_Game();
            } else if (command.equals("Back")) {
                frame.clearWindow();
//                WindowService.createWelcomeWindow();
            } else if (command.equals("Next")) {
//                if(current_hero == (hero_list.size() - 1))
//                    current_hero = 0;
//                else
//                    current_hero++;
//                frame.clear_window();
//                paint_page();
            } else if (command.equals("Prev")) {
                if(current_hero == 0)
                    current_hero = (CreateHero.heroes.size() - 1);
                else
                    current_hero--;
//                frame.clear_window();
//                paint_page();
            } else {
                System.exit(0);
            }
        }
    }
}