package dk.brynjar.lastvegas.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import dk.brynjar.lastvegas.R;
import dk.brynjar.lastvegas.Repository.CreditCard;
import dk.brynjar.lastvegas.View.JackpotActivity.SlotMachine;
import dk.brynjar.lastvegas.View.LeaderboardActivity.LeaderboardActivity;
import dk.brynjar.lastvegas.View.JackpotActivity.Jackpot;
import dk.brynjar.lastvegas.ViewModel.CreditViewModel;

public class BuycreditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private static final String[] paths = {"10", "25", "50", "100"};
    private EditText creditCardNumber;
    private EditText expMonth;
    private EditText expYear ;
    private EditText cardCvc;
    private CreditViewModel creditViewModel;
    private int purchaseAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buycredit);
        Toolbar toolbar = findViewById(R.id.credit_toolbar);
        setSupportActionBar(toolbar);
        creditCardNumber = findViewById(R.id.creditCardNumber);
        expMonth = findViewById(R.id.expMonth);
        expYear = findViewById(R.id.expYear);
        cardCvc = findViewById(R.id.cardCvc);
        purchaseAmount = 0;
        creditViewModel = ViewModelProviders.of(this).get(CreditViewModel.class);
        creditViewModel.observeCredit().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer credit) {
                //update current credit text
            }
        });

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) view).setTextColor(Color.WHITE);
        switch (position) {
            case 0:
                purchaseAmount = Integer.parseInt(paths[0]);
                break;
            case 1:
                purchaseAmount = Integer.parseInt(paths[1]);
                break;
            case 2:
                purchaseAmount = Integer.parseInt(paths[2]);
                break;
            case 3:
                purchaseAmount = Integer.parseInt(paths[3]);
                break;
        }
    }
    private CreditCard generateCreditCard(){
        // need validation
        int number = Integer.parseInt(creditCardNumber.getText().toString());
        int month = Integer.parseInt(expMonth.getText().toString());
        int year = Integer.parseInt(expYear.getText().toString());
        int cvc = Integer.parseInt(cardCvc.getText().toString());
        return new CreditCard(number,month,year,cvc);
    }

    public void buyCredit(View view){
        CreditCard creditCard = generateCreditCard();
        creditViewModel.buyCredit(creditCard, purchaseAmount);
        Toast.makeText(getApplicationContext(), "You purchased "+purchaseAmount+" credits", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


