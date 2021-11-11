package fr.android.foottracker;

import android.graphics.Camera;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Pattern;

import fr.android.foottracker.database.DAO.GameDAO;
import fr.android.foottracker.database.DAO.StatisticsDAO;
import fr.android.foottracker.database.MySQLiteOpenHelper;
import fr.android.foottracker.models.Game;
import fr.android.foottracker.models.Statistics;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class StatisticsFragment extends Fragment {
    private MySQLiteOpenHelper myDatabaseHelper;
    private int idGame;
    private String team1;
    private String team2;

    private int nbButsTeam1 = 0;
    private int nbButsTeam2 = 0;

    private int nbCartonRougeTeam1 = 0;
    private int nbCartonRougeTeam2 = 0;

    private int nbCartonJauneTeam1 = 0;
    private int nbCartonJauneTeam2 = 0;

    private int nbPenaltyTeam1 = 0;
    private int nbPenaltyTeam2 = 0;

    private int nbCoupFrancTeam1 = 0;
    private int nbCoupFrancTeam2 = 0;

    private int nbHorsJeuTeam1 = 0;
    private int nbHorsJeuTeam2 = 0;


    TextView teamName1;
    TextView teamName2;

    Button addGoalTeam1Button;
    Button addGoalTeam2Button;

    Button addCartonRougeTeam1Button;
    Button addCartonRougeTeam2Button;

    Button addCartonJauneTeam1Button;
    Button addCartonJauneTeam2Button;

    Button addPenaltyTeam1Button;
    Button addPenaltyTeam2Button;

    Button addCoupFrancTeam1Button;
    Button addCoupFrancTeam2Button;

    Button addHorsJeuTeam1Button;
    Button addHorsJeuTeam2Button;

    Button saveStatGameButton;

    EditText numberGoalsTeam1;
    EditText numberGoalsTeam2;

    EditText numberCartonRougeTeam1;
    EditText numberCartonRougeTeam2;

    EditText numberCartonJauneTeam1;
    EditText numberCartonJauneTeam2;

    EditText numberPenaltyTeam1;
    EditText numberPenaltyTeam2;

    EditText numberCoupFrancTeam1;
    EditText numberCoupFrancTeam2;

    EditText numberHorsJeuTeam1;
    EditText numberHorsJeuTeam2;

    public StatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Recuperer les données sur la page Statistics
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //Recuperation des données du match crée dans le Fragment NewMatch
            idGame = getArguments().getInt("idGame");
            team1 = getArguments().getString("teamName1");
            team2 = getArguments().getString("teamName2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Affiche le layout avec les données recupérées
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        initializeComponents(view);
        setDefaultValues();
        setListeners();
        return view;
    }

    //etape 1
    public void initializeComponents(View view){
        teamName1 = (TextView) view.findViewById(R.id.teamName1);
        teamName2 = (TextView) view.findViewById(R.id.teamName2);

        addGoalTeam1Button = (Button) view.findViewById(R.id.addGoalTeam1Button);
        addGoalTeam2Button = (Button) view.findViewById(R.id.addGoalTeam2Button);
        numberGoalsTeam1 = (EditText) view.findViewById(R.id.numberGoalsTeam1);
        numberGoalsTeam2 = (EditText) view.findViewById(R.id.numberGoalsTeam2);

        addCartonRougeTeam1Button = (Button) view.findViewById(R.id.addCartonRougeTeam1Button);
        addCartonRougeTeam2Button = (Button) view.findViewById(R.id.addCartonRougeTeam2Button);
        numberCartonRougeTeam1 = (EditText) view.findViewById(R.id.numberCartonRougeTeam1);
        numberCartonRougeTeam2 = (EditText) view.findViewById(R.id.numberCartonRougeTeam2);

        addCartonJauneTeam1Button = (Button) view.findViewById(R.id.addCartonJauneTeam1Button);
        addCartonJauneTeam2Button = (Button) view.findViewById(R.id.addCartonJauneTeam2Button);
        numberCartonJauneTeam1 = (EditText) view.findViewById(R.id.numberCartonJauneTeam1);
        numberCartonJauneTeam2 = (EditText) view.findViewById(R.id.numberCartonJauneTeam2);

        addPenaltyTeam1Button = (Button) view.findViewById(R.id.addPenaltyTeam1Button);
        addPenaltyTeam2Button = (Button) view.findViewById(R.id.addPenaltyTeam2Button);
        numberPenaltyTeam1 = (EditText) view.findViewById(R.id.numberPenaltyTeam1);
        numberPenaltyTeam2 = (EditText) view.findViewById(R.id.numberPenaltyTeam2);

        addCoupFrancTeam1Button = (Button) view.findViewById(R.id.addCoupFrancTeam1Button);
        addCoupFrancTeam2Button = (Button) view.findViewById(R.id.addCoupFrancTeam2Button);
        numberCoupFrancTeam1 = (EditText) view.findViewById(R.id.numberCoupFrancTeam1);
        numberCoupFrancTeam2 = (EditText) view.findViewById(R.id.numberCoupFrancTeam2);

        addHorsJeuTeam1Button = (Button) view.findViewById(R.id.addHorsJeuTeam1Button);
        addHorsJeuTeam2Button = (Button) view.findViewById(R.id.addHorsJeuTeam2Button);
        numberHorsJeuTeam1 = (EditText) view.findViewById(R.id.numberHorsJeuTeam1);
        numberHorsJeuTeam2 = (EditText) view.findViewById(R.id.numberHorsJeuTeam2);

        saveStatGameButton = (Button) view.findViewById(R.id.saveStatGameButton);

    }

    private void redirectToPhoto(){
        CameraFragment fragmentCamera = new CameraFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragmentCamera);
        fragmentTransaction.commit();
    }
    //etape 2
    public void setDefaultValues(){
        teamName1.setText(team1);
        teamName2.setText(team2);

        numberGoalsTeam1.setText(String.valueOf(nbButsTeam1));
        numberGoalsTeam2.setText(String.valueOf(nbButsTeam2));

        numberCartonRougeTeam1.setText(String.valueOf(nbCartonRougeTeam1));
        numberCartonRougeTeam2.setText(String.valueOf(nbCartonRougeTeam2));

        numberCartonJauneTeam1.setText(String.valueOf(nbCartonJauneTeam1));
        numberCartonJauneTeam2.setText(String.valueOf(nbCartonJauneTeam2));

        numberPenaltyTeam1.setText(String.valueOf(nbPenaltyTeam1));
        numberPenaltyTeam2.setText(String.valueOf(nbPenaltyTeam2));

        numberCoupFrancTeam1.setText(String.valueOf(nbCoupFrancTeam1));
        numberCoupFrancTeam2.setText(String.valueOf(nbCoupFrancTeam2));

        numberHorsJeuTeam1.setText(String.valueOf(nbHorsJeuTeam1));
        numberHorsJeuTeam2.setText(String.valueOf(nbHorsJeuTeam2));

    }

    //etape 3
    public void setListeners(){
        addGoalTeam1Button.setOnClickListener(this::handleAddStats);
        addGoalTeam2Button.setOnClickListener(this::handleAddStats);
        addCartonRougeTeam1Button.setOnClickListener(this::handleAddStats);
        addCartonRougeTeam2Button.setOnClickListener(this::handleAddStats);
        addCartonJauneTeam1Button.setOnClickListener(this::handleAddStats);
        addCartonJauneTeam2Button.setOnClickListener(this::handleAddStats);
        addPenaltyTeam1Button.setOnClickListener(this::handleAddStats);
        addPenaltyTeam2Button.setOnClickListener(this::handleAddStats);
        addCoupFrancTeam1Button.setOnClickListener(this::handleAddStats);
        addCoupFrancTeam2Button.setOnClickListener(this::handleAddStats);
        addHorsJeuTeam1Button.setOnClickListener(this::handleAddStats);
        addHorsJeuTeam2Button.setOnClickListener(this::handleAddStats);
        saveStatGameButton.setOnClickListener(this::saveStatGame);
    }

    public void handleAddStats(View view){
        Button buttonAddButs = view.findViewById(view.getId()); //Recupère la view du bouton cliqué grace à son id if(button.getId() != R.id.buttonEquals){
        switch(buttonAddButs.getId()){
            case R.id.addGoalTeam1Button:
                nbButsTeam1 += 1; //On stocke le nb de buts pour la team 1
                numberGoalsTeam1.setText(String.valueOf(nbButsTeam1));//On affiche le nb de buts de la team 1
                break;
            case R.id.addGoalTeam2Button:
                nbButsTeam2 += 1;
                numberGoalsTeam2.setText(String.valueOf(nbButsTeam2));//On affiche le nb de buts de la team 2
                break;
            case R.id.addCartonRougeTeam1Button:
                nbCartonRougeTeam1 += 1;
                numberCartonRougeTeam1.setText(String.valueOf(nbCartonRougeTeam1));
                break;
            case R.id.addCartonRougeTeam2Button:
                nbCartonRougeTeam2 += 1;
                numberCartonRougeTeam2.setText(String.valueOf(nbCartonRougeTeam2));
                break;
            case R.id.addCartonJauneTeam1Button:
                nbCartonJauneTeam1 += 1;
                numberCartonJauneTeam1.setText(String.valueOf(nbCartonJauneTeam1));
                break;
            case R.id.addCartonJauneTeam2Button:
                nbCartonJauneTeam2 += 1;
                numberCartonJauneTeam2.setText(String.valueOf(nbCartonJauneTeam2));
                break;
            case R.id.addPenaltyTeam1Button:
                nbPenaltyTeam1 += 1;
                numberPenaltyTeam1.setText(String.valueOf(nbPenaltyTeam1));
                break;
            case R.id.addPenaltyTeam2Button:
                nbPenaltyTeam2 += 1;
                numberPenaltyTeam2.setText(String.valueOf(nbPenaltyTeam2));
                break;
            case R.id.addCoupFrancTeam1Button:
                nbCoupFrancTeam1 += 1;
                numberCoupFrancTeam1.setText(String.valueOf(nbCoupFrancTeam1));
                break;
            case R.id.addCoupFrancTeam2Button:
                nbCoupFrancTeam2 += 1;
                numberCoupFrancTeam2.setText(String.valueOf(nbCoupFrancTeam2));
                break;
            case R.id.addHorsJeuTeam1Button:
                nbHorsJeuTeam1 += 1;
                numberHorsJeuTeam1.setText(String.valueOf(nbHorsJeuTeam1));
                break;
            case R.id.addHorsJeuTeam2Button:
                nbHorsJeuTeam2 += 1;
                numberHorsJeuTeam2.setText(String.valueOf(nbHorsJeuTeam2));
                break;
        }
    }

    public void saveStatGame(View view){
        myDatabaseHelper = new MySQLiteOpenHelper(getActivity().getApplicationContext()); //SQLITE

        Statistics gameStatisticsTeam1ToCreate = new Statistics(0, idGame, team1, nbButsTeam1, nbCartonRougeTeam1, nbCartonJauneTeam1, nbPenaltyTeam1, nbCoupFrancTeam1, nbHorsJeuTeam1);
        myDatabaseHelper.createStatistics(gameStatisticsTeam1ToCreate);
        new StatisticsDAO().create(gameStatisticsTeam1ToCreate);

        Statistics gameStatisticsTeam2ToCreate = new Statistics(0, idGame, team2, nbButsTeam2, nbCartonRougeTeam2, nbCartonJauneTeam2, nbPenaltyTeam2, nbCoupFrancTeam2, nbHorsJeuTeam2);
        myDatabaseHelper.createStatistics(gameStatisticsTeam2ToCreate);
        new StatisticsDAO().create(gameStatisticsTeam2ToCreate);

        myDatabaseHelper.close();


        redirectToPhoto();
    }
}
