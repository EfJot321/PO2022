package agh.ics.oop;

import java.util.Objects;

class Vector2d{
    public final int x;
    public final int y;

    public Vector2d(int X, int Y){
        this.x = X;
        this.y = Y;
    }

    public String toString(){
        String toReturn = "(";
        toReturn += x;
        toReturn += ",";
        toReturn += y;
        toReturn += ")";
        return toReturn;
    }

    boolean precedes(Vector2d other){
        if(this.x <= other.x && this.y <= other.y){
            return  true;
        }
        return false;
    }

    boolean follows(Vector2d other){
        if(this.x >= other.x && this.y >= other.y){
            return  true;
        }
        return false;
    }

    public Vector2d add(Vector2d other){
        int X = this.x + other.x;
        int Y = this.y + other.y;

        Vector2d toReturn = new Vector2d(X, Y);
        return toReturn;
    }

    public Vector2d subtract(Vector2d other){
        int X = this.x - other.x;
        int Y = this.y - other.y;

        Vector2d toReturn = new Vector2d(X, Y);
        return toReturn;
    }

    public Vector2d upperRight(Vector2d other){
        int X = this.x;
        int Y = this.y;

        if(X < other.x){
            X = other.x;
        }
        if(Y < other.y){
            Y = other.y;
        }

        Vector2d toReturn = new Vector2d(X, Y);
        return toReturn;
    }

    public Vector2d lowerLeft(Vector2d other){
        int X = this.x;
        int Y = this.y;

        if(X > other.x){
            X = other.x;
        }
        if(Y > other.y){
            Y = other.y;
        }

        Vector2d toReturn = new Vector2d(X, Y);
        return toReturn;
    }

    public Vector2d opposite(){
        int X = -this.x;
        int Y = -this.y;

        Vector2d toReturn = new Vector2d(X, Y);
        return toReturn;
    }

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d v2 = (Vector2d) other;
        if(this.x == v2.x && this.y == v2.y){
            return true;
        }
        return false;
    }

    public int hashCode(){
        return Objects.hash(x,y);
    }


}
