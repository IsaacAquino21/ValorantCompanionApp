package ph.edu.dlsu.mobdeve.s17.aquino.gallenero.valorantcompanionapp.models;

public class DynamicRVModel {

    String name;
    private int image;
    int pos;

    public DynamicRVModel(String name, int image, int pos) {
        this.name = name;
        this.image = image;
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public int getImage(){
        return image;
    }

    public int getPos(){
        return pos;
    }
}
