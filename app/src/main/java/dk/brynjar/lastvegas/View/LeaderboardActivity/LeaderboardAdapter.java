package dk.brynjar.lastvegas.View.LeaderboardActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import dk.brynjar.lastvegas.R;

public class LeaderboardAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Player> players;

    public LeaderboardAdapter(ArrayList<Player> players){
        this.players = players;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.number.setText(players.get(position).getNumber());
        viewHolder.name.setText(players.get(position).getName());
        viewHolder.score.setText(players.get(position).getScore());
        viewHolder.icon.setImageResource(players.get(position).getIconId());
    }

    public int getItemCount() {
        return players.size();
    }

}
