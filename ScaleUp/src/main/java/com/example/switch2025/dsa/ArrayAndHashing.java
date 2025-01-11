package com.example.switch2025.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayAndHashing {

    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> characterFreqMap = new HashMap<>();

        for (int index = 0; index < s.length(); index++) {
            Character char1 = s.charAt(index);
            Character char2 = t.charAt(index);
            // if not found will return 1
            // if already present, will subtract one from it
            characterFreqMap.put(char1,
                    characterFreqMap.getOrDefault(char1, 0) + 1);
            characterFreqMap.put(char2,
                    characterFreqMap.getOrDefault(char2, 0) - 1);
        }

        for (Character characterKey : characterFreqMap.keySet()) {
            if (characterFreqMap.get(characterKey) != 0) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> strToAnagramGroupMap = new HashMap<>();

        for (String str : strs) {
            char[] charArray = new char[26];

            for (char c : str.toCharArray()) {
                charArray[c - 'a']++;
            }

            String anagramGroupKey = new String(charArray);
            List<String> anagramList = strToAnagramGroupMap.getOrDefault(anagramGroupKey, new ArrayList<>());
            anagramList.add(str);
            strToAnagramGroupMap.put(anagramGroupKey, anagramList);
        }

        return new ArrayList<>(strToAnagramGroupMap.values());
    }

    public static String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        // ["flower","flow","flight"]
        // we can sort the Array and then compare the first and last
        // TC : n*k*logn -> k is the average length of each string

        for (int i = 1; i < strs.length; i++) {
            int commonPrefixPtr = 0;
            String currentStr = strs[i];
            for (int j = 0; j < Math.min(prefix.length(), currentStr.length()); j++) {
                if (prefix.charAt(j) != currentStr.charAt(j)) {
                    break;
                }
                commonPrefixPtr++;
            }
            prefix = prefix.substring(0, commonPrefixPtr);
        }
        return prefix;
    }

    public static int findNumberGreaterThanEqualTo(int[] nums) {
        // max contenders will the length of the nums array
        // count the occurrences in the array just like the bucket sort
        int[] count = new int[nums.length + 1];

        for (int num : nums) {
            // if the currentElement is greater than the count length, increment the lastElementCount
            int countIndex = Math.min(count.length - 1, num);
            count[countIndex] += 1;
        }

        int totalRightOccurence = 0;
        // we will start from the end and keep pn adding the rightOccurence
        // at every index just like we do in prefix array
        for (int countIndex = count.length - 1; countIndex > 0; countIndex--) {
            totalRightOccurence += count[countIndex];
            if (totalRightOccurence == countIndex) {
                return countIndex;
            }
        }

        return -1;
    }
}
