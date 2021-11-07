package fr.android.foottracker.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.android.foottracker.R;
import fr.android.foottracker.models.History;
import fr.android.foottracker.models.Team;

public class HistoryAdapter extends RecyclerView.Adapter <HistoryViewHolder> {

    private List<History> historyList;

    //Create Constructor

    public HistoryAdapter (List<History> historyView){
        System.out.println("create history adapter");
        System.out.println(historyView.size());

        historyList = historyView;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("create view holder");
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_item_layout, parent, false);

        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.updateHistory(this.historyList.get(position));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

}
