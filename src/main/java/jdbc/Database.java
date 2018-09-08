package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost";
    private static final String USERNAME = "root";
    private static final String PASSWORD = Pass.PASS;
    private static final String PORT = "3306";
    private static final String DATABASE = "ksiegarnia";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String PARAMS = "useSSL=false&seLegacyDatetimeCode=false&serverTimezone=UTC";
    private static Connection connection = null;

    private static String getFormattedURL() {
        return URL + ":" + PORT + "/" + DATABASE + "?" + PARAMS;
    }

    private static void loadDriver() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean loadConnection() {
        try {
            connection = DriverManager.getConnection(getFormattedURL(), USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection != null;
    }

    public static Connection getConnection() {
        try {
            if(connection == null || !connection.isValid(1000)) {
                connection = null;
                loadDriver();
                loadConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }
}
