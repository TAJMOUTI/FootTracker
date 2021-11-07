package fr.android.foottracker.vue;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import fr.android.foottracker.R;
import fr.android.foottracker.models.Team;

public class TeamViewHolder extends RecyclerView.ViewHolder {

    TextView teamItem;

    public TeamViewHolder(View itemView) {
        super(itemView);
        teamItem = itemView.findViewById(R.id.teamNameTextView);
    }

    public void updateTeam(Team team){
        teamItem.setText(team.getName());
    }
}
