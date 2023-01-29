package view;

import controller.PlayController;

import java.sql.*;

import static controller.PlayController.currentHero;
import static view.console.LoadHeroConsole.loadArtifacts;

public class DBService {

    private static Connection connection;
    private static Statement statement;
    private static final String DB_Driver = "org.h2.Driver";
    private boolean error;

    public DBService() {
        try {
            this.error = false;
            String DB_URL = "jdbc:postgresql://localhost:5432/swingy";
            Class.forName("org.postgresql.Driver"); // Проверяем наличие JDBC драйвера для работы с БД
            connection = DriverManager.getConnection(DB_URL, "regina", "Listenthezebra2!"); // соединениесБД
            System.out.println("Соединение с СУБД выполнено.");
            statement = connection.createStatement();
            try {
                statement.executeQuery("CREATE TABLE HERO (id bigint generated always as identity," +
                        "name VARCHAR(20) NOT NULL," +
                        "class VARCHAR(20) NOT NULL," +
                        "attack INT NOT NULL," +
                        "defence INT NOT NULL," +
                        "hitPoints INT NOT NULL," +
                        "level INT NOT NULL," +
                        "experience INT NOT NULL," +
                        "coordinateX INT NOT NULL," +
                        "coordinateY INT NOT NULL," +
                        "photoProfile VARCHAR(100) NOT NULL," +
                        "leftSide VARCHAR(100) NOT NULL," +
                        "rightSide VARCHAR(100) NOT NULL," +
                        "back VARCHAR(100) NOT NULL," +
                        "weapon INT NOT NULL," +
                        "armor INT NOT NULL," +
                        "helmet INT NOT NULL)");
            } catch (Exception e) {
                System.out.println("База данных уже создана!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC драйвер для СУБД не найден!");
            this.error = true;
        } catch (SQLException e) {
            System.out.println("Ошибка SQL!");
            this.error = true;
        }
    }

    public void findHeroById(Integer id) {
        try {
            ResultSet hero = executeQuery("SELECT * from hero where id=" + id);
            while (hero.next()) {
                PlayController.createHero(hero.getString("name"), hero.getString("class"), Integer.parseInt(hero.getString("attack"))
                        , Integer.parseInt(hero.getString("defence")), Integer.parseInt(hero.getString("hitpoints")), hero.getString("photoprofile"),
                        hero.getString("leftside"), hero.getString("rightside"), hero.getString("back"),
                        Integer.parseInt(hero.getString("coordinatex")), Integer.parseInt(hero.getString("coordinatey")),
                        Integer.parseInt(hero.getString("experience")), Integer.parseInt(hero.getString("level")));
                loadArtifacts(Integer.parseInt(hero.getString("weapon")),
                        Integer.parseInt(hero.getString("armor")), Integer.parseInt(hero.getString("helmet")));
            }
        } catch (SQLException e) {
            System.out.println("Error loadHeroBD");
        }
    }

    public ResultSet executeQuery(String query) {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            this.error = true;
            return null;
        }
        return resultSet;
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
        executeQuery("INSERT INTO hero (name, class, attack, defence, hitPoints, level, experience," +
                "coordinateX, coordinateY, photoProfile, leftSide, rightSide, back, weapon, armor, helmet) VALUES (" +
                "'" + currentHero.getName() + "','" + currentHero.getHeroClass() + "', " + currentHero.getAttack() +
                "," + currentHero.getDefence() + " , " + currentHero.getHitPoints() + ", " + currentHero.getLevel() +
                "," + currentHero.getExperience() + ", " + currentHero.getX() + ", " + currentHero.getY() +
                "," + "'" + currentHero.getPhotoProfile() +
                "','" + currentHero.getLeftSide() + "', '" + currentHero.getRightSide() + "'," +
                "'" + currentHero.getBack() + "', " + weapon + ", " + armor + ", " + helm + ");");
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean isConnectionError() {
        return error;
    }
}
