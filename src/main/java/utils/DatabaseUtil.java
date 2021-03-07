package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseUtil {

    private static final String url = "jdbc:mysql://localhost:3306/shuckle";

    private static String username;

    private static String password;

    public static void setParameters(String user, String pwd) {
        username = user;
        password = pwd;
    }

    public static Connection connectToDB() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
