public class PQ implements InterfacePQ{

    private City[] heap;
    private int size;
    private int [] IDs;


    public PQ(int length) {

        this.heap =  new City[length + 1];
        this.size = 0;
        this.IDs = new int[length +1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(City x) {
        if(size == ((heap.length -1)*3/4))
            resize();

        heap[++size] =  x;
         IDs[size] = x.getID();
        swim((size));
    }

    @Override
    public City max() {
        return isEmpty() ? null : heap[1];
    }

    @Override
    public City getMax() {
        if(size == 0)
            return null;

        City root = heap[1];
        heap[1] = heap[size];
        IDs[1] = IDs[size];
        size--;

        sink(1);

        return root;
    }

//    @Override
//    public City remove(int id) {
//        int i = IDs[0];
//        if (id == i ){
//            swap(i,0);
//            return getMax();
//        }
//        else if (IDs[i*2] > IDs[(i*2)+1]){
//            i = i*2;
//            return remove(IDs[i*2]);
//        }
//        else {
//           i = i*2+1;
//            return remove(IDs[(i * 2) + 1]);
//        }
//    }


    @Override
    public City remove(int id) {
        City temp = new City();
        for (int i = 1; i <= size; i++){
            if (heap[i].getID() == id){
                swap(1,i);
//                temp = heap[i];
               // heap[i] = heap[size];
//               size--;
              // swim(size);

                break;
            }

            }
        return getMax();
    }

    private void swim(int i) {
        if (i == 1)
            return;
        int parent = i/2;

        while (i != 1 && heap[i].compareTo( heap[parent]) > 0){
            swap(i, parent);
            i = parent;
            parent = i/2;
        }

    }

    private void sink(int i) {

        int left = 2 * i;
        int right = left + 1;

        if (left > size)
            return;

        while (left <= size) {

            int max = left;

            if (right <= size) {
                if (heap[left].compareTo(heap[right]) < 0   )
                    max = right;
            }
            if (heap[i].compareTo(heap[max]) >= 0)
                return;

            else {
                swap(i, max);
                i = max;
                left = 2 * i;
                right = left + 1;

            }

        }
    }

    private void swap(int i, int j) {
        City temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;

        int temp1 = IDs[i];
        IDs[i] = IDs[j];
        IDs[j] = temp1;
    }

    public void resize(){
        City[] newHeap =  new City[heap.length * 2];
        int[] newIDs = new int[IDs.length*2];
        for (int i = 1; i <= size; i++) {
            newHeap[i] = heap[i];
            newIDs[i] = IDs[i];
        }

        heap = newHeap;
        IDs = newIDs;
    }

     public void printAll(){
        for (int i =1; i <= size; i++)
            System.out.println(heap[i]);
     }

     //HREMA//

    public int[] toArray(){
        int[] citiesIDs = new int[size];
        for (int i = 0; i < size; i++)
            citiesIDs[i] = IDs[i+1];
        return citiesIDs;
    }


}
