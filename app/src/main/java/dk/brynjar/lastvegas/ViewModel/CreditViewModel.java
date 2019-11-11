package dk.brynjar.lastvegas.ViewModel;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import dk.brynjar.lastvegas.Repository.CreditCard;
import dk.brynjar.lastvegas.Repository.CreditRepository;

import static android.content.Context.MODE_PRIVATE;

public class CreditViewModel extends AndroidViewModel {

    private CreditRepository creditRepository;

    public CreditViewModel(Application app){
        super(app);
        creditRepository = new CreditRepository(app);
    }

    public void buyCredit(CreditCard card, int amount){
        Log.d("CreditViewModel", "requestCredit method was called.");
        creditRepository.buyCredit(card, amount);
    }
    public LiveData<Integer> observeCredit(){
        return creditRepository.observeCredit();
    }
    public void takeOneCredit(){
        creditRepository.withdrawOneCredit();
    }
    public void depositCredit(int amount){
        creditRepository.depositCredit(amount);
    }
}
