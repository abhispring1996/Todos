package com.example.switch2025.dsa.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchSuggestionSystem {

    public static void main(String[] args) {
        System.out.println("The suggestions are: "
                + getSuggestions("mouse", new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}));
    }

    public static List<List<String>> getSuggestions(String searchWord, String[] products) {
        // to keep the lexicographically similar strings together
        Arrays.sort(products);
        TrieNode root = new TrieNode();

        List<List<String>> suggestions = new ArrayList<>();

        for (String word : products) {
            registerInTrie(root, word);
        }

        for (Character searchChar : searchWord.toCharArray()) {
            Map<Character, TrieNode> chidrenMap = root.getChidrenMap();

            if (!chidrenMap.containsKey(searchChar)) {
                // No suggestions
                break;
            }

            root = chidrenMap.get(searchChar);
            suggestions.add(root.getSuggestions());
        }

        // to fill teh empty suggestions
        while (suggestions.size() < searchWord.length()) {
            suggestions.add(new ArrayList<>());
        }

        return suggestions;
    }

    private static void registerInTrie(TrieNode root, String word) {

        for (Character c : word.toCharArray()) {
            Map<Character, TrieNode> chidrenMap = root.getChidrenMap();

            if (!chidrenMap.containsKey(c)) {
                chidrenMap.put(c, new TrieNode());
            }
            root = chidrenMap.get(c);
            List<String> childNodeSuggestions = root.getSuggestions();

            if (childNodeSuggestions.size() < 3) {
                childNodeSuggestions.add(word);
            }
        }
    }
}

class TrieNode {

    private final Map<Character, TrieNode> chidrenMap;

    private final List<String> suggestions;


    TrieNode() {
        this.chidrenMap = new HashMap<>();
        suggestions = new ArrayList<>();
    }

    public List<String> getSuggestions() {
        return this.suggestions;
    }

    public Map<Character, TrieNode> getChidrenMap() {
        return chidrenMap;
    }
}
