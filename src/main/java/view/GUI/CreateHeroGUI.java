package view.GUI;

import view.CreateHero;
import view.DBService;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static view.CreateHero.current_hero;

public class CreateHeroGUI {

    public static void createWindowHero(){
        WindowService frame = new WindowService();
        frame.createBackGround("./src/main/resources/hero_create.jpg");

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
        frame.addEmptyText(2, 0);

        c.gridy = 3;
        ImageIcon imageIcon = new ImageIcon(CreateHero.heroes.get(current_hero).getPhotoProfile()); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(220, 220,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        frame.getWindow().add(new JLabel(imageIcon), c);

        frame.addEmptyText(4, 0);
        c.gridy = 5;
        frame.getWindow().add(button_panel_np, c);

        frame.addEmptyText(6, 0);
        c.gridy = 7;
        text = frame.createText("Name: " + CreateHero.heroes.get(current_hero).getName(), 0, 0);
        frame.getWindow().add(text, c);
        frame.addEmptyText(8, 0);

        c.gridy = 9;
        text = frame.createText("Class: " + CreateHero.heroes.get(current_hero).getHeroClass(), 0, 0);
        frame.getWindow().add(text, c);
        frame.addEmptyText(10, 0);

        c.gridy = 11;
        text = frame.createText("Attack: " + CreateHero.heroes.get(current_hero).getAttack(), 0, 0);
        frame.getWindow().add(text, c);
        frame.addEmptyText(12, 0);

        c.gridy = 13;
        text = frame.createText("Defence: " + CreateHero.heroes.get(current_hero).getDefence(), 0, 0);
        frame.getWindow().add(text, c);
        frame.addEmptyText(14, 0);

        c.gridy = 15;
        text = frame.createText("HitPoints: " + CreateHero.heroes.get(current_hero).getHitPoints(), 0, 0);
        frame.getWindow().add(text, c);
        frame.addEmptyText(16, 0);

        c.gridy = 17;
        frame.getWindow().add(button_panel, c);

        frame.getWindow().setVisible(true);
    }

    public void loadHero(){
        WindowService frame = new WindowService();
        frame.createBackGround("./src/main/resources/hero_create.jpg");

        JPanel button_panel = new JPanel();
        button_panel.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel.setLayout(new FlowLayout());

        JButton back = frame.createBotton("Back", 0, 0);

        back.setActionCommand("Back");

        back.addActionListener(new ButtonClickListener());

        button_panel.add(back);

        GridBagConstraints c = new GridBagConstraints();

        DBService dbService = new DBService();
        int max_count = 0;
        ResultSet resultSet = dbService.executeQuery("SELECT * FROM hero");
        try {
            while (resultSet.next())
                max_count++;
            c.gridx = 0;
            c.gridy = 0;
            for (int i = 1; i <= max_count; i++){
                JButton loadHero = frame.createBotton("Hero" + i, 0, 0);
                loadHero.setActionCommand(String.valueOf(i));
                loadHero.addActionListener(new ButtonClickListener());
                frame.getWindow().add(loadHero, c);
                c.gridy++;
            }
            WindowService.heroIsDB = 1;
        } catch (SQLException e) {
            System.out.println("SQL error in LoadHeroPage_BD");
        }
        c.fill = GridBagConstraints.VERTICAL;
        c.gridy = 10;
        frame.getWindow().add(button_panel, c);

        frame.getWindow().setVisible(true);
    }
}