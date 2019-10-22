package dk.brynjar.lastvegas.View;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import dk.brynjar.lastvegas.R;
import dk.brynjar.lastvegas.View.Jackpot;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this, Jackpot.class);
        startActivity(intent);

    }



}

