package dk.brynjar.lastvegas.Repository.JackpotModel;

public interface ISlotMachine {
    void pressButton1();
    void pressButton2();
    void pressButton3();
    void roll();
    void playSound(SlotMachine.SoundType soundType);
    void updateCredit(int credit);
}
