package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class database {
    public static void database(String url, String user, String pass) {

        {
            try {
                Connection myConn = DriverManager.getConnection(url, user, pass);
                Statement stat = myConn.createStatement();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}

