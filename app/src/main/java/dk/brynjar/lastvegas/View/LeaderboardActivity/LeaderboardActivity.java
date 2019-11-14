package dk.brynjar.lastvegas.View.LeaderboardActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import dk.brynjar.lastvegas.R;
import dk.brynjar.lastvegas.Repository.Player;
import dk.brynjar.lastvegas.View.BuycreditActivity;
import dk.brynjar.lastvegas.View.JackpotActivity.Jackpot;
import dk.brynjar.lastvegas.View.MainActivity;
import dk.brynjar.lastvegas.View.SettingsActivity;
import dk.brynjar.lastvegas.ViewModel.CreditViewModel;
import dk.brynjar.lastvegas.ViewModel.PlayerViewModel;

public class LeaderboardActivity extends AppCompatActivity {

    private RecyclerView _leaderboard;
    private RecyclerView.Adapter _leaderboardAdapter;
    private PlayerViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Toolbar toolbar = findViewById(R.id.leaderboard_toolbar);
        setSupportActionBar(toolbar);

        _leaderboard = findViewById(R.id.recyclerView1);
        _leaderboard.hasFixedSize();
        _leaderboard.setLayoutManager(new LinearLayoutManager(this));

        viewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        try{
            viewModel.deleteAllPlayers();
            for (Player player: getPlayers()) {
                viewModel.insertPlayer(player);
            }
        } catch (Exception e){
            Log.e("LeaderboardActivity","Failed inserting players\n"+e.getMessage());
        }
        viewModel.getAllPlayers().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                if (!players.isEmpty()) {
                    _leaderboardAdapter = new LeaderboardAdapter(players);
                    _leaderboard.setAdapter(_leaderboardAdapter);
                } else {
                    Log.e("LeaderboardActivity","Failed retrieving players");
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.leaderboard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_jackpot:
                startActivity(new Intent(this, Jackpot.class));
                return true;
            case R.id.action_buycredit:
                startActivity(new Intent(this, BuycreditActivity.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_logout:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("1", "Dunken", "999", R.drawable.pokemon_icon1));
        players.add(new Player("2", "Ole", "865", R.drawable.pokemon_icon2));
        players.add(new Player("3", "Miss Piggy", "755", R.drawable.pokemon_icon3));
        players.add(new Player("4", "Da Hustler", "723", R.drawable.pokemon_icon4));
        players.add(new Player("5", "Klumpen", "634", R.drawable.pokemon_icon5));
        players.add(new Player("6", "Ib", "613", R.drawable.pokemon_icon6));
        players.add(new Player("7", "Jøden", "543", R.drawable.pokemon_icon7));
        players.add(new Player("8", "Niller", "533", R.drawable.pokemon_icon8));
        players.add(new Player("9", "BOracle", "456", R.drawable.pokemon_icon9));
        players.add(new Player("10", "Mads", "454", R.drawable.pokemon_icon10));
        players.add(new Player("11", "Mogens", "433", R.drawable.pokemon_icon11));
        players.add(new Player("12", "Deyana", "412", R.drawable.pokemon_icon12));
        players.add(new Player("13", "Rasmus", "375", R.drawable.pokemon_icon13));
        players.add(new Player("14", "Suzuki Torben", "354", R.drawable.pokemon_icon14));
        players.add(new Player("15", "Drengen", "332", R.drawable.pokemon_icon15));
        players.add(new Player("16", "Bernhard", "298", R.drawable.pokemon_icon16));
        players.add(new Player("17", "Guffe", "264", R.drawable.pokemon_icon17));
        players.add(new Player("18", "Dennis", "153", R.drawable.pokemon_icon18));
        return players;
    }

}
