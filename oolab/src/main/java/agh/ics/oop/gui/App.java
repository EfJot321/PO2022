package agh.ics.oop.gui;

import java.io.FileNotFoundException;
import java.util.List;

import agh.ics.oop.Vector2d;
import agh.ics.oop.GrassField;
import agh.ics.oop.IEngine;
import agh.ics.oop.IMapElement;
import agh.ics.oop.MoveDirection;
import agh.ics.oop.OptionsParser;
import agh.ics.oop.SimulationEngine;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application{

    private GrassField map = null;
    private VBox[][] world;
    
    @Override
    public void init() throws Exception {
        super.init();
        
        //List<String> args = getParameters().getRaw();
        try{
            //MoveDirection[] directions = new OptionsParser().parse(notList(args));
            String[] stringDirections = {"f", "b", "l", "r", "f", "f", "f", "f", "l", "l", "r", "b", "f", "b", "f", "f"};
            MoveDirection[] directions = new OptionsParser().parse(stringDirections);
            map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) , new Vector2d(-5, 0)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map);
        }
        catch(IllegalArgumentException ex){
            System.out.println(ex);
            return;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        
        GridPane grid = new GridPane();
        createWorld(grid);


        Scene scene = new Scene(grid, 800, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
        
    }


    private void createWorld(GridPane grid) throws FileNotFoundException{
        Vector2d lim1 = map.limes()[0];
        Vector2d lim2 = map.limes()[1];

        int width = lim2.subtract(lim1).x+1;
        int height = lim2.subtract(lim1).y+1;

        int startX = lim1.x;
        //int endX = lim2.x+1;
        int startY = lim1.y;
        int endY = lim2.y+1;


        grid.setGridLinesVisible(true);
        grid.setAlignment(Pos.CENTER);
        
        int wh = 45;

        //cyferki
        VBox vBox = new VBox();
        vBox.getChildren().addAll(new Label("x/y"));
        vBox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setAlignment(Pos.CENTER);
        addToGrid(vBox, 0, 0, grid);
        grid.getColumnConstraints().add(new ColumnConstraints(wh));
        grid.getRowConstraints().add(new RowConstraints(wh));
        int xi;
        for(int i=1;i<width+1;i++){
            xi = startX+i-1;
            grid.getColumnConstraints().add(new ColumnConstraints(wh));
            vBox = new VBox();
            vBox.getChildren().addAll(new Label(Integer.toString(xi)));
            vBox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            vBox.setAlignment(Pos.CENTER);
            addToGrid(vBox, i, 0, grid);

        }
        int yi;
        for(int i=1;i<height+1;i++){
            yi = endY-i;
            grid.getRowConstraints().add(new RowConstraints(wh));
            vBox = new VBox();
            vBox.getChildren().addAll(new Label(Integer.toString(yi)));
            vBox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
            vBox.setAlignment(Pos.CENTER);
            addToGrid(vBox, 0, i, grid);
        }



        //5 dnia...
        world = new VBox[width][height];
        Vector2d actPos;
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                xi = startX+i;
                yi = startY+j;
                actPos = new Vector2d(xi, yi);
                if(map.isOccupied(actPos)){
                    IMapElement element = (IMapElement)map.objectAt(actPos);
                    GuiElementBox geb = new GuiElementBox(element);
                    vBox = geb.getVBox();
                    world[i][j] = vBox;
                }
                else{
                    vBox = new VBox();
                    vBox.getChildren().addAll(new Label(""));
                    world[i][j] = vBox;
                }
                addToGrid(world[i][j], xi-startX+1, height-(yi-startY), grid);
                

            }
        }
        
        

    }



    private String[] notList(List<String> list){
        String[] toReturn = new String[list.size()];
        int i=0;
        for(String string:list){
            toReturn[i] = string;
            i++;
        }
        return toReturn;
    }

    private void addToGrid(VBox vB, int x, int y, GridPane grid){
        grid.add(vB, x, y);
        GridPane.setHalignment(vB, HPos.CENTER);
    }


    
}
