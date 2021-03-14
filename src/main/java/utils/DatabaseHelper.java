package utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public final class DatabaseHelper {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);

    private static final String URL = "jdbc:mysql://localhost:3306/shuckle";

    private static String username;

    private static String password;

    private DatabaseHelper() { }

    public static void setParameters(String user, String pwd) {
        username = user;
        password = pwd;
        logger.info("Set parameters to connect to the database");
    }

    public static String getImageFromDB(String query) {
        String imageURL = null;
        try (Connection conn = DriverManager.getConnection(URL, username, password)) {
            logger.info("Connection started to the database");
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(query)) {
                    if (rs.next()) {
                        imageURL = rs.getString("url");
                    }
                }
            }
        } catch (SQLException throwables) {
            logger.error("Exception occured while trying to recover image from the database with message : {}", throwables.getMessage());
            imageURL = "An error has occurred";
        }
        return imageURL;
    }
}
