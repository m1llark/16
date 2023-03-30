package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/database";
    private static final String USERNAME = "root1";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;


        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        return connection;
    }

}
