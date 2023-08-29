package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД


    private static final String USER = "root";
    private static final String PASS = "231618";
    private static final String URL = "jdbc:mysql://localhost:3306/katatask.1.1.4";
    private static final String DB_Driver = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection (){
        Connection connection = null;
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Connection Error!");
        }
        return  connection;
    }

}
