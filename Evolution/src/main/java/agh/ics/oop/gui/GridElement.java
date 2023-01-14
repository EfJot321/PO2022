package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.*;


public class GridElement {

    private VBox posBox = null;


    public GridElement(IMapElement element, boolean isJungle) throws IOException {
        posBox = new VBox();
        posBox.setAlignment(Pos.CENTER);
        //jesli jest cos
        //jesli to dzungla to daje zielen
        if (isJungle) {
            posBox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if (element != null) {

            if (element.getType().equals("Animal")) {
                Animal animal = (Animal) element;
                if (!animal.dead) {
                    //krytycznie malo energii
                    if (animal.getEnergy() < 4) {
                        posBox.setBackground(new Background(new BackgroundFill(Color.rgb(255, 19, 0), CornerRadii.EMPTY, Insets.EMPTY)));
                    } else {
                        //energia kiedy moze sie rozmnazac
                        if (animal.canReproduce()) {
                            posBox.setBackground(new Background(new BackgroundFill(Color.rgb(65, 29, 19), CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                        //energia kiedy nawet rozmnazac sie nie moze
                        else {
                            posBox.setBackground(new Background(new BackgroundFill(Color.rgb(160, 80, 45), CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                    }

                }
            } else {
                posBox.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

            }
        }

    }

    public VBox getBox() {
        return posBox;
    }


}
