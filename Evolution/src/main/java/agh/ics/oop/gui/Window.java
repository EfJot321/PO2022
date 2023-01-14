package agh.ics.oop.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import agh.ics.oop.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class Window extends Thread {

    private SimulationEngine engine;
    private VBox allStaff;
    private Stage simulationStage;

    private ArrayList<String> toSave = new ArrayList<>();
    final ScrollPane sp = new ScrollPane();

    private boolean save;

    private Button button;

    private String path;
    private SaveData sd;


    public Window(String src, boolean save, String path) {

        this.save = save;
        this.path = path;

        Configuration conf = new Configuration(src);
        engine = new SimulationEngine(conf, 100, this);

        GridPane grid = new GridPane();

        Text numberOfAnimals = new Text("Liczba zwierzat " + 0);
        Text numberOfPlants = new Text("Liczba roslin " + 0);
        Text gaps = new Text("Liczba pustych pol " + 0);
        Text avgEnergy = new Text("Sredni poziom energii " + 0);
        Text avdDays = new Text("Srednia dlugosc zycia " + 0);
        Text mostPopularGenom = new Text("Najbardziej popularny genom  " + engine.getMostPopularGenom());

        button = new Button("Pauza");

        allStaff = new VBox(grid, numberOfAnimals, numberOfPlants, gaps, avgEnergy, mostPopularGenom, avdDays, button);
        sp.setContent(allStaff);
        Scene scene = new Scene(sp, 1000, 700);


        simulationStage = new Stage();
        simulationStage.setScene(scene);
        simulationStage.show();
    }

    public void run() {
        try {
            Thread engineThread = new Thread(engine);
            engineThread.setDaemon(true);
            engineThread.start();
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    engine.pauseOrResume();
                }
            });

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private GridPane actualScene(IWorldMap map) throws IOException {

        GridPane grid = new GridPane();

        Vector2d start = map.limes()[0];
        Vector2d end = map.limes()[1];

        grid.setGridLinesVisible(true);

        int height = Math.abs(start.y - end.y) + 1;
        int width = Math.abs(start.x - end.x) + 1;

        grid.setPrefWidth(width);
        grid.setPrefHeight(height);

        //ustalanie wielkosci pojedynczego kwadracika
        int squareSize = 20;
        if (700 / height < squareSize) {
            squareSize = (int) (700 / height);
        }
        if (squareSize < 20) {
            squareSize = 20;
        }

        Label label = new Label("y/x");

        GridPane.setHalignment(label, HPos.CENTER);
        grid.getColumnConstraints().add(new ColumnConstraints(squareSize));
        grid.getRowConstraints().add(new RowConstraints(squareSize));

        grid.add(label, 0, 0);


        for (int i = 0; i < height; i++) {
            Label label1 = new Label(Integer.toString(height + start.x - i - 1));
            grid.add(label1, 0, i + 1);
            GridPane.setHalignment(label1, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(squareSize));
        }

        for (int i = 0; i < width; i++) {
            Label label1 = new Label(Integer.toString(i + start.x));
            grid.add(label1, i + 1, 0);
            GridPane.setHalignment(label1, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(squareSize));
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                //wybieram pozycje
                Vector2d v = new Vector2d(i, j);
                //zbieram wszytskie stworzonka ktore tam sa
                List<IMapElement> vList = map.objectsAt(v);
                //System.out.println(vList);
                IMapElement showElement = null;
                //jesli cokolwiek tam jest to wyswietlam najlepsze co tam jest
                if (vList != null) {
                    showElement = vList.get(0);
                }
                //wyswietlanie
                VBox vbox = new GridElement(showElement, false).getBox();
                grid.add(vbox, i + 1, height - j);
            }
        }

        return grid;

    }


    public void updateScene(IWorldMap map) throws IOException {
        Text numberOfAnimals = new Text("Liczba zwierzat " + engine.getNOfAnimals());
        Text numberOfPlants = new Text("Liczba roslin " + map.getNumberOfGrasses());
        int gaps = map.getSize() - engine.getNOfAnimals() - map.getNumberOfGrasses();
        Text size = new Text("Liczba pustych pol " + gaps);
        Text avgEnergy = new Text("Sredni poziom energii " + engine.getAverageEnergy());
        Text mostPopularGenom = new Text("Najpopularniejszy genom  " + engine.getMostPopularGenom());
        Text avgDays = new Text("Srednia dlugosc zycia " + engine.getAverageLivingDays());

        if (this.save) {
            //zapis do pliku
            toSave.add("Dzien:" + engine.getDay());
            toSave.add("Liczba zwierzat " + engine.getNOfAnimals());
            toSave.add("Liczba pustych pol " + gaps);
            toSave.add("Sredni poziom energii " + engine.getAverageEnergy());
            toSave.add("Najpopularniejszy genom  " + engine.getMostPopularGenom());
            toSave.add("Srednia dlugosc zycia " + engine.getAverageLivingDays());
            sd = new SaveData(toSave, this.path);
        }


        GridPane grid = actualScene(map);
        allStaff.getChildren().remove(0);
        allStaff.getChildren().add(0, grid);
        allStaff.getChildren().remove(1);
        allStaff.getChildren().add(1, numberOfAnimals);
        allStaff.getChildren().remove(2);
        allStaff.getChildren().add(2, numberOfPlants);
        allStaff.getChildren().remove(3);
        allStaff.getChildren().add(3, size);
        allStaff.getChildren().remove(4);
        allStaff.getChildren().add(4, avgEnergy);
        allStaff.getChildren().remove(5);
        allStaff.getChildren().add(5, mostPopularGenom);
        allStaff.getChildren().remove(6);
        allStaff.getChildren().add(6, avgDays);
    }
}
