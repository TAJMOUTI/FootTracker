package fr.android.foottracker.database.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.android.foottracker.database.DBConnection;
import fr.android.foottracker.models.Team;

public class TeamDAO implements DAO<Team>{

    DBConnection dbConnection = new DBConnection();

    @Override
    public Team get(int id) {

        Team team = null;

        try {
            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM TEAM WHERE id = ?");
            preparedStatement.setInt(0,id); // remplace le 1er ? par la valeur 0
            ResultSet resultSet = preparedStatement.executeQuery(); // Execute la requete et affiche le resultat

            while (resultSet.next()){
            team = new Team(
                resultSet.getInt("idTeam"),
                resultSet.getString("name")
            );

        }

        }catch (Exception e){
            e.printStackTrace();
        }
        return team;
    }

    @Override
    public List<Team> getAll() {
        List<Team> teamList = new ArrayList<>();

        try {

            System.out.println(dbConnection.getConnection());

            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM TEAM ");
            ResultSet resultSet = preparedStatement.executeQuery(); // Execute la requete et affiche le resultat

            while (resultSet.next()){
                teamList.add(new Team(
                        resultSet.getInt("idTeam"),
                        resultSet.getString("name")
                )
            );

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(teamList);
        return teamList;
    }

    @Override
    public Team insert(Team object) {
        return null;
    }

    @Override
    public Team modify(Team object) {
        return null;
    }
}
