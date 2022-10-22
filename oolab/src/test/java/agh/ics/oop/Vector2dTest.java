package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    public void equalsTest(){
        System.out.println("Test metody equals()");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        assertTrue(v1.equals(v2), v1.toString()+", "+v2.toString());
        assertTrue(v2.equals(v1), v2.toString()+", "+v1.toString());
        v2 = v1;
        assertTrue(v1.equals(v2), v1.toString()+", "+v2.toString());
        v2 = new Vector2d(1, 0);
        assertFalse(v1.equals(v2), v1.toString()+", "+v2.toString());
        v1 = new Vector2d(-1,5);
        assertFalse(v1.equals(v2), v1.toString()+", "+v2.toString());
    }

    @Test
    public void toStringTest(){
        System.out.println("Test metody toString()");
        Vector2d v1 = new Vector2d(1,10);
        assertEquals("(1,10)", v1.toString());
        v1 = new Vector2d(0,0);
        assertEquals("(0,0)", v1.toString());
        v1 = new Vector2d(-100,0);
        assertEquals("(-100,0)", v1.toString());
        v1 = new Vector2d(10,-10);
        assertEquals("(10,-10)", v1.toString());
    }

    @Test
    public void precedesTest(){
        System.out.print("Test metody precedes(): ");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        assertTrue(v1.precedes(v2), v1.toString()+", "+v2.toString());
        assertTrue(v2.precedes(v1), v2.toString()+", "+v1.toString());
        v2 = new Vector2d(-10, 0);
        assertFalse(v1.precedes(v2), v1.toString()+", "+v2.toString());
        assertTrue(v2.precedes(v1), v2.toString()+", "+v1.toString());
        v1 = new Vector2d(0,10);
        v2 = new Vector2d(-1, 10);
        assertTrue(v2.precedes(v1), v2.toString()+", "+v1.toString());
        v2 = new Vector2d(0, 0);
        assertTrue(v2.precedes(v1), v2.toString()+", "+v1.toString());
        v1 = new Vector2d(-1, -5);
        assertFalse(v2.precedes(v1), v2.toString()+", "+v1.toString());
    }

    @Test
    public void followsTest(){
        System.out.println("Test metody follows()");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        assertTrue(v1.follows(v2), v1.toString()+", "+v2.toString());
        assertTrue(v2.follows(v1), v2.toString()+", "+v1.toString());
        v2 = new Vector2d(-10, 0);
        assertTrue(v1.follows(v2), v1.toString()+", "+v2.toString());
        assertFalse(v2.follows(v1), v2.toString()+", "+v1.toString());
        v1 = new Vector2d(0,10);
        v2 = new Vector2d(-1, 10);
        assertFalse(v2.follows(v1), v2.toString()+", "+v1.toString());
        v2 = new Vector2d(0, 0);
        assertFalse(v2.follows(v1), v2.toString()+", "+v1.toString());
        v1 = new Vector2d(-1, -5);
        assertTrue(v2.follows(v1), v2.toString()+", "+v1.toString());
    }

    @Test
    public void upperRightTest(){
        System.out.println("Test metody upperRight()");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        assertEquals(new Vector2d(1,10), v1.upperRight(v2));
        assertEquals(new Vector2d(1,10), v2.upperRight(v1));
        v1 = new Vector2d(2, 20);
        assertEquals(new Vector2d(2,20), v1.upperRight(v2));
        v1 = new Vector2d(-10, 15);
        assertEquals(new Vector2d(1,15), v1.upperRight(v2));
        v2 = new Vector2d(-11, 16);
        assertEquals(new Vector2d(-10,16), v1.upperRight(v2));
    }

    @Test
    public void lowerLeftTest(){
        System.out.println("Test metody lowerLeft()");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        assertEquals(new Vector2d(1,10), v1.lowerLeft(v2));
        assertEquals(new Vector2d(1,10), v2.lowerLeft(v1));
        v1 = new Vector2d(2, 20);
        assertEquals(new Vector2d(1,10), v1.lowerLeft(v2));
        assertEquals(new Vector2d(1,10), v2.lowerLeft(v1));
        v1 = new Vector2d(-10, 15);
        assertEquals(new Vector2d(-10,10), v1.lowerLeft(v2));
        v2 = new Vector2d(-11, 16);
        assertEquals(new Vector2d(-11,15), v1.lowerLeft(v2));
    }

    @Test
    public void addTest(){
        System.out.println("Test metody add()");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        assertEquals(new Vector2d(2,20), v1.add(v2));
        assertEquals(new Vector2d(2,20), v2.add(v1));
        v1 = new Vector2d(2, 20);
        assertEquals(new Vector2d(3,30), v1.add(v2));
        assertEquals(new Vector2d(3,30), v2.add(v1));
        v1 = new Vector2d(-10, 15);
        assertEquals(new Vector2d(-9,25), v1.add(v2));
        v2 = new Vector2d(-11, 16);
        assertEquals(new Vector2d(-21,31), v1.add(v2));
        v1 = new Vector2d(11, -16);
        assertEquals(new Vector2d(0,0), v1.add(v2));
    }

    @Test
    public void subtractTest(){
        System.out.println("Test metody subtract()");
        Vector2d v1 = new Vector2d(1,10);
        Vector2d v2 = new Vector2d(1,10);
        assertEquals(new Vector2d(0,0), v1.subtract(v2));
        assertEquals(new Vector2d(0,0), v2.subtract(v1));
        v1 = new Vector2d(2, 20);
        assertEquals(new Vector2d(1,10), v1.subtract(v2));
        assertEquals(new Vector2d(-1,-10), v2.subtract(v1));
        v1 = new Vector2d(-10, 15);
        assertEquals(new Vector2d(-11,5), v1.subtract(v2));
        v2 = new Vector2d(-11, 16);
        assertEquals(new Vector2d(1,-1), v1.subtract(v2));
        v1 = new Vector2d(11, -16);
        assertEquals(new Vector2d(22,-32), v1.subtract(v2));
    }

    @Test
    public void oppositeTest(){
        System.out.println("Test metody opposite()");
        Vector2d v1 = new Vector2d(1,10);
        assertEquals(new Vector2d(-1,-10), v1.opposite());
        v1 = new Vector2d(-1,10);
        assertEquals(new Vector2d(1,-10), v1.opposite());
        v1 = new Vector2d(1,-10);
        assertEquals(new Vector2d(-1,10), v1.opposite());
        v1 = new Vector2d(-1,-10);
        assertEquals(new Vector2d(1,10), v1.opposite());
        v1 = new Vector2d(0,0);
        assertEquals(new Vector2d(0,0), v1.opposite());
        v1 = new Vector2d(100, 200);
        assertEquals(v1, v1.opposite().opposite());
    }

}
