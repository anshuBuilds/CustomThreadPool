import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimpleExecutor {
    private final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
    private final Thread[] workers;
    volatile boolean isShutdown = false;

    public static final Runnable POISON = () -> {};

    SimpleExecutor() {

        workers = new Thread[4];
        for (int i = 0; i < workers.length; i++) {

            workers[i] = new Thread(()->{
                while(true) {
                    try {
                        Runnable task = queue.take();

                        if(task == POISON) break;

                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            workers[i].start();
        }
    }

    public void submit(Runnable task) {

        if(isShutdown) {
            throw new IllegalStateException("Executor has been shutdown");
        }

        try {
            queue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        isShutdown = true;

        for(int i = 0; i < workers.length; i++) {
            try {
                queue.put(POISON);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
