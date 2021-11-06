package fr.android.foottracker.models;

import androidx.annotation.Nullable;

public class Statistics {

    private String teamName;
    private int idStats;
    private int idGame;
    private int nbButs;
    private int cartonsRouges;
    private int cartonsJaunes;
    private int penaltys;
    private int coupsFrancs;
    private int horsjeu;


    //Constructor
    public Statistics(int idStats, int idGame, String teamName, int nbButs, int cartonsRouges, int cartonsJaunes, int penaltys, int coupsFrancs, int horsjeu) {
        this.idStats = idStats;
        this.idGame = idGame;
        this.teamName = teamName;
        this.nbButs = nbButs;
        this.cartonsRouges = cartonsRouges;
        this.cartonsJaunes = cartonsJaunes;
        this.penaltys = penaltys;
        this.coupsFrancs = coupsFrancs;
        this.horsjeu = horsjeu;

    }

    public Integer getIdStats() {
        return idStats;
    }

    public void setIdStats(Integer idStats) {
        this.idStats = idStats;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getNbButs() {
        return nbButs;
    }

    public void setNbButs(int nbButs) {
        this.nbButs = nbButs;
    }

    public int getCartonsRouges() {
        return cartonsRouges;
    }

    public void setCartonsRouges(int cartonsRouges) {
        this.cartonsRouges = cartonsRouges;
    }

    public int getCartonsJaunes() {
        return cartonsJaunes;
    }

    public void setCartonsJaunes(int cartonsJaunes) {
        this.cartonsJaunes = cartonsJaunes;
    }

    public int getPenaltys() {
        return penaltys;
    }

    public void setPenaltys(int penaltys) {
        this.penaltys = penaltys;
    }

    public int getCoupsFrancs() {
        return coupsFrancs;
    }

    public void setCoupsFrancs(int coupsFrancs) {
        this.coupsFrancs = coupsFrancs;
    }

    public int getHorsjeu() {
        return horsjeu;
    }

    public void setHorsjeu(int horsjeu) {
        this.horsjeu = horsjeu;
    }


}
