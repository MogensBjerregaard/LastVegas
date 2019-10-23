package dk.brynjar.lastvegas.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import dk.brynjar.lastvegas.Repository.CreditRepository;
import dk.brynjar.lastvegas.Repository.JackpotModel.ISlotMachine;
import dk.brynjar.lastvegas.Repository.JackpotModel.SlotMachine;
import dk.brynjar.lastvegas.Repository.ICreditRepository;
import dk.brynjar.lastvegas.R;
import dk.brynjar.lastvegas.ViewModel.JackpotViewModel;

public class Jackpot extends AppCompatActivity {

    private ISlotMachine slotMachine;
    private ICreditRepository creditRepository;
    private JackpotViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jackpot);
        slotMachine = new SlotMachine(this);
        creditRepository = new CreditRepository(slotMachine);
        viewModel = ViewModelProviders.of(this).get(JackpotViewModel.class);
        viewModel.observeCredit().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer credit) {
                slotMachine.updateCredit(credit);
            }
        });
    }

    public void roll(View view){
        viewModel.takeOneCredit();
        slotMachine.roll();
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

    public void buyCredit(View view) {
        slotMachine.playSound(SlotMachine.SoundType.Winning);
        //creditRepository.getCredit();
        viewModel.requestCredit();
    }

}
