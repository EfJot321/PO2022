package agh.ics.oop;

public class Plant extends AbstractWorldMapElement {

    Plant(Vector2d pos) {
        this.pos = pos;
    }


    public String toString() {
        return "*";
    }

    public String getType() {
        return "Plant";
    }


    @Override
    public String loadSrc() {
        return "C:\\Users\\MagdalenaSkrok\\podzialaj\\PO2022\\Evolution\\src\\main\\resources\\grass.jpg";  // nie wolno zaszywać ścieżek bezwzględnych tak głęboko w kodzie
    }


}
