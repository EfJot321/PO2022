package agh.ics.oop;


public class Genom {
    
    private int ind;
    private int genomLen;
    private String genes;


    public Genom(String genes){
        this.genes = genes;
        ind = 0;
        genomLen = genes.length();
    }


    public int giveNextGen(){
        //pobieram aktualny gen
        char actGen = genes.charAt(ind);
        //przechodze do kolejnego genu
        ind++;
        if(ind>=genomLen){
            ind -= genomLen;
        }
        //zwracam int
        return Character.getNumericValue(actGen);  
        

    }


    public String toString(){
        return genes;
    }


}
