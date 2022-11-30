package agh.ics.oop;


import java.util.Random;

public class GrassField extends AbstractWorldMap{
    
    private int nOfGrasses;

    private MapBoundary boundarizer = new MapBoundary();


    public GrassField(int nOfGrasses){
        super();

        this.nOfGrasses = nOfGrasses;

        for(int i=0;i<nOfGrasses;i++){
            placeGrass();
        }

    }

    private void placeGrass(){
        Vector2d pos = null;
        int maxPos = (int)(Math.sqrt(nOfGrasses*10));
        boolean notFound = true;
        while(notFound){
            pos = new Vector2d(randInt(0, maxPos), randInt(0, maxPos));
            notFound = false;
            if(isOccupied(pos)){
                notFound = true;
            }
        }
        Grass grass = new Grass(pos);
        objects.put(grass.getPosition(), grass);
        boundarizer.addElement(grass);

    }

    private int randInt(int a, int b){
        Random rn = new Random();
        int n = b-a+1;
        return Math.abs(rn.nextInt()%n) + a;
    }

    @Override
    public boolean place(Animal animal) {
        if(super.place(animal)) {
            boundarizer.addElement(animal);
            return true;
        }
        return false;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        super.positionChanged(oldPosition, newPosition);
        boundarizer.positionChanged(oldPosition, newPosition);
    }

    @Override
    public Vector2d[] limes() {
        return boundarizer.getBoundary();
    }

}
