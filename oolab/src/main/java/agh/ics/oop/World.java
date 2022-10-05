package agh.ics.oop;

public class World {
    public static void main(String[] args){
        //napis poczatkowy
        System.out.println("Start");

        //zamiana tablicy stringow na tablice enumow
        int N = args.length;
        Direction[] T = new Direction[N];
        for(int i=0;i<N;i++){
            if(args[i].equals("f")){
                T[i] = Direction.FORWARD;
            }
            if(args[i].equals("b")){
                T[i] = Direction.BACKWARD;
            }
            if(args[i].equals("l")){
                T[i] = Direction.LEFT;
            }
            if(args[i].equals("r")){
                T[i] = Direction.RIGHT;
            }

        }

        //uruchomienie metody run
        run(T);

        //napis koncowy
        System.out.println("Stop");
    }

    static void run(Direction[] tab){
        System.out.print("Zwierzak ");
        for(Direction ele : tab){
            if(ele == Direction.FORWARD){
                System.out.println("idzie do przodu.");
            }
            if(ele == Direction.BACKWARD){
                System.out.println("idzie do tylu.");
            }
            if(ele == Direction.RIGHT){
                System.out.println("skreca w prawo.");
            }
            if(ele == Direction.LEFT){
                System.out.println("skreca w lewo.");
            }
        }

    }
}
