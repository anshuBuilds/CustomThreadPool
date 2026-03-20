package executor;

import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable {

    private final BlockingQueue<Runnable> queue;
    private final Runnable POISON;

    public Worker(BlockingQueue<Runnable> queue, Runnable POISON) {
        this.queue = queue;
        this.POISON = POISON;
    }
    @Override
    public void run() {

        while(true) {
            try {
                Runnable task = queue.take();

                if(task == POISON) break;

                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
