package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement{
//    private MapDirection dir;
    private Vector2d pos;

    private IWorldMap map;

    private String gen="12345";

    private int days=0;

    List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map, Vector2d initialPosition){
//        this.dir = MapDirection.NORTH;
        this.pos = initialPosition;
        this.map = map;
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

        int len=gen.length();
        int i=days%len;
        Vector2d vect=translate(gen.charAt(i));
        pos=pos.add(vect);
        days+=1;

        positionChanged(pos.subtract(vect), pos);


    }

    private Vector2d translate(char a){

        switch (a){
            case '0':
                return new Vector2d(0,1);
            case '1':
                return new Vector2d(1,1);
            case '2':
                return new Vector2d(1,0);
            case '3':
                return new Vector2d(1,-1);
            case '4':
                return new Vector2d(0,-1);
            case '5':
                return new Vector2d(-1,-1);
            case '6':
                return new Vector2d(-1,0);
            case '7':
                return new Vector2d(-1,1);
        }
        return new Vector2d(0,0);

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
