package agh.ics.oop;



public class RectangularMap extends AbstractWorldMap{

    Vector2d lim1;
    Vector2d lim2;

    public RectangularMap(int width, int height){
        super();
        lim1 = new Vector2d(0, 0);
        lim2 = new Vector2d(width-1, height-1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(!position.follows(lim1)){
            return false;
        }
        if(!position.precedes(lim2)){
            return false;
        }
        return super.canMoveTo(position);
    }

    @Override
    public Vector2d[] limes() {
        return new Vector2d[]{lim1, lim2};
    }

}
