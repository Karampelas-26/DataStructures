import java.util.IdentityHashMap;

public class PQ implements InterfacePQ{

    private City[] heap;
    private int size;
    private int [] IDs;


    public PQ(int length) {

        this.heap =  new City[length + 1];
        this.size = 0;
        this.IDs = new int[1000];
    }

    public PQ(PQ priorityQueue){
        this.heap = new City[priorityQueue.heap.length];
        for(int i = 0; i < heap.length; i++){
            this.heap[i] = priorityQueue.heap[i];
        }
        this.IDs = new int[1000];
        for(int i = 0; i < 1000; i++)
            this.IDs[i] = priorityQueue.IDs[i];
        this.size = priorityQueue.size();
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
        IDs[x.getID()] = size;
        swim(size);
    }

    @Override
    public City max() {
        return isEmpty() ? null : heap[1];
    }

    @Override
    public City getMax() {
//        if(size == 0)
//            return null;
//
//        City root = heap[1];
//        heap[1] = heap[size];
//        IDs[1] = IDs[size];
//        size--;
//
//        sink(1);
//
//        return root;
        return remove(heap[1].getID());
    }

    @Override
    public City remove(int id) {
        int position = IDs[id];
        if (size == 0)
            return null;
        City temp = heap[position];
        heap[position] = heap[size];
        IDs[heap[position].getID()] = position;
//        heap[size] = null;
//        IDs[temp.getID()] = 0;
        size--;
        sink(position);
        return temp;
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

        int temp2 = IDs[heap[i].getID()];
        IDs[heap[i].getID()] = IDs[heap[j].getID()];
        IDs[heap[j].getID()] = temp2;
    }

    public void resize(){
        City[] newHeap =  new City[heap.length * 2];
        for (int i = 1; i <= size; i++) {
            newHeap[i] = heap[i];
        }

        heap = newHeap;
//        IDs = newIDs;
    }

     public void printAll(){
        for (int i =1; i <= size; i++)
            System.out.println(heap[i]);
     }

    public City getElementByPosition(int position){
        return heap[position];
    }


}
