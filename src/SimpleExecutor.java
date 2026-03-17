import java.util.LinkedList;
import java.util.Queue;

public class SimpleExecutor {
    private Thread worker;
    private final Queue<Runnable> queue = new LinkedList<>();

    SimpleExecutor() {
        worker = new Thread(()->{
            while(true){
                Runnable task;

                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    task = queue.poll();
                }
                task.run();
            }
        });
        worker.start();
    }

    public void submit(Runnable task) {
        synchronized (queue) {
            queue.offer(task);
            queue.notify();
        }
    }
}
