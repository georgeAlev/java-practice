package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterRace {

    public static void main(String[] args) throws InterruptedException {
        // Run one example at a time: uncomment the one you want.
        // startWithoutJoin();
        // runInsteadOfStart();
        // startAndJoin();
        // twoThreadsRace();
        // twoSynchronizedThreadsRace();
        atomicCounterRace();
    }

    // start() returns immediately, so main prints before thread A has worked → usually 0
    static void startWithoutJoin() {
        Counter counter = new Counter();

        Thread a = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
            System.out.println(Thread.currentThread().getName());
        });
        a.start();

        System.out.println(counter.getCounter());
    }

    // run() is a plain method call, so main runs the lambda itself (no new thread) → prints "main", then 10000
    static void runInsteadOfStart() {
        Counter counter = new Counter();

        Thread a = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
            System.out.println(Thread.currentThread().getName());
        });
        a.run();

        System.out.println(counter.getCounter());
    }

    // T3: join() makes main wait for thread A → always 10000
    static void startAndJoin() throws InterruptedException {
        Counter counter = new Counter();

        Thread a = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
            System.out.println(Thread.currentThread().getName());
        });
        a.start();
        a.join();

        System.out.println(counter.getCounter());
    }

    // two threads share ONE counter
    static void twoThreadsRace() throws InterruptedException {

        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
            System.out.println(Thread.currentThread().getName());
        };

        Thread a = new Thread(task);
        Thread b = new Thread(task);

        a.start();
        b.start();
        a.join();
        b.join();

        System.out.println(counter.getCounter());
    }

    // two threads share ONE counter → the race
    static void twoSynchronizedThreadsRace() throws InterruptedException {

        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.synchronizedIncrement();
            }
            System.out.println(Thread.currentThread().getName());
        };

        Thread a = new Thread(task);
        Thread b = new Thread(task);

        a.start();
        b.start();
        a.join();
        b.join();

        System.out.println(counter.getCounter());
    }

    static void atomicCounterRace() throws InterruptedException {

        AtomicCounter counter = new AtomicCounter();

        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increment();
            }
            System.out.println(Thread.currentThread().getName());
        };

        Thread a = new Thread(task);
        Thread b = new Thread(task);

        a.start();
        b.start();
        a.join();
        b.join();

        System.out.println(counter.getCounter());
    }



}
