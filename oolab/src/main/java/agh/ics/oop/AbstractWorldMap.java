package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap{
    protected List<IMapElement> objects;

    public AbstractWorldMap(){
        objects = new ArrayList<>();
    }

    public boolean canMoveTo(Vector2d position) {
        return !(isOccupied(position) && ((IMapElement) objectAt(position)).getType().equals("Animal"));
    }

    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            objects.add(animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        return (objectAt(position) != null);
    }

    public Object objectAt(Vector2d position) {
        Object toReturn = null;
        for(IMapElement object : objects){
            if(object.isAt(position)){
                if(object.getType().equals("Animal")){
                    return object;
                }
                toReturn = object;
                
            }
        }
        return toReturn;
    }

    abstract Vector2d[] limes();

    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        String toReturn = visualizer.draw(limes()[0], limes()[1]);
        return toReturn;
    }

}
