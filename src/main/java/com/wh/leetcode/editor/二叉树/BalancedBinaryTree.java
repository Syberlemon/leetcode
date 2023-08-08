package com.wh.leetcode.editor.二叉树;

//给定一个二叉树，判断它是否是高度平衡的二叉树。 
//
// 本题中，一棵高度平衡二叉树定义为： 
//
// 
// 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,2,3,3,null,null,4,4]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 树中的节点数在范围 [0, 5000] 内 
// -104 <= Node.val <= 104 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 1367 👎 0

import com.wh.leetcode.editor.util.TreeNode;

public class BalancedBinaryTree {
    public static void main(String[] args) {
        Solution solution = new BalancedBinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /**
     * 当前节点是否平衡= 左右子树的高度差小于1 && 左子树高度平衡 && 右子树高度平衡
     * 递归终止条件：null 则返回true
     * 计算树高度 也是递归，递归终止条件 null 返回0
     * @date: 2023/7/31 21:23
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left)-height(root.right)) <= 1
                    && isBalanced(root.left)
                    && isBalanced(root.right);
        }
    }
    public int height(TreeNode node){
        if(node == null) {
            return 0;
        } else {
            return Math.max(height(node.left), height(node.right)) + 1;
        }

    }

    /**
     * 如果不平衡返回树高为-1，只要子树不平衡，所有包含这颗子树的树都不平衡，所以从下往上推可以剪枝
     * 计算高度：左子树不平衡 || 右子树不平衡 || 左右子树高度差大于1 都不平衡 为-1
     * @date: 2023/7/31 21:36
     */
    public boolean isBalanced2(TreeNode root) {
        return height2(root) >= 0;
    }

    public int height2(TreeNode node) {
        if (node == null){
            return 0;
        }
        int heightLeft = height2(node.left);
        int heightRight = height2(node.right);
        if (heightLeft == -1
                || heightRight == -1
                || Math.abs(heightLeft-heightRight) > 1){
            return -1;
        } else {
            return Math.max(heightLeft, heightRight) + 1;
        }
    }
}


}
//leetcode submit region end(Prohibit modification and deletion)

