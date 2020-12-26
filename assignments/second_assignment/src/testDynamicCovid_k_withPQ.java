import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class testDynamicCovid_k_withPQ {

    public static void main(String[] args) {
        String file = args[0];//"C:\\Users\\User\\Desktop\\ergasia2domes\\src\\DailyCovidCases";
        int kParameter = Integer.parseInt(args[1]);
        PQ priorityQueue = new PQ(kParameter);
        int citiesIDs[] = new int[5];

        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            String[] arr;
            int ID, population, covidCases;
            String name;
            int counter =0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                arr = data.split(" ");
                ID = Integer.parseInt(arr[0]);
                name = arr[1];
                population = Integer.parseInt(arr[2]);
                covidCases = Integer.parseInt(arr[3]);

                if (ID > 10_000 || ID <= 0) {
                    System.err.println("Invalid ID");
                    System.exit(1);
                }

                if (name.length() > 50) {
                System.err.println("Invalid name");
                    System.exit(1);
                }
                if (population > 10_000_000) {
                    System.err.println("Invalid population");
                    System.exit(1);
                }
                if (covidCases > population) {
                    System.err.println("Invalid covidcases");
                    System.exit(1);
                }
                City city = new City(ID, name, population, covidCases);
                priorityQueue.insert(city);
//                citiesIDs = priorityQueue.toArray();

                //print cities id
                for (int i: citiesIDs)
                    System.out.print(i+" ");
                System.out.println("");
//                System.out.println(citiesIDs.length);

                //remove last city
                if (citiesIDs.length > kParameter){
                  priorityQueue.remove(citiesIDs[citiesIDs.length-1]);
                  System.out.println("remove");
                }


                //print cities id
//                citiesIDs = priorityQueue.toArray();
                for (int i: citiesIDs)
                    System.out.print(i+" ");
                System.out.println("------------------");

                //pritn k cities
                counter++;
                if ( counter % 5 == 0){
                    System.out.println(" Top K cities are: " + counter);
//                    priorityQueue.printAll();
                }
//                System.out.println(counter);
            }
            System.out.println(" ok: " + counter);
//            priorityQueue.printAll();

//            City [] cities = priorityQueue.toAr(priorityQueue);
//            for(City c:cities)
//                System.out.println(c);
            myReader.close();

        } catch (FileNotFoundException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }


    }
}