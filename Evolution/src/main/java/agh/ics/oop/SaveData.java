package agh.ics.oop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SaveData { // to klasa, nie metoda

    public SaveData(ArrayList<String> data, String path) {
        try {
            PrintWriter myWriter = new PrintWriter(new FileWriter(path));
            myWriter.println("");
            for (int i = 0; i < data.size(); i++) {
                myWriter.println(data.get(i));
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
