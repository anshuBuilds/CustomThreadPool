import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimpleExecutor {
    private Thread worker;
    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);

    SimpleExecutor() {
        worker = new Thread(()->{
            while(true){
                Runnable task = queue.poll();
                if(task != null){
                    task.run();
                }
            }
        });
        worker.start();
    }

    public void submit(Runnable task) {
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
