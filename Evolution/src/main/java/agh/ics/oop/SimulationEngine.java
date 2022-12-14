package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;


import agh.ics.oop.gui.App;
import javafx.application.Platform;


public class SimulationEngine implements IEngine, Runnable {

    private IWorldMap map;

    private int nOfAnimals = 0;
    private List<Animal> animals;

    private App mainApp;
    private int moveDelay;


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


    @Override
    public void run() {



        try {
            int iterator = 0;
            if(nOfAnimals > 0){
                while (true){
                    animals.get(iterator%nOfAnimals).move();
                    Thread.sleep(moveDelay);
                    Platform.runLater(() -> {mainApp.updateScene((RectangularMap)map);});
                    iterator++;
                    if(iterator>50){
                        break;
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Symulacja zostala przerwana!");
        }


    }

}
