package dk.brynjar.lastvegas.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import dk.brynjar.lastvegas.Repository.CreditRepository;
import dk.brynjar.lastvegas.Repository.JackpotModel.ISlotMachine;
import dk.brynjar.lastvegas.Repository.JackpotModel.SlotMachine;
import dk.brynjar.lastvegas.Repository.ICreditRepository;
import dk.brynjar.lastvegas.R;

public class Jackpot extends AppCompatActivity {

    private ISlotMachine slotMachine;
    private ICreditRepository creditRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jackpot);
        slotMachine = new SlotMachine(this);
        creditRepository = new CreditRepository(slotMachine);
    }

    public void roll(View view){
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
        creditRepository.getCredit();

    }

}
