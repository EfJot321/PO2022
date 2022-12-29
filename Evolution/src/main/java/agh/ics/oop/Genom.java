package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Genom {
    
    private int ind;
    private int genomLen;
    public List<Integer> genes;

    //konstruktor gdy podano mi rodzicow
    public Genom(Animal a1, Animal a2, int len){
        this.genomLen = len;
        //TODO
    }

    //konstruktor gdy mam tylko dlugosc genotypu
    public Genom(int len){
        genomLen = len;
        genes = new ArrayList<>();
        for(int i=0;i<genomLen;i++){
            genes.add(randInt(0, 7));
        }
    }

    private int randInt(int a, int b){
        Random rn = new Random();
        int n = b-a+1;
        return Math.abs(rn.nextInt()%n) + a;
    }


    public int giveNextGen(){
        //pobieram aktualny gen
        int actGen = genes.get(ind);
        //przechodze do kolejnego genu
        ind++;
        if(ind>=genomLen){
            ind -= genomLen;
        }
        //zwracam
        return actGen;  
    }




    public String toString(){
        return genes.toString();
    }


}
