package dk.brynjar.lastvegas.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import dk.brynjar.lastvegas.R;
import dk.brynjar.lastvegas.View.LeaderboardActivity.LeaderboardActivity;
import dk.brynjar.lastvegas.View.JackpotActivity.Jackpot;

public class BuycreditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buycredit);
        Toolbar toolbar = findViewById(R.id.credit_toolbar);
        setSupportActionBar(toolbar);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.credit_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_jackpot:
                startActivity(new Intent(this, Jackpot.class));
                return true;
            case R.id.action_leaderboard:
                startActivity(new Intent(this, LeaderboardActivity.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


