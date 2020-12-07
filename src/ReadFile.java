import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ReadFile {

    public ReadFile() {
    }

    public City[] readFile(String file) {
        StringDoubleEndedQueueImpl<City> covidCities = new StringDoubleEndedQueueImpl<City>();
        try{
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            String [] arr;
            int ID, population, covidCases;
            String name;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                arr = data.split(" ");
                ID = Integer.parseInt(arr[0]);
                name = arr[1];
                population = Integer.parseInt(arr[2]);
                covidCases = Integer.parseInt(arr[3]);
                City city = new City(ID, name, population, covidCases);
                covidCities.addFirst(city);            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
        City[] cities = toArray(covidCities);
        return cities;
    }


    private City[] toArray(StringDoubleEndedQueueImpl queue){
        StringDoubleEndedQueueImpl temp = queue;
        City[] cities = new City[temp.size()];
        for(int i=0; i < temp.size(); i++){
            cities[i] = (City)temp.getFirst();
            temp.removeFirst();

        }
        return cities;

    }
}
