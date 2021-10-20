package fr.android.foottracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TeamsFragment extends Fragment {

    EditText teamName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_teams,container, false);


    }

    public void createNewTeam(View view){

        teamName = (EditText) getView().findViewById(R.id.editTeamName); //Recupère la view du champs teamName grace à son id


        String teamNameText = teamName.getText().toString(); // recupère le contenu du champs teamName
        //TODO: SQL Request

    }
}
