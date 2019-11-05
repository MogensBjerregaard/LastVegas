package dk.brynjar.lastvegas.View.SlotMachine;

// a roll image contains the drawable id of the gif used and its type
public class RollImage {
    private int drawableId;
    private String type;
    public RollImage(int drawableId, String type){
        this.drawableId = drawableId;
        this.type = type;
    }
    public int getDrawableId(){
        return drawableId;
    }
    public String getType(){
        return type;
    }
}