package agh.ics.oop;

public class Plant extends AbstractWorldMapElement{

    Plant(Vector2d pos){
        this.pos = pos;
    }


    public String toString(){
        return "*";
    }

    public String getType(){
        return "Plant";
    }


    @Override
    public String loadSrc() {
        return "/home/filipjedrzejewski/PO2022/oolab/src/main/resources/grass.png";
    }


}
