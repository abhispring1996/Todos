package com.example.revision.hashing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * To group anagrams
     * @param words
     * @return
     */
    public static List<List<String>> groupAnagrams(String [] words) {

        Map<String, List<String>> map = new HashMap<>();
        // TC : O(ArrayLen*wordLen*26(Time taken to perform hashing operations of hash and equals))
        char[] freq = new char[26];
        for (String word : words) {

            // will create a key using the freq array
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }
            String key = new String(freq);
            List<String> anagramList = map.getOrDefault(key, new ArrayList<>());
            anagramList.add(word);
            map.put(key, anagramList);
        }
        return new ArrayList<>(map.values());
    }

    public static boolean containsDuplicate(int[] nums) {

        Set<Integer> numsSet = new HashSet<>();

        for (int i : nums) {

            if (!numsSet.add(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
//        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
//        containsDuplicate(new int[]{1,2,3,4,1});

        Path directory = Paths.get("C:\\SnowflakeUploadFiles\\OneChunkUpload\\Group_of_5\\1/");

        try {
            List<Path> filePaths = Files.walk(directory)
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

            // Print all file paths
            for (Path path : filePaths) {
                System.out.println(path);
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }
        Set<String> set = new HashSet<String>(){{
            add("add");
            add("subtract");
        }};
        System.out.println(set);
    }
}
