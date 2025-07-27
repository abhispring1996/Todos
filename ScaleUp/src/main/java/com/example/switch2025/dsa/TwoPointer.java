package com.example.switch2025.dsa;

import java.util.HashSet;
import java.util.Set;

public class TwoPointer {

    public int numberOfSubSequencesMatchingTheTarget(int[] nums, int target) {
        int[] pow = new int[nums.length];
        pow[0] = 1;

        int numberOfSubSeq = 0;

        // do the pre computation of powers of 2
        for (int index = 1; index < pow.length; index++) {
            pow[index] = (pow[index - 1] * 2);
        }

        int leftPointer = 0;
        int rightPointer = nums.length - 1;

        // 3,5,6,7
        // using equals as the single element can act as the min and max [2,3,4,4,6,7], target:12 -> value 6
        while (leftPointer <= rightPointer) {

            if (nums[leftPointer] + nums[rightPointer] > target) {
                rightPointer--;
            } else {
                // excluding the current element at left pointer
                // how many subsequences can be create from [leftpointer+1,rightpointer]
                numberOfSubSeq += pow[rightPointer - leftPointer];
                leftPointer++;
            }
        }
        return numberOfSubSeq;
    }

    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int resSubArrays = 0;
        int currSum = 0;
        int targetSum = k * threshold;

        // 2,2,2,2,5,5,5,8
        for (int index = 0; index < k; index++) {
            currSum += arr[index];
        }

        if (currSum >= targetSum) {
            resSubArrays++;
        }

        for (int index = k; index < arr.length; index++) {
            currSum = currSum + arr[index] - arr[index - k];
            if (currSum >= targetSum) {
                resSubArrays++;
            }
        }

        return resSubArrays;
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> characterSet = new HashSet<>();
        int left = 0;
        int resLen = 0;
        // pwwkew
        for (int right = 0; right < s.length(); right++) {
            // on finding the duplicate removing all the characters as they are worthless now
            while (characterSet.contains(s.charAt(right))) {
                characterSet.remove(s.charAt(left));
                left++;
            }

            characterSet.add(s.charAt(right));
            resLen = Math.max(resLen, right - left);
        }

        return resLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("kwwkew"));
    }
}
