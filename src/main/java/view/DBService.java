package view;

import java.sql.*;

public class DBService {

    private static Connection connection;
    private static Statement statement;
    private static final String DB_Driver = "org.h2.Driver";
    private boolean error;

    public DBService() {
        try {
            this.error = false;
//            String DB_URL = "jdbc:h2:mem:testb";
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
                        "face VARCHAR(100) NOT NULL," +
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

    public void executeUpdate(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Ошибка SQL Update!");
            this.error = true;
        }
    }

    public ResultSet executeQuery(String query) {
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            this.error = true;
            System.out.println("Ошибка SQL Query!");
            return null;
        }
        return resultSet;
    }

    public void close_connection() {
        try {
            connection.close();       // отключение от БД
            System.out.println("БД отключена");
        } catch (SQLException e) {
            System.out.println("Ошибка отсоединения БД");
            this.error = true;
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public boolean isConnectionError(){
        return error;
    }
}
