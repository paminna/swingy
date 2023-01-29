package view.GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;


import javax.imageio.ImageIO;
import javax.swing.*;

import controller.PlayController;
import model.Hero;
import view.console.PlayService;

public class WindowService {

    private static JFrame window;
    private static final int BOTTON_WIDTH = 100;
    private static final int HEIGHT = 40;
    private static final int LABEL_WIDTH = 200;
    private static final ActionListener actionListener = new Action();
    private static final String WINDOW_NAME = "SWINGY";
    private static final int windowWidth = 900;
    private static final int windowHeight = 700;
    private static KeyListener signal;
    private static int size = 0;
    public static int generateFirst = 0;
    public static int heroIsDB;

    public static KeyListener getSignal() {
        return signal;
    }

    public static int getSize() {
        return size;
    }

    public static void createWelcomePage() {
        WindowService windowService = new WindowService();
        windowService.getWindow();
    }

    public static JFrame getWindow() {
        if (window == null) {
            window = new JFrame(WINDOW_NAME);
            window.setSize(windowWidth, windowHeight);
            return createWelcomeWindow();
        }
        return window;
    }

    public static JButton createBotton(String nameBotton, int x, int y) {
        JButton button = new JButton(nameBotton);
        button.setBounds(x, y, BOTTON_WIDTH, HEIGHT);
        button.addActionListener(actionListener);
        window.add(button);
        return button;
    }

    public static JFrame createWelcomeWindow() {
        createBackGround("./src/main/resources/mainPage.png");
        window.setLayout(null);
        window.setVisible(true);
        createText("Welcome to swingy!", 350, 100);
        createBotton("create hero", 150, 300);
        createBotton("load hero", 350, 300);
        createBotton("exit", 550, 300);
        createBotton("go to console", 350, 600);
        return window;
    }

