package dsa.leetcode;

public class PartitionList {

    public static void main(String[] args) {

    }

    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode higher = new ListNode(0);

        ListNode smallHead = small, higherHead = higher;

        while (head != null) {
            if (head.val < x) {
                //small list
                smallHead.next = head;
                smallHead = smallHead.next;
            } else {
                //high list
                higherHead.next = head;
                higherHead = higherHead.next;
            }
            head = head.next;
        }

        higherHead.next = null;
        smallHead.next = higher.next;

        return small.next;

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

    }
}
