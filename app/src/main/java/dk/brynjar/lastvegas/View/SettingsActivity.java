package dk.brynjar.lastvegas.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import dk.brynjar.lastvegas.R;
import dk.brynjar.lastvegas.View.LeaderboardActivity.LeaderboardActivity;
import dk.brynjar.lastvegas.View.JackpotActivity.Jackpot;

public class SettingsActivity extends AppCompatActivity {
    private FirebaseUser user;
    private EditText editTextUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        user = FirebaseAuth.getInstance().getCurrentUser();
        editTextUserName = findViewById(R.id.textViewUserName);
        editTextUserName.setText(user.getDisplayName());
    }

    // updates the name of the Firebase user
    public void saveSettings(View v) {
        if (editTextUserName.getText().toString().equals(user.getDisplayName())){
            Toast.makeText(getApplicationContext(), "You need to edit the name first!", Toast.LENGTH_SHORT).show();
            return;
        }
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(editTextUserName.getText().toString())
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("SettingsActivity", "User profile updated.");
                            Toast.makeText(getApplicationContext(), "The name was updated", Toast.LENGTH_SHORT).show();
                        }
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
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
            case R.id.action_leaderboard:
                startActivity(new Intent(this, LeaderboardActivity.class));
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
}
