package dk.brynjar.lastvegas.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JackpotViewModel extends ViewModel {
    private MutableLiveData<Integer> credit;

    public JackpotViewModel(){
        credit = new MutableLiveData<>();
        credit.setValue(0);
    }
    public void requestCredit(){
        int currentCredit = credit.getValue();
        credit.setValue(currentCredit+10);
    }
    public LiveData<Integer> observeCredit(){
        return credit;
    }
    public void takeOneCredit(){
        int currentCredit = credit.getValue();
        if (currentCredit>0) credit.setValue(--currentCredit);
    }

}
