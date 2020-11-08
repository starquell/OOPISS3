package test;

import main.java.threadgroup.ThreadGroupStatusTracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

class ThreadGroupStatusTrackerTest {

    static void longTask(int ms) {
         try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static ByteArrayOutputStream redirectStdout() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        return baos;
    }

    @Test
    void testTracking() throws InterruptedException, IOException {

        final var group = new ThreadGroup("Test group");
        final var subgroup = new ThreadGroup(group, "Test subgroup");

        final var threads = new Thread[] {
                new Thread(group, () -> longTask(2500)),
                new Thread(group, () -> longTask(1500)),
                new Thread(group, () -> longTask(3000)),
                new Thread(group, () -> longTask(4500)),
                new Thread(subgroup, () -> longTask(1300)),
                new Thread(subgroup, () -> longTask(3500))
        };

        final var redirectedOut = redirectStdout();

        final var a = ThreadGroupStatusTracker.trackAsync(group, 1000);

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(Files.readString(Paths.get("expectedOutput.txt")).trim(),
                     redirectedOut.toString().trim());
    }
}