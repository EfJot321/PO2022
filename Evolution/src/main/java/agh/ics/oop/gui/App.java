package agh.ics.oop.gui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class App extends Application {

    private int nOfSimulations = 0;

    private CheckBox cb;

    @Override
    public void init() {

    }

    private String createFile() {
        String path = "./src/main/saves/dataSave" + nOfSimulations + ".csv";
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("Stworzono plik: " + myObj.getName());
            } else {
                //jezeli istnieje to usuwam i tworze nowy
                myObj.delete();
                createFile();
            }
        } catch (IOException e) {
            System.out.println("Wystapil blad!");
            e.printStackTrace();
        }
        return path;
    }

    private void runNewSimulation(boolean save) {
        //ucheckowanie checkboxa od zapisu
        cb.setSelected(false);
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        String src = selectedFile.getPath();
        String newPath = "";
        if (save) {
            newPath = createFile();
        }
        Window simWin = new Window(src, save, newPath);
        Thread newWidowThread = new Thread(simWin);
        newWidowThread.start();
        nOfSimulations += 1;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        this.cb = new CheckBox("Chce zapis do pliku");
        Button button = new Button("Wybierz plik");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                runNewSimulation(cb.isSelected());
            }
        });


        Text text = new Text("Witaj wedrowcze, wybierz plik konfiguracyjny");
        VBox firstElements = new VBox(text, button, cb);
        firstElements.setAlignment(Pos.CENTER);

        Scene scene = new Scene(firstElements, 900, 700);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
