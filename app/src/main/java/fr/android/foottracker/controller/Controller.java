/*
package fr.android.foottracker.controller;

import android.content.Context;
import fr.android.foottracker.models.AccesLocal;
import fr.android.foottracker.models.Team;

public class Controller {


    private static Controller instance = null;
    private static Team team;
    private static AccesLocal accesLocal;
    */
/*
    * Constructeur du controlleur
    * *//*

    private Controller() {
        System.out.println ("Controller()");
    }

    public static final Controller getInstance(Context context){
        if(Controller.instance == null){
            Controller.instance = new Controller();
            accesLocal = new AccesLocal(context);
            team = accesLocal.recupDernierTeam();
        }
        return null;
    }

    public Team getTeam(){
        return team;
    }

    public String getTeamName(){
        return team.getName();
    }

    public Team createTeam(Integer idTeam, String name){
        team = new Team(null, name);
        accesLocal.ajoutTeam(team);
        return team;
    }

}
*/
