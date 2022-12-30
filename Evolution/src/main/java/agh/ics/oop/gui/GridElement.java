package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.*;


public class GridElement {

    private VBox posBox = null;
    private InputStream animalSource=getClass().getResourceAsStream("animal.png");
    private InputStream grassSource=getClass().getResourceAsStream("grass.png");



    public GridElement(IMapElement element, boolean isJungle) throws IOException {
        posBox = new VBox();
        posBox.setAlignment(Pos.CENTER);
        //jesli jest cos
        //jesli to dzungla to daje zielen
        if(isJungle){
            posBox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        if(element != null){

            if(element.getType().equals("Animal")){
                Animal animal=(Animal) element;
                if( !animal.isDead()) {
                    posBox.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
            else{
                posBox.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

            }
        }

    }

    public VBox getBox(){
        return posBox;
    }




}
