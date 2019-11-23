package dk.brynjar.lastvegas.View.JackpotActivity;

import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Arrays;
import java.util.Random;

import dk.brynjar.lastvegas.R;

public class SlotMachine implements ISlotMachine {
    private RollImage[] imageArray; //contains all  roll images
    private ImageView roll1;
    private ImageView roll2;
    private ImageView roll3;
    private TextView score;
    private Button button1;
    private Button button2;
    private Button button3;
    private FloatingActionButton rollButton;
    private boolean isButton1OnHold;
    private boolean isButton2OnHold;
    private boolean isButton3OnHold;
    private int[] rollImageIds;
    private AppCompatActivity appCompatActivity;
    private boolean isGameExtended;
    private MediaPlayer jackpotSound;
    private MediaPlayer winningSound;
    private MediaPlayer creditSound;
    private MediaPlayer rollSound;
    public enum SoundType {Credit, Jacpot, Roll, Winning};

    public SlotMachine(AppCompatActivity appCompatActivity){
        this.appCompatActivity = appCompatActivity;
        roll1 = appCompatActivity.findViewById(R.id.roll1);
        roll2 = appCompatActivity.findViewById(R.id.roll2);
        roll3 = appCompatActivity.findViewById(R.id.roll3);
        button1 = appCompatActivity.findViewById(R.id.buttonHold1);
        button2 = appCompatActivity.findViewById(R.id.buttonHold2);
        button3 = appCompatActivity.findViewById(R.id.buttonHold3);
        rollButton = appCompatActivity.findViewById(R.id.floatingActionButton);
        score = appCompatActivity.findViewById(R.id.score);
        jackpotSound = MediaPlayer.create(appCompatActivity,R.raw.jackpot);
        winningSound = MediaPlayer.create(appCompatActivity,R.raw.winning);
        creditSound = MediaPlayer.create(appCompatActivity,R.raw.credit);
        rollSound = MediaPlayer.create(appCompatActivity,R.raw.roll);
        rollImageIds = new int[3];
        isGameExtended = false;

        imageArray = loadGifIds();

        initializeGame();
        resetButtons();
        enableHoldButtons();
        roll();
    }

    public void setCredit(int credit){
        score.setText("Score: "+credit);
    }

    private void initializeGame(){
        rollImageIds = get3RandomPicks();
        updateRollImages();
    }

    private void resetButtons(){
        isButton1OnHold = false;
        isButton2OnHold = false;
        isButton3OnHold = false;
    }

    private void enableHoldButtons(){
        for (Button b: new Button[]{button1,button2,button3}) {
            b.setEnabled(true);
        }
    }

    private void disableHoldButtons(){
        for (Button b: new Button[]{button1,button2,button3}) {
            b.setEnabled(false);
        }
    }

    public void pressButton1(){
        isButton1OnHold = true;
        button1.setEnabled(false);
    }

    public void pressButton2(){
        isButton2OnHold = true;
        button2.setEnabled(false);
    }

    public void pressButton3(){
        isButton3OnHold = true;
        button3.setEnabled(false);
    }

    public int roll(){
        rollButton.setEnabled(false);
        int[] threeRandomImageIds = get3RandomPicks();
        if (!isButton1OnHold) rollImageIds[0]= threeRandomImageIds[0];
        if (!isButton2OnHold) rollImageIds[1]= threeRandomImageIds[1];
        if (!isButton3OnHold) rollImageIds[2]= threeRandomImageIds[2];
        return simulateRolling();
    }

    private int simulateRolling() {
        playSound(SoundType.Roll);
        if (!isButton1OnHold) roll1.setImageDrawable(appCompatActivity.getDrawable(R.drawable.rolling1));
        if (!isButton2OnHold) roll2.setImageDrawable(appCompatActivity.getDrawable(R.drawable.rolling2));
        if (!isButton3OnHold) roll3.setImageDrawable(appCompatActivity.getDrawable(R.drawable.rolling3));
        final int[] creditWon = {0};
        new Handler().postDelayed(new Runnable() {      
            @Override
            public void run() {
                updateRollImages();
            }
        }, 3500);
        creditWon[0] = checkResult();
        rollButton.setEnabled(true);
        return creditWon[0];
    }

    private void updateRollImages(){
        roll1.setImageDrawable(appCompatActivity.getDrawable(imageArray[rollImageIds[0]].getDrawableId()));
        roll2.setImageDrawable(appCompatActivity.getDrawable(imageArray[rollImageIds[1]].getDrawableId()));
        roll3.setImageDrawable(appCompatActivity.getDrawable(imageArray[rollImageIds[2]].getDrawableId()));
    }

