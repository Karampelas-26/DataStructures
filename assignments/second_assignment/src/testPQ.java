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
        pq.insert(cit2);
        pq.insert(cit3);
        pq.insert(cit4);
        pq.insert(cit5);
        pq.insert(cit6);
        pq.insert(cit7);
        pq.insert(cit8);
        pq.insert(cit9);
        City pr = pq.remove(8);

        System.out.println("remove prebeza "+pr);


        //remove each time max of pq and print it
        City[] cc = new City[pq.size()];
        for (int i = 0; i < cc.length; i++){
            cc[i] = pq.getMax();
            System.out.println(cc[i]);
        }

//        for(int j = 0; j <1000; j++){
//            if (pq.IDs[j] != 0)
//                System.out.println(j+"->"+pq.IDs[j]);
//        }
        System.out.println("=================");

    }
}
