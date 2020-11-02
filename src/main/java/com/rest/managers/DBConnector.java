package com.rest.managers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
    static Connection connection = null;

    public static Connection getConnection() throws IOException {
        try {
            Properties props = new Properties();
            props.load(DBConnector.class.getClassLoader()
                    .getResourceAsStream("database.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException x) {
            System.out.println("Connection Failed");
            x.printStackTrace();
        }

        return connection;
    }
}
