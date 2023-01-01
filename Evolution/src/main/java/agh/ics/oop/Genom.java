package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Genom {
    
    private int ind;
    private int genomLen;
    public List<Integer> genes;

    //konstruktor gdy podano mi rodzicow
    public Genom(Animal a1, Animal a2, int len){
        this.genomLen = len;
        genes = new ArrayList<>();
        //obliczanie procentow dla genow rodzicow
        int percentA1=a1.getEnergy()/(a1.getEnergy()+a2.getEnergy());
        int percentA2=a2.getEnergy()/(a1.getEnergy()+a2.getEnergy());
        //zamiana procentow na liczbe elementow z genow rodzicow
        int countA1= (int) Math.ceil(percentA1*len);
        int countA2= (int) Math.floor(percentA2*len);
        //losowanie strony od ktorej zaczne
        int side = randInt(0, 1);
        if(side==1){
            //dodawanie od pierwszego rodzica od lewej
            for(int i=0;i<countA1;i++){
                genes.add(a1.getGenom().get(i));
            }
            //dodawanei od drugirgo rodzica od prawej
            for(int i=0;i<countA2;i++){
                genes.add(a2.getGenom().get(a2.getGenom().size()-i-1));
            }
        }
        else{
            //dodawanie od drugirgo rodzica od lewej
            for(int i=0;i<countA2;i++){
                genes.add(a2.getGenom().get(i));
            }
            //dodawanie od pierwszego rodzica od prawej
            for(int i=0;i<countA1;i++){
                genes.add(a1.getGenom().get(a1.getGenom().size()-i-1));
            }
        }
        //mutacje
        //ile mutacji
        int nOfMutation=randInt(0, genes.size()-1);
        Set<Integer> changes=null;
        //gdzie mutacje
        while(changes.size()<nOfMutation){
            changes.add(randInt(0,genes.size()-1));
        }
        //mutujemy - pelna losowosc
        Object[] changesArray=changes.toArray();
        for(int i=0; i<nOfMutation;i++){
            genes.remove(changesArray[i]);
            genes.add((int)changesArray[i],(randInt(0,7)));

        }

        //mutujemy - lekka korekta
//        Object[] changesArray1=changes.toArray();
//        for(int i=0; i<nOfMutation;i++){
//            int gen=genes.get((int)changesArray1[i]);
//            genes.remove(changesArray[i]);
//            int side1=randInt(0,1);
//            if(side1==1){
//                genes.add((int)changesArray[i],gen+1);
//            }
//            else{
//                genes.add((int)changesArray[i],gen-1);
//            }
//        }
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
