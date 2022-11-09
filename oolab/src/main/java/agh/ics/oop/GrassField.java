package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GrassField implements IWorldMap{
    private List<Animal> animals;

    private Grass[] grassTab;
    private int nOfGrasses;


    public GrassField(int nOfGrasses){
        animals = new ArrayList<>();

        this.nOfGrasses = nOfGrasses;
        grassTab = new Grass[nOfGrasses];

        for(int i=0;i<nOfGrasses;i++){
            grassTab[i] = placeGrass(i);
        }

    }

    private Grass placeGrass(int num){
        Vector2d pos = null;
        int maxPos = (int)(Math.sqrt(nOfGrasses*10));
        boolean notFound = true;
        while(notFound){
            pos = new Vector2d(randInt(0, maxPos), randInt(0, maxPos));
            notFound = false;
            for(int i=0;i<num;i++){
                if(grassTab[i].getPosition().equals(pos)){
                    notFound = true;
                }
            }
        }
        return new Grass(pos);
    }

    private int randInt(int a, int b){
        Random rn = new Random();
        int n = b-a+1;
        return rn.nextInt()%n + a;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
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
        //zwierzaki
        for(Animal animal : animals){
            if(animal.isAt(position)){
                return animal;
            }
        }
        //trawki
        for(Grass grass : grassTab){
            if(grass.getPosition().equals(position)){
                return grass;
            }
        }
        //lyse pole
        return null;
    }

    public String toString(){
        Vector2d lim1 = new Vector2d(0, 0);
        Vector2d lim2 = new Vector2d(5, 5);
        if(animals.size()>0){
            lim1 = animals.get(0).getPos();
            lim2 = animals.get(0).getPos();
            for(Animal animal : animals){
                lim1 = lim1.lowerLeft(animal.getPos());
                lim2 = lim2.upperRight(animal.getPos());
            }
        }
        //dla lepszego wygladu
        lim1.subtract(new Vector2d(1, 1));
        lim2.add(new Vector2d(1, 1));

        MapVisualizer visualizer = new MapVisualizer(this);
        String toReturn = visualizer.draw(lim1, lim2);
        return toReturn;
    }
}
