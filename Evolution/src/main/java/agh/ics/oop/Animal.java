package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement{
//    private MapDirection dir;
    private Vector2d pos;

    private IWorldMap map;

    public Genom genom;
    private int genomLen;

    private int rotation = 0;
    private int days;
    private int energy;
    private int nOfChildrens = 0;
    private int minReproduceEnergy;
    private float birthE;
    public boolean afterReproduction;
    public boolean dead;


    List<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map, Vector2d initialPosition, int startEnergy, int mRe, float birthE,int genomLen){
        this.map = map;
        this.pos = initialPosition;
        this.energy = startEnergy;
        this.minReproduceEnergy = mRe;
        this.birthE = birthE;
        this.days = 0;
        this.genomLen = genomLen;

        this.genom = new Genom(this.genomLen);

        this.afterReproduction = false;
        this.dead = false;

    }

    public Animal(IWorldMap map, Vector2d initialPosition, int startEnergy, int mRe, float birthE,Genom genom, int genomLen){
        this.map = map;
        this.pos = initialPosition;
        this.energy = startEnergy;
        this.minReproduceEnergy = mRe;
        this.birthE = birthE;
        this.days = 0;
        this.genomLen = genomLen;

        this.genom = genom;

        this.afterReproduction = false;
        this.dead = false;

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
            Vector2d newPos = pos.add(vect);
            if (map.canMoveTo(newPos)) {
                pos = pos.add(vect);
                positionChanged(pos.subtract(vect), pos);
            }
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
                Genom newGenom= new Genom(this, lover, this.genomLen);
                Animal newBorn = new Animal(map, pos, childEnergy, minReproduceEnergy, birthE, newGenom, newGenom.genes.size());
                //dodaje do mapy
                if(map.place(newBorn)){
                    //dzieciak nie moze sie od razu rozmnażać (to nieetyczne)
                    newBorn.afterReproduction = false;
                    //zwracam dzieciaka do silnika
                    return newBorn;
                }
                else{
                    //jesli cos sie nie udalo to wracamy do punktu wyjscia
                    this.energy += p1E;
                    lover.energy += p2E;
                }
                
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
