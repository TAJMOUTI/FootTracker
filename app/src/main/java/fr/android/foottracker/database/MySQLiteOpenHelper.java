package fr.android.foottracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.android.foottracker.TeamsFragment;
import fr.android.foottracker.models.Game;
import fr.android.foottracker.models.Statistics;
import fr.android.foottracker.models.Team;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "foot_tracker";
    private static final int DATABASE_VERSION = 3;

    protected String createTeamTable = "create table team (idTeam INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)";
    protected String createGameTable = "create table game (idGame INTEGER PRIMARY KEY AUTOINCREMENT, teamName1 TEXT, teamName2 TEXT, dateGame TEXT, localisation TEXT)";
    protected String createStatisticsTable = "create table statistics (idStats INTEGER PRIMARY KEY AUTOINCREMENT, idGame INTEGER, teamName TEXT, nbButs INTEGER, cartonsRouges INTEGER, cartonsJaunes INTEGER, penaltys INTEGER, coupsFrancs INTEGER, horsjeu INTEGER)";
    /**
     * Constructeur
     * @param context
     */

    public MySQLiteOpenHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        System.out.println("constructor MySQLiteOpenHelper");
        getWritableDatabase();

    }

    /**
     * Si changement de BDD
     * @param sqLiteDatabase
     */

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(createTeamTable);
            sqLiteDatabase.execSQL(createGameTable);
            sqLiteDatabase.execSQL(createStatisticsTable);
        } catch (Exception e) {
            System.out.println("Error while trying to create database");
            System.out.println(e);
        }
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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "game");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + "statistics");
        onCreate(sqLiteDatabase);
    }

    /**
     * Créer une Team
     * @return boolean : true si l'ajout a fonctionné sinon false
     */
    public boolean createTeam(Team team){
        SQLiteDatabase db = getWritableDatabase();
        System.out.println("create team in sqlite open helper");
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("idTeam");
        contentValues.put("name", team.getName());
        long result = db.insert("Team", null, contentValues);
        if(result == -1){
            return false;
        } else return true;
    }

    public boolean createGame(Game game){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.putNull("idGame");
        contentValues.put("teamName1", game.getNameTeam1());
        contentValues.put("teamName2", game.getNameTeam2());
        contentValues.put("dateGame", game.getDateGame());
        contentValues.put("localisation", game.getLocalisation());
        long result = db.insert("game", null, contentValues);
        if(result == -1){
            return false;
        } else return true;
    }

    public boolean createStatistics(Statistics statistics){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("IdStats");
        contentValues.put("IdGame", statistics.getIdGame());
        contentValues.put("TeamName", statistics.getTeamName());
        contentValues.put("NbButs", statistics.getNbButs());
        contentValues.put("CartonsRouges", statistics.getCartonsRouges());
        contentValues.put("CartonsJaunes", statistics.getCartonsJaunes());
        contentValues.put("Penaltys", statistics.getPenaltys());
        contentValues.put("CoupsFrancs", statistics.getCoupsFrancs());
        contentValues.put("Horsjeu", statistics.getHorsjeu());

        long result = db.insert("statistics", null, contentValues);
        if(result == -1){
            return false;
        } else return true;
    }


    /**
     * Récuperation des teams de la bdd
     * @return liste des teams
     */
    public ArrayList<Team> getTeamsList() {
        SQLiteDatabase db = getWritableDatabase();

        System.out.println("getTeamsList");
        ArrayList<Team> teamList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM team";
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Parcours chacune des lignes recues et les ajoute à la liste teamList
        if (cursor.moveToFirst()) {
            do {
                int idTeam = Integer.parseInt(cursor.getString(0));
                String teamName = cursor.getString(1);
                Team team = new Team(idTeam, teamName);
                // Adding team to list
                teamList.add(team);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return teams list
        return teamList;
    }

    public ArrayList<Game> getGamesList() {
        ArrayList<Game> gamesList = new ArrayList<Game>();
        String selectQuery = "SELECT * FROM game";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Parcours chacune des lignes recues et les ajoute à la liste teamList
        if (cursor.moveToFirst()) {
            do {
                int idGame = Integer.parseInt(cursor.getString(0));
                String teamName1 = cursor.getString(1);
                String teamName2 = cursor.getString(2);
                String date = cursor.getString(3);
                String localisation = cursor.getString(4);
                Game game = new Game(idGame, teamName1, teamName2, date, localisation);
                // Adding contact to list
                gamesList.add(game);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return gamesList;
    }


    public ArrayList<Statistics> getStatisticssList() {
        ArrayList<Statistics> statisticsList = new ArrayList<Statistics>();
        String selectQuery = "SELECT * FROM statistics";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Parcours chacune des lignes recues et les ajoute à la liste teamList
        if (cursor.moveToFirst()) {
            do {
                int idStat = Integer.parseInt(cursor.getString(0));
                int idGame = Integer.parseInt(cursor.getString(1));
                String teamName = cursor.getString(2);
                int nbButs = Integer.parseInt(cursor.getString(3));
                int nbCartonRouge = Integer.parseInt(cursor.getString(4));
                int nbCartonJaune = Integer.parseInt(cursor.getString(5));
                int nbPenalty = Integer.parseInt(cursor.getString(6));
                int nbCoupFranc = Integer.parseInt(cursor.getString(7));
                int nbHorsJeu = Integer.parseInt(cursor.getString(8));

                String teamName2 = cursor.getString(2);
                String date = cursor.getString(3);
                String localisation = cursor.getString(4);
                Statistics statistics = new Statistics(idStat, idGame, teamName, nbButs, nbCartonRouge, nbCartonJaune, nbPenalty, nbCoupFranc, nbHorsJeu);
                // Adding contact to list
                statisticsList.add(statistics);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // return contact list
        return statisticsList;
    }

}
