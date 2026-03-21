package queue;

public interface MyBlockingQueue<T> {
    void put(T item) throws InterruptedException;

    T take() throws InterruptedException;

    int size();

    boolean isEmpty();
}
