package dk.brynjar.lastvegas.View.JackpotActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import dk.brynjar.lastvegas.View.BuycreditActivity;
import dk.brynjar.lastvegas.View.LeaderboardActivity.LeaderboardActivity;
import dk.brynjar.lastvegas.View.SettingsActivity;
import dk.brynjar.lastvegas.R;
import dk.brynjar.lastvegas.ViewModel.CreditViewModel;

public class Jackpot extends AppCompatActivity {

    private ISlotMachine slotMachine;
    private CreditViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jackpot);
        slotMachine = new SlotMachine(this);
        Toolbar toolbar = findViewById(R.id.jackpot_toolbar);
        setSupportActionBar(toolbar);
        viewModel = ViewModelProviders.of(this).get(CreditViewModel.class);
        viewModel.observeCredit().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer credit) {
                slotMachine.setCredit(credit);
            }
        });
    }

    public void roll(View view){
        int creditStatus = viewModel.observeCredit().getValue();
        if (creditStatus == 0) {
            Toast.makeText(this,"You have no credit!", Toast.LENGTH_SHORT).show();
            return;
        }
        viewModel.takeOneCredit();
        int winCredit = slotMachine.roll();
        if (winCredit>0){
            viewModel.depositCredit(winCredit);
        }
    }

    public void pressHoldButton1(View view) {
        slotMachine.pressButton1();
    }
    public void pressHoldButton2(View view) {
        slotMachine.pressButton2();
    }
    public void pressHoldButton3(View view) {
        slotMachine.pressButton3();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.jackpot_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.action_buycredit:
                startActivity(new Intent(this, BuycreditActivity.class));
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
