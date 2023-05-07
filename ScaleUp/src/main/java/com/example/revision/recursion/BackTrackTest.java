package com.example.revision.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackTrackTest {
    /**
     * To generate parenthesis
     * @param n
     * @param ret
     * @param openingBracket
     * @param closingBracket
     * @param currentString
     */
    public static void createParenthesis(int n,List<String> ret,int openingBracket,int closingBracket,String currentString){

        if(currentString.length() == 2*n){
            ret.add(currentString);
        }
        // TC -> O(2^No of pairs i.e 2n) -> O(2^2n)
        // SC -> O(2^2n) recursive + Ret list
        if(openingBracket < n){
            createParenthesis(n,ret,openingBracket+1,closingBracket,currentString+"(");
        }
        if(closingBracket < openingBracket){
            createParenthesis(n,ret,openingBracket,closingBracket+1,currentString+")");
        }

    }

    public static final Map<Character,String> letters = new HashMap<>();

    static{
        letters.put('2',"abc");
        letters.put('3',"def");
        letters.put('4',"ghi");
        letters.put('5',"jkl");
        letters.put('6',"mno");
        letters.put('7',"pqrs");
        letters.put('8',"tuv");
        letters.put('9',"wxyz");
    }

    /**
     * To create letter combinations
     */
    public static void createLetterCombinations(int idx,List<String>ret,String curr,String digits){

        if(curr.length()==digits.length()){
            ret.add(curr);
            return;
        }

        String letter = letters.get(digits.charAt(idx));
        // TC -> N(Time to copy the string)*(No of choices -> Max Letter length -> Pqrs -> 4) ^ (Length of given digit string) -> N*(4^N)
        for(int i=0;i<letter.length();i++){

            createLetterCombinations(idx+1,ret,curr + letter.charAt(i),digits);
        }
    }

    public static void main(String[] args) {
        List<String> ret = new ArrayList<>();
        createLetterCombinations(0,ret,"","238");
        System.out.println(ret);
    }
}
