package fr.android.foottracker.database.DAO;


import com.mysql.jdbc.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public Team get(String name, int id) {
        return null;
    }

    @Override
    public List<Team> getAll() {
        List<Team> teamList = new ArrayList<>();

        try {

            System.out.println(dbConnection.getConnection());

            PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM TEAM ORDER BY name ASC ");
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
    public int create(Team team) {
        System.out.println("DAO CREATE TEAM");

        PreparedStatement preparedStatement;
        String createTeamQuery = "INSERT INTO team(Name) VALUES (?)";
        int generatedKeys = -1;
        try {
            preparedStatement = dbConnection.getConnection().prepareStatement(createTeamQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString( 1, team.getName());

            int teamCreated = preparedStatement.executeUpdate();
            //Recupere l'id de la game crée
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                generatedKeys = rs.getInt(1);
            }
            if(teamCreated != 0){
                System.out.println("Team has been successfully added");
                return generatedKeys;
            }
        } catch (SQLException pbSQL) {
            pbSQL.printStackTrace() ;
        }
        return generatedKeys;
    }

    @Override
    public Team modify(Team object) {
        return null;
    }
}
