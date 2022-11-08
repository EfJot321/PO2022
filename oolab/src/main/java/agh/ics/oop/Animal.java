package agh.ics.oop;


public class Animal {
    private MapDirection dir;
    private Vector2d pos;

    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.dir = MapDirection.NORTH;
        this.pos = initialPosition;
        this.map = map;
    }

    public String toString(){
        if(dir == MapDirection.NORTH){
            return "^";
        }
        if(dir == MapDirection.EAST){
            return ">";
        }
        if(dir == MapDirection.SOUTH){
            return "v";
        }
        if(dir == MapDirection.WEST){
            return "<";
        }
        return "";
    }

    public Vector2d getPos(){
        return pos;
    }

    public boolean isAt(Vector2d position){
        return pos.equals(position);
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
            Vector2d targetPos = pos.add(distance);
            if(map.canMoveTo(targetPos)){
                pos = targetPos;
            }
        }
    }
}
