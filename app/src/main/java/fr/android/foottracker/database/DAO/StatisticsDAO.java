package fr.android.foottracker.database.DAO;

import com.mysql.jdbc.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.android.foottracker.database.DBConnection;
import fr.android.foottracker.models.Statistics;

public class StatisticsDAO implements DAO<Statistics> {
    DBConnection dbConnection = new DBConnection();

    @Override
    public Statistics get(int id) {
        return null;
    }

    public Statistics get(String teamName, int idGame) {
        Statistics statistics = null;

        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM Statistics WHERE idGame = ? AND teamName = ?");
            preparedStatement.setInt(0, idGame); //
            preparedStatement.setString(1, teamName);
            ResultSet resultSet = preparedStatement.executeQuery(); // Execute la requete et affiche le resultat

            while (resultSet.next()){
                statistics = new Statistics(
                        resultSet.getInt("idStats"),
                        resultSet.getInt("idGame"),
                        resultSet.getString("teamName"),
                        resultSet.getInt("nbButs"),
                        resultSet.getInt("cartonsRouges"),
                        resultSet.getInt("cartonsJaunes"),
                        resultSet.getInt("penaltys"),
                        resultSet.getInt("coupsFrancs"),
                        resultSet.getInt("horsjeu")
                );

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return statistics;
    }

    @Override
    public List<Statistics> getAll() {
        return null;
    }

    @Override
    public int create(Statistics statistics) {
        PreparedStatement preparedStatement;
        String createTeamQuery = "INSERT INTO Statistics(idGame, teamName, nbButs, cartonsRouges, cartonsJaunes, penaltys, coupsFrancs, horsjeu) VALUES (?,?,?,?,?,?,?,?)";
        int generatedKeys = -1;

        try {
            preparedStatement = dbConnection.getConnection().prepareStatement(createTeamQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt( 1, statistics.getIdGame());
            preparedStatement.setString( 2, statistics.getTeamName());
            preparedStatement.setInt( 3, statistics.getNbButs());
            preparedStatement.setInt( 4, statistics.getCartonsRouges());
            preparedStatement.setInt( 5, statistics.getCartonsJaunes());
            preparedStatement.setInt( 6, statistics.getPenaltys());
            preparedStatement.setInt( 7, statistics.getCoupsFrancs());
            preparedStatement.setInt( 8, statistics.getHorsjeu());

            int gameCreated = preparedStatement.executeUpdate();
            //Recupere l'id de la game crée
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                generatedKeys = rs.getInt(1);
            }

            if(gameCreated != 0){
                System.out.println("game has been successfully added");
                return generatedKeys;
            }
        } catch (SQLException pbSQL) {
            pbSQL.printStackTrace() ;
        }
        return generatedKeys;
    }


    @Override
    public Statistics modify(Statistics object) {
        return null;
    }
}
