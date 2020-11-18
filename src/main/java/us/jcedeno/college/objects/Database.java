package us.jcedeno.college.objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lombok.Getter;

public class Database {
    private @Getter Connection connection;

    public Database(String url) {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + url);
            // Create db table if needed.
            executeQuery(
                    "CREATE TABLE IF NOT EXISTS PEOPLE (id INTEGER PRIMARY KEY AUTOINCREMENT, first TEXT, last TEXT, age INT, ssn INTEGER, cc INTEGER)");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public boolean executeQuery(String sql) {
        try {
            // Swiftly check for null. Perhaps use assert?
            if (connection != null && !connection.isClosed())
                return connection.createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // If anything fails, return false;
        return false;
    }

    //POST
    public void insertPerson(Person person) {
        var query = "INSERT INTO PEOPLE (FIRST, LAST, AGE, SSN, CC) VALUES " + person.toValues();
        executeQuery(query);
    }
    //GET
    public Person selectPerson(String id) {
        try {
            var rs = connection.createStatement().executeQuery("SELECT * FROM PEOPLE WHERE id='" + id + "'");
            while (rs.next()) {
                return fromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Throw generic exception and deal with it later.
    public ArrayList<Person> findAllPeople() throws Exception {
        var list = new ArrayList<Person>();
        var promise = connection.createStatement().executeQuery("SELECT * FROM PEOPLE");

        while (promise.next())
            list.add(fromResultSet(promise));

        return list;
    }

    // Recursive method to deserialize person.
    private Person fromResultSet(ResultSet res) throws SQLException {
        return Person.of(res.getString("first"), res.getString("last"), res.getInt("age"), res.getLong("ssn"),
                res.getLong("cc"));
    }
}
