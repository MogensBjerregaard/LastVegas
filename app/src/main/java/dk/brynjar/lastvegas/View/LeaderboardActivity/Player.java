package dk.brynjar.lastvegas.View.LeaderboardActivity;

public class Player {
    private String number;
    private String name;
    private String score;
    private int iconId;

    Player(String number, String name, String score, int iconId) {
        this.number = number;
        this.name = name;
        this.score = score;
        this.iconId = iconId;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public int getIconId() {
        return iconId;
    }
}
