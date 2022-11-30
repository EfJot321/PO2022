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


}
