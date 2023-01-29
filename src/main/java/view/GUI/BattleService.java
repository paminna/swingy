package view.GUI;

import model.Artifact;
import model.Hero;
import view.console.PlayService;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static controller.PlayController.currentHero;
import static view.CreateHero.current_hero;
import static view.CreateHero.heroes;

public class BattleService {
    private static int winner;
    private static Hero enemy;

    public static int getWinner() {
        return winner;
    }

    public void paint_page(Hero enemy, int winner) {
        this.winner = winner;
        this.enemy = enemy;

        WindowService frame = new WindowService();
        JPanel contentPane = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        JPanel hero = print_Hero();
        constraints.gridx = 1;
        constraints.weighty = 0.33;
        constraints.weightx = 0.33;
        hero.setBackground(new Color(87, 55, 23));
        contentPane.add(hero, constraints);

        JPanel vs = print_vs();
        constraints.gridx = 2;
        constraints.weighty = 0.33;
        constraints.weightx = 0.33;
        vs.setBackground(new Color(87, 55, 23));
        contentPane.add(vs, constraints);

        JPanel warrior = print_Enemy(enemy);
        constraints.gridx = 3;
        constraints.weighty = 0.33;
        constraints.weightx = 0.33;
        warrior.setBackground(new Color(87, 55, 23));
        contentPane.add(warrior, constraints);

        frame.getWindow().setContentPane(contentPane);
        frame.getWindow().setVisible(true);
    }

    public JPanel print_Hero() {
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
        ImageIcon heroIcon = new ImageIcon(currentHero.getPhotoProfile()); // load the image to a imageIcon
        Image heroImage = heroIcon.getImage();
        Image newHeroImg = heroImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newHeroImg);
        game.add(new JLabel(imageIcon), cont);

        cont.gridy = 5;
        text = frame.createText("Name: " + currentHero.getName(), 0, 0);
        game.add(text, cont);
        cont.gridy = 7;
        text = frame.createText("Class: " + currentHero.getHeroClass(), 0, 0);
        game.add(text, cont);
        cont.gridy = 9;
        if (currentHero.getWeapon() != null)
            text = frame.createText("Attack: " + currentHero.getAttack() + " (+" + currentHero.getWeapon().getAttack() + ")", 0, 0);
        else
            text = frame.createText("Attack: " + currentHero.getAttack(), 0, 0);
        game.add(text, cont);
        cont.gridy = 11;
        if (currentHero.getArmor() != null)
            text = frame.createText("Defence: " + currentHero.getDefence() + " (+" + currentHero.getArmor().getDefence() + ")", 0, 0);
        else
            text = frame.createText("Defence: " + currentHero.getDefence(), 0, 0);
        game.add(text, cont);
        cont.gridy = 13;
        if (currentHero.getHelmet() != null)
            text = frame.createText("HitPoints: " + currentHero.getHitPoints() + " (+" + currentHero.getHelmet().getHitPoints() + ")", 0, 0);
        else
            text = frame.createText("HitPoints: " + currentHero.getHitPoints(), 0, 0);
        game.add(text, cont);

        cont.gridy = 15;
        text = frame.createText("Lvl: " + currentHero.getLevel(), 0, 0);
        game.add(text, cont);

        cont.gridy = 17;
        text = frame.createText("Exp: " + currentHero.getExperience(), 0, 0);
        game.add(text, cont);

