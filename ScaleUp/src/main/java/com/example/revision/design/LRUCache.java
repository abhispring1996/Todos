package com.example.revision.design;

import java.util.HashMap;
import java.util.Map;

/**
 * For designing LRU Cache we need a data structure which checks Cache miss or hit in constant time -> Map and
 * data structure where we can attach and detach the elements in constant time -> Doubly Linked List
 */
public class LRUCache {
    Map<Object, DoublyLinkedNode> valueToDoublyLinkedNodeMap = new HashMap<>();
    DoublyLinkedNode head;
    DoublyLinkedNode tail;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public void put(Object key, Object value) {

        if (valueToDoublyLinkedNodeMap.containsKey(key)) {
            DoublyLinkedNode existingNode = valueToDoublyLinkedNodeMap.get(key);
            existingNode.value = value;
            moveToFront(existingNode);
        } else {
            if (valueToDoublyLinkedNodeMap.size() == capacity) {
                removeLast();
            }
            DoublyLinkedNode newNode = new DoublyLinkedNode(key, value);
            addToFront(newNode);
            valueToDoublyLinkedNodeMap.put(key, newNode);
        }
    }

    /**
     * It involves detaching the currentNode and adding it to Front
     *
     * @param existingNode
     */
    private void moveToFront(DoublyLinkedNode existingNode) {
    }

    /**
     * it removes the last element
     */
    private void removeLast() {

    }

    /**
     * It adds element to the head of the Linked List.
     *
     * @param newNode
     */
    private void addToFront(DoublyLinkedNode newNode) {
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public Object get(int key) {
        if (valueToDoublyLinkedNodeMap.containsKey(key)) {
            DoublyLinkedNode currNode = valueToDoublyLinkedNodeMap.get(key);
            moveToFront(currNode);
            return currNode.value;
        }
        return null;
    }

    public class DoublyLinkedNode {
        Object key;
        Object value;
        DoublyLinkedNode prev;
        DoublyLinkedNode next;

        public DoublyLinkedNode(Object key, Object value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

}
