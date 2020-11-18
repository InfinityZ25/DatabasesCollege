/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package us.jcedeno.college;

import lombok.Getter;
import us.jcedeno.college.objects.Database;

public class DatabaseApp {
    private @Getter Database database = new Database("database.db");

    public String getGreeting() {
        return "Hello world.";
    }
    public static void main(String[] args) throws Exception {
        var app = new DatabaseApp();
        var query = "CREATE TABLE PEOPLE (ID INT PRIMARY KEY NOT NULL, FIRSTNAME TEXT NOT NULL, LASTNAME TEXT NOT NULL, AGE INT NOT NULL, SSN INTEGER NOT NULL, CC INTEGER NOT NULL )";
        var result = app.getDatabase().executeQuery(query);
        System.out.println("Result of promise " + result);
    }
}
