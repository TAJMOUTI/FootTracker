package fr.android.foottracker.vue;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import fr.android.foottracker.R;
import fr.android.foottracker.models.History;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    TextView historyNameTeam1;
    TextView historyButsTeam1;
    TextView historyNameTeam2;
    TextView historyButsTeam2;
    TextView historyCartonJauneTeam1;
    TextView historyCartonJaune;
    TextView historyCartonJauneTeam2;
    TextView historyCartonRougeTeam1;
    TextView historyCartonRouge;
    TextView historyCartonRougeTeam2;
    TextView historyPenaltyTeam1;
    TextView historyPenalty;
    TextView historyPenaltyTeam2;
    TextView historyCoupFrancTeam1;
    TextView historyCoupFranc;
    TextView historyCoupFrancTeam2;
    TextView historyHorsJeuTeam1;
    TextView historyHorsJeu;
    TextView historyHorsJeuTeam2;

    
    public HistoryViewHolder(View itemView) {
        super(itemView);
        System.out.println("in history view holder");
        historyNameTeam1 = itemView.findViewById(R.id.textViewNameTeam1);
        historyButsTeam1 = itemView.findViewById(R.id.textViewButsTeam1);
        historyNameTeam2 = itemView.findViewById(R.id.textViewButsTeam2);
        historyButsTeam2 = itemView.findViewById(R.id.textViewNameTeam2);
        historyCartonJauneTeam1 = itemView.findViewById(R.id.numberCartonJauneTeam1);
        historyCartonJaune = itemView.findViewById(R.id.labelCartonJaune);
        historyCartonJauneTeam2 = itemView.findViewById(R.id.numberCartonJauneTeam2);
        historyCartonRougeTeam1 = itemView.findViewById(R.id.numberCartonRougeTeam1);
        historyCartonRouge = itemView.findViewById(R.id.labelCartonRouge);
        historyCartonRougeTeam2 = itemView.findViewById(R.id.numberCartonRougeTeam2);
        historyPenaltyTeam1 = itemView.findViewById(R.id.numberPenaltyTeam1);
        historyPenalty = itemView.findViewById(R.id.labelPenalty);
        historyPenaltyTeam2 = itemView.findViewById(R.id.numberPenaltyTeam2);
        historyCoupFrancTeam1 = itemView.findViewById(R.id.numberCoupFrancTeam1);
        historyCoupFranc = itemView.findViewById(R.id.labelCoupFranc);
        historyCoupFrancTeam2 = itemView.findViewById(R.id.numberCoupFrancTeam2);
        historyHorsJeuTeam1 = itemView.findViewById(R.id.numberHorsJeuTeam1);
        historyHorsJeu = itemView.findViewById(R.id.labelHorsJeu);
        historyHorsJeuTeam2 = itemView.findViewById(R.id.numberHorsJeuTeam2);
    }

    public void updateHistory(History history){
        System.out.println("update history");
        System.out.println(history.getTeamNameTeam1());
        System.out.println(history.getTeamNameTeam2());
        System.out.println(history.getNbButsTeam1());
    historyNameTeam1.setText(history.getTeamNameTeam1());
    historyNameTeam2.setText(history.getTeamNameTeam2());
    historyButsTeam1.setText(String.valueOf(history.getNbButsTeam1()));
    historyButsTeam2.setText(String.valueOf(history.getNbButsTeam2()));

    historyCartonJauneTeam1.setText(String.valueOf(history.getCartonsJaunesTeam1()));
    historyCartonJauneTeam2.setText(String.valueOf(history.getCartonsJaunesTeam2()));
    historyCartonRougeTeam1.setText(String.valueOf(history.getCartonsRougesTeam1()));
    historyCartonRougeTeam2.setText(String.valueOf(history.getCartonsRougesTeam2()));

    historyPenaltyTeam1.setText(String.valueOf(history.getPenaltysTeam1()));
    historyPenaltyTeam2.setText(String.valueOf(history.getPenaltysTeam2()));
    historyCoupFrancTeam1.setText(String.valueOf(history.getCoupsFrancsTeam1()));
    historyCoupFrancTeam2.setText(String.valueOf(history.getCoupsFrancsTeam2()));
    historyHorsJeuTeam1.setText(String.valueOf(history.getHorsjeuTeam1()));
    historyHorsJeuTeam2.setText(String.valueOf(history.getHorsjeuTeam2()));

    }
}
