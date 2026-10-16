package concurrency;

public class Counter {

    private int counter;

    public void increment() {
        this.counter++;
    }

    public int getCounter() {
        return this.counter;
    }

    public synchronized void synchronizedIncrement() {
        this.counter++;
    }
}
