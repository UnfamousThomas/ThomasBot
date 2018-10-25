package utils;
import java.sql.*;

public class database {
    public database(){
        String dbUrl="jdbc:mysql://localhost:3306/discordbot";
        String user = "Administrator";
        String pass = "koolkool123";
    try {

        Connection myConn = DriverManager.getConnection(dbUrl, user, pass);

        Statement stat = myConn.createStatement();

        ResultSet myRs = stat.executeQuery("SELECT * FROM suggestions");

        while ((myRs.next())) {
            System.out.println(myRs.getString("User") + ", ");
            System.out.println(myRs.getInt("ID"));
        }
        int rows = stat.executeUpdate("insert into suggestions " + "(Username, Discriminator, ID, Time) " +
                "values " +
                "('SecondSword1738', '5965'), 2, 120)");

        while ((myRs.next())) {
            System.out.println(myRs.getString("User") + ", ");
            System.out.println(myRs.getInt("ID"));
        }
    } catch(Exception e) {
        e.printStackTrace();
    }
}}
