package com.wh.leetcode.editor.util;

/**
 * Created on 2020/10/9
 *
 * @author wanghao1
 */

public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
