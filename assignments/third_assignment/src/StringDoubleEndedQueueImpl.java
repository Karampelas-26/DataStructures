import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringDoubleEndedQueueImpl<T> implements StringDoubleEndedQueue<T> {

    protected static int count = 0;

    private Node head = null;
    private Node tail = null;

    public StringDoubleEndedQueueImpl() {

    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public void addFirst(T item) {
        Node data = new Node(item);
        if (this.isEmpty()){
            this.head = data;
            this.tail = data;
        }
        else {
            data.setNext(head);
            data.setPrev(null);
            this.head.setPrev(data);
            this.head = data;
        }
        count++;
    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        String data = (String) head.getData();
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
        }
        count--;
        return (T) data;
    }

    @Override
    public void addLast(T item) {
        Node data = new Node(item);
        if (this.isEmpty()) {
            this.head = data;
            this.tail = data;
        }
        else {
            data.setPrev(tail);
            data.setNext(null);
            tail.setNext(data);
            tail = data;
        }
        count++;
    }

    @Override
    public T removeLast() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        String data = (String) tail.getData();
        if (head == tail) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        count--;
        return (T) data;
    }

    @Override
    public T getFirst() {
        if (this.isEmpty()){
            throw new NoSuchElementException();
        }
        return (T) head.data;
    }

    @Override
    public T getLast() {
        if (this.isEmpty()){
            throw new NoSuchElementException();
        }
        return (T) tail.data;
    }

    @Override
    public void printQueue(PrintStream stream) {

        if (this.isEmpty()) {
            stream.println("List is empty!");
        }
        else if (this.head.getNext() == null) {
            stream.println(this.head.getData());
        }
        else {
            Node temp = this.head;
                do {
                    String str = (String) temp.getData().toString();
                    stream.print(str + "\n");
                    temp = temp.getNext();
                } while (temp.getNext() != null);
                stream.println(temp.getData());
        }
    }

    @Override
    public int size() {
        return count;
    }
}
