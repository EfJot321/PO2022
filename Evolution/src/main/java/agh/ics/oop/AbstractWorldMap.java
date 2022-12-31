package agh.ics.oop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class compareMapElements implements Comparator<IMapElement> {

    @Override
    public int compare(IMapElement el1, IMapElement el2) {
        // -1 - el1 jest lepsze
        // 1 - el2 jest lepsze
        // 0 - sa rowne

        //jesli ktorys z nich jest roslinka
        if(el2.getType().equals("Plant")){
            Animal animal=(Animal)el1;
            if(animal.dead){
                return 1;
            }
            return -1;
        }
        if(el1.getType().equals("Plant")){
            Animal animal=(Animal)el2;
            if(animal.dead){
                return -1;
            }
            return 1;
        }

        //juz wiem ze to zwierzaki
        Animal a1 = (Animal)el1;
        Animal a2 = (Animal)el2;

        //porownuje energie
        int dE = a2.getEnergy() - a1.getEnergy();
        if(dE != 0){
            return dE;
        }

        //porownuje wiek
        int dA = a2.getAge() - a1.getAge();
        if(dA != 0){
            return dA;
        }

        //porownuje liczbe dzieci
        int dC = a2.getChildrenNum() - a1.getChildrenNum();
        if(dC != 0){
            return dC;
        }

        //dowolny sposob
        return 1;
    }
}

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected Map<Vector2d, List<IMapElement>> objects;


    public int width;
    public int height;

    public int numberOfPlants;

    public int nOfGrasses;

    public Vector2d lim1;
    public Vector2d lim2;

    private int dE;

    public AbstractWorldMap(int width, int height, int dE){
        this.objects = new HashMap<>();

        this.lim1 = new Vector2d(0, 0);
        this.lim2 = new Vector2d(width-1, height-1);
        this.width=width;
        this.height=height;
        this.dE = dE;
        this.nOfGrasses = 0;
    }

    public boolean canMoveTo(Vector2d position) {
        return true;    //TODO
    }

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

    public int eatIfICan(Animal animal){
        //zbieram elementy znajdujace sie na pozycji zwierzaka
        List<IMapElement> elements = objectsAt(animal.getPosition());
        //jesli zwierzak jest tym wybranym do jedzenia
        if(animal == elements.get(0)){
            //to sprawdzam czy jest trawka dla zwierzaka
            IMapElement plantElement = null;
            for(IMapElement element : elements){
                if(element.getType().equals("Plant")){
                    plantElement = element;
                    break;
                }
            }
            if(plantElement != null){
                //jak jest trawka to ja zjada
                elements.remove(plantElement);
                nOfGrasses -= 1;
                //a jak przetrawi to ma energie
                return this.dE;
            }
        }
        return 0;
    }

    public Animal getBestLover(Animal animal){
        List<IMapElement> elements = objectsAt(animal.getPosition());
        for(IMapElement element : elements){
            //sprawdzam czy to nie ten zwierzak ktory kogos szuka
            //nie musze sprawdzac czy to nie trawa bo gdyby tu by byla trawa to juz jest zjedzona
            if(element != animal){
                Animal lover = (Animal)element;
                if(lover.canReproduce() && !lover.dead){
                    //to jest na pewno najlepszy bo elements jest posortowane po energii
                    return lover;
                }
            }
        }
        return null;
    }



    


    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, IMapElement element){
        removeFromMap(element, oldPosition);
        addToMap(element, newPosition);
    }



    public void addToMap(IMapElement element, Vector2d position){
        List<IMapElement> elements = objectsAt(position);
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
        List<IMapElement> elements = objectsAt(position);
        objects.remove(position);
        elements.remove(element);
        if(elements.size() > 0){
            objects.put(position, elements);
        }
    }




    abstract Vector2d[] limes();




}
