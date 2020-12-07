public class Covid_k {


    public static void main(String[] args) {
        String file = args[0];//"C:\\Users\\User\\Desktop\\ergasia2domes\\src\\DailyCovidCases";
        int kParameter = Integer.parseInt(args[1]);
        ReadFile reader = new ReadFile();
        City[] cities = reader.readFile(file);
        if (kParameter > cities.length) {
            System.out.println("invalid k parameter ");
            System.exit(0);
        }
        else {
            QuickSort quickSort = new QuickSort();
            quickSort.sort(cities, 0, cities.length - 1);
            System.out.println("The top " + kParameter + " cities are:");
            for(int i = 0; i < kParameter; i++) {
                System.out.println(cities[i].getName());
            }
        }
    }

}
