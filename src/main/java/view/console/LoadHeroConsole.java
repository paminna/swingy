package view.console;

import controller.PlayController;
import view.DBService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static controller.PlayController.currentHero;


/**
 * @author RMNurgalieva
 */
public class LoadHeroConsole {

    public DBService dbservice = new DBService();
    private Scanner scanner = new Scanner(System.in);
    int res = 0;
    int countHeroes = 0;

    public void loadHeroFromDB() throws SQLException {
        while (true) {
            if (dbservice.getConnection() != null) {
                ResultSet heroes = dbservice.executeQuery("SELECT * FROM HERO");
                try {
                    while (heroes.next()) {
                        countHeroes++;
                    }
                    for (int i = 1; i <= countHeroes; i++) {
                        System.out.println(i + ") hero" + i);
                    }
                    if (countHeroes == 0) {
                        System.out.println("database is empty yet");
                    }
                    System.out.println(countHeroes + 1 + ")Back");
                } catch (SQLException e) {
                    System.out.println("DB problem");
                }
                try {
                    res = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Error argument");
                    break;
                }
                if (res == countHeroes + 1) {
                    break;
                } else if (res > 0 && res <= countHeroes) {
                    break;
                } else {
                    System.out.println("Error argument");
                }
            }
        }
        if (res == countHeroes + 1) {
            PlayController.createWelcomePageConsole();
        } else {
            loadHeroDB(res);
            PlayController.startPlay();
        }
    }

    public void loadHeroDB(int id) {
        try {
            ResultSet hero = dbservice.executeQuery("SELECT * from hero where id=" + id);
            while (hero.next()) {
                PlayController.createHero(hero.getString("name"), hero.getString("class"), Integer.parseInt(hero.getString("attack"))
                        , Integer.parseInt(hero.getString("defence")), Integer.parseInt(hero.getString("hitPoints")), hero.getString("photoProfile"),
                        hero.getString("leftSide"), hero.getString("rightSide"), hero.getString("back"),
                        Integer.parseInt(hero.getString("coordinatex")), Integer.parseInt(hero.getString("coordinatey")));
                loadArtifacts(Integer.parseInt(hero.getString("weapon")),
                        Integer.parseInt(hero.getString("armor")), Integer.parseInt(hero.getString("helmet")));
            }
        } catch (SQLException e) {
            System.out.println("Error loadHeroBD gameController");
        }
    }

    public void loadArtifacts(int weapon, int armor, int helmet) {
        if (weapon != 0) {
            currentHero.setWeapon(PlayService.getWeapon());
        } else if (armor != 0) {
            currentHero.setArmor(PlayService.getArmor());
        } else if (helmet != 0) {
            currentHero.setHelmet(PlayService.getHelmet());
        }
    }

    public void saveHeroDB() {
        int weapon = 0;
        int armor = 0;
        int helm = 0;
        if (currentHero.getWeapon() != null)
            weapon = currentHero.getWeapon().getAttack();
        if (currentHero.getArmor() != null)
            armor = currentHero.getArmor().getDefence();
        if (currentHero.getHelmet() != null)
            helm = currentHero.getHelmet().getHitPoints();
        dbservice.executeQuery("INSERT INTO hero (name, class, attack, defence, hitpoints, level, experience," +
                "coordinatex, coordinatey, photoprofile, face, leftside, rightside, back, weapon, armor, helmet) VALUES (" +
                "'" + currentHero.getName() + "','" + currentHero.getHeroClass() + "', " + currentHero.getAttack() +
                "," + currentHero.getDefence() + " , " + currentHero.getHitPoints() + ", " + currentHero.getLevel() +
                "," + currentHero.getExperience() + ", " + currentHero.getX() + ", " + currentHero.getY() +
                "," + "'" + currentHero.getPhotoProfile() +
                "','" + currentHero.getLeftSide() + "', '" + currentHero.getRightSide() + "'," +
                "'" + currentHero.getBack() + "', " + weapon + ", " + armor + ", " + helm + ");");
    }

    public void saveHeroFile() {
        String fileName = "hero";
        int count = 0;
        while (true) {
            count++;
            File file = new File("./src/main/resources/saves/" + fileName + count);
            if (!file.exists())
                break;
        }
        try (FileWriter writer = new FileWriter("./src/main/resources/saves/" + fileName + count, false)) {
            writer.write(currentHero.getName() + "\n");
            writer.write(currentHero.getHeroClass() + "\n");
            writer.write(currentHero.getAttack() + "\n");
            writer.write(currentHero.getDefence() + "\n");
            writer.write(currentHero.getHitPoints() + "\n");
            writer.write(currentHero.getLevel() + "\n");
            writer.write(currentHero.getExperience() + "\n");
            writer.write(currentHero.getX() + "\n");
            writer.write(currentHero.getY() + "\n");
            writer.write(currentHero.getLeftSide() + "\n");
            writer.write(currentHero.getRightSide() + "\n");
            writer.write(currentHero.getBack() + "\n");
            writer.write(currentHero.getPhotoProfile() + "\n");
            if (currentHero.getWeapon() != null)
                writer.write(currentHero.getWeapon().getAttack() + "\n");
            else
                writer.write(0 + "\n");
            if (currentHero.getArmor() != null)
                writer.write(currentHero.getArmor().getDefence() + "\n");
            else
                writer.write(0 + "\n");
            if (currentHero.getHelmet() != null)
                writer.write(currentHero.getHelmet().getHitPoints() + "\n");
            else
                writer.write(0 + "\n");
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
