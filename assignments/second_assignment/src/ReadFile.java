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

                if ( ID > 10_000 || ID <= 0 ){
                    System.err.println("Invalid ID");
                    System.exit(1);
                }

                if ( name.length() > 50 ){
                    System.err.println("Invalid name");
                    System.exit(1);
                }
                if ( population >10_000_000){
                    System.err.println("Invalid population");
                    System.exit(1);
                }
                if (covidCases > population){
                    System.err.println("Invalid covidcases");
                    System.exit(1);
                }


                City city = new City(ID, name, population, covidCases);
                covidCities.addFirst(city);
            }

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
        int i = 0;
       do{
            cities[i] = (City)temp.getLast();
            temp.removeLast();
            i++;


        } while (!temp.isEmpty());
        return cities;

    }
}
