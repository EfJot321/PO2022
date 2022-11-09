package agh.ics.oop;

public class Grass {

    private Vector2d position;

    Grass(Vector2d pos){
        position = pos;
    }

    public Vector2d getPosition(){
        return position;
    }

    public String toString(){
        return "*";
    }
}
