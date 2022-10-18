package agh.ics.oop;

import org.junit.jupiter.api.Test;

public class MapDirectionTest {

    @Test
    public void nextTest(){
        System.out.print("Test metody next(): ");
        MapDirection dir = MapDirection.NORTH;
        if(dir.next() != MapDirection.EAST){
            System.out.println("niezaliczony");
            return;
        }
        dir = dir.next();
        if(dir.next() != MapDirection.SOUTH){
            System.out.println("niezaliczony");
            return;
        }
        dir = dir.next();
        if(dir.next() != MapDirection.WEST){
            System.out.println("niezaliczony");
            return;
        }
        dir = dir.next();
        if(dir.next() != MapDirection.NORTH){
            System.out.println("niezaliczony");
            return;
        }
        System.out.println("zaliczony");
    }

    @Test
    public void previousTest(){
        System.out.print("Test metody previous(): ");
        MapDirection dir = MapDirection.NORTH;
        if(dir.previous() != MapDirection.WEST){
            System.out.println("niezaliczony");
            return;
        }
        dir = dir.previous();
        if(dir.previous() != MapDirection.SOUTH){
            System.out.println("niezaliczony");
            return;
        }
        dir = dir.previous();
        if(dir.previous() != MapDirection.EAST){
            System.out.println("niezaliczony");
            return;
        }
        dir = dir.previous();
        if(dir.previous() != MapDirection.NORTH){
            System.out.println("niezaliczony");
            return;
        }
        System.out.println("zaliczony");
    }






}
