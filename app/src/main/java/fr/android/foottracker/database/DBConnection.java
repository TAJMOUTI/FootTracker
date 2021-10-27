package fr.android.foottracker;

 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;



public class DBConnection {

    private Connection connection;

    static String DB_URL = "jdbc:mysql://10.0.2.2:3306/Foot_Tracker";
    static String USER = "root";
    static String PASSWORD = "";

    public Connection ConnectionToDataBase(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }

        new Thread(() -> {
            String msg = "Inside Thread";

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

        }).start();




        return connection;

    }


}