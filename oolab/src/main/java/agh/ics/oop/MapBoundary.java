package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

class compX implements Comparator<IMapElement> {

    @Override
    public int compare(IMapElement arg0, IMapElement arg1) {
        //porownywanie x
        if(arg0.getPosition().x < arg1.getPosition().x){
            return -1;
        }
        if(arg0.getPosition().x > arg1.getPosition().x){
            return 1;
        }
        //jezeli x sa rowne to porownuje y
        if(arg0.getPosition().y < arg1.getPosition().y){
            return -1;
        }
        if(arg0.getPosition().y > arg1.getPosition().y){
            return 1;
        }
        //jezeli jest zwierzem to -1 a jak nie to 1
        if(arg0.getType().equals("Animal")){
            return -1;
        }
        return 1;
    }
}

class compY implements Comparator<IMapElement> {

    @Override
    public int compare(IMapElement arg0, IMapElement arg1) {
        //porownywanie y
        if(arg0.getPosition().y < arg1.getPosition().y){
            return -1;
        }
        if(arg0.getPosition().y > arg1.getPosition().y){
            return 1;
        }
        //jezeli y sa rowne to porownuje x
        if(arg0.getPosition().x < arg1.getPosition().x){
            return -1;
        }
        if(arg0.getPosition().x > arg1.getPosition().x){
            return 1;
        }
        //jezeli jest zwierzem to -1 a jak nie to 1
        if(arg0.getType().equals("Animal")){
            return -1;
        }
        return 1;
    }
}

public class MapBoundary implements IPositionChangeObserver{

    private SortedSet<IMapElement> setX = new TreeSet<IMapElement>(new compX());
    private SortedSet<IMapElement> setY = new TreeSet<IMapElement>(new compY());

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for(IMapElement tourist:setX){
            //sprawdzam czy element jest na tej pozycji, nie musze sprawdzac czy to trawa bo zwierzeta sa przed trawa
            if(tourist.getPosition().equals(newPosition)){
                //bye
                setX.remove(tourist);
                setY.remove(tourist);

                //hello
                setX.add(tourist);
                setY.add(tourist);
                break;
            }
        }
        
    }


    public void addElement(IMapElement element) {
        setX.add(element);
        setY.add(element);
    }

    public Vector2d[] getBoundary() {
        Vector2d lim1 = new Vector2d(setX.first().getPosition().x, setY.first().getPosition().y);
        Vector2d lim2 = new Vector2d(setX.last().getPosition().x, setY.last().getPosition().y);

        return new Vector2d[] {lim1, lim2};
    }
    
}
