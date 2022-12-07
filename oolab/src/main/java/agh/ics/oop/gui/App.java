package agh.ics.oop.gui;



import agh.ics.oop.Vector2d;
import agh.ics.oop.GrassField;
import agh.ics.oop.IMapElement;
import agh.ics.oop.OptionsParser;
import agh.ics.oop.SimulationEngine;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application{

    private GrassField map = null;
    SimulationEngine engine = null;

    private TextField arguments;
    private VBox allStaff;

    int wh = 45;

    private int moveDelay = 500;

    
    @Override
    public void init() throws Exception {
        super.init();
        
        try{
            map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) , new Vector2d(-5, 0)};

            engine = new SimulationEngine(map, positions, moveDelay, this);

        }
        catch(IllegalArgumentException ex){
            System.out.println(ex);
            return;
        }
    }

    private void startSimulation(){
        String[] args = arguments.getText().split(" ");
        System.out.println(args);

        engine.setDirections(new OptionsParser().parse(args));
        Thread engineThread = new Thread(engine);
        engineThread.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        createWorld(map);

        Button startButton = new Button("Start!");
        startButton.setOnAction(e -> {startSimulation();});
        arguments = new TextField();

        HBox controls = new HBox(arguments, startButton);
        controls.setAlignment(Pos.CENTER);

        GridPane grid = createWorld(map);

        allStaff = new VBox(controls, grid);
        allStaff.setAlignment(Pos.CENTER);

        Scene scene = new Scene(allStaff, 1100, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
        
    }



    private GridPane createWorld(GrassField actMap){
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.setAlignment(Pos.CENTER);

        Vector2d lim1 = actMap.limes()[0];
        Vector2d lim2 = actMap.limes()[1];

        int width = lim2.subtract(lim1).x+1;
        int height = lim2.subtract(lim1).y+1;


        int startX = lim1.x;
        //int endX = lim2.x+1;
        int startY = lim1.y;
        int endY = lim2.y+1;


        

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
        Vector2d actPos;
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                xi = startX+i;
                yi = startY+j;
                actPos = new Vector2d(xi, yi);
                if(actMap.isOccupied(actPos)){
                    IMapElement element = (IMapElement)actMap.objectAt(actPos);
                    GuiElementBox geb = new GuiElementBox(element);
                    vBox = geb.getVBox();
                }
                else{
                    vBox = new VBox();
                    vBox.getChildren().addAll(new Label(""));
                }
                addToGrid(vBox, xi-startX+1, height-(yi-startY), grid);
                

            }
        }

        return grid;
        
        
        

    }

    public void updateWorld(GrassField actMap){
        GridPane grid = createWorld(actMap);
        
        allStaff.getChildren().remove(1);
        allStaff.getChildren().add(grid);

    }



    private void addToGrid(VBox vB, int x, int y, GridPane grid){
        grid.add(vB, x, y);
        GridPane.setHalignment(vB, HPos.CENTER);
    }


    
}
