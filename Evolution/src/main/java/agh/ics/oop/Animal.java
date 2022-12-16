package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement{
//    private MapDirection dir;
    private Vector2d pos;

    private IWorldMap map;

    private Genom genom;

    private int days=0;

    List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map, Vector2d initialPosition){
//        this.dir = MapDirection.NORTH;
        this.pos = initialPosition;
        this.map = map;

        genom = new Genom("1234511110000");
    }

     public String toString(){
         return "Z";
    }

    public Vector2d getPosition(){
        return pos;
    }

    public String getType(){
        return "Animal";
    }

    public boolean isAt(Vector2d position){
        return this.pos.equals(position);
    }

    public void move(){
        Vector2d vect = genom.giveGenVector2d();
        Vector2d newPos = pos.add(vect);
        if(map.canMoveTo(newPos)){
            pos=pos.add(vect);
            positionChanged(pos.subtract(vect), pos);
        }
        
        days+=1;

    }

    


    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d actPos, Vector2d nextpos){
        for(IPositionChangeObserver obs:observers){
            obs.positionChanged(actPos, nextpos);
        }
    }
//
    @Override
    public String loadSrc() {
        //sciezka do obrazka
        return "";
    }


}
