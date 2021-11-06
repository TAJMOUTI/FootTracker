package fr.android.foottracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import fr.android.foottracker.TeamsFragment;
import fr.android.foottracker.models.Team;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "foot_tracker";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    private String createTeamTable = "create table team (idTeam INTEGER PRIMARY KEY, name TEXT NOT NULL)";
    private String createGameTable = "create table game (idGame INTEGER PRIMARY KEY, idTeam1 INTEGER, idTeam2 INTEGER, dateGame TEXT, localisation TEXT, FOREIGN KEY (idTeam1) REFERENCES Team(idTeam), FOREIGN KEY (idTeam2) REFERENCES Team(idTeam))";
    /**
     * Constructeur
     * @param context
     */

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("constructor MySQLiteOpenHelper");

    }

    /**
     * Si changement de BDD
     * @param sqLiteDatabase
     */

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        System.out.println("ON CREATE DB");
        try{
            sqLiteDatabase.execSQL(createTeamTable);
            sqLiteDatabase.execSQL(createGameTable);
        } catch (Exception e) {
            System.out.println("errrrrrrrror ON CREATE DB");
    }
        System.out.println("AFTER ON CREATE DB");
    }

    /**
     * Si changement de version
     * @param sqLiteDatabase
     * @param oldVersion ancienne version
     * @param newVersion nouvelle version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "team");
        onCreate(sqLiteDatabase);
    }

    /**
     * Créer une Team
     * @param team Team
     * @return boolean : true si l'ajout a fonctionné sinon false
     */
    public boolean createTeam(Team team){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idTeam", team.getIdTeam());
        contentValues.put("name", team.getName());
        long result = db.insert("Team", null, contentValues);
        if(result == -1){
            return false;
        } else return true;
    }


    /**
     * Récuperation des teams de la bdd
     * @return liste des teams
     */
    public ArrayList<Team> getTeamsList() {
        System.out.println("getTeamsList");
        ArrayList<Team> teamList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM team";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Parcours chacune des lignes recues et les ajoute à la liste teamList
        if (cursor.moveToFirst()) {
            do {
                int idTeam = Integer.parseInt(cursor.getString(0));
                String teamName = cursor.getString(1);
                Team team = new Team(idTeam, teamName);
                // Adding contact to list
                teamList.add(team);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return teamList;
    }

}
