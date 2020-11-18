package us.jcedeno.college.objects;

import java.sql.Connection;
import java.sql.DriverManager;

import lombok.Getter;

public class Database {
    private @Getter Connection connection;

    public Database(String url) {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + url);

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
}
