package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {

    private final AtomicInteger counter = new AtomicInteger();

    public void increment() {
        this.counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }
}
