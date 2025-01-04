package com.example.switch2025.dsa;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Sorting {

    public static List<Integer> findTopKFrequentElementsSort(int[] nums, int k) {
        Map<Integer, Integer> elementFreqMap = new HashMap<>();

        for (int element : nums) {
            elementFreqMap.put(element, elementFreqMap.getOrDefault(element, 0) + 1);
        }

        List<Pair> pairList = new ArrayList<>();
        // Need sort based on the value present in the Map
        //construct Pair
        for (Integer key : elementFreqMap.keySet()) {
            pairList.add(new Pair(key, elementFreqMap.get(key)));
        }

        pairList.sort(new PairComparator());

        int index = 0;
        List<Integer> topKFrequentElements = new ArrayList<>();

        while (index < k) {
            topKFrequentElements.add(pairList.get(index).getKey());
            index++;
        }

        //            TC      SC -> u is the number of unique elements
        //Sorting   u*logu    0(u)
        //MinHeap   u*logk
        return topKFrequentElements;
    }

    public static List<Integer> findTopKFrequentHeapSort(int[] nums, int k) {
        Map<Integer, Integer> elementFreqMap = new HashMap<>();

        for (int element : nums) {
            elementFreqMap.put(element, elementFreqMap.getOrDefault(element, 0) + 1);
        }

        PriorityQueue<Pair> pairPriorityQueue = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        for (Integer key : elementFreqMap.keySet()) {
            pairPriorityQueue.add(new Pair(key, elementFreqMap.get(key)));
            // logk -> for each insertion since size is maintained at k

            if (pairPriorityQueue.size() > k) {
                pairPriorityQueue.poll();
            }
        }

        List<Integer> topKFrequentElements = new ArrayList<>();

        while (!pairPriorityQueue.isEmpty()) {
            Pair polledPair = pairPriorityQueue.poll();
            topKFrequentElements.add(polledPair.getKey());
        }

        //            TC      SC -> u is the number of unique elements
        //Sorting   u*logu    0(u)
        //MinHeap   u*logk
        Collections.reverse(topKFrequentElements);
        return topKFrequentElements;
    }

    public static int[] findTopKFrequentBucketSort(int[] nums, int k) {
        Map<Integer, Integer> elementFreqMap = new HashMap<>();

        for (int element : nums) {
            elementFreqMap.put(element, elementFreqMap.getOrDefault(element, 0) + 1);
        }

        // 1, 1, 1, 2, 2, 3

        Bucket[] bucket = new Bucket[nums.length + 1];

        for (Integer key : elementFreqMap.keySet()) {
            int freq = elementFreqMap.get(key);

            if (null == bucket[freq]) {
                bucket[freq] = new Bucket();
            }

            bucket[freq].getElements().add(key);
        }

        int[] ret = new int[k];
        int retIndex = 0;
        int index = bucket.length - 1;

        while (index > 0) {
            if (null != bucket[index]) {
                for (Integer element : bucket[index].getElements()) {

                    if (retIndex == k) {
                        return ret;
                    }
                    ret[retIndex] = element;
                    retIndex++;
                }
            }
            index--;
        }
        return ret;
    }


    public static void main(String[] args) {
        System.out.println("Top K Frequent elements is: " + Arrays.toString(findTopKFrequentBucketSort(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }
}

class Bucket {
    private final List<Integer> elements;

    Bucket() {
        this.elements = new ArrayList<>();
    }

    public void add(Integer element) {
        this.elements.add(element);
    }

    public List<Integer> getElements() {
        return this.elements;
    }
}

class PairComparator implements Comparator<Pair> {

    @Override
    public int compare(Pair p1, Pair p2) {
        return p2.getValue() - p1.getValue();
    }
}

class Pair {
    private final Integer key;
    private final Integer value;

    public Pair(Integer key, Integer value) {

        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}