    public static JLabel createText(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, LABEL_WIDTH, HEIGHT);
        label.setFont(new Font("Verdana", Font.PLAIN, 18));
        window.add(label);
        return label;
    }

    public static void createBackGround(String path) {
        JLabel content = new JLabel();
        content.setIcon(new ImageIcon(path));
        content.setLayout(new BorderLayout());
        window.setContentPane(content);
        window.setLayout(new GridBagLayout());
        window.setVisible(true);
    }

    public static void clearWindow() {
        window.getContentPane().removeAll();
        window.getContentPane().repaint();
    }

    public void addEmptyText(int gridy, int gridx) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = gridy;
        c.gridx = gridx;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = createText(" ", 16, 100);
        window.add(text, c);
    }

    public static void startPlay() {
        signal = new KeyListener();
        WindowService.window.setVisible(true);
        WindowService.window.setFocusable(true);
        WindowService.window.setFocusTraversalKeysEnabled(false);
        WindowService.window.addKeyListener(signal);
        WindowService.window.setFocusable(true);
        render();
    }

    public static void render() {
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        JPanel map = paint_map();
        constraints.gridx = 1;
        constraints.weighty = 0.55;
        constraints.weightx = 0.50;
        map.setBackground(new Color(87, 125, 60));
        contentPane.add(map, constraints);

        JPanel game = paintGame();
        constraints.gridx = 2;
        constraints.weighty = 0.45;
        constraints.weightx = 0.50;
        game.setBackground(new Color(32, 124, 55));
        contentPane.add(game, constraints);

        WindowService.window.setContentPane(contentPane);
        WindowService.window.setVisible(true);
    }

    static JPanel paint_map() {
        int width = 100;
        int height = 100;
        int heroWidth = 800;
        int heroHeight = 700;
        size = (PlayController.currentHero.getLevel() - 1) * 5 + 10;
        JPanel map = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        ImageIcon imageIcon = new ImageIcon("./src/main/resources/grass.png"); // load the image to a imageIcon
        if (size >= 10) {
            width = 50;
            height = 50;
            heroHeight = 500;
            heroWidth = 600;
        }
        if (size >= 25) {
            width = 40;
            height = 40;
            heroHeight = 400;
            heroWidth = 500;
        }
        Image newimg = imageIcon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        ImageIcon avatarIcon = null;
        try {
            BufferedImage logo = ImageIO.read(new File("./src/main/resources/grass.png"));
            BufferedImage source = ImageIO.read(new File(PlayController.currentHero.getCurrentPhoto()));

            Image hero = source.getScaledInstance(heroWidth, heroHeight, java.awt.Image.SCALE_SMOOTH);
            logo.getGraphics().drawImage(hero, 0, 0, null);
            Image newimgAvatar = logo.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            avatarIcon = new ImageIcon(newimgAvatar);
        } catch (Exception e) {
            System.out.println("ERROR paint_map");
        }
        if (generateFirst == 0) {
            map = paintEnemy(map);
            generateFirst = 1;
        } else {
            map = paint_saved(map);
        }
        for (int y = 0; y < size; y++) {
            c.gridy = y;
            for (int x = 0; x < size; x++) {
                c.gridx = x;
                //здесь ставится положение героя
                if (c.gridy == PlayController.currentHero.getY() && c.gridx == PlayController.currentHero.getX())
                    map.add(new JLabel(avatarIcon), c);
                else
                    map.add(new JLabel(imageIcon), c);
            }
        }
        map.setBackground(new Color(87, 125, 60));
        return (map);
    }

    public static JPanel paintGame() {
        WindowService frame = new WindowService();

        JPanel game = new JPanel(new GridBagLayout());
        ImageIcon imageIcon = null;
        GridBagConstraints cont = new GridBagConstraints();

        cont.gridx = 0;
        cont.gridy = 1;
        cont.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.createText("Hero", 0, 0);
        game.add(text, cont);

        frame.addEmptyText(2, 0);

        cont.gridy = 3;
        ImageIcon heroIcon = new ImageIcon(PlayController.currentHero.getPhotoProfile()); // load the image to a imageIcon
        Image heroImage = heroIcon.getImage();
        Image newHeroImg = heroImage.getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newHeroImg);
        game.add(new JLabel(imageIcon), cont);

        JPanel button_panel = new JPanel();
        button_panel.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel.setLayout(new FlowLayout());

        JButton save_db = frame.createBotton("Save hero", 0, 0);
        save_db.setActionCommand("Save hero");
        save_db.addActionListener(new ButtonClickListener());
        button_panel.add(save_db);

        JButton exit = frame.createBotton("Exit", 0, 0);
        exit.setActionCommand("Exit");
        exit.addActionListener(new ButtonClickListener());
        button_panel.add(exit);

        cont.gridy = 5;
        game.add(button_panel, cont);

        cont.gridy = 7;
        text = frame.createText("Name: " + PlayController.currentHero.getName(), 0, 0);
        game.add(text, cont);
        cont.gridy = 9;
        text = frame.createText("Class: " + PlayController.currentHero.getHeroClass(), 0, 0);
        game.add(text, cont);
        cont.gridy = 11;
        if (PlayController.currentHero.getWeapon() != null)
            text = frame.createText("Attack: " + PlayController.currentHero.getAttack() + " (+" + PlayController.currentHero.getWeapon().getAttack() + ")", 0, 0);
        else
            text = frame.createText("Attack: " + PlayController.currentHero.getAttack(), 0, 0);
        game.add(text, cont);
        cont.gridy = 13;
        if (PlayController.currentHero.getArmor() != null)
            text = frame.createText("Defence: " + PlayController.currentHero.getDefence() + " (+" + PlayController.currentHero.getDefence() + ")", 0, 0);
        else
            text = frame.createText("Defence: " + PlayController.currentHero.getDefence(), 0, 0);
        game.add(text, cont);
        cont.gridy = 15;
        if (PlayController.currentHero.getHelmet() != null)
            text = frame.createText("HitPoints: " + PlayController.currentHero.getHitPoints() + " (+" + PlayController.currentHero.getHelmet().getHitPoints() + ")", 0, 0);
        else
            text = frame.createText("HitPoints: " + PlayController.currentHero.getHitPoints(), 0, 0);
        game.add(text, cont);

        cont.gridy = 17;
        text = frame.createText("Level: " + PlayController.currentHero.getLevel(), 0, 0);
        game.add(text, cont);

        cont.gridy = 19;
        text = frame.createText("Experience: " + PlayController.currentHero.getExperience(), 0, 0);
        game.add(text, cont);

        JPanel inventory_panel = new JPanel();
        inventory_panel.setOpaque(false); // убираем белый цвет
        inventory_panel.setLayout(new FlowLayout());
        return game;
    }

    private static JPanel paintEnemy(JPanel map) {
        GridBagConstraints c = new GridBagConstraints();
        ImageIcon avatarIcon = null;
        PlayService.enemy = PlayService.createEnemy(size, PlayController.currentHero.getLevel());
        try {
            int width = 100;
            int height = 100;
            if (WindowService.getSize() >= 10) {
                width = 50;
                height = 50;
            }
            BufferedImage source = ImageIO.read(new File(PlayService.enemy.getPhotoProfile()));
            Image newimgAvatar = source.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            avatarIcon = new ImageIcon(newimgAvatar);
            c.gridx = PlayService.enemy.getX();
            c.gridy = PlayService.enemy.getY();
            map.add(new JLabel(avatarIcon), c);
        } catch (Exception e) {
            System.out.println("ERROR paint_enemy_neutrals1");
            System.exit(-1);
        }
        return (map);
    }

    private static JPanel paint_saved(JPanel map) {
        GridBagConstraints c = new GridBagConstraints();
        ImageIcon avatarIcon = null;
        try {
            int width = 100;
            int height = 100;
            if (WindowService.getSize() >= 10) {
                width = 50;
                height = 50;
            }
            BufferedImage source = ImageIO.read(new File(PlayService.enemy.getPhotoProfile()));
            Image newimgAvatar = source.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            avatarIcon = new ImageIcon(newimgAvatar);
            c.gridx = PlayService.enemy.getX();
            c.gridy = PlayService.enemy.getY();
            map.add(new JLabel(avatarIcon), c);
        } catch (Exception e) {
            System.out.println("Error paint_saved!");
        }
        return (map);
    }

    public Hero returnEnemyByCoordinates(int x, int y) {
        if (PlayService.enemy.getY() == y && PlayService.enemy.getX() == x) {
            return PlayService.enemy;
        }
        return null;
    }

    public static void stage_Battle(Hero enemy) {
        WindowService frame = new WindowService();
        frame.clearWindow();
        BattleService battle = new BattleService();
        battle.paint_page(enemy, BattleService.getWinner(enemy));
    }
}