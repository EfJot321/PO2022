package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    
    IWorldMap map;
    MoveDirection[] moves;

    int nOfAnimals = 0;
    List<Animal> animals;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] startPoss){
        this.map = map;
        this.moves = moves;

        this.animals = new ArrayList<>();
        for(Vector2d startPos : startPoss){
            Animal newBorn = new Animal(this.map, startPos);
            if(map.place(newBorn)){
                animals.add(newBorn);
                nOfAnimals++;
            }
        }

    }

    @Override
    public void run() {
        int iterator = 0;
        if(nOfAnimals > 0){
            for(MoveDirection command : moves){
                animals.get(iterator%nOfAnimals).move(command);
                iterator++;
            }
        }
        
        
    }
        
}
