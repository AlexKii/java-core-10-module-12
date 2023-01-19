package thread_tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class FirstTask {

    private FirstTask() {
        throw new IllegalStateException("Utility class");
    }

    private static final long START = System.currentTimeMillis();
    private static final Thread PERSECOND =new Thread(() ->
            System.out.println("Start was " + (System.currentTimeMillis() - START) / 1000 + " second(s) ago"));
    private static final Thread PERFIVESECONDS = new Thread(() ->
            System.out.println("5 seconds have passed"));

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public static void runTime() {

        scheduler.scheduleAtFixedRate(PERSECOND, 1, 1, SECONDS);
        scheduler.scheduleAtFixedRate(PERFIVESECONDS, 5, 5, SECONDS);

        scheduler.schedule(scheduler::shutdown, 17, SECONDS);
    }
}
