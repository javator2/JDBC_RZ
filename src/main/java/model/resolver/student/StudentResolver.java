package model.resolver.student;

import com.google.common.base.Preconditions;
import jdbc.Database;
import model.Student;
import model.resolver.AbstractResolver;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentResolver extends AbstractResolver<Student> {
    private static final String TABLE_NAME = "student";
    private static final String COL_ID = "id";
    private static final String COL_FIRSTNAME = "name";
    private static final String COL_LASTNAME = "lastname";


    @Override
    public Student get(int id) {
        Student student = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            Connection connection = Database.getConnection();
            statement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + " = ? LIMIT 1");
            statement.setInt(1, id);
            result = statement.executeQuery();
            if(result.next()) {
                student = new Student(result.getInt(COL_ID), result.getString(COL_FIRSTNAME), result.getString(COL_LASTNAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(result != null) result.close();
                if(statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return student;
    }

    @Override
    public List<Student> get() {
        List<Student> studentsList = new ArrayList<>();
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = Database.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            while(result.next()) {
                studentsList.add(new Student(result.getInt(COL_ID), result.getString(COL_FIRSTNAME), result.getString(COL_LASTNAME)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(result != null) result.close();
                if(statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentsList;
    }

    @Override
    public boolean delete(int id) {
        boolean executeResult = false;
        PreparedStatement statement = null;
        try {
            statement = Database.getConnection().prepareStatement("DELETE FROM" + TABLE_NAME + " WHERE `" + COL_ID + "`=?");
            statement.setInt(1, id);
            executeResult = (statement.executeUpdate() > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return executeResult;
    }

    @Override
    public boolean insert(Map<String, String> arguments) {
        Preconditions.checkNotNull(arguments);
        int executeResult = 0;
        PreparedStatement statement = null;
        try {

            if(arguments.containsKey("firstname") && arguments.containsKey("lastname")) {
                statement = Database.getConnection().prepareStatement("INSERT INTO "+TABLE_NAME+" ("+COL_FIRSTNAME+", "+COL_LASTNAME+") VALUES(?,?);");
                statement.setString(1, arguments.get("firstname"));
                statement.setString(2, arguments.get("lastname"));
                executeResult = (statement.executeUpdate() > 0) ? 1 : 0;
            } else executeResult = -1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(executeResult == -1) throw new IllegalArgumentException("Brak klucza 'firstname' lub 'lastname'");

        return executeResult == 1;
    }

    @Override
    public boolean update(int id, Map<String, String> arguments) {
        boolean queryResult = false;
        List<String> paramsFromMap = new ArrayList<>();
        Preconditions.checkNotNull(arguments);
        StringBuilder query = new StringBuilder();
        for(Map.Entry<String, String> eMap : arguments.entrySet()) {
            switch (eMap.getKey()) {
                case "firstname":
                    query.append(COL_FIRSTNAME + "=?, ");
                    paramsFromMap.add(eMap.getValue());
                    break;
                case "lastname":
                    query.append(COL_LASTNAME + "=?, ");
                    paramsFromMap.add(eMap.getValue());
                    break;
            }
        }
        if(paramsFromMap.size() > 0) {
            query.delete(query.length() - 2, query.length());
            PreparedStatement statement = null;
            try {
                int index = 0;
                statement = Database.getConnection().prepareStatement("UPDATE " + TABLE_NAME + " SET " + query + " WHERE `"+COL_ID+"` = ?");

                for(String param : paramsFromMap) {
                    statement.setString(++ index, param);
                }
                statement.setInt(paramsFromMap.size() + 1, id);
                queryResult = (statement.executeUpdate() > 0);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(statement != null) statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return queryResult;
    }

    public void printStudentsList() {
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = Database.getConnection().createStatement();
            result = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            System.out.println("ID : First name : Last name");
            while(result.next()) {
                System.out.println(String.format("%d : %s : %s", result.getInt(COL_ID), result.getString(COL_FIRSTNAME), result.getString(COL_LASTNAME)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(result != null) result.close();
                if(statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
