/*
package fr.android.foottracker.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import fr.android.foottracker.database.MySQLiteOpenHelper;

public class AccesLocal {

    // propriétés
    private String nomBase = "foot_tracker";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    public AccesLocal(Context context) {
        accesBD = new MySQLiteOpenHelper(context);
    }

    */
/**
     * ajout d'une team dans la bdd
     * @param team
     *//*


    public void ajoutTeam(Team team){
        bd = accesBD.getWritableDatabase();
        String req = "insert into team (idTeam, name) values("+team.getIdTeam()+",\""+team.getName()+"\")";
        bd.execSQL(req);
    }

    */
/**
     * ajout d'une game dans la bdd
     * @param game
     *//*


    */
/**
    public void ajoutGame(Game game){
        bd = accesBD.getWritableDatabase();
        String req = "insert into game (idGame, idTeam1, idTeam2, dateGame, localisation) values ("+game.getIdGame()+","+game.getIdTeam1()+","+game.getIdTeam2()+",\""+game.getDateGame()+"\",\""+game.getLocalisation()+"\")";
        bd.execSQL(req);
    }*//*


    */
/**
     * Récuperation de la derniere team de la bdd
     * @return
     *//*

    public Team recupDernierTeam(){
        bd = accesBD.getReadableDatabase();
        Team team = null;
        String req = "select * from team";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Integer idTeam = curseur.getInt(0);
             String name = curseur.getString(1);
             team = new Team(idTeam, name);
        }
        curseur.close();
        return team;
    }

    */
/**
     * Récuperation de la derniere game de la bdd
     * @return
     *//*


    */
/**
    public Team recupDernierGame(){
        bd = accesBD.getReadableDatabase();
        Team team = null;
        String req = "select * from game";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Date dateGame = new Date();

        }
    }*//*


}
*/
