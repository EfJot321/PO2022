package agh.ics.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import agh.ics.oop.gui.Window;
import javafx.application.Platform;
class GenomStat{

    private Genom genom;
    private int stats;
    public GenomStat(Genom genom, int stats){
        this.genom=genom;
        this.stats=stats;
    }
    public int getStats(){
        return this.stats;
    }
    public Genom getGenom(){
        return this.genom;
    }
    public void addToStats(){
        this.stats+=1;
    }
}

public class SimulationEngine implements IEngine, Runnable {

    private IWorldMap map;

    private int nOfAnimals = 0;
    private List<Animal> animals;
    private List<Animal> deadAnimals;
    private List<Animal> newBornAnimals;

    private Window window;
    private int moveDelay;
    private int plantsPerDay;

    private int oldDead =0;

    private int days=0;



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
            Animal newBorn = new Animal(this.map, pos, config.startE, config.minE, config.birthE, config.genomLen, config.minMutNum, config.maxMutNum);
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
                        nOfAnimals -= 1;
                        animals.remove(deadAnimal);

                    }
                    nOfAnimals+=oldDead;
                    oldDead=deadAnimals.size();
                    
                    
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
                    days+=1;
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
    public int getDay(){
        return days;
    }

    public int getAverageEnergy(){
        int avgEnergy=0;
        for(int i=0; i<animals.size(); i++){
            avgEnergy+=animals.get(i).getEnergy();
        }
        avgEnergy/=animals.size();
        return avgEnergy;
    }

    public int getAverageLivingDays(){
        int avgDays=0;
        if(deadAnimals.size()!=0) {
            for (int i = 0; i < deadAnimals.size(); i++) {
                avgDays += deadAnimals.get(i).getAge();
            }
            avgDays /= deadAnimals.size();
            return avgDays;
        }
        return avgDays;
    }

    public Genom getMostPopularGenom(){
        List<GenomStat> popularGenoms = new ArrayList<>();
        for(int i=0;i<animals.size();i++){
            boolean ifExist=false;
            for(int j=0; j<popularGenoms.size();j++){
               if(compareGenoms(animals.get(i).genom,popularGenoms.get(j).getGenom())){
                   popularGenoms.get(j).addToStats();
                   ifExist=true;
                   break;
               }
            }
            if(!ifExist){
                GenomStat genomStats=new GenomStat(animals.get(i).genom,1);
                popularGenoms.add(genomStats);
            }
        }
        int stats=0;
        Genom mostPopular=null;
        for(int i=0; i<popularGenoms.size();i++){
            if(popularGenoms.get(i).getStats()>stats){
                stats=popularGenoms.get(i).getStats();
                mostPopular=popularGenoms.get(i).getGenom();
            }
        }
        return mostPopular;
    }

    private boolean compareGenoms(Genom g1, Genom g2){
        for(int i=0;i<g1.genes.size();i++){
            if(g1.genes.get(i)==g2.genes.get(0)){
                boolean equals=true;
                int counter=1;
                while(counter<g1.genes.size()){
                    if(g1.genes.get((i+counter)%(g1.genes.size()))!=g2.genes.get(counter)){
                        equals=false;
                        break;
                    }
                    counter+=1;
                }
                if(equals){
                    return true;
                }
            }
        }
        return false;
    }


}
