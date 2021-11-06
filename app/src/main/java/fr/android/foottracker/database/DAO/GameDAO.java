package fr.android.foottracker.database.DAO;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.mysql.jdbc.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.android.foottracker.database.DBConnection;
import fr.android.foottracker.models.Game;

public class GameDAO implements DAO<Game> {

    DBConnection dbConnection = new DBConnection();

    @Override
    public Game get(int id) {
        return null;
    }

    @Override
    public Game get(String name, int id) {
        return null;
    }

    @Override
    public List<Game> getAll() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int create(Game game) {

        PreparedStatement preparedStatement;
        String createGameQuery = "INSERT INTO Game(nameTeam1, nameTeam2, dateGame, localisation) VALUES (?,?,?,?)";
        int generatedKey = -1;
        try {
            preparedStatement = dbConnection.getConnection().prepareStatement(createGameQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString( 1, game.getNameTeam1());
            preparedStatement.setString( 2, game.getNameTeam2());
            preparedStatement.setDate( 3,  new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setString( 4, game.getLocalisation());
            int gameCreated = preparedStatement.executeUpdate();

            //Recupere l'id de la game crée
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            if(gameCreated != 0){
                System.out.println("game has been successfully added");
                return generatedKey;
            }
        } catch (SQLException pbSQL) {
            pbSQL.printStackTrace() ;
        }
        return generatedKey;
    }

    @Override
    public Game modify(Game object) {
        return null;
    }



}
