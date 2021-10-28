package fr.android.foottracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.Callable;

import fr.android.foottracker.TeamsRecyclerView.TeamAdapter;
import fr.android.foottracker.database.DAO.TeamDAO;
import fr.android.foottracker.models.Team;

public class TeamsFragment extends Fragment {

    EditText teamName;

    RecyclerView teamListRecyclerView;
    TeamAdapter teamsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teams,container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        teamListRecyclerView = view.findViewById(R.id.teamList);

        try{

            Callable<List<Team>> callable = () -> new TeamDAO().getAll();
            List<Team> teamList = callable.call();

            System.out.println("dgsdsdsd: " + teamList);
            LinearLayoutManager linearLayout_affichteam = new LinearLayoutManager(getActivity());
            linearLayout_affichteam.setOrientation(LinearLayoutManager.VERTICAL);

            teamsAdapter = new TeamAdapter(teamList);

            teamListRecyclerView.setAdapter(teamsAdapter);
            teamListRecyclerView.setLayoutManager(linearLayout_affichteam);

        }catch(Exception e){
            e.printStackTrace();
        }

    }



    public void createNewTeam(View view){

        teamName = (EditText) getView().findViewById(R.id.editTeamName); //Recupère la view du champs teamName grace à son id


        String teamNameText = teamName.getText().toString(); // recupère le contenu du champs teamName
        //TODO: SQL Request

    }
}
