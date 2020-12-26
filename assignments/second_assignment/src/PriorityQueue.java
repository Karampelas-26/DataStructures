import java.util.function.Predicate;

public class PriorityQueue implements InterfacePQ {

    private final float GROW_CAPACITY = 0.75f;
    private City[] heap;
    private int activeElements;
    private int[] positionIDs;

    PriorityQueue(int capacity) {
        this.heap = new City[capacity];
        this.activeElements = 0;
        this.positionIDs = new int[1000];
    }

    @Override
    public boolean isEmpty() {
        return activeElements == 0;
    }

    @Override
    public int size() {
        return activeElements;
    }

    @Override
    public void insert(City item) {
        System.out.println((float) activeElements / (heap.length - 1));
        if ((float) activeElements / (heap.length - 1) > GROW_CAPACITY)
            resize();
        heap[++activeElements] = item;
        positionIDs[item.getID()] = activeElements;
        swim(activeElements);
    }

    @Override
    public City max() {
        if (isEmpty())
            return null;
        return heap[1];
    }

    @Override
    public City getMax() {
        City root = heap[1];
        heap[1] = heap[activeElements];
        activeElements--;
        sink(1);
        return root;
    }

    @Override
    public City remove(int id) {
        int position = positionIDs[id];
        City temp = heap[position];
        heap[position] = heap[activeElements];
        activeElements--;
        sink(position);
        return temp;
    }

    private void swap(int i, int j) {
        City temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;

        int num = positionIDs[heap[i].getID()];
        positionIDs[heap[i].getID()] = positionIDs[heap[j].getID()];
        positionIDs[heap[j].getID()] = num;
    }

    public void sink(int position) {
        int left = position * 2;
        int right = left + 1;

        if (left > activeElements)
            return;

        while (left <= activeElements) {
            int max = left;
            if (right <= activeElements) {
                if (heap[left].compareTo(heap[right]) > 0)
                    max = right;
            }

            if (heap[position].compareTo(heap[max]) <= 0)
                return;
            else {
                swap(position, max);
                left = position * 2;
                right = left + 1;
            }
        }
    }

    public void swim(int position) {
        if (position == 1)
            return;

        int parent = position / 2;

        while (position != 1 && heap[position].compareTo(heap[parent]) > 0) {
            swap(position, parent);
            position = parent;
            parent = position / 2;
        }
    }

    private void resize() {
        City[] tempArr = new City[heap.length * 2];
        for (int i = 1; i <= size(); i++)
            tempArr[i] = heap[i];
        heap = tempArr;
    }

    public void printAll(){
        for (City city: heap)
            System.out.println(city);
    }
}
