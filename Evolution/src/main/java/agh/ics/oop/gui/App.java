package agh.ics.oop.gui;


import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static java.lang.Math.abs;

public class App extends Application{

    
    private VBox allStaff;

    @Override
    public void init() {
        try {
            String src = "/home/filipjedrzejewski/PO2022/Evolution/src/main/configurations/config1.csv";
            
            Configuration conf = new Configuration(src);

            Vector2d[] startPoss = new Vector2d[3];
            startPoss[0] = new Vector2d(1, 10);
            startPoss[1] = new Vector2d(10, 7);
            startPoss[2] = new Vector2d(4, 3);
            SimulationEngine engine = new SimulationEngine(conf, startPoss, 300, this);
            Thread engineThread = new Thread(engine);
            engineThread.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = new GridPane();

        allStaff= new VBox(grid);

        Scene scene = new Scene(allStaff, 1000, 700);

        primaryStage.setScene(scene);
        primaryStage.show();


    }


    private GridPane actualScene(RectangularMap map){

        int squareSize = 33;

        GridPane grid = new GridPane();


        Vector2d start=map.limes()[0];
        Vector2d end=map.limes()[1];

        grid.setGridLinesVisible(true);

        int height=abs(start.y-end.y)+1;
        int width=abs(start.x-end.x)+1;

        grid.setPrefWidth(width);
        grid.setPrefHeight(height);

        Label label = new Label("y/x");

        GridPane.setHalignment(label, HPos.CENTER);
        grid.getColumnConstraints().add(new ColumnConstraints(squareSize));
        grid.getRowConstraints().add(new RowConstraints(squareSize));

        grid.add(label,0,0);


        for(int i=0; i<height; i++){
            Label label1 = new Label(Integer.toString(height+start.x-i-1));
            grid.add(label1,0,i+1);
            GridPane.setHalignment(label1, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(squareSize));
        }

        for(int i=0; i<width; i++){
            Label label1 = new Label(Integer.toString(i+start.x));
            grid.add(label1,i+1,0);
            GridPane.setHalignment(label1, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(squareSize));
        }
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                Vector2d v = new Vector2d(i,j);
                if(map.isOccupied(v)){
                    IMapElement obj = (IMapElement) map.objectAt(v);
                    Label label1 = new Label(obj.toString());
                    grid.add(label1,i+1,height-j);
                    GridPane.setHalignment(label1, HPos.CENTER);
                }
            }
        }

        return grid;

    }


    public void updateScene(RectangularMap map){
        GridPane grid = actualScene(map);
        allStaff.getChildren().remove(0);
        allStaff.getChildren().add(grid);
    }
    


    
}
