package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animal extends AbstractWorldMapElement{
//    private MapDirection dir;
    private Vector2d pos;

    private IWorldMap map;


    private int rotation;
    private int days;
    private int energy;
    private int nOfChildrens = 0;
    private int minReproduceEnergy;
    private float birthE;


    public Genom genom;
    private int genomLen;
    private int minNumMut;
    private int maxNumMut;
    private  boolean randMut;
    private boolean isCrazy;

    public boolean afterReproduction;
    public boolean dead;


    List<IPositionChangeObserver> observers = new ArrayList<>();

    //konstruktor bez danego genomu
    public Animal(IWorldMap map, Vector2d initialPosition, int startEnergy, int mRe, float birthE,int genomLen, int minNumMut, int maxNumMut, boolean crazy, boolean randMut){
        this.map = map;
        this.pos = initialPosition;
        this.energy = startEnergy;
        this.minReproduceEnergy = mRe;
        this.birthE = birthE;
        this.days = 0;

        this.genomLen = genomLen;
        this.minNumMut = minNumMut;
        this.maxNumMut = maxNumMut;
        this.randMut = randMut;
        this.isCrazy = crazy;
        this.genom = new Genom(this.genomLen, this.isCrazy);

        this.rotation = randInt(0,7);

        this.afterReproduction = false;
        this.dead = false;

    }

    //konstruktor z danym genomem
    public Animal(IWorldMap map, Vector2d initialPosition, int startEnergy, int mRe, float birthE,Genom genom, int genomLen, int minNumMut, int maxNumMut, boolean crazy, boolean randMut){
        this.map = map;
        this.pos = initialPosition;
        this.energy = startEnergy;
        this.minReproduceEnergy = mRe;
        this.birthE = birthE;
        this.days = 0;

        this.genomLen = genomLen;
        this.minNumMut = minNumMut;
        this.maxNumMut = maxNumMut;
        this.randMut = randMut;
        this.isCrazy = crazy;
        this.genom = genom;

        this.rotation = randInt(0,7);

        this.afterReproduction = false;
        this.dead = false;

    }

    private int randInt(int a, int b){
        Random rn = new Random();
        int n = b-a+1;
        return Math.abs(rn.nextInt()%n) + a;
    }

     public String toString(){
         return "Z";
    }

    public Vector2d getPosition(){
        return pos;
    }

    public String getType(){
        return "Animal";
    }

    public boolean isAt(Vector2d position){
        return this.pos.equals(position);
    }

    private Vector2d giveVector(int rotationValue){
        //tlumacze gen na najblizsze przesuniecie
        switch (rotationValue){
            case 0:
                return new Vector2d(0,1);
            case 1:
                return new Vector2d(1,1);
            case 2:
                return new Vector2d(1,0);
            case 3:
                return new Vector2d(1,-1);
            case 4:
                return new Vector2d(0,-1);
            case 5:
                return new Vector2d(-1,-1);
            case 6:
                return new Vector2d(-1,0);
            case 7:
                return new Vector2d(-1,1);
        }
        return new Vector2d(0,0);
    }

    public void isDead(){
        dead = (energy == 0);
    }


    public void move(){
        if(!this.dead) {
            //zmiana rotatcji
            rotation = (rotation + genom.giveNextGen()) % 8;
            //wyznaczenie wektora odpowiadającego aktualnej rotacji
            Vector2d vect = giveVector(rotation);
            Vector2d newPos = map.moveTo(pos,pos.add(vect));
            if(newPos.equals(pos)){
                //obracam sie jesli nigdzie nie poszedlem
                rotation = (rotation+4)%8;
            }
            else{
                if(!newPos.equals(pos) && !newPos.equals(pos.add(vect))){
                    //jezeli jestem w piekielnym portalu i mnie przenioslo gdzies to odejmuje energie tak jakbym rodzil
                    this.energy -= (int)(this.energy*this.birthE);
                }
            }
            vect = pos;
            pos = newPos;
            positionChanged(vect, pos);
            //czas leci...
            days += 1;
            //..a sily brak...
            energy -= 1;
            //ale moge sie reprodukowac
            afterReproduction = false;
        }
    }

    public void eat(){
        energy += map.eatIfICan(this);
    }

    public Animal reproduce() {
        if(energy > minReproduceEnergy && !afterReproduction){
            //szukam kochanka
            Animal lover = map.getBestLover(this);
            //jesli jest ktos warty jego milosci
            if(lover != null){
                //obliczam energie dla dzieciaka
                int p1E = this.whileReproduce();
                int p2E = lover.whileReproduce();
                int childEnergy = p1E + p2E;
                //cud stworzenia
                Genom newGenom= new Genom(this, lover, this.genomLen, this.minNumMut, this.maxNumMut, this.isCrazy, this.randMut);
                Animal newBorn = new Animal(map, pos, childEnergy, minReproduceEnergy, birthE, newGenom, genomLen, minNumMut, maxNumMut, isCrazy, randMut);
                //dodaje do mapy
                map.place(newBorn);
                //dzieciak nie moze sie od razu rozmnażać (to nieetyczne)
                newBorn.afterReproduction = false;
                //zwracam dzieciaka do silnika
                return newBorn;

                
            }
        }
        return null;
    }

    public boolean canReproduce(){
        return (energy >= minReproduceEnergy);
    }

    public int whileReproduce(){
        int deltaE = (int)(energy*birthE);
        energy -= deltaE;
        afterReproduction = true;
        return deltaE;
    }

    
    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d actPos, Vector2d nextpos){
        for(IPositionChangeObserver obs:observers){
            obs.positionChanged(actPos, nextpos, this);
        }
    }

    public int getEnergy(){
        return this.energy;
    }

    public int getAge(){
        return this.days;
    }

    public int getChildrenNum(){
        return this.nOfChildrens;
    }

    public List<Integer> getGenom(){
        return this.genom.genes;
    }

    @Override
    public String loadSrc() {
        //sciezka do obrazka
        return "C:\\Users\\MagdalenaSkrok\\podzialaj\\PO2022\\Evolution\\src\\main\\resources\\animal.png";
    }


}
