package agh.ics.oop.gui;

import java.util.List;

import agh.ics.oop.Configuration;
import agh.ics.oop.IMapElement;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.Vector2d;
import agh.ics.oop.WorldMap;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class Window extends Thread{

    private SimulationEngine engine;
    private VBox allStaff;
    private Stage simulationStage;

    public Window(String src){
        Configuration conf = new Configuration(src);
        engine = new SimulationEngine(conf, 300, this);

        GridPane grid = new GridPane();
        allStaff= new VBox(grid);
        Scene scene = new Scene(allStaff, 1000, 800);


        simulationStage = new Stage();
        simulationStage.setScene(scene);
        simulationStage.show();
    }

    public void run() {
        try{
            Thread engineThread = new Thread(engine);
            engineThread.start();
        }   
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    private GridPane actualScene(WorldMap map){

        

        GridPane grid = new GridPane();


        Vector2d start=map.limes()[0];
        Vector2d end=map.limes()[1];

        grid.setGridLinesVisible(true);

        int height=Math.abs(start.y-end.y)+1;
        int width=Math.abs(start.x-end.x)+1;

        grid.setPrefWidth(width);
        grid.setPrefHeight(height);

        //ustalanie wielkosci pojedynczego kwadracika
        int squareSize = 40;
        if(700/height < squareSize){
            squareSize = (int)(700/height);
        }
        if(squareSize < 20){
            squareSize = 20;
        }

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
                //wybieram pozycje
                Vector2d v = new Vector2d(i,j);
                //zbieram wszytskie stworzonka ktore tam sa
                List<IMapElement> vList = map.objectsAt(v);
                //System.out.println(vList);
                IMapElement showElement = null;
                //jesli cokolwiek tam jest to wyswietlam najlepsze co tam jest
                if(vList != null){
                    showElement = vList.get(0);
                }
                //wyswietlanie
                VBox vbox = new GridElement(showElement, map.isItJungle(v)).getBox();
                grid.add(vbox,i+1,height-j);
            }
        }

        return grid;

    }


    public void updateScene(WorldMap map){

        GridPane grid = actualScene(map);
        allStaff.getChildren().remove(0);
        allStaff.getChildren().add(grid);
    }
}