        return (game);
    }

    public JPanel print_vs() {
        WindowService frame = new WindowService();
        JPanel game = new JPanel(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.gridx = 0;
        cont.gridy = 1;
        cont.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.createText("Battle", 0, 0);
        game.add(text, cont);

        frame.addEmptyText(2, 0);

        frame.addEmptyText(4, 0);

        JButton battle = frame.createBotton("Battle", 0, 0);
        battle.setActionCommand("Battle");
        battle.addActionListener(new ButtonClickListener());
        cont.gridy = 5;
        game.add(battle, cont);

        frame.addEmptyText(6, 0);

        JButton run = frame.createBotton("Run", 0, 0);
        run.setActionCommand("Run");
        run.addActionListener(new ButtonClickListener());
        cont.gridy = 7;
        game.add(run, cont);
        return game;
    }


    public JPanel print_Enemy(Hero enemy) {
        WindowService frame = new WindowService();

        JPanel game = new JPanel(new GridBagLayout());
        ImageIcon imageIcon = null;
        GridBagConstraints cont = new GridBagConstraints();
        cont.gridx = 0;
        cont.gridy = 1;
        cont.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.createText("Enemy", 0, 0);
        game.add(text, cont);

        frame.addEmptyText(2, 0);
        int width = 100;
        int height = 100;
        if (WindowService.getSize() >= 10) {
            width = 50;
            height = 50;
        }

        cont.gridy = 3;
        ImageIcon heroIcon = new ImageIcon(enemy.getPhotoProfile()); // load the image to a imageIcon
        Image heroImage = heroIcon.getImage();
        Image newHeroImg = heroImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newHeroImg);
        game.add(new JLabel(imageIcon), cont);

        cont.gridy = 5;
        text = frame.createText("Name: " + enemy.getName(), 0, 0);
        game.add(text, cont);
        cont.gridy = 7;
        text = frame.createText("Class: " + enemy.getHeroClass(), 0, 0);
        game.add(text, cont);
        cont.gridy = 9;

        text = frame.createText("Attack: " + enemy.getAttack(), 0, 0);
        game.add(text, cont);
        cont.gridy = 11;

        text = frame.createText("Defence: " + enemy.getDefence(), 0, 0);
        game.add(text, cont);
        cont.gridy = 13;
        text = frame.createText("HitPoints: " + enemy.getHitPoints(), 0, 0);
        game.add(text, cont);

        cont.gridy = 15;
        text = frame.createText("Lvl: " + enemy.getLevel(), 0, 0);
        game.add(text, cont);

        cont.gridy = 17;
        text = frame.createText("", 0, 0);
        game.add(text, cont);

        cont.gridy = 19;
        text = frame.createText("", 0, 0);
        game.add(text, cont);

        cont.gridy = 21;
        text = frame.createText("", 0, 0);
        game.add(text, cont);
        return (game);
    }

    public static int getWinner(Hero enemy) {
        int attack_bonus = 0;
        int defence_bonus = 0;
        int hp_bonus = 0;
        if (currentHero.getWeapon() != null)
            attack_bonus = currentHero.getWeapon().getAttack();
        if (currentHero.getArmor() != null)
            defence_bonus = currentHero.getArmor().getDefence();
        if (currentHero.getHelmet() != null)
            hp_bonus = currentHero.getHelmet().getHitPoints();
        Random rand = new Random();
        int diff = rand.nextInt(1 + 1);
        int hero_hp = currentHero.getHitPoints() + hp_bonus;
        int enemy_hp = enemy.getHitPoints();
        if (diff == 0)
            hero_hp += 5;
        else
            enemy_hp += 5;
        for (; hero_hp > 0 || enemy_hp > 0; enemy_hp -= 3, hero_hp -= 3) {
            if (enemy.getAttack() - (currentHero.getDefence() + defence_bonus) > 0)
                hero_hp -= enemy.getAttack() - currentHero.getDefence();
            if ((currentHero.getAttack() + attack_bonus) - enemy.getDefence() > 0)
                enemy_hp -= currentHero.getAttack() - enemy.getDefence();
        }
        if (hero_hp > enemy_hp)
            return (1);
        return (0);
    }

    public static void printHeroWin() {
        WindowService.clearWindow();
        WindowService frame = new WindowService();
        frame.createBackGround("./src/main/resources/hero_create.jpg");

        JPanel button_panel = new JPanel();
        button_panel.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel.setLayout(new FlowLayout());

        JButton createHero = frame.createBotton("Finish", 0, 0);
        createHero.setActionCommand("Finish");
        createHero.addActionListener(new ButtonClickListener());
        button_panel.add(createHero);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.createText("You won and got the artifact", 0, 0);
        frame.getWindow().add(text, c);

        Artifact artifact = PlayService.generateArtifact();
        c.gridy = 2;
        ImageIcon imageIcon = new ImageIcon(currentHero.getPhotoProfile()); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        frame.getWindow().add(new JLabel(imageIcon), c);

        c.gridy = 3;
        text = frame.createText("Name: " + currentHero.getName(), 0, 0);
        frame.getWindow().add(text, c);
        c.gridy = 4;
        text = frame.createText("Class: " + currentHero.getHeroClass(), 0, 0);
        frame.getWindow().add(text, c);
        c.gridy = 5;
        text = frame.createText("Attack: " + currentHero.getAttack(), 0, 0);
        frame.getWindow().add(text, c);
        c.gridy = 6;
        text = frame.createText("Defence: " + currentHero.getDefence(), 0, 0);
        frame.getWindow().add(text, c);
        c.gridy = 7;
        text = frame.createText("HitPoints: " + currentHero.getHitPoints(), 0, 0);
        frame.getWindow().add(text, c);
        c.gridy = 8;
        text = frame.createText("Level: " + currentHero.getLevel(), 0, 0);
        frame.getWindow().add(text, c);
        c.gridy = 9;
        text = frame.createText("Experience: " + currentHero.getExperience() + " (+" + (currentHero.getLevel() * 1000 + ((currentHero.getLevel() - 1) * (currentHero.getLevel() - 1)) * 450) + ")", 0, 0);
        frame.getWindow().add(text, c);
        if (artifact != null) {
            c.gridy = 10;
            imageIcon = new ImageIcon(artifact.getPhoto()); // load the image to a imageIcon
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newimg);
            frame.getWindow().add(new JLabel(imageIcon), c);
        }
        PlayService.upgradeHero(artifact);
        c.gridy = 11;
        if (WindowService.heroIsDB == 1) {
            currentHero.setExperience(currentHero.getExperience() + currentHero.getLevel() * 1000 + ((currentHero.getLevel() - 1) * (currentHero.getLevel() - 1)) * 450);
            if (currentHero.getExperience() > 1000) {
                currentHero.setLevel(1);
            }
            if (currentHero.getExperience() > 2450) {
                currentHero.setLevel(2);
            }
            if (currentHero.getExperience() > 4800) {
                currentHero.setLevel(3);
            }
            if (currentHero.getExperience() > 8050) {
                currentHero.setLevel(4);
            }
            if (currentHero.getExperience() > 12200) {
                currentHero.setLevel(5);
            }
        } else {
            heroes.get(current_hero).setExperience(heroes.get(current_hero).getExperience() + heroes.get(current_hero).getLevel() * 1000 + ((heroes.get(current_hero).getLevel() - 1) * (heroes.get(current_hero).getLevel() - 1)) * 450);
            if (heroes.get(current_hero).getExperience() > 1000) {
                heroes.get(current_hero).setLevel(1);
            }
            if (heroes.get(current_hero).getExperience() > 2450) {
                heroes.get(current_hero).setLevel(2);
            }
            if (heroes.get(current_hero).getExperience() > 4800) {
                heroes.get(current_hero).setLevel(3);
            }
            if (heroes.get(current_hero).getExperience() > 8050) {
                heroes.get(current_hero).setLevel(4);
            }
            if (heroes.get(current_hero).getExperience() > 12200) {
                heroes.get(current_hero).setLevel(5);
            }
        }
        frame.getWindow().add(button_panel, c);

        frame.getWindow().setVisible(true);
    }

    public static void printHeroLose() {
        WindowService.clearWindow();
        WindowService frame = new WindowService();
        frame.createBackGround("./src/main/resources/hero_create.jpg");

        JPanel button_panel = new JPanel();
        button_panel.setOpaque(false); // убираем белый цвет на jpanel кнопок
        button_panel.setLayout(new FlowLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.VERTICAL;
        JLabel text = frame.createText("You lose", 0, 0);
        frame.getWindow().add(text, c);

        c.gridy = 2;
        ImageIcon imageIcon = new ImageIcon(currentHero.getPhotoProfile()); // load the image to a imageIcon
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        frame.getWindow().add(new JLabel(imageIcon), c);

        c.gridy = 3;
        text = frame.createText("Name: " + currentHero.getName(), 0, 0);
        frame.getWindow().add(text, c);
        c.gridy = 4;
        text = frame.createText("Class: " + currentHero.getHeroClass(), 0, 0);
        frame.getWindow().add(text, c);
        c.gridy = 5;
        text = frame.createText("Attack: " + currentHero.getAttack(), 0, 0);
        frame.getWindow().add(text, c);
        c.gridy = 6;
        text = frame.createText("Defence: " + currentHero.getDefence(), 0, 0);
        frame.getWindow().add(text, c);
        c.gridy = 7;
        text = frame.createText("HitPoints: " + currentHero.getHitPoints(), 0, 0);
        frame.getWindow().add(text, c);
        c.gridy = 8;
        text = frame.createText("Lvl: " + currentHero.getLevel(), 0, 0);
        frame.getWindow().add(text, c);
        c.gridy = 9;
        text = frame.createText("Exp: " + currentHero.getExperience(), 0, 0);
        frame.getWindow().add(text, c);

        c.gridy = 10;
        JButton createHero = frame.createBotton("Exit", 0, 0);
        createHero.setActionCommand("Exit");
        createHero.addActionListener(new ButtonClickListener());
        button_panel.add(createHero);
        frame.getWindow().add(button_panel, c);

        frame.getWindow().setVisible(true);
    }
}