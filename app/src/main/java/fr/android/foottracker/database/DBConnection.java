package fr.android.foottracker.database;

 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.util.concurrent.Callable;


public class DBConnection {

    private Connection connection;

    static String DB_URL = "jdbc:mysql://127.0.0.1:8080/foot_tracker";
    static String USER = "root";
    static String PASSWORD = "";

    public Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Callable<Connection> getConnection = () -> {

                Connection c = null;

                try {
                    c =  DriverManager.getConnection(DB_URL, USER, PASSWORD);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return c;

            };

            connection = getConnection.call();
        }catch (Exception e){
            e.printStackTrace();
        }

        return connection;

    }


}
