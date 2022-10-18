package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class Vector2dTest {

    @Test
    public void equalsTest(){
        System.out.print("Test metody equals(): ");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        if(!v1.equals(v2)){
            System.out.println("niezaliczony");
            return;
        }
        if(!v2.equals(v1)) {
            System.out.println("niezaliczony");
            return;
        }
        v2 = v1;
        if(!v1.equals(v2)){
            System.out.println("niezaliczony");
            return;
        }
        v2 = new Vector2d(1, 0);
        if(v1.equals(v2)){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(-1,5);
        if(v1.equals(v2)){
            System.out.println("niezaliczony");
            return;
        }
        System.out.println("zaliczony");
    }

    @Test
    public void toStringTest(){
        System.out.print("Test metody toString(): ");
        Vector2d v1 = new Vector2d(1,10);
        if(!v1.toString().equals("(1,10)")){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(0,0);
        if(!v1.toString().equals("(0,0)")){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(-100,0);
        if(!v1.toString().equals("(-100,0)")){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(10,-10);
        if(!v1.toString().equals("(10,-10)")){
            System.out.println("niezaliczony");
            return;
        }
        System.out.println("zaliczony");
    }

    @Test
    public void precedesTest(){
        System.out.print("Test metody precedes(): ");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        if(v1.precedes(v2)){
            System.out.println("niezaliczony");
            return;
        }
        if(v2.precedes(v1)){
            System.out.println("niezaliczony");
            return;
        }
        v2 = new Vector2d(-10, 0);
        if(v1.precedes(v2)){
            System.out.println("niezaliczony");
            return;
        }
        if(!v2.precedes(v1)){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(0,10);
        v2 = new Vector2d(-1, 10);
        if(v2.precedes(v1)){
            System.out.println("niezaliczony");
            return;
        }
        v2 = new Vector2d(0, 0);
        if(v2.precedes(v1)){
            System.out.println("niezaliczony");
            return;
        }
        System.out.println("zaliczony");
    }

    @Test
    public void followsTest(){
        System.out.print("Test metody follows(): ");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        if(!v1.follows(v2)){
            System.out.println("niezaliczony");
            return;
        }
        if(!v2.follows(v1)){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(2, 30);
        if(!v1.follows(v2)){
            System.out.println("niezaliczony");
            return;
        }
        if(v2.follows(v1)){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(0, -1);
        v2 = new Vector2d(-10, -2);
        if(!v1.follows(v2)){
            System.out.println("niezaliczony");
            return;
        }
        System.out.println("zaliczony");
    }

    @Test
    public void upperRightTest(){
        System.out.print("Test metody upperRight(): ");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        if(!v1.upperRight(v2).equals(new Vector2d(1,10))){
            System.out.println("niezaliczony");
            return;
        }
        if(!v2.upperRight(v1).equals(new Vector2d(1,10))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(2, 20);
        if(!v1.upperRight(v2).equals(new Vector2d(2,20))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(-10, 15);
        if(!v1.upperRight(v2).equals(new Vector2d(1,15))){
            System.out.println("niezaliczony");
            return;
        }
        v2 = new Vector2d(-11, 16);
        if(!v1.upperRight(v2).equals(new Vector2d(-10,16))){
            System.out.println("niezaliczony");
            return;
        }
        System.out.println("zaliczony");
    }

    @Test
    public void lowerLeftTest(){
        System.out.print("Test metody lowerLeft(): ");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        if(!v1.lowerLeft(v2).equals(new Vector2d(1,10))){
            System.out.println("niezaliczony");
            return;
        }
        if(!v2.lowerLeft(v1).equals(new Vector2d(1,10))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(2, 20);
        if(!v1.lowerLeft(v2).equals(new Vector2d(1,10))){
            System.out.println("niezaliczony");
            return;
        }
        if(!v2.lowerLeft(v1).equals(new Vector2d(1,10))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(-10, 15);
        if(!v1.lowerLeft(v2).equals(new Vector2d(-10,10))){
            System.out.println("niezaliczony");
            return;
        }
        v2 = new Vector2d(-11, 16);
        if(!v1.lowerLeft(v2).equals(new Vector2d(-11,15))){
            System.out.println("niezaliczony");
            return;
        }
        System.out.println("zaliczony");
    }

    @Test
    public void addTest(){
        System.out.print("Test metody add(): ");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        if(!v1.add(v2).equals(new Vector2d(2,20))){
            System.out.println("niezaliczony");
            return;
        }
        if(!v2.add(v1).equals(new Vector2d(2,20))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(2, 20);
        if(!v1.add(v2).equals(new Vector2d(3,30))){
            System.out.println("niezaliczony");
            return;
        }
        if(!v2.add(v1).equals(new Vector2d(3,30))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(-10, 15);
        if(!v1.add(v2).equals(new Vector2d(-9,25))){
            System.out.println("niezaliczony");
            return;
        }
        v2 = new Vector2d(-11, 16);
        if(!v1.add(v2).equals(new Vector2d(-21,31))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(11, -16);
        if(!v1.add(v2).equals(new Vector2d(0,0))){
            System.out.println("niezaliczony");
            return;
        }
        System.out.println("zaliczony");
    }

    @Test
    public void subtractTest(){
        System.out.print("Test metody subtract(): ");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        if(!v1.subtract(v2).equals(new Vector2d(0,0))){
            System.out.println("niezaliczony");
            return;
        }
        if(!v2.subtract(v1).equals(new Vector2d(0,0))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(2, 20);
        if(!v1.subtract(v2).equals(new Vector2d(1,10))){
            System.out.println("niezaliczony");
            return;
        }
        if(!v2.subtract(v1).equals(new Vector2d(-1,-10))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(-10, 15);
        if(!v1.subtract(v2).equals(new Vector2d(-11,5))){
            System.out.println("niezaliczony");
            return;
        }
        v2 = new Vector2d(-11, 16);
        if(!v1.subtract(v2).equals(new Vector2d(1,-1))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(11, -16);
        if(!v1.subtract(v2).equals(new Vector2d(22,-32))){
            System.out.println("niezaliczony");
            return;
        }
        System.out.println("zaliczony");
    }

    @Test
    public void oppositeTest(){
        System.out.print("Test metody opposite(): ");
        Vector2d v1 = new Vector2d(1,10);
        if(!v1.opposite().equals(new Vector2d(-1,-10))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(-1,10);
        if(!v1.opposite().equals(new Vector2d(1,-10))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(1,-10);
        if(!v1.opposite().equals(new Vector2d(-1,10))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(-1,-10);
        if(!v1.opposite().equals(new Vector2d(1,10))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(0,0);
        if(!v1.opposite().equals(new Vector2d(0,0))){
            System.out.println("niezaliczony");
            return;
        }
        v1 = new Vector2d(100, 200);
        if(!v1.opposite().opposite().equals(v1)){
            System.out.println("niezaliczony");
            return;
        }

        System.out.println("zaliczony");
    }

}
