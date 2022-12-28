package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement{
//    private MapDirection dir;
    private Vector2d pos;

    private IWorldMap map;

    private Genom genom;

    private int rotation = 0;
    private int days;
    private int energy;
    private int nOfChildrens = 0;

    List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map, Vector2d initialPosition, int startEnergy){
        this.map = map;
        this.pos = initialPosition;
        this.energy = startEnergy;
        this.days = 0;

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

    private Vector2d giveVector(int rotationValue){
        //tlumacze gen na najblizsze przesuniecie
        switch (rotationValue){
            case 0:
                return new Vector2d(0,1);
            case 1:
                return new Vector2d(1,1);
            case 2:
                return new Vector2d(1,0);
            case 3:
                return new Vector2d(1,-1);
            case 4:
                return new Vector2d(0,-1);
            case 5:
                return new Vector2d(-1,-1);
            case 6:
                return new Vector2d(-1,0);
            case 7:
                return new Vector2d(-1,1);
        }
        return new Vector2d(0,0);
    }

    public boolean isDead(){
        if(energy == 0){
            return true;
        }
        return false;
    }

    public void move(){
        //zmiana rotatcji
        rotation = (rotation+genom.giveNextGen())%8;
        //wyznaczenie wektora odpowiadającego aktualnej rotacji
        Vector2d vect = giveVector(rotation);
        Vector2d newPos = pos.add(vect);
        if(map.canMoveTo(newPos)){
            pos=pos.add(vect);
            positionChanged(pos.subtract(vect), pos);
        }
        //czas leci...
        days += 1;
        //..a sily brak...
        energy -= 1;
    }

    public void eat(){
        energy += map.eatIfICan(this);
    }

    public void reproduce() {
        //TO DO
    }

    
    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d actPos, Vector2d nextpos){
        for(IPositionChangeObserver obs:observers){
            obs.positionChanged(actPos, nextpos, this);
        }
    }

    public int getEnergy(){
        return this.energy;
    }

    public int getAge(){
        return this.days;
    }

    public int getChildrenNum(){
        return this.nOfChildrens;
    }


    @Override
    public String loadSrc() {
        //sciezka do obrazka
        return "";
    }


}
