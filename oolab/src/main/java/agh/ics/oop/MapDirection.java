package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public String toString(){
        if(this == NORTH){
            return "Polnoc";
        }
        if(this == SOUTH){
            return "Poludnie";
        }
        if(this == WEST){
            return "Zachod";
        }
        if(this == EAST){
            return "Wschod";
        }
        return null;
    }

    public MapDirection next(){
        if(this == NORTH){
            return EAST;
        }
        if(this == SOUTH){
            return WEST;
        }
        if(this == WEST){
            return NORTH;
        }
        if(this == EAST){
            return SOUTH;
        }
        return null;
    }

    public MapDirection previous(){
        if(this == NORTH){
            return WEST;
        }
        if(this == SOUTH){
            return EAST;
        }
        if(this == WEST){
            return SOUTH;
        }
        if(this == EAST){
            return NORTH;
        }
        return null;
    }

    public Vector2d toUnitVector(){
        Vector2d toReturn;
        if(this == NORTH){
            toReturn = new Vector2d(0,1);
            return toReturn;
        }
        if(this == SOUTH){
            toReturn = new Vector2d(0,-1);
            return toReturn;
        }
        if(this == WEST){
            toReturn = new Vector2d(-1,0);
            return toReturn;
        }
        if(this == EAST){
            toReturn = new Vector2d(1,0);
            return toReturn;
        }
        toReturn = null;
        return toReturn;
    }

}
