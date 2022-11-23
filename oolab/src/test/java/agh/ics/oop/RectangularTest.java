package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class RectangularTest {

    IWorldMap mapSim(String[] args, Vector2d[] positions, int width, int height){
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(width, height);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        return map;
    }
    
    @Test
    public void oneAnimaltest(){
        String[] args;
        Vector2d[] positions;
        IWorldMap map;

        //czy chodze do przodu i nie wychodze poza mape
        args = new String[]{"forward",};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5, 5);
        assertNotNull(map.objectAt(new Vector2d(2, 3)));

        args = new String[]{"forward","f", "f", "forward", "forward"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5, 5);
        assertNotNull(map.objectAt(new Vector2d(2, 4)));


        //czy chodze do tylu i nie wychodze poza mape
        args = new String[]{"backward",};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5, 5);
        assertNotNull(map.objectAt(new Vector2d(2, 1)));

        args = new String[]{"backward","b", "b", "backward", "backward"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5, 5);
        assertNotNull(map.objectAt(new Vector2d(2, 0)));

        //nie wychodze z prawej poza mape
        args = new String[]{"right", "r", "forward"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5, 5);
        assertNotNull(map.objectAt(new Vector2d(2, 1)));

        args = new String[]{"right", "forward", "f", "f", "f", "f"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5, 5);
        assertNotNull(map.objectAt(new Vector2d(4, 2)));
        

        //nie wychodze z lewej poza mape
        args = new String[]{"left", "l", "forward"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5, 5);
        assertNotNull(map.objectAt(new Vector2d(2, 1)));

        args = new String[]{"left", "forward", "f", "f", "f", "f"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5, 5);
        assertNotNull(map.objectAt(new Vector2d(0, 2)));


        //kombinacje wszystkiego
        args = new String[]{"f", "forward", "l", "b", "left"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5, 5);
        assertNotNull(map.objectAt(new Vector2d(3, 4)));

        args = new String[]{"b", "left", "b", "backward", "r", "right", "forward", "right", "forward", "f"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5, 5);
        assertNotNull(map.objectAt(new Vector2d(4, 0)));

        args = new String[]{"r", "right", "right", "forward", "backward", "l", "forward", "b", "b", "b", "b", "b", "right"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5, 5);
        assertNotNull(map.objectAt(new Vector2d(2, 4)));


        //kombinacje wszystkiego, tylko ze teraz z niepoprawnymi danymi
        boolean isEx = false;
        try{
            args = new String[]{"f", "forward","braykuv", "l", "b", "kdj", "a","left"};
            positions = new Vector2d[]{new Vector2d(2, 2)};
            map = mapSim(args, positions, 5, 5);
        }
        catch(IllegalArgumentException ex){
            isEx = true;
        }
        assertTrue(isEx);


        isEx = false;
        try {
            args = new String[]{"b", "left", "b", "hyilcbvyl", "backward", "r", "right", "k","forward", "rrr", "right", "forward", "f"};
            positions = new Vector2d[]{new Vector2d(2, 2)};
            map = mapSim(args, positions, 5, 5);
        }
        catch(IllegalArgumentException ex){
            isEx = true;
        }
        assertTrue(isEx);

        isEx = false;
        try {
            args = new String[]{"no tak","r", "ojaaaa","right", "right", "forward", "backward", "l", "forward", "b", "b", "b", "ok","b", "b", "right"};
            positions = new Vector2d[]{new Vector2d(2, 2)};
            map = mapSim(args, positions, 5, 5);
        }
        catch(IllegalArgumentException ex){
            isEx = true;
        }
        assertTrue(isEx);
    
    }

    @Test
    public void noOrTwoOrMoreAnimalsTest(){
        String[] args;
        Vector2d[] positions;
        IWorldMap map;

        //dwa zwierzeta - zderzenia, kontrola dwoch stworzen, niewychodzenie poza mape
        args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
        map = mapSim(args, positions, 10, 5);
        assertNotNull(map.objectAt(new Vector2d(3, 4)));
        assertNotNull(map.objectAt(new Vector2d(2, 0)));

        args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "b", "b", "b", "b", "b", "b", "b", "b"};
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
        map = mapSim(args, positions, 10, 5);
        assertNotNull(map.objectAt(new Vector2d(2, 4)));
        assertNotNull(map.objectAt(new Vector2d(3, 0)));

        args = new String[]{"f", "b", "l", "r", "f", "f", "f", "f", "l", "l", "r", "b", "f", "b", "f", "f", "b", "b"};
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4)};
        map = mapSim(args, positions, 10, 5);
        assertNotNull(map.objectAt(new Vector2d(1, 3)));
        assertNotNull(map.objectAt(new Vector2d(5, 1)));

        //cztery zwierzaki
        args = new String[]{"f", "b", "l", "r", "f", "f", "f", "f", "l", "l", "r", "b", "f", "b", "f", "f"};
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(3, 2), new Vector2d(8, 0) };
        map = mapSim(args, positions, 10, 5);
        assertNotNull(map.objectAt(new Vector2d(1, 4)));
        assertNotNull(map.objectAt(new Vector2d(2, 3)));
        assertNotNull(map.objectAt(new Vector2d(4, 4)));
        assertNotNull(map.objectAt(new Vector2d(9, 0)));

        args = new String[]{"f", "b", "l", "r", "f", "f", "f", "f", "l", "l", "r", "b", "f", "b", "f", "f", "r", "l", "l", "r", "f", "f", "f", "f", "r", "r", "r", "r", "f", "f", "f", "f"};
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(3, 2), new Vector2d(8, 0) };
        map = mapSim(args, positions, 10, 5);
        assertNotNull(map.objectAt(new Vector2d(1, 4)));
        assertNotNull(map.objectAt(new Vector2d(2, 4)));
        assertNotNull(map.objectAt(new Vector2d(3, 3)));
        assertNotNull(map.objectAt(new Vector2d(8, 0)));

        args = new String[]{"f", "b", "l", "r", "f", "f", "f", "f", "l", "l", "r", "b", "f", "b", "f", "f", "r", "l", "l", "r", "f", "f", "f", "f", "r", "r", "r", "r"};
        positions = new Vector2d[]{new Vector2d(10, 10), new Vector2d(10, 11), new Vector2d(10, 12), new Vector2d(10, 13) };
        map = mapSim(args, positions, 20, 20);
        assertNotNull(map.objectAt(new Vector2d(8, 13)));
        assertNotNull(map.objectAt(new Vector2d(9, 11)));
        assertNotNull(map.objectAt(new Vector2d(11, 12)));
        assertNotNull(map.objectAt(new Vector2d(11, 10)));

        //brak zwierzat
        args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "b", "b", "b", "b", "b", "b", "b", "b"};
        positions = new Vector2d[]{};
        map = mapSim(args, positions, 10, 10);
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                assertNull(map.objectAt(new Vector2d(i, j)));
            }
        }

        //tworzenie zwierzat na sobie lub poza mapa
        boolean isEx = false;
        try {
            args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "b", "b", "b", "b", "b", "b", "b", "b"};
            positions = new Vector2d[]{new Vector2d(3, 4), new Vector2d(4, 4), new Vector2d(3, 4), new Vector2d(5, 2)};
            map = mapSim(args, positions, 5, 5);
        }
        catch(IllegalArgumentException ex){
            isEx = true;
        }
        assertTrue(isEx);

        isEx = false;
        try{
            args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "b", "b", "b", "b", "b", "b", "b", "b"};
            positions = new Vector2d[]{new Vector2d(3, 4), new Vector2d(4, 4), new Vector2d(3, 4), new Vector2d(5, 2), new Vector2d(3, 4), new Vector2d(0, 0), new Vector2d(-1,-1)};
            map = mapSim(args, positions, 5, 5);
        }
        catch(IllegalArgumentException ex){
            isEx = true;
        }
        assertTrue(isEx);

        
    }

    
}
