package com.example.revision.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringTest {
    /**
     * To check whether if two strings are anagrams
     * @param a
     * @param b
     * @return
     */
    public static boolean checkAnagrams(String a,String b){
        Map<Character, Integer> map = new HashMap<>();

        if (a.length() != b.length()) return false;
        //anagram
        for (int i = 0; i < a.length(); i++) {
            map.put(a.charAt(i), map.getOrDefault(a.charAt(i), 0) + 1);
            map.put(b.charAt(i), map.getOrDefault(b.charAt(i), 0) - 1);
        }

        for (Character c : map.keySet()) {
            if (map.get(c) != 0) {
                return false;
            }
        }
        return true;
    }

    private static String constructNullIgnoreWhereClause(List<String> columns){

        StringBuilder whereCondition = new StringBuilder();
        int index = 0;

        while(index < columns.size()-1){
            whereCondition.append(columns.get(index)+" IS NOT NULL "+"AND ");
            index++;
        }
        whereCondition.append(columns.get(index)+" IS NOT NULL ");
        return whereCondition.toString();
    }

    public static void main(String[] args) {
        System.out.println(constructNullIgnoreWhereClause(Arrays.asList("Abhi","cHINU")));
    }
}
