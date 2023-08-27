package com.example.revision.heaps;

import java.util.Collections;
import java.util.PriorityQueue;

public class HeapTest {

    public static void main(String[] args) {
        System.out.println(eatMaxChocos(new int[]{2, 4, 6, 8, 10},5));
    }

    /**
     * To find min cost for the n ropes given
     */
    public static int minCostForNRopes(int[] ropes) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int minCost = 0;

        // Brute force : to sort the array at first and then after adding the first two small elements ,place the addition at the right place
        // using binary search/insertion sort. TC : 0(Nlogn + N*N) = N^2

        // 5, 17, 100, 11
        for (int i : ropes) {
            pq.add(i);
        }

        // TC : O(nLogn(adding ropes elements) + nlogn(adding ropes in priority queue))
        while (pq.size() > 1) {

            int firstRope = pq.poll();
            int secondRope = pq.poll();

            minCost += firstRope + secondRope;
            pq.add(firstRope + secondRope);
        }

        return minCost;
    }

    /**
     * To eat max number of chocolates when magician
     * replaces half of the chocolates that child eats in
     * A units of time
     *
     * @param chocos
     * @return
     */
    public static int eatMaxChocos(int[] chocos, int A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // Brute force will be same as previous statement
        // TC : O(NLogN + N^2)

        // TC : N*Log(N)
        for (int i : chocos) {
            pq.add(i);
        }

        int totalChocosEaten = 0;

        // TC : ALogN
        while (A > 0) {
            int chocosEaten = pq.poll(); //LogN
            int toBePutBack = chocosEaten / 2;

            totalChocosEaten+= chocosEaten;
            pq.add(toBePutBack); // LogN
            A--;
        }

        // Net TC : NlogN

        return totalChocosEaten;
    }
}
