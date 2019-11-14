package dk.brynjar.lastvegas.Repository;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player_table")
public class Player {

    private String number;
    @PrimaryKey @NonNull
    private String name;
    private String score;
    private int iconId;

    public Player(String number, String name, String score, int iconId) {
        this.number = number;
        if (name != null){
            this.name = name;
        } else {
            this.name = "empty";
        }
        this.score = score;
        this.iconId = iconId;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String getNumber() {
        return number;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }
    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
    public int getIconId() {
        return iconId;
    }
}
