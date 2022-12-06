package agh.ics.oop;

public class Grass extends AbstractWorldMapElement{

    Grass(Vector2d pos){
        this.pos = pos;
    }


    public String toString(){
        return "*";
    }

    public String getType(){
        return "Grass";
    }


    @Override
    public String loadSrc() {
        return "/home/filipjedrzejewski/PO2022/oolab/src/main/resources/grass.png";
    }


}
