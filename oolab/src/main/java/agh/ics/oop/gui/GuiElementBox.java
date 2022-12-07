package agh.ics.oop.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class GuiElementBox {
    private VBox vBox;

    public GuiElementBox(IMapElement element){
        String source = element.loadSrc();
        Image image=null;
        try {
            image = new Image(new FileInputStream(source));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        Text txt;

        if(element.getType().equals("Animal")){
            txt = new Text(element.getPosition().toString());
        }
        else{
            txt = new Text(element.getType());
        }

        vBox = new VBox();
        vBox.getChildren().addAll(imageView, txt);
        vBox.setAlignment(Pos.CENTER);
    }

    public VBox getVBox(){
        return vBox;
    }
}
