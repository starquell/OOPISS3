import java.util.concurrent.LinkedBlockingQueue;
 
public class ThreadPool  {
    
    private final WorkerThread[] workers;
    private final LinkedBlockingQueue<Runnable> tasks;
 
    public ThreadPool(int poolSize) {
        tasks = new LinkedBlockingQueue<Runnable>();
        workers = new WorkerThread[poolSize];
 
        for (int i = 0; i < poolSize; i++) {
            workers[i] = new WorkerThread();
            workers[i].start();
        }
    }
 
    public void execute (Runnable task) {
        synchronized (tasks) {
            tasks.add(task);
            tasks.notify();
        }
    }
 
    private class WorkerThread extends Thread {

        public void run() {
            Runnable task;
 
            while (true) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    task = tasks.poll();
                }
                task.run();
            }
        }
    }
 
    public void shutdown() {
        for (WorkerThread worker : workers) {
            worker.interrupt();
        }
    }
}