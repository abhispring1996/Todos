package com.example.switch2025.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringTest {

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

    public static void main(String[] args) {
        System.out.println("Longest Common Prefix is : " + longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }
}
