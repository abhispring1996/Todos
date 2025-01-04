package com.example.switch2025.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Hashing {

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
}
