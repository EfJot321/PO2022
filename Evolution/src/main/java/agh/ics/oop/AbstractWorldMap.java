package agh.ics.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class compareMapElements implements Comparator<IMapElement> {

    @Override
    public int compare(IMapElement el1, IMapElement el2) {
        // 1 - el1 jest lepsze
        // -1 - el2 jest lepsze
        // 0 - sa rowne

        //jesli ktorys z nich jest roslinka
        if(el2.getType().equals("Plant")){
            return -1;
        }
        if(el1.getType().equals("Plant")){
            return 1;
        }

        //juz wiem ze to zwierzaki
        Animal a1 = (Animal)el1;
        Animal a2 = (Animal)el2;

        //porownuje energie
        int dE = a1.getEnergy() - a2.getEnergy();
        if(dE != 0){
            return dE;
        }

        //porownuje wiek
        int dA = a1.getAge() - a2.getAge();
        if(dA != 0){
            return dA;
        }

        //porownuje liczbe dzieci
        int dC = a1.getChildrenNum() - a2.getChildrenNum();
        if(dC != 0){
            return dC;
        }

        //dowolny sposob
        return 1;
    }
}

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d, List<IMapElement>> objects;

    //final MapVisualizer visualizer;

    public AbstractWorldMap(){
       // visualizer = new MapVisualizer(this);
        objects = new HashMap<>();
    }

    // public boolean canMoveTo(Vector2d position) {
    //     return !(isOccupied(position) && ((IMapElement) objectsAt(position)).getType().equals("Animal"));
    // }

    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            //dodawanie zwierzaka do mapy
            addToMap(animal, animal.getPosition());
            //dodawanie siebie jako obserwatora do zwierzaka
            animal.addObserver(this);
            return true;
        }
        throw new IllegalArgumentException(animal.getPosition().toString() + " is wrong place.");
    }

    public boolean isOccupied(Vector2d position) {
        return (objects.get(position) != null);
    }

    public List<IMapElement> objectsAt(Vector2d position) {
        return objects.get(position);
    }


    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement element){
        removeFromMap(element, oldPosition);
        addToMap(element, newPosition);
    }

    public void addToMap(IMapElement element, Vector2d position){
        List<IMapElement> elements = objects.get(position);
        objects.remove(position);
        if(elements == null){
            elements = new ArrayList<IMapElement>();
            elements.add(element);
            objects.put(position, elements);
        }
        else {
            elements.add(element);
            elements.sort(new compareMapElements());
            objects.put(position, elements);
        }
    }

    public void removeFromMap(IMapElement element, Vector2d position){
        List<IMapElement> elements = objects.get(position);
        objects.remove(position);
        elements.remove(element);
        if(elements.size() > 0){
            objects.put(position, elements);
        }
    }


    abstract Vector2d[] limes();

//    public String toString(){
//        Vector2d[] limess = limes();
//        return visualizer.draw(limess[0], limess[1]);
//    }


}
