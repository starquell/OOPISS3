import org.junit.jupiter.api.BeforeEach;

import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;



class ThreadPoolTest {

    private final AtomicInteger counter = new AtomicInteger(0);

     @BeforeEach
    void clearCounter() {
         var counter = new AtomicInteger(0);
    }


    static void sleepAndIncrement(int ms, AtomicInteger counter) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException ignored) {}
        counter.incrementAndGet();
    }

    @org.junit.jupiter.api.Test
    void allParallel() throws InterruptedException {

        final var poolSize = 4;
        final var pool = new ThreadPool(poolSize);
        final int sleepTime = 1000;

        for (int i = 0; i < poolSize; ++i) {
            pool.execute(() -> sleepAndIncrement(sleepTime, counter));
        }
        Thread.sleep(sleepTime + 100);

        assertEquals(poolSize, counter.get());
    }

    @org.junit.jupiter.api.Test
    void allSuccessively() throws InterruptedException {

         final var poolSize = 1;
         final var pool = new ThreadPool(poolSize);
         final int sleepTime = 400;

         for (int i = 0; i < poolSize; ++i) {
             final int finalI = i;

             pool.execute(() -> {
                 try {
                     Thread.sleep(sleepTime);
                 } catch (InterruptedException ignored) {}

                 assertEquals(finalI + 1, counter.incrementAndGet());
            });
         }
         Thread.sleep((sleepTime + 100) * poolSize);
     }
}

