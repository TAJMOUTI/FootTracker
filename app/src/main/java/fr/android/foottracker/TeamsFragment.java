package fr.android.foottracker;

import android.os.Bundle;
import android.service.controls.Control;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import fr.android.foottracker.database.MySQLiteOpenHelper;
import fr.android.foottracker.vue.TeamAdapter;
import fr.android.foottracker.database.DAO.TeamDAO;
import fr.android.foottracker.models.Team;

public class TeamsFragment extends Fragment {

    EditText teamName;
    RecyclerView teamListRecyclerView;
    TeamAdapter teamsAdapter;
    FragmentTransaction ft;
    List<Team> teamList;
    private MySQLiteOpenHelper myDatabaseHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams,container, false);
        Button button = (Button) view.findViewById(R.id.createTeamButton);
        button.setOnClickListener(this::createTeam);
        ft = getActivity().getSupportFragmentManager().beginTransaction();
        return view;
    }

    public void createTeam(View view){
        int insertIndex = teamList.size();
        teamName = (EditText) getView().findViewById(R.id.editTeamName); //Recupère la view du champs teamName grace à son id
        String teamNameText = teamName.getText().toString(); // recupère le contenu du champs teamName

        if(teamNameText.length() == 0){
            Toast.makeText(getActivity().getApplicationContext(), "Team name shouldn't be empty", Toast.LENGTH_LONG).show();
        } else {
            Team teamToCreate = new Team(0, teamNameText);

            getActivity().getApplicationContext().deleteDatabase("foot_tracker.db");

            myDatabaseHelper = new MySQLiteOpenHelper(getActivity()); //SQLITE
        myDatabaseHelper.createTeam(teamToCreate);
        //myDatabaseHelper.close();

        new TeamDAO().create(teamToCreate); //MYSQL

        teamName.setText("");

        teamList.add(insertIndex, teamToCreate);
        teamsAdapter.notifyItemInserted(insertIndex);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        teamListRecyclerView = view.findViewById(R.id.teamList);
        try {
            Callable<List<Team>> callable = () -> new TeamDAO().getAll();
            teamList = callable.call();

            System.out.println("dgsdsdsd: " + teamList);
            LinearLayoutManager linearLayout_affichteam = new LinearLayoutManager(getActivity());
            linearLayout_affichteam.setOrientation(LinearLayoutManager.VERTICAL);

            teamsAdapter = new TeamAdapter(teamList);

            teamListRecyclerView.setAdapter(teamsAdapter);
            teamListRecyclerView.setLayoutManager(linearLayout_affichteam);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
