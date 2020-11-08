package test.lab;

import main.lab.CyclicBarrier;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CyclicBarrierTest {

    public void testForNThreads (int threadsNum) throws InterruptedException {

        var barrier = new CyclicBarrier(threadsNum);

        assertEquals(threadsNum, barrier.getParties());

        var nthThreadWaits = new AtomicBoolean[threadsNum - 1];
        Arrays.fill(nthThreadWaits, new AtomicBoolean(false));

        IntStream.range(0, threadsNum - 1).
                mapToObj(num -> new Runnable() {
                          public void run() {
                              try {
                                  Thread.sleep(100);
                                  nthThreadWaits[num].set(true);
                                  barrier.await();
                                  nthThreadWaits[num].set(false);
                              } catch (InterruptedException ignored) { }
                            }
            }).map(Thread::new)
              .forEach(Thread::start);

        Thread.sleep(150);
        assertTrue(Arrays.stream(nthThreadWaits).allMatch(AtomicBoolean::get));
        assertEquals(threadsNum - 1, barrier.getNumberWaiting());

        barrier.await();
        Thread.sleep(100);
        assertTrue(Arrays.stream(nthThreadWaits).allMatch(waits -> waits.compareAndSet(false, false)));
    }

    @Test
    public void mainTest() throws InterruptedException {

        testForNThreads(2);
        testForNThreads(5);
        testForNThreads(7);
    }
}