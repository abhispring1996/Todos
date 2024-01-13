/*
package com.example.pocs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CustomConcurrentLinkedQueue queue = new CustomConcurrentLinkedQueue();


        List<Future> futureList = new ArrayList<>();
        EventDistributorTask eventDistributorTask = new EventDistributorTask(queue);
        EventCleanerTask eventCleanerTask = new EventCleanerTask(queue);
        futureList.add(executorService.submit(eventDistributorTask));
        futureList.add(executorService.submit(eventCleanerTask));


        String state = "STARTED";
        String value = "Test_";


        for (int i = 0; i < 5; i++) {
            Event event = new Event(value + i, state);
            queue.offer(event);
            System.out.println("Event loaded : " + event);
        }


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        eventCleanerTask.shutdown();
        eventDistributorTask.shutdown();

        for (Future tasks : futureList) {

            if (tasks.isDone()) {
                System.out.println("Task Completed");
            }
        }

        executorService.shutdown();
    }

    static class Event {
        String value;
        String state;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        Event(String value, String state) {
            this.value = value;
            this.state = state;
        }


        @Override
        public String toString() {
            return "Event{" +
                    "value='" + value + '\'' +
                    ", state='" + state + '\'' +
                    '}';
        }
    }

    static class EventDistributorTask implements Callable {

        CustomConcurrentLinkedQueue queue;

        boolean shutdown;

        EventDistributorTask(CustomConcurrentLinkedQueue queue) {
            this.queue = queue;
        }

        @Override
        public Object call() throws Exception {

            CustomConcurrentLinkedQueue.Node head = null;
            CustomConcurrentLinkedQueue.Node currentNode = null;

            while (true) {

                if (shutdown && queue.isEmpty()) {
                    return "DONE";
                }

                if (head == null && queue.first() != null) {
                    head = queue.first();
                    currentNode = head;
                }

                while (currentNode != null && currentNode.next != null) {
                    Event payload = (Event) currentNode.value;

                    if (payload != null) {
                        System.out.println("Distributing event : " + payload);
                        payload.setState("DONE");
                    }
                    currentNode = queue.succ(currentNode);
                }

            }
        }

        public void shutdown() {
            this.shutdown = true;
        }
    }

    static class EventCleanerTask implements Callable {

        CustomConcurrentLinkedQueue queue;

        boolean shutdown;

        EventCleanerTask(CustomConcurrentLinkedQueue queue) {
            this.queue = queue;
        }

        @Override
        public Object call() throws Exception {

            while (true) {

                if (shutdown && queue.isEmpty()) {
                    return "DONE";
                }

                Event currentValue = (Event) this.queue.peek();

                if (currentValue != null && currentValue.getState() == "DONE") {

                    currentValue = (Event) this.queue.poll();
                    System.out.println("Cleaned event : " + currentValue.toString());

                }
            }
        }

        public void shutdown() {
            this.shutdown = true;
        }
    }
}
*/
