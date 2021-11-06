package fr.android.foottracker;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import fr.android.foottracker.database.DAO.GameDAO;
import fr.android.foottracker.database.DAO.TeamDAO;
import fr.android.foottracker.models.Game;
import fr.android.foottracker.models.Team;

public class NewMatchFragment extends Fragment {
    Spinner spinnerTeam1;
    Spinner spinnerTeam2;
    String teamName1;
    String teamName2;
    String date;
    String localisation;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newmatch, container, false);
        initializeComponents(view);
        Button button = (Button) view.findViewById(R.id.createMatchButton);
        button.setOnClickListener(this::createGame);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createGame(View view){
        date = "29-12-1997";
        localisation = "6 rue jytrstyd";
        Game gameToCreate = new Game(0, teamName1, teamName2, date, localisation);
//        System.out.println("before creating a game");
        int newGame = new GameDAO().create(gameToCreate); //Save Game in database and return id of created game

        //Open Statistics Fragment and pass arguments
        Bundle bundle = new Bundle();
        bundle.putInt("idGame", newGame);
        bundle.putString("teamName1", teamName1);
        bundle.putString("teamName2", teamName2);

        StatisticsFragment fragmentStatistics = new StatisticsFragment();
        fragmentStatistics.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragmentStatistics);
        fragmentTransaction.commit();
    }

    private void initializeComponents(View view) {
         spinnerTeam1 = (Spinner) view.findViewById(R.id.spinnerTeam1);
         spinnerTeam2 = (Spinner) view.findViewById(R.id.spinnerTeam2);

        try{
            Callable<List<Team>> callable = () -> new TeamDAO().getAll();
            List<Team> teamList = callable.call();
            ArrayList<String> teamNameList = new ArrayList<>();

            for (Team t : teamList) {
                teamNameList.add(t.getName());
                System.out.println(t.getName());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, teamNameList);

            // Layout for All ROWs of Spinner.  (Optional for ArrayAdapter).
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTeam1.setAdapter(adapter);
            spinnerTeam2.setAdapter(adapter);
            spinnerTeam1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view,
                                           int position, long id) {
                    Object item = adapterView.getItemAtPosition(position);
                    if (item != null) {
                        teamName1 = item.toString();
                    }
                }

               @Override
               public void onNothingSelected(AdapterView<?> adapterView) {

               }

            });
            spinnerTeam2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view,
                                           int position, long id) {
                    Object item = adapterView.getItemAtPosition(position);
                    if (item != null) {
                        teamName2 = item.toString();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }

            });


        } catch (Exception e) {
                e.printStackTrace();
            }

    }


}

