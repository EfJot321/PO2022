package agh.ics.oop;

abstract class AbstractWorldMapElement implements IMapElement {

    Vector2d pos;


    @Override
    public Vector2d getPosition() {
        return pos;
    }

    @Override
    public boolean isAt(Vector2d position) {
        return this.pos.equals(position);
    }


    @Override
    abstract public String getType();
}
