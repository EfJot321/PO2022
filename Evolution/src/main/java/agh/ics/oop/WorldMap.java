package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldMap extends AbstractWorldMap{

    

    private int nOfGrasses;

    

    private List<Vector2d> jungle = new ArrayList<Vector2d>();

    public WorldMap(int width, int height, int n, int dE){
        super(width, height, dE);

        //tworzenie dzungli
        for(int x=(int) Math.floor(width/8);x<Math.ceil(width*7/8);x++){
            for(int y=(int) Math.floor(height/3);y<Math.ceil(height*2/3);y++){
                jungle.add(new Vector2d(x, y));
            }
        }

        
        nOfGrasses=n;
        for (int i=0;i<nOfGrasses;i++){
            placeGrass();
        }
    }

    public boolean isItJungle(Vector2d pos){
        return jungle.contains(pos);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(!position.follows(lim1)){
            return false;
        }
        if(!position.precedes(lim2)){
            return false;
        }
        //return super.canMoveTo(position);
        return true;
    }

    @Override
    public Vector2d[] limes() {
        return new Vector2d[]{lim1, lim2};
    }

    private void placeGrass(){
        Vector2d pos = null;
        boolean notFound = true;
        while(notFound){
            notFound = false;
            //zmienna losowa
            int marker = randInt(0, 10);
            //80%
            if(marker<=8){
                marker = randInt(0, jungle.size()-1);
                pos = jungle.get(marker);
            }
            //20%
            else{   
                pos = new Vector2d(randInt(0, this.width-1), randInt(0, this.height-1));
                if(jungle.contains(pos)){
                    notFound = true;
                    continue;
                }
            }
             
        }
        Plant grass = new Plant(pos);
        addToMap((IMapElement)grass, grass.getPosition());

    }

    private int randInt(int a, int b){
        Random rn = new Random();
        int n = b-a+1;
        return Math.abs(rn.nextInt()%n) + a;
    }

}