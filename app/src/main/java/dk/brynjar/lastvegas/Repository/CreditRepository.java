package dk.brynjar.lastvegas.Repository;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dk.brynjar.lastvegas.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class CreditRepository {

    private Retrofit retrofit;
    private IAzureWebApi azureWebApi;
    private MutableLiveData<Integer> credit;
    private Context context;
    private SharedPreferences prefs;

    public CreditRepository(Application app){
        this.context = app.getApplicationContext();
        prefs = context.getSharedPreferences("LastVegas", MODE_PRIVATE);
        credit = new MutableLiveData<>();
        int initialValue = readCreditFromPrefs();
        credit.setValue(initialValue);
        retrofit = new Retrofit.Builder().baseUrl("https://lastvegas.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        azureWebApi = retrofit.create(IAzureWebApi.class);
    }
    public LiveData<Integer> observeCredit(){
        return credit;
    }
    public void buyCredit(CreditCard card, int amount){
        getCreditFromAzure(card, amount);
    }
    public void withdrawOneCredit(){
        depositCredit(-1);
    }

    public void depositCredit(int credit){
        int currentCredit = readCreditFromPrefs();
        writeCreditToPrefs(credit+currentCredit);
        this.credit.setValue(readCreditFromPrefs());
    }

    private void getCreditFromAzure(CreditCard card,int amount){
        azureWebApi.getCredit().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()){
                    // log the error
                    return;
                }
                if (response.body() != null){
                    int credit = response.body();
                    depositCredit(credit);
                    MediaPlayer.create(context, R.raw.winning).start();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("CreditRepository", "Failed requesting credit from Azure (getCreditFromAzure method)");
            }
        });
    }

    private void writeCreditToPrefs(int amount) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(user.getEmail(), String.valueOf(amount));
        editor.apply();
    }
    private int readCreditFromPrefs() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String creditString = prefs.getString(user.getEmail(), "0");
        return Integer.parseInt(creditString);
    }

}
