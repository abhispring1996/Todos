package com.example.revision.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashTest {


    /**
     * To find longest substring without repeating characters
     * @param s
     * @return
     */
    public static int longestSubstring(String s){

        Map<Character,Integer> map = new HashMap<>();

        int maxLen = Integer.MIN_VALUE;
        int left =0;

        for(int i=0;i<s.length();i++){

            Character currChar = s.charAt(i);
            // moving left pointer just above duplicated character
            // always make left as max becoz duplicated can be before the new left for ex -> abba for last "a"
            if(map.containsKey(currChar)){
                left = Math.max(left,map.get(currChar)+1);
            }

            map.put(currChar,i);
            maxLen = Math.max(maxLen,i-left+1);
        }

        return maxLen;
    }

    /**
     * return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
     * @param nums
     * @param k
     * @return
     */
    public static boolean isAbsoluteDiffPresent(int [] nums,int k){

        Map<Integer,Integer> valueIndex = new HashMap<>();
        // TC : O(n) SC: O(1)
        for(int i=0;i< nums.length;i++){

            if(valueIndex.containsKey(nums[i]) &&
                    Math.abs(valueIndex.get(nums[i])-i)<=k){
                return true;
            }
            valueIndex.put(nums[i],i);
        }

        return false;
    }

    /**
     * To find if there is a subset of the given set with sum equal to given sum.
     * @param nums
     * @return
     */
    public static boolean isSumPresent(int [] nums,int target){

        Set<Integer> elementsSet = new HashSet<>();
        // TC : O(n) SC: O(1)
        for(int i : nums){

            if(elementsSet.contains(Math.abs(target-i))){
                return true;
            }
            elementsSet.add(i);
        }

        return false;

    }



    public static void main(String[] args) {
        System.out.println(isSumPresent(new int[]{3, 34, 4, 12, 5, 2},30));
    }
}
