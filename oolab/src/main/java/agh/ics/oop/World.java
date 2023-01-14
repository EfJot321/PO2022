package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

public class World {


    public static void main(String[] args) {
        Application.launch(App.class, args);
        // try{


        //     MoveDirection[] directions = new OptionsParser().parse(args);
        //     //IWorldMap map = new RectangularMap(15, 5);
        //     IWorldMap map = new GrassField(10);
        //     Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) , new Vector2d(-5, 0)};
        //     IEngine engine = new SimulationEngine(directions, map, positions);
        //     engine.run();
        //     System.out.println(map);
        // }
        // catch(IllegalArgumentException ex){
        //     System.out.println(ex);
        //     return;
        // }

    }


    static void run(Direction[] tab) {
        for (Direction ele : tab) {
            System.out.print("Zwierzak ");
            if (ele == Direction.FORWARD) {
                System.out.println("idzie do przodu.");
            }
            if (ele == Direction.BACKWARD) {
                System.out.println("idzie do tylu.");
            }
            if (ele == Direction.RIGHT) {
                System.out.println("skreca w prawo.");
            }
            if (ele == Direction.LEFT) {
                System.out.println("skreca w lewo.");
            }
        }

    }
}
