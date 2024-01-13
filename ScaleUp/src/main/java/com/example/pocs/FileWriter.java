package com.example.pocs;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class FileWriter implements Runnable {

    java.io.FileWriter writer;
    boolean shutdown;
    String fileDestination;

    BlockingQueue<String> blockingQueue;

    public FileWriter(String fileDestination, BlockingQueue<String> queue) {
        this.blockingQueue = queue;
        this.fileDestination = fileDestination;
        File file = new File(fileDestination);
        try {
            this.writer = new java.io.FileWriter(file, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void run() {
        runIndefinitely();
    }

    private void runIndefinitely() {

        while (true) {

            if (shutdown) {
                System.out.println("Shutting down thread : " + Thread.currentThread().getName());
                try {
                    this.writer.flush();
                    this.writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return;
            }

            try {
                Object toWrite = blockingQueue.poll(10, TimeUnit.SECONDS);
                String payload = (String) toWrite;
                if (null != payload) {
                    System.out.println("Writing content " + payload + " at location " + fileDestination + "for thread : " + Thread.currentThread().getName());
                    this.writer.write(payload + "\n");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
