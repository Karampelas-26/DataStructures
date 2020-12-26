import javax.swing.plaf.synth.SynthUI;
import java.io.File;
import java.util.Scanner;

public class DynamicCovid_k_withPQ {

    public static void main(String[] args) {
        final String file = args[0];
        final int kParameter = Integer.parseInt(args[1]);
        PQ priorityQueue = new PQ(kParameter);

        try{
            File myFile = new File(file);
            Scanner scanner = new Scanner(myFile);
            String[] arr;
            int ID, population, covidCases;
            String name;
            int counter = 0;

            while (scanner.hasNextLine()){

                //split each line to data able for use on City
                String data = scanner.nextLine();
                arr = data.split(" ");
                ID = Integer.parseInt(arr[0]);
                name = arr[1];
                population = Integer.parseInt(arr[2]);
                covidCases = Integer.parseInt(arr[3]);

                //checking if variables are valid
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

                priorityQueue.insert(new City(ID, name, population, covidCases));

                //if size of priority queue is greater than kParameter
                //then remove last element
                if (priorityQueue.size() > kParameter){
                    PQ temp = new PQ(priorityQueue);
                    City[] cities = new City[temp.size()];
                    int i = 0;
                    while (!temp.isEmpty()) {
                        cities[i] = temp.getMax();
                        i++;
                    }
                    City lastElement =cities[cities.length-1];
                    priorityQueue.remove(lastElement.getID());
                }

                //print top k cities
                if (++counter % kParameter == 0){
                    System.out.println("Top K cities are: ");
                    PQ temp1 = new PQ(priorityQueue);
                    for (int i = 0; i < kParameter; i++)
                        System.out.println(temp1.getMax());
                }
            }
            //print the final priority queue
            System.out.println("Final Priority Queue");
            while (!priorityQueue.isEmpty()){
                System.out.println(priorityQueue.getMax());
            }
            scanner.close();
        } catch (Exception e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }
}

