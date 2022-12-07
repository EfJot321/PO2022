package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;


import agh.ics.oop.gui.App;
import javafx.application.Platform;


public class SimulationEngine implements IEngine, Runnable {
    
    private IWorldMap map;
    private MoveDirection[] moves;

    private int nOfAnimals = 0;
    private List<Animal> animals;

    private App mainApp;
    private int moveDelay;

    public void setDirections(MoveDirection[] directions){
        this.moves = directions;
    }


    public SimulationEngine(IWorldMap map, Vector2d[] startPoss, int moveDelay, App mA){
        this.map = map;

        this.mainApp = mA;
        this.moveDelay = moveDelay;

        this.animals = new ArrayList<>();
        for(Vector2d startPos : startPoss){
            Animal newBorn = new Animal(this.map, startPos);
            if(map.place(newBorn)){
                animals.add(newBorn);
                nOfAnimals++;
            }
        }

    }

    //drugi konstruktor zeby testy sie nie rzucaly
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
        
        

        try {
            int iterator = 0;
        if(nOfAnimals > 0){
            for(MoveDirection command : moves){
                animals.get(iterator%nOfAnimals).move(command);
                iterator++;
                if(iterator%nOfAnimals == 1){
                    Thread.sleep(moveDelay);
                }
                
                Platform.runLater(() -> {mainApp.updateWorld((GrassField)map);});
                //System.out.print(map);

            }
        }
        } catch (InterruptedException e) {
            System.out.println("Symulacja zostala przerwana!");
        }
        
        
    }
        
}
