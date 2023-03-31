package com.example.revision.hashing;

import java.util.HashMap;
import java.util.Map;

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

    public static void main(String[] args) {

    }
}
