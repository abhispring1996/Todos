package com.example.revision.linkedlist;

public class LLTest {

    public static class Node{

        Node next;
        int value;

        public Node(int value){
            this.value = value;
            this.next = null;
        }

    }

    public static void printLL(Node head){

        Node curr = head;

        while(curr!=null){
            System.out.print(curr.value+"->");
            curr = curr.next;
        }
    }

    /**
     * To find nth node form the end
     * @param head
     * @param n
     */
    public static Node nThNodeFromEnd(Node head,int n) {

        // creating a dummy node for more clarity in terms of length
        Node dummy = new Node(0);
        dummy.next = head;
        Node slow = dummy;
        Node fast = dummy;

        // 0->1->2->3->4->5

        // reach till the n so that after that we are left with (No of nodes - n)
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // 0->1->2->3->4->5
        // s
        //       f
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 0->1->2->3->4->5
        //          s
        //                 f

        slow.next = slow.next.next;
        return head;
    }

    /**
     * To find the middle element in a LL
     * @param head
     * @return
     */
    public static int middleElement(Node head){

        Node slow = head;
        Node fast = head;

        // 2->4->6->7->5->1 -> Ans -> 7
        // 1->2->3->4->5   -> Ans -> 5

        while(fast!=null && fast.next!= null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow.value;
    }

    /**
     * To reverse the LL
     * @param head
     * @return
     */
    public static Node reverseLL(Node head){

        Node curr = head;
        Node next = null;
        Node prev = null;
        // 1 -> 2-> 3-> 4->5

        while(curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * reverse LL in groups of k
     * @param head
     * @param k
     * @return
     */
    public static Node reverseInGroups(Node head,int k){

        Node curr = head;
        Node prev = null;
        Node next = null;
        int groupCount = k;
        // 1->2->2->4->5->6->7->8 -> k=4
        while(curr!=null && groupCount>0){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            groupCount--;
        }
         if(head!=null){
             head.next= reverseInGroups(curr,k);
         }
         return prev;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);
        System.out.print("Original List : ");
        printLL(head);
        System.out.print("Reversed List : ");
        printLL(reverseInGroups(head,4));
    }
}