    private int checkResult(){
        String name0 = imageArray[rollImageIds[0]].getType();
        String name1 = imageArray[rollImageIds[1]].getType();
        String name2 = imageArray[rollImageIds[2]].getType();

        if(isButton1OnHold || isButton2OnHold || isButton3OnHold) isGameExtended = true;

        int creditWon = 0;
        if (name0.equals(name1) && name1.equals(name2)){ // 3 matching images - we have a winner
            playSound(SoundType.Jacpot);
            creditWon = getJackpot(name0);
            resetButtons();
            disableHoldButtons();
            isGameExtended = false;
            return creditWon;
        } else {
            Toast.makeText(appCompatActivity.getApplicationContext(),"Try again!", Toast.LENGTH_SHORT).show();

            if (!isGameExtended) {
                resetButtons();
                enableHoldButtons();
            }
            else {
                resetButtons();
                disableHoldButtons();
            }
            isGameExtended = false;
            return creditWon;
        }

    }

    public void playSound(SoundType soundType) {
        switch (soundType){
            case Roll:
                rollSound.start();
                break;
            case Credit:
                creditSound.start();
                break;
            case Jacpot:
                jackpotSound.start();
                break;
            case Winning:
                winningSound.start();
                break;
            default:

        }
    }

    private int getJackpot(String name0) {
        int jackPot = 0;
        switch (name0){
            case "seven":
                jackPot = 100;
                break;
            case "berry":
                jackPot = 35;
                break;
            case "duck":
                jackPot = 30;
                break;
            case "flyingr":
                jackPot = 25;
                break;
            case "purplemonster":
                jackPot = 20;
                break;
            case "greyufo":
                jackPot = 15;
                break;
            case "pokemon":
                jackPot = 10;
                break;
        }
        Toast.makeText(appCompatActivity.getApplicationContext(),"Jackpot!\nYou won "+jackPot, Toast.LENGTH_SHORT).show();
        return jackPot;
    }

    private int[] get3RandomPicks(){
        int[] threeRandomPicks = new int[3];
        int randomNumber = 0;
        for (int i = 0; i<3; i++){
            do {
                randomNumber = new Random().nextInt(99) % imageArray.length;
            } while (Arrays.asList(threeRandomPicks).contains(randomNumber));
            threeRandomPicks[i] = randomNumber;
        }
        return  threeRandomPicks;
    }

    private RollImage[] loadGifIds(){
        RollImage[] imageArray = new RollImage[21];
        imageArray[0] = new RollImage(R.drawable.berry1, "berry");
        imageArray[1] = new RollImage(R.drawable.berry2, "berry");
        imageArray[2] = new RollImage(R.drawable.duck1, "duck");
        imageArray[3] = new RollImage(R.drawable.duck2, "duck");
        imageArray[4] = new RollImage(R.drawable.flyingr1, "flyingr");
        imageArray[5] = new RollImage(R.drawable.flyingr2, "flyingr");
        imageArray[6] = new RollImage(R.drawable.flyingr3, "flyingr");
        imageArray[7] = new RollImage(R.drawable.greyufo1, "greyufo");
        imageArray[8] = new RollImage(R.drawable.greyufo2, "greyufo");
        imageArray[9] = new RollImage(R.drawable.pokemon1, "pokemon");
        imageArray[10] = new RollImage(R.drawable.pokemon2, "pokemon");
        imageArray[11] = new RollImage(R.drawable.pokemon3, "pokemon");
        imageArray[12] = new RollImage(R.drawable.pokemon4, "pokemon");
        imageArray[13] = new RollImage(R.drawable.pokemon5, "pokemon");
        imageArray[14] = new RollImage(R.drawable.purplemonster1, "purplemonster");
        imageArray[15] = new RollImage(R.drawable.purplemonster2, "purplemonster");
        imageArray[16] = new RollImage(R.drawable.purplemonster3, "purplemonster");
        imageArray[17] = new RollImage(R.drawable.purplemonster4, "purplemonster");
        imageArray[18] = new RollImage(R.drawable.seven1, "seven");
        imageArray[19] = new RollImage(R.drawable.seven2, "seven");
        imageArray[20] = new RollImage(R.drawable.seven3, "seven");
        return imageArray;
    }
}
