package edu.uj.po.simulation.timer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeSimulationPropagator implements Runnable {
    private volatile AtomicInteger counter;
    private String name;
    private static final Integer TIME_DELAY = 5009;
    private static TimeSimulationPropagator instance;
    private Integer threshold;
    private CountDownLatch latch;
    private boolean frozen;
    private int latchCount = 0;
    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    private TimeSimulationPropagator() {
        super();
        Random rdn = new Random();
        this.name = "TimeSimulationPropagator_MAIN_" + rdn.nextInt();
        this.counter = new AtomicInteger();
        this.threshold = 3600;
        this.frozen = false;
    }

    public synchronized static TimeSimulationPropagator getInstance() {
        if (instance == null) {
            instance = new TimeSimulationPropagator();
        }
        return instance;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
        this.latchCount += 1;
        System.out.println("Setting latch..." + " " + this.latchCount);
    }

    public void setCounter(AtomicInteger counter) {
        this.counter = counter;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public AtomicInteger getCounter() {
        return counter;
    }

    public synchronized void tick() {
        counter.incrementAndGet();
        if (latch != null) {
            System.out.println("Releasing latch...");
            latch.countDown();
            latch = null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void reset() {
        this.counter.set(0);
    }

    public void start() throws InterruptedException {
        synchronized (this) {
            while (counter.get() < threshold) {
                if (!frozen) {
                    tick();
                    wait(TIME_DELAY);
                    System.out.println(this.name + " " + "Counter: " + counter.get());
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            start();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
