package com.wh.leetcode.editor.cn;
//给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 示例 1: 
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3. 
//
// 示例 2: 
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3. 
// Related Topics 链表 
// 👍 350 👎 0

import com.wh.leetcode.editor.util.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReorderList {
    public static void main(String[] args) {
        Solution solution = new ReorderList().new Solution();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        solution.reorderList(one);
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
    //思路：用map存储位置和节点，然后遍历插入节点
    public void reorderList(ListNode head) {
        ListNode node = head;
        int count = 0;
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        while(node != null){
            nodeMap.put(count, node);
            count++;
            node = node.next;
        }
        count -= 1;
        int end = count;//记录节点位置
        int start = 0;
        while(start < count/2){
            //倒数第二个节点还指向尾节点，故尾节点挪到前面后会形成环，需要把倒数第二个节点指向null再挪尾结点
            ListNode startNode = nodeMap.get(start);
            ListNode endPreNode = nodeMap.get(end-1);
            ListNode endNode = endPreNode.next;
            ListNode temp = startNode.next;
            endPreNode.next = null;
            startNode.next = endNode;
            endNode.next = temp;
            start += 1;
            end -= 1;
        }
    }

    //执行i++后的节点是挪到前面的最后节点要指向的
    //关心的应该是最后的链表有没有环，至于计算中间是否产生环不重要
    public void reorderList_answer(ListNode head) {
        if(head == null){
            return ;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while(node != null) {
            list.add(node);
            node = node.next;
        }
        int i =0, j = list.size()-1;
        while(i < j){
            list.get(i).next = list.get(j);
            i++;
            if(i == j){
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    //快慢指针可以找链表中心点 + 翻转链表 + 合并链表
    public void reorderList_answer2(ListNode head) {
        if(head == null){
            return ;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }
    private ListNode middleNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
    private void mergeList(ListNode l1, ListNode l2){
        ListNode l1_tmp;
        ListNode l2_tmp;
        while(l1 != null && l2 != null){
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}