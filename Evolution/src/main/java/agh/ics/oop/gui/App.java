package agh.ics.oop.gui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class App extends Application{


    @Override
    public void init() {

    }

    private void runNewSimulation(){
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        String src= selectedFile.getPath();
        Window simWin = new Window(src);
        Thread newWidowThread = new Thread(simWin);
        newWidowThread.start();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Button button = new Button("Wybierz plik");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                runNewSimulation();
            }
        });

        Text text=new Text("Witaj wędrowcze, wgraj plik");
        VBox firstElements= new VBox(text, button);
        firstElements.setAlignment(Pos.CENTER);

        Scene scene = new Scene(firstElements, 900, 700);

        primaryStage.setScene(scene);
        primaryStage.show();

        


    }

}
