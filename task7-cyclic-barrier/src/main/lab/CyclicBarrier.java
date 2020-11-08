package main.lab;

import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrier {

    private final int threads;
    private final AtomicInteger waiting = new AtomicInteger(0);

    public CyclicBarrier (int threads) {
        this.threads = threads;
    }

    public void await() throws InterruptedException {
        if (waiting.incrementAndGet() != threads) {
            synchronized (this) {
                while (waiting.get() != 0) {
                    wait();
                }
            }
        }
        else {
            waiting.set(0);
            synchronized (this) {
                notifyAll();
            }
        }
    }

    public int getNumberWaiting() {
        return waiting.get();
    }

    public int getParties() {
        return threads;
    }
}
