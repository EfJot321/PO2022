package agh.ics.oop;


public class Animal {
    private MapDirection dir;
    private Vector2d pos;

    public Animal(){
        dir = MapDirection.NORTH;
        pos = new Vector2d(2,2);
    }

    public String toString(){
        String toReturn = "";
        toReturn += "Pos: ";
        toReturn += pos.toString();
        toReturn += ", Dir: ";
        toReturn += dir.toString();
        return toReturn;
    }

    public Vector2d getPos(){
        return pos;
    }

    public boolean isAt(Vector2d position){
        return pos.equals(position);
    }

    private boolean ifICanGo(Vector2d position){
        Vector2d ogr1 = new Vector2d(0,0);
        Vector2d ogr2 = new Vector2d(4,4);

        if(!position.lowerLeft(ogr1).equals(ogr1)){
            return false;
        }
        if(!position.upperRight(ogr2).equals(ogr2)){
            return false;
        }
        return true;
    }


    public void move(MoveDirection direction){
        if(direction == MoveDirection.LEFT){
            dir = dir.previous();
        }
        if(direction == MoveDirection.RIGHT){
            dir = dir.next();
        }
        if(direction == MoveDirection.FORWARD || direction == MoveDirection.BACKWARD){
            Vector2d distance = dir.toUnitVector();
            if(direction == MoveDirection.BACKWARD){
                distance = distance.opposite();
            }
            if(ifICanGo(pos.add(distance))){
                pos = pos.add(distance);
            }
        }
    }
}
