package agh.ics.oop;


import java.util.Random;

public class RectangularMap extends AbstractWorldMap{

    Vector2d lim1;
    Vector2d lim2;
    private int nOfGrasses;

    private int width;
    private int height;

    public RectangularMap(int width, int height, int n){
        super();
        lim1 = new Vector2d(0, 0);
        lim2 = new Vector2d(width-1, height-1);
        this.width=width;
        this.height=height;
        nOfGrasses=n;
        for (int i=0;i<nOfGrasses;i++){
            placeGrass();
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(!position.follows(lim1)){
            return false;
        }
        if(!position.precedes(lim2)){
            return false;
        }
        return super.canMoveTo(position);
    }

    @Override
    public Vector2d[] limes() {
        return new Vector2d[]{lim1, lim2};
    }

    private void placeGrass(){
        Vector2d pos = null;
        boolean notFound = true;
        while(notFound){
            pos = new Vector2d(randInt(0, this.width-1), randInt(0, this.height-1));
            notFound = false;
            if(isOccupied(pos)){
                notFound = true;
            }
        }
        Grass grass = new Grass(pos);
        objects.put(grass.getPosition(), grass);


    }

    private int randInt(int a, int b){
        Random rn = new Random();
        int n = b-a+1;
        return Math.abs(rn.nextInt()%n) + a;
    }

}
