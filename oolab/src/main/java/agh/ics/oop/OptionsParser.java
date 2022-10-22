package agh.ics.oop;

public class OptionsParser {

    public MoveDirection[] parse(String[] args){
        int n=0;
        MoveDirection[] toReturn = new MoveDirection[args.length];
        for(int i=0;i<args.length;i++){
            toReturn[i] = null;
            if(args[i].equals("f") || args[i].equals("forward")){
                toReturn[i] = MoveDirection.FORWARD;
            }
            if(args[i].equals("b") || args[i].equals("backward")){
                toReturn[i] = MoveDirection.BACKWARD;
            }
            if(args[i].equals("r") || args[i].equals("right")){
                toReturn[i] = MoveDirection.RIGHT;
            }
            if(args[i].equals("l") || args[i].equals("left")){
                toReturn[i] = MoveDirection.LEFT;
            }
            if(toReturn != null){
                n++;
            }
        }

        if(n<args.length){
            MoveDirection[] hTab = new MoveDirection[n];
            int i=0;
            int j=0;
            while(i < n){
                if(toReturn[j] != null){
                    hTab[i] = toReturn[j];
                    i++;
                }
                j++;
            }
            return hTab;
        }
        return toReturn;

    }
}
