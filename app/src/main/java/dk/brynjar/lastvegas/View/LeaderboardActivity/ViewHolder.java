package dk.brynjar.lastvegas.View.LeaderboardActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import dk.brynjar.lastvegas.R;

class ViewHolder extends RecyclerView.ViewHolder {

    TextView number;
    TextView name;
    TextView score;
    ImageView icon;

    ViewHolder(View itemView) {
        super(itemView);
        number = itemView.findViewById(R.id.tv_number);
        name = itemView.findViewById(R.id.tv_name);
        score = itemView.findViewById(R.id.tv_score);
        icon = itemView.findViewById(R.id.iv_icon);
    }

}

