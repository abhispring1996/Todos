package com.example.switch2025.design.synchronization;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class MutexAndSemaphore {

    public static void main(String[] args) throws InterruptedException {
        // mutex -> for ex: printer where only one person can access it a time
        Printer printer = new Printer();
        Runnable printerTask = () -> {
            printer.print("PrintTask: " + Thread.currentThread().getName());
        };

        Thread printerT1 = new Thread(printerTask, "Task1");
        Thread printerT2 = new Thread(printerTask, "Task2");
        printerT1.start();
        printerT2.start();

        Thread.sleep(5000);

        //Semaphore -> multiple threads can access at a time for ex -> in a parking lot, slots are available for
        //             fixed number of vehicles
        ParkingLot parkingLot = new ParkingLot(2);
        Runnable parkingTask = () -> {
            parkingLot.park("PB02" + Thread.currentThread().getName());
        };

        Thread parkingT1 = new Thread(parkingTask, "T1");
        Thread parkingT2 = new Thread(parkingTask, "T2");
        Thread parkingT3 = new Thread(parkingTask, "T3");

        parkingT1.start();
        parkingT2.start();
        parkingT3.start();
    }
}

class Printer {
    private final ReentrantLock reentrantLock = new ReentrantLock();

    public void print(String taskName) {
        reentrantLock.lock();
        System.out.println("Printer Mutex Lock acquired by:" + taskName);
        reentrantLock.unlock();
    }

}

class ParkingLot {
    private final Semaphore slots;

    public ParkingLot(int capacity) {
        slots = new Semaphore(capacity);
    }

    public void park(String vehicleNumber) {

        try {
            System.out.println(vehicleNumber + " waiting for the parking slot");
            slots.acquire();
            System.out.println(vehicleNumber + " is being parked");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            slots.release();
            System.out.println(vehicleNumber + " has left");
        }
    }
}