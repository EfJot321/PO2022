package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{

    private List<Animal> animals;

    private Vector2d lim1;
    private Vector2d lim2;


    public RectangularMap(int width, int height){
        lim1 = new Vector2d(0, 0);
        lim2 = new Vector2d(width-1, height-1);
        animals = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(!position.lowerLeft(lim1).equals(lim1)){
            return false;
        }
        if(!position.upperRight(lim2).equals(lim2)){
            return false;
        }
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPos())){
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(objectAt(position) == null){
            return false;
        }
        return true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal : animals){
            if(animal.isAt(position)){
                return animal;
            }
        }
        return null;
    }

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        String toReturn = visualizer.draw(lim1, lim2);
        return toReturn;
    }

}
