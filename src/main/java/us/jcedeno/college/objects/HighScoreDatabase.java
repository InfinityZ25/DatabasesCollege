package us.jcedeno.college.objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashSet;
import java.util.Optional;

import lombok.Getter;

public class HighScoreDatabase {
    private @Getter Connection connection;

    public HighScoreDatabase(String url) {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + url);
            // Create db table if needed.
            executeQuery("create table if not exists scores(user TEXT primary key, score INT)");

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
        } catch (Exception ignore) {
        }
        // If anything fails, return false;
        return false;
    }

    // POST
    public boolean insertUser(User user) {
        return executeQuery("insert into scores (user, score) values " + user);
    }

    // DELETE
    public boolean deleteUser(String username) {
        return executeQuery("delete from scores where user='" + username + "'");
    }

    // GET
    public Optional<User> selectUser(String username) {
        try {
            var rs = connection.createStatement().executeQuery("select * from scores where user='" + username + "'");
            while (rs.next()) {
                return Optional.of(User.of(username, rs.getInt("score")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(null);
    }

    // Throw generic exception and deal with it later.
    public HashSet<User> findAllPeople() throws Exception {
        var set = new HashSet<User>();

        var promise = connection.createStatement().executeQuery("select * from scores");
        while (promise.next())
            set.add(User.of(promise.getString("user"), promise.getInt("score")));

        return set;
    }

}
