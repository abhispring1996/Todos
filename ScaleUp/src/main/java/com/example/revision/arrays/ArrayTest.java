package com.example.revision.arrays;

import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.ArrayList;

public class ArrayTest {

    /**
     * Find the max value for f(i,j) = |A[i]-A[j]| +|i-j|
     * @param nums
     * @return
     */

    public static int maxDiff(int [] nums){
        return 0;
    }

    /**
     * To find first missing positive number
     * @param nums
     * @return
     */
    public static int firstMissingNumber(int[] nums) {
        // 1. To create a visited array  TC -> O(n)     SC-> O(n)
        // 2. To sort and then find      TC -> 0(nlogn) SC -> O(1)

        // A = [100,2,3,1,-1]
        // TC -> 0(n) SC -> O(n)
        // place numbers at the exact position
        for (int i = 0; i < nums.length; i++) {

            while (nums[i] > 0 && nums[i] < nums.length && nums[i]!=nums[nums[i]-1]) {

                int toBeSwap = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[toBeSwap - 1] = toBeSwap;
            }

        }

        int firstMissNumber = 1;

        // iterate to find the first missing positive number
        for (int i = 0; i < nums.length; i++) {

            if (firstMissNumber != nums[i]) {
                return firstMissNumber;
            }
            firstMissNumber++;
        }
        // returning if every number is present
        return nums.length+1;
    }

    public static class Interval {
        int start;
        int end;

        public Interval(){

        }
    }

    /**
     * To insert a  interval and merge if necessary
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {


        ArrayList<Interval> ret = new ArrayList<>();

        int index = 0;

        // inserting all intervals having start time less than start time of interval to be merged
        while (index < intervals.size()) {

            Interval currentInterval = intervals.get(index);

            if (currentInterval.start < newInterval.start) {
                ret.add(currentInterval);
                index++;
            } else {
                break;
            }
        }

        // inserting the new interval
        if (ret.size() == 0 || ret.get(ret.size() - 1).end < newInterval.start) {
            ret.add(newInterval);
        } else {
            Interval lastInterval = ret.get(ret.size() - 1);
            lastInterval.end = Math.max(lastInterval.end, newInterval.end);
        }
        // 1-3 4-6 5-9
        // merging
        while (index < intervals.size()) {

            Interval lastInterval = ret.get(ret.size() - 1);
            Interval currInterval = intervals.get(index);

            if (lastInterval.end >= currInterval.start) {
                lastInterval.end = Math.max(lastInterval.end, currInterval.end);
            } else {
                ret.add(currInterval);
            }
            index++;
        }

        return ret;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingNumber(new int[]{100, 2, 3, 1, -1}));
        System.out.println(firstMissingNumber(new int[]{1, 2, 3, 4, 5}));
        System.out.println(firstMissingNumber(new int[]{100, -2, -3, 0, -1}));
        System.out.println(firstMissingNumber(new int[]{2, 3, 1, 2}));

    }
}
