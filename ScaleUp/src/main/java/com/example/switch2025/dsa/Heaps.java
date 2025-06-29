package com.example.switch2025.dsa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Heaps {

    public static boolean carPooling(int[][] trips, int capacity) {
        List<Trip> tripLists = new ArrayList<>();

        for (int[] trip : trips) {
            tripLists.add(new Trip(trip[0], trip[1], trip[2]));
        }
        Comparator<Trip> tripComparator = ((trip1, trip2) -> (trip1.getFrom() - trip2.getFrom()));
        tripLists.sort(tripComparator);

        Comparator<Trip> activeTripsComparator = ((trip1, trip2) -> (trip1.getTo() - trip2.getTo()));
        PriorityQueue<Trip> activeTripsQueue = new PriorityQueue<>(activeTripsComparator);

        int currentPassengers = 0;

        for (Trip currentTrip : tripLists) {
            // keep on polling the trip that will have already ended
            while (!activeTripsQueue.isEmpty() && activeTripsQueue.peek().getTo() <= currentTrip.getFrom()) {
                currentPassengers -= activeTripsQueue.poll().getPassengers();
            }

            currentPassengers += currentTrip.getPassengers();

            if (currentPassengers > capacity) {
                return false;
            }

            activeTripsQueue.add(currentTrip);
        }

        return true;
    }


    public static void main(String[] args) {
        int [] arr = new int[]{1,2,3};
        Set<Integer> set = IntStream.of(arr).boxed().collect(Collectors.toSet());
        System.out.println("Can we carpool: " + carPooling(new int[][]{{9, 3, 4}, {9, 1, 7}, {4, 2, 4}, {7, 4, 5}}, 24));
    }
}

class Trip {
    private final int passengers;
    private final int from;
    private final int to;

    Trip(int passengers, int from, int to) {
        this.passengers = passengers;
        this.from = from;
        this.to = to;
    }

    public int getPassengers() {
        return passengers;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}
