package view.console;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.*;

import controller.PlayController;
import model.Artifact;
import model.Hero;
import view.DBService;
import view.GUI.WindowService;

import static controller.PlayController.currentHero;
import static view.CreateHero.current_hero;
import static view.CreateHero.heroes;


public class PlayService {

    private static int generationTimes = 0;
    private static int sizeOfField = 0;
    public static Hero enemy;
    private final static List<Artifact> artifacts = new LinkedList<>();
    private static Artifact helmet = new Artifact(1, "./src/main/resources/artifact/helm.png", "Helmet", 10, 0, 5);
    private static Artifact weapon = new Artifact(2, "./src/main/resources/artifact/weapon.png", "Weapon", 0, 15, 3);
    private static Artifact armor = new Artifact(3, "./src/main/resources/artifact/armor.png", "Armor", 10, 0, 6);

    public static Artifact getHelmet() {
        return helmet;
    }

    public static Artifact getWeapon() {
        return weapon;
    }

    public static Artifact getArmor() {
        return armor;
    }

    public static int getSizeOfField() {
        return sizeOfField;
    }

    public void printBattleField() throws SQLException {
        sizeOfField = (currentHero.getLevel() - 1) * 5 + 10;
        if (generationTimes == 0) {
            generationTimes = 1;
            enemy = createEnemy(sizeOfField, currentHero.getLevel());
        }
        for (int y = 0; y < sizeOfField; y++) {
            for (int x = 0; x < sizeOfField; x++) {
                if (x == currentHero.getX() && y == currentHero.getY()) {
                    System.out.print("H");
                } else if (x == enemy.getX() && y == enemy.getY()) {
                    System.out.print("E");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
        printHeroAndEnemyInformation(currentHero, enemy);
        printActionBar();
    }

    public static Hero createEnemy(int size, int level) {
        int x_enemy = currentHero.getX();
        int y_enemy = currentHero.getY();
        Hero hero;

        while (x_enemy == currentHero.getX() && y_enemy == currentHero.getY()) {
            x_enemy = (int) Math.floor(Math.random() * size);
            y_enemy = (int) Math.floor(Math.random() * size);
        }
        if (level >= 2) {
            hero = new Hero("Enemy(Lvl >=2)", "Enenmy", 10, 5, 15,
                    "./src/main/resources/enemy2.png", "", "", "", x_enemy, y_enemy, 500, 2);
        } else {
            hero = new Hero("Enemy(Lvl<2)", "Enemy", 8, 2, 10,
                    "./src/main/resources/enemy1.png", "", "", "", x_enemy, y_enemy,300, 1);
        }
        return hero;
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
            System.out.println("Defence: " + hero.getDefence() + "(+"
                    + hero.getArmor().getDefence() + ")");
        else
            System.out.println("Defence: " + hero.getDefence());
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
            System.out.println("6) Exit");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                PlayController.printError("Error argument!");
            }
            if (answer > 0 && answer < 5) {
                int key = 0;
                if (answer == 1)
                    key = KeyEvent.VK_W;
                else if (answer == 2)
                    key = KeyEvent.VK_S;
                else if (answer == 3)
                    key = KeyEvent.VK_A;
                else if (answer == 4)
                    key = KeyEvent.VK_D;
                move(key, sizeOfField);
                break;
            } else if (answer == 5) {
                break;
            } else if (answer == 6) {
                System.exit(0);
            } else {
                System.out.println("Error argument!");
            }
        }
        if (answer == 5) {
            DBService dbService = new DBService();
            dbService.saveHeroDB();
        }
        if (currentHero.getX() == enemy.getX() && currentHero.getY() == enemy.getY()) {
            battle();
        }
        printBattleField();
    }

    public static void move(int key, int size) {
        if (key == KeyEvent.VK_W) {
            if (currentHero.getY() - 1 >= 0){
                currentHero.setY(currentHero.getY() - 1);
                currentHero.setCurrentPhoto(currentHero.getBack());
            }
        } else if (key == KeyEvent.VK_S) {
            if (currentHero.getY() + 1 < size) {
                currentHero.setY(currentHero.getY() + 1);
                currentHero.setCurrentPhoto(currentHero.getPhotoProfile());
            }
        } else if (key == KeyEvent.VK_D) {
            if (currentHero.getX() + 1 < size) {
                currentHero.setX(currentHero.getX() + 1);
                currentHero.setCurrentPhoto(currentHero.getRightSide());
            }
        } else if (key == KeyEvent.VK_A) {
            if (currentHero.getX() - 1 >= 0) {
                currentHero.setX(currentHero.getX() - 1);
                currentHero.setCurrentPhoto(currentHero.getLeftSide());
            }
        }
    }

    public void battle() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int answer = 0;

        System.out.println("--- Battle ---");
        System.out.println("Name: " + currentHero.getName() + " / " + enemy.getName());
        System.out.println("Level: " + currentHero.getLevel() + " / " + enemy.getLevel());
        if (currentHero.getWeapon() != null)
            System.out.println("Attack: " + currentHero.getAttack() + "(+"
                    + currentHero.getWeapon().getAttack() + ")" + " / " + enemy.getAttack());
        else
            System.out.println("Attack: " + currentHero.getAttack() + " / " + enemy.getAttack());
        if (currentHero.getArmor() != null)
            System.out.println("Defence: " + currentHero.getDefence() + "(+"
                    + currentHero.getArmor().getDefence() + ")" + " / " + enemy.getDefence());
        else
            System.out.println("Defence: " + currentHero.getDefence() + " / " + enemy.getDefence());
        if (currentHero.getHelmet() != null)
            System.out.println("HitPoints: " + currentHero.getHitPoints() + "(+"
                    + currentHero.getHelmet().getHitPoints() + ")" + " / " + enemy.getHitPoints());
        else
            System.out.println("HitPoints: " + currentHero.getHitPoints() + " / " + enemy.getHitPoints());
        System.out.println("Experience: " + currentHero.getExperience() + "/" + enemy.getExperience());
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
                print_HeroLose();
            }
        } else if (answer == 2) {
            System.out.println("---------Hero run--------");
            hero_run();
        }
    }

    public boolean isHeroWon() {
        Hero hero = currentHero;
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
        currentHero.setExperience(currentHero.getLevel() * 1000 + ((currentHero.getLevel() - 1) * (currentHero.getLevel() - 1)) * 450);
        int answer = 0;
        System.out.println("Winner!");
        System.out.println("Name: " + currentHero.getName());
        System.out.println("Class: " + currentHero.getHeroClass());
        System.out.println("Attack: " + currentHero.getAttack());
        System.out.println("Defence: " + currentHero.getDefence());
        System.out.println("HitPoints: " + currentHero.getHitPoints());
        System.out.println("Level: " + currentHero.getLevel());
        System.out.println("Experience after vicotory: " + currentHero.getExperience());
        Artifact artifact = this.generateArtifact();
        if (artifact != null) {
            System.out.println("New artifact: " + artifact.getType());
        } else {
            System.out.println("No artifacts");
        }
        upgradeHero(artifact);
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
        }
        else
        {
            heroes.get(current_hero).setExperience(heroes.get(current_hero).getExperience() +  heroes.get(current_hero).getLevel() * 1000 + ((heroes.get(current_hero).getLevel() - 1) * (heroes.get(current_hero).getLevel() - 1)) * 450);
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
                generationTimes = 0;
                WelcomePageConsole welcomePageConsole = new WelcomePageConsole();
                welcomePageConsole.drawWelcomePage();
                break;
            } else
                System.out.println("Error argument!");
        }
        printBattleField();
    }

    public static Artifact generateArtifact() {
        artifacts.add(helmet);
        artifacts.add(weapon);
        artifacts.add(armor);
        return artifacts.get((int) (Math.random() * 3));
    }

    public static void upgradeHero(Artifact artifact) {
        Hero hero = heroes.get(current_hero);
        if (artifact != null) {
            if (WindowService.heroIsDB == 1){
                if (artifact.getType().equals("Armor") && (currentHero.getArmor() == null
                        || currentHero.getArmor().getHitPoints() < artifact.getHitPoints())) {
                    currentHero.setArmor(artifact);
                    currentHero.setDefence(artifact.getDefence());
                } else if (artifact.getType().equals("Helmet") && (null == currentHero.getHelmet()
                        || currentHero.getHelmet().getHitPoints() < artifact.getHitPoints())) {
                    currentHero.setHelmet(artifact);
                    currentHero.setHitPoints(artifact.getHitPoints());
                } else if (artifact.getType().equals("Weapon") && (null == currentHero.getWeapon()
                        || currentHero.getWeapon().getHitPoints() < artifact.getHitPoints())) {
                    currentHero.setWeapon(artifact);
                    currentHero.setAttack(artifact.getAttack());
                }
            }
            else{
            if (artifact.getType().equals("Armor") && (hero.getArmor() == null
                    || hero.getArmor().getDefence() < artifact.getDefence())) {
                heroes.get(current_hero).setArmor(artifact);
                heroes.get(current_hero).setDefence(artifact.getDefence());
            } else if (artifact.getType().equals("Helmet") && (null == hero.getHelmet()
                    || hero.getHelmet().getHitPoints() < artifact.getHitPoints())) {
                heroes.get(current_hero).setHelmet(artifact);
                heroes.get(current_hero).setHitPoints(artifact.getHitPoints());
            } else if (artifact.getType().equals("Weapon") && (null == hero.getWeapon()
                    || hero.getWeapon().getAttack() < artifact.getAttack())) {
                heroes.get(current_hero).setWeapon(artifact);
                heroes.get(current_hero).setAttack(artifact.getAttack());
            }
            }
        }
    }

    public void print_HeroLose() {
        Scanner scanner = new Scanner(System.in);
        int answer = 0;
        System.out.println("You lose!");
        System.out.println("Name: " + currentHero.getName());
        System.out.println("Class: " + currentHero.getHeroClass());
        System.out.println("Attack: " + currentHero.getAttack());
        System.out.println("Defence: " + currentHero.getDefence());
        System.out.println("HitPoints: " + currentHero.getHitPoints());
        System.out.println("Level: " + currentHero.getLevel());
        System.out.println("Experience: " + currentHero.getExperience());
        while(true) {
            System.out.println("1) Exit");
            try {
                answer = scanner.nextInt();
            } catch (Exception e) {
                PlayController.printError("Error argument!");
            } if(answer == 1) {
                System.exit(0);
            } else
                System.out.println("Error argument!");
        }
    }

    private void hero_run() throws SQLException {
        Random random = new Random();
        int rand = random.nextInt(1 + 1);
        if(rand == 1) {
            if(isHeroWon()) {
                heroWon();
            } else {
                print_HeroLose();
            }
        } else {
            if (rand == 0)
                PlayService.move(KeyEvent.VK_W, sizeOfField);
            else if (rand == 1)
                PlayService.move(KeyEvent.VK_S, sizeOfField);
            else if (rand == 2)
                PlayService.move(KeyEvent.VK_D, sizeOfField);
            else if (rand == 3)
                PlayService.move(KeyEvent.VK_A, sizeOfField);
            printBattleField();
        }
    }
}
