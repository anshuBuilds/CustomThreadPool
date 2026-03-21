package queue;

public class ArrayBlockingQueueCustom<T> implements MyBlockingQueue<T> {

    private final T[] items;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private final int capacity;

    public ArrayBlockingQueueCustom(int capacity) {
        this.capacity = capacity;
        this.items = (T[]) new Object[capacity];
    }


    @Override
    public void put(T item) throws InterruptedException {

    }

    @Override
    public T take() throws InterruptedException {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
