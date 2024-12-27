package com.example.switch2025.design.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AdderAndSubtractor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Counter counter = new Counter();

        Adder adderTask = new Adder(counter);
        Subtractor subtractor = new Subtractor(counter);

        Future<?> adderFuture = executorService.submit(adderTask);
        Future<?> subtractorFuture = executorService.submit(subtractor);

        try {
            adderFuture.get();
            subtractorFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Final Value after AdderSubtractor Processing is " + counter.getValue());
    }
}

class Counter {
    private int value;
    private final Object obj = new Object();

    /**
     * If we use make the method synchronized
     * <p>
     * The lock acquired is on the Counter object itself, so any thread trying to call any synchronized method
     * on the same instance will be blocked until the lock is released.
     */
    public void incrementValue(int value) {
        synchronized (obj) {
            this.value += value;
        }
    }

    public void decrementValue(int value) {
        synchronized (obj) {
            this.value -= value;
        }
    }

    public int getValue() {
        return this.value;
    }
}

class Adder implements Runnable {
    private final Counter counter;

    Adder(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10000; i++) {
            counter.incrementValue(1);
        }
    }
}

class Subtractor implements Runnable {
    private final Counter counter;

    Subtractor(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10000; i++) {
            counter.decrementValue(1);
        }
    }
}
