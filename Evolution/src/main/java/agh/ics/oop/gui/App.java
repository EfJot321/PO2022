package agh.ics.oop.gui;



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

    

    
    @Override
    public void init() throws Exception {
        super.init();
        
        
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        

        GridPane grid = new GridPane();

        Scene scene = new Scene(grid, 1100, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
        
    }



    


    
}
