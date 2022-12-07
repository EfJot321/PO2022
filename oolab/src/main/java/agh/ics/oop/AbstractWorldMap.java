package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d, IMapElement> objects;

    final MapVisualizer visualizer;

    public AbstractWorldMap(){
        visualizer = new MapVisualizer(this);
        objects = new HashMap<>();
    }

    public boolean canMoveTo(Vector2d position) {
        return !(isOccupied(position) && ((IMapElement) objectAt(position)).getType().equals("Animal"));
    }

    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            //dodawanie zwierzaka do mapy
            objects.put(animal.getPosition(),animal);
            //dodawanie siebie jako obserwatora do zwierzaka
            animal.addObserver(this);
            return true;
        }
        throw new IllegalArgumentException(animal.getPosition().toString() + " is wrong place.");
        
    }

    public boolean isOccupied(Vector2d position) {
        return (objects.get(position) != null);
    }

    public Object objectAt(Vector2d position) {
        return objects.get(position);
    }


    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        IMapElement object = objects.get(oldPosition);
        if(object.getType().equals("Animal")){
            objects.remove(oldPosition);
            objects.put(newPosition, object);
        }
    }

    abstract Vector2d[] limes();

    public String toString(){
        Vector2d[] limess = limes();
        return visualizer.draw(limess[0], limess[1]);
    }

  
}
