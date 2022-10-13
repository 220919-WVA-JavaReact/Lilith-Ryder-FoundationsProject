package com.revature.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// this class provides SQL database connectivity for calling and persisting data within the application.
public class ConnUtil {

        private static Connection conn = null;

        private ConnUtil() {

        }

        public static Connection getConn(){

                try {
                        if (conn != null && !conn.isClosed()) {
                                System.out.println("Use a previously made connection");
                                return conn;
                                }
                } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }
                String url = System.getProperty("DBurl");
                String username = System.getProperty("DBusername");
                String password = System.getProperty("DBpassword");


                try {
                        Class.forName("org.postgresql.Driver");
                        conn = DriverManager.getConnection(url, username, password);
                        System.out.println("Established Connection to Database!");
                } catch (SQLException | ClassNotFoundException e) {
                        System.out.println("Could not establish connection");
                        e.printStackTrace();
                }
                return conn;
        }
}