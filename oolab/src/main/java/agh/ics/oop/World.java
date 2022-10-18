package agh.ics.oop;

public class World {


    public static void main(String[] args){
        //zad1(args);
        zad2(args);
    }

    static void zad2(String[] args){
        System.out.println("Zadanie 2");
        checkVector2d();
        checkMapDirection();
    }

    static void checkVector2d(){
        System.out.println("sprawdzenie funkcji wektorow");
        System.out.print("v1: ");
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        System.out.print("v2: ");
        Vector2d position2 = new Vector2d(-1,3);
        System.out.println(position2);
        System.out.print("wynik funkcji: ");
        System.out.println(position1.add(position2));
    }

    static void checkMapDirection(){
        System.out.println("sprawdzenie funkcji mapdirection");
        System.out.print("Dir1: ");
        MapDirection dir1 = MapDirection.SOUTH;
        System.out.println(dir1);
        System.out.print("Dir2: ");
        MapDirection dir2 = MapDirection.WEST;
        System.out.println(dir2);
        System.out.print("wynik funkcji: ");
        System.out.println(dir1.toUnitVector());
    }

    static void zad1(String[] args){
        //napis poczatkowy
        System.out.println("Zadanie 1");
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
        for(Direction ele : tab){
            System.out.print("Zwierzak ");
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
