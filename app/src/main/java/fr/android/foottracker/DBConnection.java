package fr.android.foottracker;

 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;

public class DBConnection {

    static String DB_URL = "jdbc:mysql://localhost:3306/Foot_Tracker";
    static String USER = "root";
    static String PASSWORD = "";

    public static Connection ConnectionToDataBase(){
        String msg = "";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            if (connection == null) {
                msg = "Connection goes wrong, there is no one";
            } else {

                System.out.println("The query was successfully done");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;

    }


}