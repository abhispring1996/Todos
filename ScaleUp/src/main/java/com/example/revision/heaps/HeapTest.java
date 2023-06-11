package com.example.revision.heaps;

import java.util.PriorityQueue;

public class HeapTest {

    public static void main(String[] args) {
        System.out.println(minCostForNRopes(new int[]{5, 17, 100, 11}));
    }
    /**
     * To find min cost for the n ropes given
     */
    public static int minCostForNRopes(int [] ropes){

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int minCost =0;

        // Brute force : to sort the array at first and then after adding the first two small elements ,place the addition at the right place
        // using binary search/insertion sort. TC : 0(Nlogn + N*N) = N^2

        // 5, 17, 100, 11
        for(int i: ropes){
            pq.add(i);
        }

        // TC : O(nLogn(adding ropes elements) + nlogn(adding ropes in priority queue))
        while(pq.size()> 1){

            int firstRope = pq.poll();
            int secondRope = pq.poll();

            minCost += firstRope + secondRope;
            pq.add(firstRope + secondRope);
        }

        return minCost ;
    }
}
