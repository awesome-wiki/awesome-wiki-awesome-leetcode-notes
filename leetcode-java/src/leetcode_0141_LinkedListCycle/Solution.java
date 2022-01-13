package leetcode_0141_LinkedListCycle;

/**
 * description: 环形链表
 * url: https://leetcode-cn.com/problems/linked-list-cycle/
 *
 * @author Michael
 * @date 2022/1/13
 * @time 11:28 下午
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            // 慢指针每次移动 1 个位置
            slow = slow.next;
            // 快指针每次移动 2 个位置
            fast = fast.next.next;
            // 如果最后快慢指针能够相等，表示它俩相遇了，存在环
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
