package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
   public static Connection getConnection() {
    Connection c = null;
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        String url = "jdbc:mysql://localhost:3306/quanlybanhang"; 
        String user = "root";
        String pass = "4437"; 
        c = DriverManager.getConnection(url, user, pass);
    } catch (Exception e) {

    }
    return c;
}

public static void closeConnection(Connection c) {
        try {
            if (c!=null) {
                c.close();
            }
        } catch (Exception e) {
        }
    }
}
