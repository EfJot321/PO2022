package agh.ics.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import agh.ics.oop.gui.Window;
import javafx.application.Platform;


public class SimulationEngine implements IEngine, Runnable {

    private IWorldMap map;

    private int nOfAnimals = 0;
    private List<Animal> animals;
    private List<Animal> deadAnimals;
    private List<Animal> newBornAnimals;

    private Window window;
    private int moveDelay;
    private int plantsPerDay;



    public SimulationEngine(Configuration config, int moveDelay, Window window){
        this.window = window;
        this.moveDelay = moveDelay;
        this.plantsPerDay = config.plantsPerDay;
        this.map = new WorldMap(config.width, config.height, config.startPlantsNum, config.dE);
        //obsluga zwierzakow
        Vector2d pos;
        boolean notFound;
        this.newBornAnimals = new ArrayList<>();
        this.deadAnimals = new ArrayList<>();
        this.animals = new ArrayList<>();
        //tworze zwierzaki
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
            Animal newBorn = new Animal(this.map, pos, config.startE, config.minE, config.birthE, config.genomLen);
            if(map.place(newBorn)){
                animals.add(newBorn);
                nOfAnimals++;
            }
            
        }
    
    }


    @Override
    public void run() {

        try {
                while (animals.size() > 0){
                    //sprawdzam ktore zwierzaki umarly
                    for(Animal animal : animals){
                        animal.isDead();
                        if(animal.dead){
                            deadAnimals.add(animal);
                        }
                    }
                    //zwierzeta umieraja
                    for(Animal deadAnimal : deadAnimals){
                        animals.remove(deadAnimal);
                        nOfAnimals -= 1;
                    }
                    deadAnimals = new ArrayList<>();
                    
                    
                    //zwierzeta ida
                    for(Animal animal : animals){
                        animal.move();
                    }
                    //zwierzeta jedza
                    for(Animal animal : animals){
                        animal.eat();
                    }
                    //zwierzeta sie reprodukuja
                    for(Animal animal : animals) {
                        Animal newBorn = animal.reproduce();
                        //dodawanie zwierzaka do listy zwierzakow ktore sie urodzily dzis
                        if (newBorn != null) {
                            newBornAnimals.add(newBorn);
                        }
                    }
                    //dodaje nowonarodzone dzieciaki do listy zwierzakow
                    for(Animal animal: newBornAnimals){
                        animals.add(animal);
                        nOfAnimals++;
                    }
                    newBornAnimals = new ArrayList<>();

                    //rosna rosliny
                    map.plantsAreGrowing(plantsPerDay);
                    //wyswietlanie
                    Platform.runLater(() -> {
                        try {
                            window.updateScene((WorldMap)map);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    Thread.sleep(moveDelay);
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

    public int getNOfAnimals(){
        return nOfAnimals;
    }


}
