package dk.brynjar.lastvegas.View.JackpotActivity;

public interface ISlotMachine {
    void pressButton1();
    void pressButton2();
    void pressButton3();
    int roll();
    void playSound(SlotMachine.SoundType soundType);
    void setCredit(int credit);
}
