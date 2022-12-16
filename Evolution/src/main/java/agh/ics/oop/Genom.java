package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Genom {
    
    private int ind;
    private int genomLen;
    private String genes;


    public Genom(String genes){
        this.genes = genes;
        ind = 0;
        genomLen = genes.length();
    }


    public Vector2d giveGenVector2d(){
        //pobieram aktualny gen
        char actGen = genes.charAt(ind);
        //przechodze do kolejnego genu
        ind++;
        if(ind>=genomLen){
            ind -= genomLen;
        }
        //tlumacze gen na najblizsze przesuniecie
        switch (actGen){
            case '0':
                return new Vector2d(0,1);
            case '1':
                return new Vector2d(1,1);
            case '2':
                return new Vector2d(1,0);
            case '3':
                return new Vector2d(1,-1);
            case '4':
                return new Vector2d(0,-1);
            case '5':
                return new Vector2d(-1,-1);
            case '6':
                return new Vector2d(-1,0);
            case '7':
                return new Vector2d(-1,1);
        }
        return new Vector2d(0,0);

    }


    public String toString(){
        return genes;
    }


}
