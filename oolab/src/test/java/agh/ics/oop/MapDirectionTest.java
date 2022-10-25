package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MapDirectionTest {

    @Test
    public void nextTest(){
        System.out.println("Test metody next()");
        MapDirection dir = MapDirection.NORTH;
        assertEquals(MapDirection.EAST, dir.next());
        dir = dir.next();
        assertEquals(MapDirection.SOUTH, dir.next());
        dir = dir.next();
        assertEquals(MapDirection.WEST, dir.next());
        dir = dir.next();
        assertEquals(MapDirection.NORTH, dir.next());
    }

    @Test
    public void previousTest(){
        System.out.println("Test metody previous()");
        MapDirection dir = MapDirection.NORTH;
        assertEquals(MapDirection.WEST, dir.previous());
        dir = dir.previous();
        assertEquals(MapDirection.SOUTH, dir.previous());
        dir = dir.previous();
        assertEquals(MapDirection.EAST, dir.previous());
        dir = dir.previous();
        assertEquals(MapDirection.NORTH, dir.previous());
    }






}
