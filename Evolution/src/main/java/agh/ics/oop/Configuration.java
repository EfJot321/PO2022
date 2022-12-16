package agh.ics.oop;

import java.io.FileReader;
import com.opencsv.CSVReader;



public class Configuration {

    //pozniej bede sie zastanawial nad typami zmiennych
    public int width, height, worldVariant, startPlantsNum, dE, plantsPerDay, 
        plantsVariant, startAnimalNum, startE, minE, birthE, minMutNum, 
        maxMutNum, mutationVariant, genomLen, animalVariant;
    
    
    public Configuration(String source){
        readFile(source);
    }


    private void readFile(String file){
        try {
  
            FileReader filereader = new FileReader(file);
      
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            

            nextRecord = csvReader.readNext();
            width = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            height = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            worldVariant = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            startPlantsNum = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            dE = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            plantsPerDay = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            plantsVariant = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            startAnimalNum = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            startE = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            minE = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            birthE = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            minMutNum = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            maxMutNum = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            mutationVariant = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            genomLen = Integer.parseInt(nextRecord[1]);

            nextRecord = csvReader.readNext();
            animalVariant = Integer.parseInt(nextRecord[1]);

            
            csvReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
