public class testPQ {

    public static void main(String[] args) {

        City cit1 = new City(23 ,"Manchester" ,510746 ,1200);
        City cit2 = new City(31, "Amsterdam", 821752, 678);
        City cit3 = new City(58, "Athens" ,3218218 ,3504);
        City cit4 = new City(124, "Karditsa" ,56747 ,78);
        City cit5 = new City(6, "Arta", 43166, 5);
        City cit6 = new City(7, "Hgoumenitsa", 510746 ,500);
        City cit7 = new City(8, "Prebeza" ,35089, 79);
        City cit8 = new City(9 ,"Ioannina" ,150000, 82000);
        City cit9 = new City(10, "Trikala", 80302 ,7);


        PQ pq = new PQ(6);
        pq.insert(cit1);
        pq.printAll();
        System.out.println("###################");
        pq.insert(cit2);
         pq.printAll();
        System.out.println("###################");
        pq.insert(cit3);
         pq.printAll();
        System.out.println("###################");
        pq.insert(cit4);
         pq.printAll();
        System.out.println("###################");
        pq.insert(cit5);
         pq.printAll();
        System.out.println("###################");
        pq.insert(cit6);
         pq.printAll();
        System.out.println("###################");
        pq.insert(cit7);
         pq.printAll();
        System.out.println("###################");
        pq.insert(cit8);
         pq.printAll();
        System.out.println("###################");
        pq.insert(cit9);
         pq.printAll();
        System.out.println("###################");

        System.out.println(Double.MAX_VALUE);


    }
}
