package agh.ics.oop;


import java.util.Random;

public class GrassField extends AbstractWorldMap{
    private int nOfGrasses;


    public GrassField(int nOfGrasses){
        super();

        this.nOfGrasses = nOfGrasses;

        for(int i=0;i<nOfGrasses;i++){
            Grass grass = placeGrass(i);
            objects.put(grass.getPosition(), grass);
        }

    }

    private Grass placeGrass(int num){
        Vector2d pos = null;
        int maxPos = (int)(Math.sqrt(nOfGrasses*10));
        boolean notFound = true;
        while(notFound){
            pos = new Vector2d(randInt(0, maxPos), randInt(0, maxPos));
            notFound = false;
            for(int i=0;i<num;i++){
                if(isOccupied(pos)){
                    notFound = true;
                }
            }
        }
        return new Grass(pos);
    }

    private int randInt(int a, int b){
        Random rn = new Random();
        int n = b-a+1;
        return Math.abs(rn.nextInt()%n) + a;
    }



    @Override
    Vector2d[] limes() {
        Vector2d lim1 = new Vector2d(0, 0);
        Vector2d lim2 = new Vector2d(5, 5);
        if(objects.size()>0){
            lim1 = new Vector2d(0, 0);
            lim2 = new Vector2d(0, 0);
            for(Vector2d pos : objects.keySet()){
                lim1 = lim1.lowerLeft(pos);
                lim2 = lim2.upperRight(pos);
            }

        }
        //dla lepszego wygladu
        lim1.subtract(new Vector2d(1, 1));
        lim2.add(new Vector2d(1, 1));

        return new Vector2d[]{lim1, lim2};
    }

}
