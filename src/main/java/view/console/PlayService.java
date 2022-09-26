package view.console;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import controller.PlayController;
import model.Artifact;
import model.Hero;

import static view.GUI.WindowService.createWelcomePage;

/**
 * @author RMNurgalieva(уровень - 1)*5+10
 */
public class PlayService {

    private static int generationTimes = 0;
    private static int sizeOfField = 0;
    public static Hero enemy;
    final List<Artifact> artifacts = new LinkedList<>();
    private static Artifact helmet = new Artifact(1, "", "helmet", 10, 0, 5);
    private static Artifact weapon = new Artifact(2, "", "Weapon", 0, 15, 3);
    private static Artifact armor = new Artifact(3, "", "armor", 10, 0, 6);

    public static Artifact getHelmet() {
        return helmet;
    }

    public static Artifact getWeapon() {
        return weapon;
    }

    public static Artifact getArmor() {
        return armor;
    }

    public void printBattleField() throws SQLException {
        sizeOfField = (PlayController.currentHero.getLevel() - 1) * 5 + 10;
        if (generationTimes == 0) {
            generationTimes = 1;
            enemy = createEnemy(sizeOfField, PlayController.currentHero.getLevel());
        }
        for (int y = 0; y < sizeOfField; y++) {
            for (int x = 0; x < sizeOfField; x++) {
                if (x == PlayController.currentHero.getX() && y == PlayController.currentHero.getY()) {
                    System.out.print("H");
                } else if (x == enemy.getX() && y == enemy.getY()) {
                    System.out.print("E");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        printHeroAndEnemyInformation(PlayController.currentHero, enemy);
        printActionBar();
    }

    public Hero createEnemy(int size, int level) {
        int x_enemy = PlayController.currentHero.getX();
        int y_enemy = PlayController.currentHero.getY();

        while (x_enemy == PlayController.currentHero.getX() && y_enemy == PlayController.currentHero.getY()) {
            x_enemy = (int) Math.floor(Math.random() * size);
            y_enemy = (int) Math.floor(Math.random() * size);
        }
        if (level > 4) {
            return new Hero("Enemy(Lvl >4)", "Enenmy", 5, 5, 5,
                    "", "", "", "", x_enemy, y_enemy);
        } else {
            return new Hero("Enemy(Lvl<4)", "Enemy", 2, 2, 2,
                    "", "", "", "", x_enemy, y_enemy);
        }
    }

    public void printHeroAndEnemyInformation(Hero hero, Hero enemy) {
        System.out.println("--------Your hero-------");
        System.out.println("Name: " + hero.getName() + "\n"
                + "Class: " + hero.getHeroClass());
        System.out.println("Level: " + hero.getLevel() + "\n"
                + "Experience: " + hero.getExperience());
        if (hero.getWeapon() != null)
            System.out.println("Attack: " + hero.getAttack() + "(+"
                    + hero.getWeapon().getAttack() + ")");
        else
            System.out.println("Attack: " + hero.getAttack());
        if (hero.getArmor() != null)
            System.out.println("Defense: " + hero.getDefence() + "(+"
                    + hero.getArmor().getDefence() + ")");
        else
            System.out.println("Defense: " + hero.getArmor());
        if (hero.getHelmet() != null)
            System.out.println("HitPoints: " + hero.getHitPoints() + "(+"
                    + hero.getHelmet().getHitPoints() + ")");
        else
            System.out.println("HitPoints: " + hero.getHitPoints());
        if (hero.getWeapon() != null)
            System.out.println("Artifact 1: " + "Weapon");
        if (hero.getArmor() != null)
            System.out.println("Artifact 2: " + "Armor");
        if (hero.getHelmet() != null)
            System.out.println("Artifact 3: " + "Helmet");
    }

    public void printActionBar() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int answer = 0;
        while (true) {
            System.out.println("1) UP");
            System.out.println("2) DOWN");
            System.out.println("3) LEFT");
            System.out.println("4) RIGHT");
            System.out.println("5) Save hero");
            System.out.println("6) Switch to GUI");
            System.out.println("7) Exit");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                PlayController.printError("Error argument!");
            }
            if (answer > 0 && answer < 5) {
                int key = 0;
                if (answer == 1)
                    key = KeyEvent.VK_UP;
                else if (answer == 2)
                    key = KeyEvent.VK_DOWN;
                else if (answer == 3)
                    key = KeyEvent.VK_LEFT;
                else if (answer == 4)
                    key = KeyEvent.VK_RIGHT;
                move(key, sizeOfField);
                break;
            } else if (answer == 5 || answer == 6) {
                break;
            } else if (answer == 7) {
                System.exit(0);
            } else {
                System.out.println("Error argument!");
            }
        }
        if (answer == 5) {
            LoadHeroConsole loadHeroConsole = new LoadHeroConsole();
            loadHeroConsole.saveHeroDB();
        } else if (answer == 6) {
            createWelcomePage();
        }
        if (PlayController.currentHero.getX() == enemy.getX() && PlayController.currentHero.getY() == enemy.getY()) {
            battle();
        }
        printBattleField();
    }

    public static void move(int key, int size) {
        if (key == KeyEvent.VK_UP) {
            if (PlayController.currentHero.getY() - 1 > 0){
                PlayController.currentHero.setY(PlayController.currentHero.getY() - 1);
                PlayController.currentHero.setCurrentPhoto(PlayController.currentHero.getBack());
            }
        } else if (key == KeyEvent.VK_DOWN) {
            if (PlayController.currentHero.getY() + 1 < size) {
                PlayController.currentHero.setY(PlayController.currentHero.getY() + 1);
                PlayController.currentHero.setCurrentPhoto(PlayController.currentHero.getPhotoProfile());
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            if (PlayController.currentHero.getX() + 1 < size) {
                PlayController.currentHero.setX(PlayController.currentHero.getX() + 1);
                PlayController.currentHero.setCurrentPhoto(PlayController.currentHero.getRightSide());
            }
        } else if (key == KeyEvent.VK_LEFT) {
            if (PlayController.currentHero.getX() - 1 > 0) {
                PlayController.currentHero.setX(PlayController.currentHero.getX() - 1);
                PlayController.currentHero.setCurrentPhoto(PlayController.currentHero.getLeftSide());
            }
        }
    }

    public void battle() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int answer = 0;

        System.out.println("--- Battle ---");
        System.out.println("Name: " + PlayController.currentHero.getName() + " / " + enemy.getName());
        System.out.println("Level: " + PlayController.currentHero.getLevel() + " / " + enemy.getLevel());
        if (PlayController.currentHero.getWeapon() != null)
            System.out.println("Attack: " + PlayController.currentHero.getAttack() + "(+"
                    + PlayController.currentHero.getWeapon().getAttack() + ")" + " / " + enemy.getAttack());
        else
            System.out.println("Attack: " + PlayController.currentHero.getAttack() + " / " + enemy.getAttack());
        if (PlayController.currentHero.getArmor() != null)
            System.out.println("Defence: " + PlayController.currentHero.getDefence() + "(+"
                    + PlayController.currentHero.getArmor().getDefence() + ")" + " / " + enemy.getDefence());
        else
            System.out.println("Defence: " + PlayController.currentHero.getDefence() + " / " + enemy.getDefence());
        if (PlayController.currentHero.getHelmet() != null)
            System.out.println("HitPoints: " + PlayController.currentHero.getHitPoints() + "(+"
                    + PlayController.currentHero.getHelmet().getHitPoints() + ")" + " / " + enemy.getHitPoints());
        else
            System.out.println("HitPoints: " + PlayController.currentHero.getHitPoints() + " / " + enemy.getHitPoints());
        System.out.println("--------------");
        while (true) {
            System.out.println("1) Battle");
            System.out.println("2) Run");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                PlayController.printError("Error argument!");
            }
            if (answer == 1 || answer == 2) {
                break;
            } else
                System.out.println("Error arguments!");
        }
        if (answer == 1) {
            if (isHeroWon()) {
                heroWon();
            } else {
                System.out.println("---------Hero lose--------");
//                print_HeroLose();
            }
        } else if (answer == 2) {
            System.out.println("---------Hero run--------");
//            hero_run();
        }
    }

