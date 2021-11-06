package fr.android.foottracker.models;

public class Game {
    private int idGame;
    private String nameTeam1;
    private String nameTeam2;
    private String dateGame;
    private String localisation;

    public void setNameTeam1(String nameTeam1) {
        this.nameTeam1 = nameTeam1;
    }

    public void setNameTeam2(String nameTeam2) {
        this.nameTeam2 = nameTeam2;
    }

    public String getNameTeam1() {
        return nameTeam1;
    }

    public String getNameTeam2() {
        return nameTeam2;
    }
    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getDateGame() {
        return dateGame;
    }

    public void setDateGame(String dateGame) {
        this.dateGame = dateGame;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Game(int idGame, String nameTeam1, String nameTeam2, String dateGame, String localisation) {
        this.idGame = idGame;
        this.nameTeam1 = nameTeam1;
        this.nameTeam2 = nameTeam2;
        this.dateGame = dateGame;
        this.localisation = localisation;
    }
}
