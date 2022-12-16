package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import agh.ics.oop.gui.App;
import javafx.application.Platform;


public class SimulationEngine implements IEngine, Runnable {

    private IWorldMap map;

    private int nOfAnimals = 0;
    private List<Animal> animals;

    private App mainApp;
    private int moveDelay;


    public SimulationEngine(Configuration config, int moveDelay, App mA){
        this.mainApp = mA;
        this.moveDelay = moveDelay;
        this.map = new WorldMap(config.width, config.height, config.startPlantsNum);
        //obsluga zwierzakow
        Vector2d pos;
        boolean notFound;
        this.animals = new ArrayList<>();
        for(int i=0;i<config.startAnimalNum;i++){
            //losuje pozycje
            notFound = true;
            pos = null;
            while(notFound){
                pos = new Vector2d(randInt(0, config.width-1), randInt(0, config.height-1));
                notFound = false;
                //nie chce zeby na samym poczatku zwierzaki byly na sobie
                if(map.isOccupied(pos)){
                    notFound = true;
                }
            }
            //tworze zwierzaka
            Animal newBorn = new Animal(this.map, pos, config.startE);
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
                    Platform.runLater(() -> {mainApp.updateScene((WorldMap)map);});
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

    private int randInt(int a, int b){
        Random rn = new Random();
        int n = b-a+1;
        return Math.abs(rn.nextInt()%n) + a;
    }

}
