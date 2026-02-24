package com.jvm.tuning;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ObjectCreator {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting object allocation load...");

        ExecutorService service = Executors.newFixedThreadPool(3);

        for (int jobId = 1; jobId <= 24; jobId++) {
            service.submit(new DataLoader(jobId));
        }

        // Gracefully shutdown and wait for all tasks to complete
        service.shutdown();
        boolean finished = service.awaitTermination(1, TimeUnit.HOURS);
        if (finished) {
            System.out.println("All tasks completed. Starting monitoring phase...");
            monitor();
        } else {
            System.err.println("Timeout while waiting for tasks to finish.");
        }
    }

    private static void monitor() throws InterruptedException {
        long monitorDurationMs = 60 * 60 * 1000L;  // Monitor for 10 minutes
        long checkIntervalMs = 60 * 1000L;         // Check every 60 seconds

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < monitorDurationMs) {
            MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();

            System.out.printf("Monitoring: Heap Used: %.2f MB, Committed: %.2f MB%n",
                    heapUsage.getUsed() / (1024.0 * 1024.0),
                    heapUsage.getCommitted() / (1024.0 * 1024.0));

            Thread.sleep(checkIntervalMs);
        }
        System.out.println("Monitoring complete.");
    }

    static class DataLoader implements Runnable {
        private int jobId;

        DataLoader(int jobId) {
            this.jobId = jobId;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Job with id:" + jobId + " started.%n");
                List<byte[]> retainedObjects = new ArrayList<>();

                // Allocate about 2.5 GB (250 iterations * 10,000 * 1KB = 2.5GB)

                for (int iteration = 0; iteration < 270; iteration++) { // 10 threads*80*10MB = 8GB
                    for (int i = 0; i < 10_000; i++) { // 10 MB per iteration
                        byte[] obj = new byte[1024];
                        retainedObjects.add(obj);
                    }

//                    if (iteration % 100 == 0 && jobId % 2 == 0) {
//                        Runtime runtime = Runtime.getRuntime();
//                        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
//                        System.out.printf("Iteration %d: Used memory approx %.2f MB, Retained objects: %d%n",
//                                iteration, usedMemory / (1024.0 * 1024.0), retainedObjects.size());
//                        String errorMsg = "Simulated exception for jobId: " + jobId+".%n";
//                        System.out.printf(errorMsg);
//                        throw new RuntimeException(errorMsg);
//                    }

                    Thread.sleep(200);
                }

                // At this point, method returns and retainedObjects reference goes out of scope;
                // objects are eligible for GC.
                System.out.printf("Job with id:" + jobId + " completed.%n");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("JobId: " + jobId + " interrupted.");
            }
        }
    }
}

