package com.wh.leetcode.editor.cn;

/**
 * Created on 2020/10/9
 *
 * @author wanghao1
 */

public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
