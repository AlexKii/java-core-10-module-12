package thread_tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class FirstTask {

    private FirstTask() {
        throw new IllegalStateException("Utility class");
    }

    private static final long START = System.currentTimeMillis();
    private static final Thread PER_SECOND =new Thread(() ->
            System.out.println("The start was " + (System.currentTimeMillis() - START) / 1000 + " second(s) ago"));
    private static final Thread PER_FIVE_SECONDS = new Thread(() ->
            System.out.println("5 seconds have passed"));

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public static void runTime() {

        scheduler.scheduleAtFixedRate(PER_SECOND, 1, 1, SECONDS);
        scheduler.scheduleAtFixedRate(PER_FIVE_SECONDS, 5, 5, SECONDS);

        scheduler.schedule(scheduler::shutdown, 17, SECONDS);
    }
}
