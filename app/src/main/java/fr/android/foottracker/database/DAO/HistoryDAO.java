package fr.android.foottracker.database.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import fr.android.foottracker.database.DBConnection;
import fr.android.foottracker.models.Game;
import fr.android.foottracker.models.History;
import fr.android.foottracker.models.Team;

public class HistoryDAO implements DAO<History> {
    DBConnection dbConnection = new DBConnection();

    @Override
    public History get(int id) {

        History history= null;
        try {
            //RECUPERER TOUTES LES GAMES dans l'ordre decroissant
            //FOR EACH GAME rechercher les stat associées
            //retourner un objet History
            // {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM STATISTICS WHERE id = ?");
            preparedStatement.setInt(0,id); // remplace le 1er ? par la valeur 0
            ResultSet resultSet = preparedStatement.executeQuery(); // Execute la requete et affiche le resultat

            while (resultSet.next()){
                /*history = new History(
/                       resultSet.getInt("idTeam"),
                        resultSet.getString("name")
                );*/

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return history;
    }

    @Override
    public History get(String name, int id) {
        return null;
    }

    @Override
    public List<History> getAll() {
        return null;
    }

    @Override
    public int create(History object) {
        return 0;
    }

    @Override
    public History modify(History object) {
        return null;
    }
}
