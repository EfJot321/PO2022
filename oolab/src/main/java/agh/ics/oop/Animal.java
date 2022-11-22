package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement{
    private MapDirection dir;
    private Vector2d pos;

    private IWorldMap map;

    List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.dir = MapDirection.NORTH;
        this.pos = initialPosition;
        this.map = map;
    }

    public String toString(){
        if(dir == MapDirection.NORTH){
            return "^";
        }
        if(dir == MapDirection.EAST){
            return ">";
        }
        if(dir == MapDirection.SOUTH){
            return "v";
        }
        if(dir == MapDirection.WEST){
            return "<";
        }
        return "";
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


    public void move(MoveDirection direction){
        if(direction == MoveDirection.LEFT){
            dir = dir.previous();
        }
        if(direction == MoveDirection.RIGHT){
            dir = dir.next();
        }
        if(direction == MoveDirection.FORWARD || direction == MoveDirection.BACKWARD){
            Vector2d distance = dir.toUnitVector();
            if(direction == MoveDirection.BACKWARD){
                distance = distance.opposite();
            }
            Vector2d targetPos = pos.add(distance);
            if(map.canMoveTo(targetPos)){
                positionChanged(pos, targetPos);
                pos = targetPos;
            }
        }

    }

    void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        for(IPositionChangeObserver obs:observers){
            if(obs.equals(observer)){
                observers.remove(observer);
            }
        }
    }

    void positionChanged(Vector2d actPos, Vector2d nextpos){
        for(IPositionChangeObserver obs:observers){
            obs.positionChanged(actPos, nextpos);
        }
    }


}
