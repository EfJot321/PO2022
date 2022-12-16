package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GridElement {
    
    private VBox posBox = null;

    public GridElement(IMapElement element, boolean isJungle){
        posBox = new VBox();
        posBox.setAlignment(Pos.CENTER);
        //jesli jest cos
        if(element != null){
            Label label = new Label(element.toString());
            posBox.getChildren().add(label);
        }
        //jesli to dzungla to daje zielen
        if(isJungle){
            posBox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public VBox getBox(){
        return posBox;
    }

}
