package edu.uj.po.simulation.timer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TimeSimulationPropagator implements Runnable {
    private volatile AtomicInteger counter;
    private String name;
    private static final Integer TIME_DELAY = 2000;
    private static TimeSimulationPropagator instance;
    private Integer threshold;
    private CountDownLatch latch;

    private TimeSimulationPropagator() {
        super();
        Random rdn = new Random();
        this.name = "TimeSimulationPropagator_MAIN_" + rdn.nextInt();
        this.counter = new AtomicInteger();
        this.threshold = 3600;
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

    public void tick() {
        counter.incrementAndGet();
        if (latch != null) {
            latch.countDown();
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
                tick();
                wait(TIME_DELAY);
                System.out.println(this.name + " " + "Counter: " + counter.get());
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
