package thread_tasks;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Thread.sleep;

public class SecondTask {

    private SecondTask() {
        throw new IllegalStateException("Utility class");
    }

    private static final List<String> LIST = new CopyOnWriteArrayList<>();
    private static final Thread A = new Thread(SecondTask::fizz);
    private static final Thread B = new Thread(SecondTask::buzz);
    private static final Thread C = new Thread(SecondTask::fizzbuzz);
    private static final Thread D = new Thread(SecondTask::number);


    public static void runFizzBuzz(int n) throws InterruptedException {

        addToList(n);

        synchronized (LIST) {
            A.start();
            B.start();
            C.start();
            sleep(100);
            D.start();
        }
    }

    private static void addToList(int n) {

        for (int i = 0; i <= n; i++) {
            LIST.add(i, String.valueOf(i));
        }
    }

    private static void fizz() {

        for (int i = 1; i < LIST.size(); i++) {
            if (i % 3 == 0 && i % 15 != 0) {
                LIST.set(i, "fizz");
            }
        }
    }

    private static void buzz() {

        for (int i = 1; i < LIST.size(); i++) {
            if (i % 5 == 0 && i % 15 != 0) {
                LIST.set(i, "buzz");
            }
        }
    }

    private static void fizzbuzz() {

        for (int i = 1; i < LIST.size(); i++) {
            if (i % 15 == 0) {
                LIST.set(i, "fizzbuzz");
            }
        }
    }

    private static void number() {

        for (String str : LIST) {
            System.out.println(str);
        }
    }
}