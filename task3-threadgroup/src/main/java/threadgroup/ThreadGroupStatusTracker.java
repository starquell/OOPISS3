package main.java.threadgroup;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadGroupStatusTracker {

    static public ExecutorService trackAsync (ThreadGroup group, int period) {

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.scheduleWithFixedDelay(() -> {
            System.out.println("*****************************************************\n");
            group.list();
            System.out.println("\n****************************************************");
        }, 50, period, TimeUnit.MILLISECONDS);

        return executor;
    }
}

