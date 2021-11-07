package fr.android.foottracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import fr.android.foottracker.database.MySQLiteOpenHelper;
import fr.android.foottracker.models.Team;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private MySQLiteOpenHelper myDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabaseHelper = new MySQLiteOpenHelper(this);
        //createTeam(56653, "pink team");
        //createTeam(98675, "black team");
        //getTeam();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //Set our Toolbar as the ActionBar

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new TeamsFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_history);
        }

        if(android.os.Build.VERSION.SDK_INT > 9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }


    public void createTeam(int idTeam, String teamName){
        Team newTeam = new Team(idTeam, teamName);
        boolean insertData = myDatabaseHelper.createTeam(newTeam);
        if(insertData){
            System.out.println("data inserted");
        } else System.out.println("error");
    }




    public void getTeam(){
        Log.d("Reading: ", "Reading all teams..");
        ArrayList<Team> teamList = myDatabaseHelper.getTeamsList();

        for (Team t : teamList) {
            System.out.println("in for loop");

            String log = "Id: " + t.getIdTeam() + " ,Name: " + t.getName();
            // Writing Contacts to log
            Log.d("GET TEAMS: ", log);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HistoryFragment()).commit();
                break;
            case R.id.nav_newmatch:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NewMatchFragment()).commit();
                break;
            case R.id.nav_teams:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TeamsFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true; //Select Item after we clicked
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}