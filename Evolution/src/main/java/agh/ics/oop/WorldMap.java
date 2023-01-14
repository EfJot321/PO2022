package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class WorldMap extends AbstractWorldMap {
    private List<Vector2d> jungle = new ArrayList<Vector2d>();
    private int minDead = 0;
    private boolean isEquator;


    public WorldMap(int width, int height, int n, int dE, boolean isEquator) {
        super(width, height, dE);

        this.isEquator = isEquator;

        if (isEquator) {
            //tworzenie dzungli na rowniku
            for (int x = (int) Math.floor(width / 8); x < Math.ceil(width * 7 / 8); x++) {
                for (int y = (int) Math.floor(height / 3); y < Math.ceil(height * 2 / 3); y++) {
                    jungle.add(new Vector2d(x, y));
                }
            }
        } else {
            //tworzenie dzungli wszedzie bo nikt jeszcze nie zmarl
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    jungle.add(new Vector2d(i, j));
                }
            }
        }


        plantsAreGrowing(n);
    }

    public boolean isItJungle(Vector2d pos) {
        return jungle.contains(pos);
    }

    @Override
    public Vector2d moveTo(Vector2d actPosition, Vector2d position) {
        if (position.y < 0 || position.y >= height) {
            return actPosition;
        }
        if (position.x < 0) {
            return position.add(new Vector2d(width, 0));
        }
        if (position.x >= width) {
            return position.subtract(new Vector2d(width, 0));
        }
        return position;
    }

    @Override
    public Vector2d[] limes() {
        return new Vector2d[]{lim1, lim2};
    }

    public void plantsAreGrowing(int n) {
        if (!isEquator) {
            //updateuje jungle jesli nie ma rownika tylko toksyczne trupy
            findJungle();
        }

        for (int i = 0; i < n; i++) {
            if (nOfGrasses < width * height) {
                placeGrass();
            }
        }

    }

    private void findJungle() {
        //szukam elementown jungli ktore maja za duzo smierci na sobie
        List<Vector2d> toDelete = new ArrayList<>();
        for (Vector2d element : jungle) {
            int counter = 0;
            if (objectsAt(element) != null) {
                for (IMapElement ele : objectsAt(element)) {
                    if (ele.getType().equals("Animal")) {
                        Animal a = (Animal) ele;
                        if (a.dead) {
                            counter += 1;
                        }
                    }
                }
            }

            if (counter > minDead) {
                toDelete.add(element);
            }
        }
        //usuwam te elementy
        for (Vector2d element : toDelete) {
            jungle.remove(element);
        }
        //jezeli z jungli juz nic nie zostalo to musze ustalic nowa najmniejsza liczbe smierci
        if (jungle.size() <= 0) {
            minDead = Integer.MAX_VALUE;
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int counter = 0;
                    if (objectsAt(new Vector2d(i, j)) != null) {
                        for (IMapElement element : objectsAt(new Vector2d(i, j))) {
                            if (element.getType().equals("Animal")) {
                                Animal a = (Animal) element;
                                if (a.dead) {
                                    counter += 1;
                                }
                            }
                        }
                    }
                    if (counter < minDead) {
                        minDead = counter;
                    }
                }
            }
            //i tworze jungle zgodnie z aktualna najmniejsza liczba smierci
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int counter = 0;
                    Vector2d pos = new Vector2d(i, j);
                    if (objectsAt(pos) != null) {
                        for (IMapElement element : objectsAt(pos)) {
                            if (element.getType().equals("Animal")) {
                                Animal a = (Animal) element;
                                if (a.dead) {
                                    counter += 1;
                                }
                            }
                        }
                    }
                    if (counter <= minDead) {
                        jungle.add(pos);
                    }
                }
            }
        }
    }

    private void placeGrass() {
        Vector2d pos = null;
        boolean notFound = true;
        while (notFound) {
            notFound = false;
            //zmienna losowa
            int marker = randInt(0, 10);
            //80%
            if (marker <= 8) {
                marker = randInt(0, jungle.size() - 1);
                pos = jungle.get(marker);
            }
            //20%
            else {
                pos = new Vector2d(randInt(0, this.width - 1), randInt(0, this.height - 1));
                if (jungle.contains(pos)) {
                    notFound = true;
                    continue;
                }
            }

            //sprawdzam czy nie ma tu juz roslinki na wybranej pozycji
            List<IMapElement> elements = this.objectsAt(pos);
            if (elements != null) {
                for (IMapElement element : elements) {
                    if (element.getType().equals("Plant")) {
                        notFound = true;
                        break;
                    }
                }
            }
        }
        Plant grass = new Plant(pos);
        addToMap((IMapElement) grass, grass.getPosition());
        nOfGrasses += 1;

    }

    private int randInt(int a, int b) {
        Random rn = new Random(); // nowy obiekt co wywołanie
        int n = b - a + 1;
        return Math.abs(rn.nextInt() % n) + a;
    }

    @Override
    public int getNumberOfGrasses() {
        return nOfGrasses;
    }

    public int getSize() {
        return this.height * this.width;
    }
}
