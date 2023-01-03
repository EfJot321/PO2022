package agh.ics.oop;

import java.util.*;

public class Genom {
    
    private int ind;
    private int genomLen;
    private int minM, maxM;
    private boolean isCrazy;
    public List<Integer> genes;

    //konstruktor gdy podano mi rodzicow
    public Genom(Animal a1, Animal a2, int len, int minM, int maxM, boolean isCrazy, boolean randMut){
        this.genomLen = len;
        this.minM = minM;
        this.maxM = maxM;
        this.isCrazy = isCrazy;

        genes = new ArrayList<>();
        //obliczanie procentow dla genow rodzicow
        double percentA1=100*a1.getEnergy()/(a1.getEnergy()+a2.getEnergy());
        //double percentA2=100*a2.getEnergy()/(a1.getEnergy()+a2.getEnergy());
        //zamiana procentow na liczbe elementow z genow rodzicow
        int countA1= (int) Math.ceil(percentA1*genomLen/100);
        int countA2=genomLen-countA1;
        //losowanie strony od ktorej zaczne
        int side = randInt(0, 1);
        if(side==1){
            //dodawanie od pierwszego rodzica od lewej
            for(int i=0;i<countA1;i++){
                genes.add(a1.getGenom().get(i));
            }
            //dodawanie od drugiego rodzica od prawej
            for(int i=0;i<countA2;i++){
                genes.add(a2.getGenom().get(a2.getGenom().size()+i-countA2));
            }
        }
        else{
            //dodawanie od drugiego rodzica od lewej
            for(int i=0;i<countA2;i++){
                genes.add(a2.getGenom().get(i));
            }
            //dodawanie od pierwszego rodzica od prawej
            for(int i=0;i<countA1;i++){
                genes.add(a1.getGenom().get(a1.getGenom().size()+i-countA1));
            }
        }
        //mutacje
        //ile mutacji
        int nOfMutation=randInt(minM, maxM);
        List<Integer> changes = new ArrayList<>();
        //gdzie mutacje
        while(changes.size()<nOfMutation){
            int ind = randInt(0,genes.size()-1);
            if(!changes.contains(ind)){
                changes.add(ind);
            }
        }
        if(randMut){
            //mutujemy - pelna losowosc
            for(int i=0; i<nOfMutation;i++){
                int oldGen= genes.get(changes.get(i));
                int mutation=randInt(0,7);
                while(oldGen==mutation){
                    mutation=randInt(0,7);
                }
                genes.remove(changes.get(i));
                genes.add(changes.get(i),mutation);
            }
        }
        else {
            //mutujemy - lekka korekta
            for(int i=0; i<nOfMutation;i++){
                int gen=genes.get(changes.get(i));
                genes.remove(changes.get(i));
                int side1=randInt(0,1);
                if(side1==1){
                    genes.add(changes.get(i),(gen+1)%8);
                }
                else{
                    genes.add(changes.get(i),(gen-1)%8);
                }
            }
        }



    }

    //konstruktor gdy nie mam rodzicow
    public Genom(int len, boolean isCrazy){
        this.genomLen = len;
        this.isCrazy = isCrazy;

        //genom wybierany losowo
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
        if(isCrazy){
            if(randInt(0,100) < 80){
                ind++;
            }
            else{
                ind = randInt(0,this.genomLen-1);
            }
        }
        else {
            ind++;
        }
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
