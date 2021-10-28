package fr.android.foottracker.TeamsRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.android.foottracker.R;
import fr.android.foottracker.models.Team;

public class TeamAdapter extends RecyclerView.Adapter <TeamViewHolder> {

    private List<Team> teamList;

    //Create Constructor

    public TeamAdapter (List<Team> teamView){
        teamList = teamView;
    }

    @Override
    public TeamViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.team_item_layout, parent, false);

        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        holder.updateTeam(this.teamList.get(position));
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }
}
