package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models;


public class StaticRvModel {

    private int image;
    private String text;

    public StaticRvModel(int image, String text) {
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}