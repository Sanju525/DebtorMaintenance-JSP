package project.bfour.debtormaintenance.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConn {
    static Connection c;

    public static Connection getCon () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (c == null) {
                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root",
                        "12345678");
//                c = DriverManager.getConnection("jdbc:mysql://192.168.1.19:3306/project1", "sanju",
//                        "server@mysql");
                System.out.println("connected " + c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    }
}
