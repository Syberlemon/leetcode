package com.wh.leetcode.editor.cn;
//给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。 
//
// 例如， 
//
// 
//给定二叉搜索树:
//
//        4
//       / \
//      2   7
//     / \
//    1   3
//
//和值: 2
// 
//
// 你应该返回如下子树: 
//
// 
//      2     
//     / \   
//    1   3
// 
//
// 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。 
// Related Topics 树 
// 👍 93 👎 0

import com.wh.leetcode.editor.util.TreeNode;

public class SearchInABinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new SearchInABinarySearchTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        while(root != null){
            if(root.val == val){
                return root;
            }
            if(root.val > val){
                return searchBST(root.left, val);
            } else {
                return searchBST(root.right, val);
            }
        }
        return null;
    }

    public TreeNode searchBST_answer(TreeNode root, int val) {
        if(root == null || val == root.val)  return root;
        return val < root.val ? searchBST_answer(root.left, val) : searchBST_answer(root.right, val);
    }

    public TreeNode searchBST_answer2(TreeNode root, int val){
        while(root != null && val != root.val){
            root = val < root.val ? root.left : root.right;
        }
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}