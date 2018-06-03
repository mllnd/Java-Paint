package xyz.mllnd.javapaint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public final class PaintingsManager {
    private final static String SCHEMA = String.join("", Arrays.asList(
            "create table if not exists paintings(",
            ");"
    ));

    Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:java-paint");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void initSchema() {
        Connection connection = getConnection();

        try {
            connection.prepareStatement(SCHEMA).execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
