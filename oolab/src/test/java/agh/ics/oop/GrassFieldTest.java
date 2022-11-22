package agh.ics.oop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


public class GrassFieldTest {

    IWorldMap mapSim(String[] args, Vector2d[] positions, int nOfGrasses){
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(nOfGrasses);
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        return map;
    }
    
    @Test
    public void oneAnimaltest(){
        String[] args;
        Vector2d[] positions;
        IWorldMap map;

        //czy chodze do przodu
        args = new String[]{"forward",};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5);
        assertNotNull(map.objectAt(new Vector2d(2, 3)));


        //czy chodze do tylu
        args = new String[]{"backward",};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5);
        assertNotNull(map.objectAt(new Vector2d(2, 1)));


        //obroty
        args = new String[]{"right", "r", "forward"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5);
        assertNotNull(map.objectAt(new Vector2d(2, 1)));

        args = new String[]{"left", "l", "forward"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5);
        assertNotNull(map.objectAt(new Vector2d(2, 1)));


        //kombinacje wszystkiego
        args = new String[]{"f", "forward", "l", "b", "left"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5);
        assertNotNull(map.objectAt(new Vector2d(3, 4)));

        //kombinacje wszystkiego, tylko ze teraz z niepoprawnymi danymi
        args = new String[]{"f", "forward","braykuv", "l", "b", "kdj", "a","left"};
        positions = new Vector2d[]{new Vector2d(2, 2)};
        map = mapSim(args, positions, 5);
        assertNotNull(map.objectAt(new Vector2d(3, 4)));

    
    }

    @Test
    public void noOrTwoOrMoreAnimalsTest(){
        String[] args;
        Vector2d[] positions;
        IWorldMap map;

        
        //cztery zwierzaki
        args = new String[]{"f", "b", "l", "r", "f", "f", "f", "f", "l", "l", "r", "b", "f", "b", "f", "f"};
        positions = new Vector2d[]{new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(3, 2), new Vector2d(8, 0) };
        map = mapSim(args, positions, 1);
        assertNotNull(map.objectAt(new Vector2d(1, 4)));
        assertNotNull(map.objectAt(new Vector2d(2, 3)));
        assertNotNull(map.objectAt(new Vector2d(4, 4)));
        assertNotNull(map.objectAt(new Vector2d(9, 0)));


        args = new String[]{"f", "b", "l", "r", "f", "f", "f", "f", "l", "l", "r", "b", "f", "b", "f", "f", "r", "l", "l", "r", "f", "f", "f", "f", "r", "r", "r", "r"};
        positions = new Vector2d[]{new Vector2d(10, 10), new Vector2d(10, 11), new Vector2d(10, 12), new Vector2d(10, 13) };
        map = mapSim(args, positions, 20);
        assertNotNull(map.objectAt(new Vector2d(8, 13)));
        assertNotNull(map.objectAt(new Vector2d(9, 11)));
        assertNotNull(map.objectAt(new Vector2d(11, 12)));
        assertNotNull(map.objectAt(new Vector2d(11, 10)));

        //tworzenie zwierzat na sobie
        int counter;
        args = new String[]{};
        positions = new Vector2d[]{new Vector2d(3, 4), new Vector2d(4, 4), new Vector2d(3, 4), new Vector2d(5, 2)};
        map = mapSim(args, positions, 5);
        counter = 0;
        for(int i=-100;i<100;i++){
            for(int j=-100;j<100;j++){
                if(map.objectAt(new Vector2d(i, j)) != null && ((IMapElement) map.objectAt(new Vector2d(i, j))).getType().equals("Animal")){
                    counter++;
                }
            }
        }
        assertEquals(3, counter);

        args = new String[]{};
        positions = new Vector2d[]{new Vector2d(3, 4), new Vector2d(4, 4), new Vector2d(3, 4), new Vector2d(5, 2), new Vector2d(3, 4), new Vector2d(0, 0), new Vector2d(-1,-1)};
        map = mapSim(args, positions, 5);
        counter = 0;
        for(int i=-100;i<100;i++){
            for(int j=-100;j<100;j++){
                if(map.objectAt(new Vector2d(i, j)) != null && ((IMapElement) map.objectAt(new Vector2d(i, j))).getType().equals("Animal")){
                    counter++;
                }
            }
        }
        assertEquals(5, counter);

        
    }

    
}