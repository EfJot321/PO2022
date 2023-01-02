package agh.ics.oop;

import java.util.*;

public class Genom {
    
    private int ind;
    private int genomLen;
    public List<Integer> genes;

    //konstruktor gdy podano mi rodzicow
    public Genom(Animal a1, Animal a2, int len){
        this.genomLen = len;
        genes = new ArrayList<>();
        //obliczanie procentow dla genow rodzicow
        double percentA1=100*a1.getEnergy()/(a1.getEnergy()+a2.getEnergy());
        double percentA2=100*a2.getEnergy()/(a1.getEnergy()+a2.getEnergy());
//        System.out.println("rodzic1:");
//        System.out.println(a1.getGenom());
//        System.out.println("rodzic2:");
//        System.out.println(a2.getGenom());
//        System.out.println(percentA1);
//        System.out.println(percentA2);
        //zamiana procentow na liczbe elementow z genow rodzicow
        int countA1= (int) Math.ceil(percentA1*len/100);
        int countA2= (int) Math.floor(percentA2*len/100);
//        System.out.println(a1.genom.genomLen);
//        System.out.println(countA1);
//        System.out.println(countA2);
        //losowanie strony od ktorej zaczne
        int side = randInt(0, 1);
//        System.out.println(side);
        if(side==1){
            //dodawanie od pierwszego rodzica od lewej
            for(int i=0;i<countA1;i++){
                genes.add(a1.getGenom().get(i));
//                System.out.println(genes);
            }
            //dodawanei od drugirgo rodzica od prawej
            for(int i=0;i<countA2;i++){
                genes.add(a2.getGenom().get(a2.getGenom().size()+i-countA2));
//                System.out.println(genes);
            }
        }
        else{
            //dodawanie od drugirgo rodzica od lewej
            for(int i=0;i<countA2;i++){
                genes.add(a2.getGenom().get(i));
//                System.out.println(genes);
            }
            //dodawanie od pierwszego rodzica od prawej
            for(int i=0;i<countA1;i++){
                genes.add(a1.getGenom().get(a1.getGenom().size()+i-countA1));
            }
        }
//        System.out.println(a1.getGenom());
//        System.out.println(a2.getGenom());
        System.out.println("dziecko");
        System.out.println(genes);
        //mutacje
        //ile mutacji
        int nOfMutation=randInt(0, genes.size()-1);
        System.out.println(nOfMutation);
        Set<Integer> changes=new TreeSet<>();
        //gdzie mutacje
        while(changes.size()<nOfMutation){
            changes.add(randInt(0,genes.size()-1));
        }
        //mutujemy - pelna losowosc
        Object[] changesArray=changes.toArray();
        for(int i=0; i<nOfMutation;i++){
            int oldGen= genes.get((int)changesArray[i]);
            int mutation=randInt(0,7);
            while(oldGen==mutation){
                mutation=randInt(0,7);
            }
            genes.remove((int)changesArray[i]);
            genes.add((int)changesArray[i],mutation);
        }
        System.out.println(genes);

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
