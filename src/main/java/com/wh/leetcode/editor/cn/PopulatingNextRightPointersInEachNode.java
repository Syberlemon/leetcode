package com.wh.leetcode.editor.cn;
//给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下： 
//
// struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//} 
//
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。 
//
// 初始状态下，所有 next 指针都被设置为 NULL。 
//
// 
//
// 示例： 
//
// 
//
// 输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"ri
//ght":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right
//":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left"
//:null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":nu
//ll,"next":null,"right":null,"val":7},"val":3},"val":1}
//
//输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4
//","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next"
//:null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":
//null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":
//"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"va
//l":1}
//
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
// 
//
// 
//
// 提示： 
//
// 
// 你只能使用常量级额外空间。 
// 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。 
// 
// Related Topics 树 深度优先搜索 
// 👍 286 👎 0

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        Solution solution = new PopulatingNextRightPointersInEachNode().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};


class Solution {
    //想用bfs 是在节点入队的时候，发现上一个是末尾，就不继续指next，并且把下层末尾定下来
    public Node connect(Node root) {
        if(root == null){
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        Node last = root;//上层最后一个元素
        Node queueTail = root;//队列的最后一个元素
        while(queue.size() > 0){
            Node node = queue.poll();
            if(node != null && node.left != null && node.right != null){
                queue.offer(node.left);
                queue.offer(node.right);
                if(queueTail != last){//如果队列最后一个元素不是上层最后一个元素，那么next指针赋值
                    queueTail.next = node.left;
                }
                node.left.next = node.right;
                queueTail = node.right;
                if(last.right == queueTail){//如果当前队列最后一个元素是最后一个元素，用last记录
                    last = node.right;
                }
            }
        }
        return root;
    }

    //每次for循环一层，当最后一个元素的时候，不给next赋值
    public Node connect_answer(Node root) {
        if(root == null){
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Node node = queue.poll();
                if(i < size - 1){
                    node.next = queue.peek();
                }
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    //利用上一层的next指针
    public Node connect_answer2(Node root) {
        if(root == null){
            return root;
        }
        Node leftmost = root;
        while(leftmost.left != null){
            Node head = leftmost;
            while(head != null){
                head.left.next = head.right;
                if(head.next != null){
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            //当前节点为null，说明该层走完了,去下一层
            leftmost = leftmost.left;
        }
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}