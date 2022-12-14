package agh.ics.oop.gui;


import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import agh.ics.oop.RectangularMap;
import agh.ics.oop.Vector2d;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import static java.lang.Math.abs;

public class App extends Application{

    private RectangularMap mapp;

    @Override
    public void init() {
        try {
            System.out.println("Start");
            mapp=new RectangularMap(10,7,2);
            mapp.place(new Animal(mapp,new Vector2d(2,2)));
            mapp.place(new Animal(mapp,new Vector2d(3,3)));
            System.out.println("Stop");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = new GridPane();

        Scene scene = new Scene(grid, 1100, 700);

        Vector2d start=mapp.limes()[0];
        Vector2d end=mapp.limes()[1];

        grid.setGridLinesVisible(true);

        int height=abs(start.y-end.y)+1;
        int width=abs(start.x-end.x)+1;



        grid.setPrefWidth(width);
        grid.setPrefHeight(height);

        Label label = new Label("y/x");

        GridPane.setHalignment(label, HPos.CENTER);
        grid.getColumnConstraints().add(new ColumnConstraints(33));
        grid.getRowConstraints().add(new RowConstraints(33));

        grid.add(label,0,0);


        for(int i=0; i<height; i++){
            Label label1 = new Label(Integer.toString(height+start.x-i-1));
            grid.add(label1,0,i+1);
            GridPane.setHalignment(label1, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(33));
        }

        for(int i=0; i<width; i++){
            Label label1 = new Label(Integer.toString(i+start.x));
            grid.add(label1,i+1,0);
            GridPane.setHalignment(label1, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(33));
        }
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                Vector2d v = new Vector2d(i,j);
                if(mapp.isOccupied(v)){
                    IMapElement obj = (IMapElement) mapp.objectAt(v);
                    Label label1 = new Label(obj.toString());
                    grid.add(label1,i+1,height-j);
                    GridPane.setHalignment(label1, HPos.CENTER);
                }
            }
        }
        primaryStage.setScene(scene);
        primaryStage.show();


        
    }



    


    
}
