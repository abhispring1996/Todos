package com.example.switch2025.design.datastructures;

public class MyMap<K, V> {
    private static final int INITIAL_LOAD = 16;
    private final Entry<K, V>[] entryArray = new Entry[INITIAL_LOAD];

    private int getBucketKey(K key) {
        return key.hashCode() % INITIAL_LOAD;
    }

    private void put(K key, V value) {
        int bucketKey = getBucketKey(key);

        if (entryArray[bucketKey] == null) {
            entryArray[bucketKey] = new Entry<>(key, value);
        } else {
            Entry<K, V> currentEntry = entryArray[bucketKey];
            Entry<K, V> prev = null;
            while (currentEntry != null && !currentEntry.key.equals(key)) {
                prev = currentEntry;
                currentEntry = currentEntry.next;
            }

            if (currentEntry == null) {
                prev.next = new Entry<>(key, value);
            } else {
                currentEntry.value = value;
            }
        }
    }

    private V get(K key) {
        int bucketKey = getBucketKey(key);
        Entry<K, V> currentEntry = entryArray[bucketKey];

        if (currentEntry == null) {
            return null;
        }

        while (currentEntry != null) {
            if (currentEntry.key.equals(key)) {
                return currentEntry.value;
            }
            currentEntry = currentEntry.next;
        }

        return null;
    }

    private void remove(K key) {
        int bucketKey = getBucketKey(key);
        Entry<K, V> currentEntry = entryArray[bucketKey];

        if (currentEntry == null) {
            return;
        }

        Entry<K, V> prev = null;

        while (currentEntry != null && !currentEntry.key.equals(key)) {
            prev = currentEntry;
            currentEntry = currentEntry.next;
        }

        if (currentEntry != null) {
            prev.next = currentEntry.next;
        }
    }

    public static void main(String[] args) {

    }
}

class Entry<K, V> {
    K key;
    V value;
    Entry<K, V> next;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
