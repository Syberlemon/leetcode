package com.wh.leetcode.editor.cn;
//给定一个二叉树 
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
// 进阶： 
//
// 
// 你只能使用常量级额外空间。 
// 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。 
// 
//
// 
//
// 示例： 
//
// 
//
// 输入：root = [1,2,3,4,5,null,7]
//输出：[1,#,2,3,#,4,5,7,#]
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。 
//
// 
//
// 提示： 
//
// 
// 树中的节点数小于 6000 
// -100 <= node.val <= 100 
// 
//
// 
//
// 
// 
// Related Topics 树 深度优先搜索 
// 👍 239 👎 0

import com.alibaba.fastjson.JSONObject;
import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNodeIi {
    public static void main(String[] args) {
        Solution solution = new PopulatingNextRightPointersInEachNodeIi().new Solution();
        Integer[] tree  = {1,2,3,4,5,null,7};
        Node node = new Node();
        Node res = solution.connect(node.createBinaryTreeByArray(tree, 0));
        System.out.println(JSONObject.toJSONString(res));

    }
    //leetcode submit region begin(Prohibit modification and deletion)

// Definition for a Node.
static class Node {
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

    public Node createBinaryTreeByArray(Integer []array, int index)
    {
        Node tn = null;
        if (index<array.length) {
            Integer value = array[index];
            if (value == null) {
                return null;
            }
            tn = new Node(value);
            tn.left = createBinaryTreeByArray(array, 2*index+1);
            tn.right = createBinaryTreeByArray(array, 2*index+2);
            return tn;
        }
        return tn;
    }
};


class Solution {
    public Node connect(Node root) {
        Queue<Node> nodeQueue = new LinkedList<>();
        if(root == null){
            return null;
        }
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()){
            Node node = nodeQueue.poll();
            Node next = getRight(node.next);
            if(node.left != null && node.right != null){
                node.left.next = node.right;
                node.right.next = next;
                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
            } else if(node.left != null){
                node.left.next = next;
                nodeQueue.add(node.left);
            } else if(node.right != null){
                node.right.next = next;
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    private Node getRight(Node node){
        Node next = null;
        while (node != null){
            if(node.left != null){
                next = node.left;
            } else if(node.right != null){
                next = node.right;
            }
            node = node.next;
        }
        return next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}