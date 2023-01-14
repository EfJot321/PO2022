package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;


class Vecment {
    public Vector2d position;
    public IMapElement element;

    public Vecment(Vector2d pos, IMapElement elem) {
        position = pos;
        element = elem;
    }
}


class compX implements Comparator<Vecment> {

    @Override
    public int compare(Vecment arg0, Vecment arg1) {
        //porownywanie x
        if (arg0.position.x < arg1.position.x) {
            return -1;
        }
        if (arg0.position.x > arg1.position.x) {
            return 1;
        }
        //jezeli x sa rowne to porownuje y
        if (arg0.position.y < arg1.position.y) {
            return -1;
        }
        if (arg0.position.y > arg1.position.y) {
            return 1;
        }
        //jezeli jest zwierzem to -1 a jak nie to 1
        if (arg0.element.getType().equals("Animal")) {
            return -1;
        }
        return 1;
    }
}

class compY implements Comparator<Vecment> {

    @Override
    public int compare(Vecment arg0, Vecment arg1) {
        //porownywanie y
        if (arg0.position.y < arg1.position.y) {
            return -1;
        }
        if (arg0.position.y > arg1.position.y) {
            return 1;
        }
        //jezeli y sa rowne to porownuje x
        if (arg0.position.x < arg1.position.x) {
            return -1;
        }
        if (arg0.position.x > arg1.position.x) {
            return 1;
        }
        //jezeli jest zwierzem to -1 a jak nie to 1
        if (arg0.element.getType().equals("Animal")) {
            return -1;
        }
        return 1;
    }
}

public class MapBoundary implements IPositionChangeObserver {

    private SortedSet<Vecment> setX = new TreeSet<Vecment>(new compX());
    private SortedSet<Vecment> setY = new TreeSet<Vecment>(new compY());

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

        for (Vecment tourist : setX) {
            //sprawdzam czy element jest na tej pozycji, nie musze sprawdzac czy to trawa bo zwierzeta sa przed trawa
            if (tourist.position.equals(oldPosition)) {
                //bye
                setX.remove(tourist);
                setY.remove(tourist);

                //go
                tourist.position = newPosition;

                //hello
                setX.add(tourist);
                setY.add(tourist);
                break;
            }
        }

    }


    public void addElement(IMapElement element) {
        Vecment toAdd = new Vecment(element.getPosition(), element);
        setX.add(toAdd);
        setY.add(toAdd);
    }

    public Vector2d[] getBoundary() {
        Vector2d lim1 = new Vector2d(setX.first().position.x, setY.first().position.y);
        Vector2d lim2 = new Vector2d(setX.last().position.x, setY.last().position.y);

        return new Vector2d[]{lim1, lim2};
    }

}
