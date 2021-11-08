package fr.android.foottracker;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import fr.android.foottracker.database.DAO.GameDAO;
import fr.android.foottracker.database.DAO.StatisticsDAO;
import fr.android.foottracker.models.Game;
import fr.android.foottracker.models.History;
import fr.android.foottracker.models.Statistics;
import fr.android.foottracker.vue.HistoryAdapter;

public class HistoryFragment extends Fragment {

    RecyclerView historyListRecyclerView;
    HistoryAdapter historyAdapter;
    FragmentTransaction ft;
    List<Game> gameList = new ArrayList<Game>();
    List<History> historyList = new ArrayList<History>();

    public HistoryFragment() {
        // Required empty public constructor
    }

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        //Recuperer les données sur la page Statistics -> Faut les recup sur la page History
        super.onCreate(savedInstanceState);
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("ON CREATE HISTORY FRAGMENT");
        View view = inflater.inflate(R.layout.fragment_history,container, false);
        ft = getActivity().getSupportFragmentManager().beginTransaction();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        historyListRecyclerView = view.findViewById(R.id.historyList);
        try {
            System.out.println("get games");
            //1 - Récuperer toutes les games existantes
            Callable<List<Game>> callableGame = () -> new GameDAO().getAll();
            gameList = callableGame.call();

            // 2 - Pour chaque game, on crée un historique
            for(Game game : gameList) {
                System.out.println("in for each");

                //Recuperation des stats pour la team 1
                System.out.println("team1 "+game.getNameTeam1());
                Callable<Statistics> callableStat1 =  () -> new StatisticsDAO().get(game.getNameTeam1(), game.getIdGame());
                Statistics stat1 = callableStat1.call();

                System.out.println("stat1.getTeamName()");
                System.out.println(stat1.getTeamName());
                //Recuperation des stats pour la team 2
                System.out.println("team2 "+game.getNameTeam2());
                Callable<Statistics> callableStat2 =  () -> new StatisticsDAO().get(game.getNameTeam2(), game.getIdGame());
                Statistics stat2 = callableStat2.call();

                System.out.println("create history 79");

                //Création d'un historique pour la game
                History historyGame = new History(stat1.getTeamName(), stat1.getNbButs(), stat1.getCartonsRouges(), stat1.getCartonsJaunes(), stat1.getPenaltys(), stat1.getCoupsFrancs(), stat1.getHorsjeu(), stat2.getTeamName(), stat2.getNbButs(), stat2.getCartonsRouges(), stat2.getCartonsJaunes(), stat2.getPenaltys(), stat2.getCoupsFrancs(), stat2.getHorsjeu());
                historyList.add(historyGame);
            }

            //Création d'un historique pour la game
            //History historyGame = new History("stat1.getTeamName()", 3, 5, 5, 3, 2, 1," stat2.getTeamName()", 4, 6, 7, 1, 3, 3);
            //historyList.add(historyGame);

            // 3 - On affiche l'historique grace à l'Adapter et le View Holder
            LinearLayoutManager linearLayout_displayHistory = new LinearLayoutManager(getActivity());
            linearLayout_displayHistory.setOrientation(LinearLayoutManager.VERTICAL);

            System.out.println("before adapter");
            System.out.println(historyList.size());
            historyAdapter = new HistoryAdapter(historyList);

            System.out.println("set adapter");

            historyListRecyclerView.setAdapter(historyAdapter);
            historyListRecyclerView.setLayoutManager(linearLayout_displayHistory);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