    public boolean isHeroWon() {
        Hero hero = PlayController.currentHero;
        if (hero.getArmor() != null) {
            hero.setHitPoints(hero.getHitPoints() + hero.getArmor().getHitPoints());
        }
        while (hero.getHitPoints() > 0 && enemy.getHitPoints() > 0) {
            if (enemy.getAttack() - hero.getDefence() > 0)
                hero.setHitPoints(hero.getHitPoints() - (enemy.getAttack() - hero.getDefence()));
            if (hero.getAttack() - enemy.getDefence() > 0)
                enemy.setHitPoints(enemy.getHitPoints() - (hero.getAttack() - enemy.getDefence()));
        }
        if (hero.getHitPoints() <= 0) {
            return false;
        }
        return true;
    }

    public void heroWon() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Hero hero = PlayController.currentHero;
        hero.setExperience(hero.getLevel() * 1000 + ((hero.getLevel() - 1) * (hero.getLevel() - 1)) * 450);
        int answer = 0;
        System.out.println("Winner!");
        System.out.println("Name: " + hero.getName());
        System.out.println("Class: " + hero.getHeroClass());
        System.out.println("Attack: " + hero.getAttack());
        System.out.println("Defence: " + hero.getDefence());
        System.out.println("HitPoints: " + hero.getHitPoints());
        System.out.println("Level: " + hero.getLevel());
        System.out.println("Experience after vicotory: " + hero.getExperience());
        Artifact artifact = generateArtifact();
        if (artifact != null) {
            System.out.println("New artifact: " + artifact.getType());
        } else {
            System.out.println("No artifacts");
        }
        upgradeHero(artifact);
        while (true) {
            System.out.println("1) Finish");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                PlayController.printError("Error argument!");
            }
            if (answer == 1) {
                enemy.setX(-1);
                enemy.setY(-1);
                System.exit(0);
                break;
            } else
                System.out.println("Error argument!");
        }
        printBattleField();
    }

    public Artifact generateArtifact() {
        artifacts.add(helmet);
        artifacts.add(weapon);
        artifacts.add(armor);
        return artifacts.get((int) (Math.random() * 3));
    }

    public void upgradeHero(Artifact artifact) {
        Hero hero = PlayController.currentHero;
        if (artifact != null) {
            if (artifact.getType().equals("Armor") && hero.getArmor().getHitPoints() < artifact.getHitPoints()) {
                PlayController.currentHero.setArmor(artifact);
            } else if (artifact.getType().equals("Helmet") && null == hero.getHelmet() && hero.getHelmet().getHitPoints() < artifact.getHitPoints()) {
                PlayController.currentHero.setHelmet(artifact);
            } else if (artifact.getType().equals("Weapon") && null == hero.getWeapon()) {
                PlayController.currentHero.setWeapon(artifact);
            }
        }
    }
}
