package fr.android.foottracker;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class Functions {

    public static String DB_URL = "jdbc:mysql://localhost:3306/Foot_Tracker?serverTimezone=UTC";
    public static String USER = "root";
    public static String PASSWORD = "";

    public static Statement connectionSQLBDD(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("connected");
            Statement st = conn.createStatement();
            return st;
        } catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    };
}
