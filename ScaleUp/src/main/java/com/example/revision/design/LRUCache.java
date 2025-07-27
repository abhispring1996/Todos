package com.example.revision.design;

import java.util.HashMap;
import java.util.Map;

/**
 * For designing LRU Cache we need a data structure which checks Cache miss or hit in constant time -> Map and
 * data structure where we can attach and detach the elements in constant time -> Doubly Linked List
 */
public class LRUCache<K, V> {
    private final Map<K, DoublyLinkedNode> map = new HashMap<>();
    private final DoublyLinkedNode head;
    private final DoublyLinkedNode tail;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DoublyLinkedNode(null, null);
        tail = new DoublyLinkedNode(null, null);
        head.next = tail;
        tail.prev = head;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            DoublyLinkedNode existingNode = map.get(key);
            existingNode.value = value;
            moveToFront(existingNode);
        } else {
            if (map.size() == capacity) {
                removeLast();
            }
            DoublyLinkedNode newNode = new DoublyLinkedNode(key, value);
            addToFront(newNode);
            map.put(key, newNode);  // only insert in map here
        }
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        DoublyLinkedNode node = map.get(key);
        moveToFront(node);
        return node.value;
    }

    private void moveToFront(DoublyLinkedNode node) {
        // Detach node
        node.prev.next = node.next;
        node.next.prev = node.prev;
        // Attach at front
        addToFront(node);
    }

    private void addToFront(DoublyLinkedNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
        // No map update here
    }

    private void removeLast() {
        if (tail.prev == head) {
            return; // No elements
        }
        DoublyLinkedNode last = tail.prev;
        last.prev.next = tail;
        tail.prev = last.prev;
        map.remove(last.key);
    }

    private class DoublyLinkedNode {
        K key;
        V value;
        DoublyLinkedNode prev;
        DoublyLinkedNode next;

        DoublyLinkedNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

