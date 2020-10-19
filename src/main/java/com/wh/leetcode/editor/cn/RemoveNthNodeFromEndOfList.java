package com.wh.leetcode.editor.cn;
//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。 
//
// 示例： 
//
// 给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
// 
//
// 说明： 
//
// 给定的 n 保证是有效的。 
//
// 进阶： 
//
// 你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 
// 👍 1073 👎 0

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        solution.removeNthFromEnd(one, 2);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    //遍历一遍放到map中，得知要删的是哪个后，从map中取出来删
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null){
            return null;
        }
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        int count = 1;
        ListNode node = head;
        while(node != null){
            nodeMap.put(count, node);
            node = node.next;
            count++;
        }
        int del = count - n;//count多加1，故-n之后不需要再+1
        if(del <= 0){
            return null;
        } else if(del == 1){
            //第一个节点，没有前一个节点
            head = nodeMap.get(del + 1);
        } else {
            ListNode front = nodeMap.get(del -1);
//            ListNode delNode = nodeMap.get(del);
//            front.next = delNode.next;
            //不需要拿当前节点，直接把上个节点的指针改掉即可
            front.next = front.next.next;
        }
        return head;
    }

    //答案收获：1、哑结点dummy是为了不特殊处理头结点 2、这个是两边遍历链表，第一遍计算长度，第二遍处理next指针
    public ListNode removeNthFromEnd_answer(ListNode head, int n) {
        ListNode dummy = new ListNode(0,head);
        int length = getLength(head);
        ListNode cur = dummy;
        for(int i = 0; i < length - n + 1; ++i){
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
    private int getLength(ListNode head){
        int length = 0;
        while(head != null){
            ++length;
            head = head.next;
        }
        return length;
    }

    //用栈，在弹出第n个后，下一个就是要处理的元素，因为题干说n是有效的，所以没判断空等
    public ListNode removeNthFromEnd_answer2(ListNode head, int n) {
        ListNode dummy = new ListNode(0,  head);
        //Deque是双端队列，在一头出入的时候可以当栈用
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        for(int i = 0; i < n; ++i){
            stack.pop();
        }
        //这里pop和peek都行
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    //倒数第n个，用两个指针，第一个快n+1步，则当第一个走到头，第二个指针是要处理的元素（倒数第n个的前一个）
    public ListNode removeNthFromEnd_answer3(ListNode head, int n) {
        ListNode dummy = new ListNode(0,head);
        ListNode first = head;
        ListNode second = dummy;
        for(int i = 0; i < n; ++i){
            first = first.next;
        }
        while(first != null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}