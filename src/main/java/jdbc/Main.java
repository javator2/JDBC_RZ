package jdbc;

import java.sql.*;

public class Main {

    public static final String URL = "jdbc:mysql://localhost/ksiegarnia?useSSL=false&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String password = Pass.PASS;

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //rejestracja sterownika / wczytanie do pamięci
            connection = DriverManager.getConnection(URL, userName, password);
            statement = connection.createStatement();
            String sql = "select * from books";
            ResultSet resultSet = statement.executeQuery(sql);

            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
