package com.wh.leetcode.editor.cn;
//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 
// 👍 807 👎 0

import java.util.ArrayList;
import java.util.List;

public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new ValidateBinarySearchTree().new Solution();
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
    public boolean isValidBST1(TreeNode root) {
        if(root == null){
            return true;
        }
        //中序遍历，然后判断递增
        List<Integer> list = new ArrayList<>();
        readTree(root, list);
        for (int i = 1; i < list.size(); i++){
            if(list.get(i) <= list.get(i-1)){
                return false;
            }
        }
        return true;
    }
    private void readTree(TreeNode node, List<Integer> list){
        if(node != null){
            readTree(node.left, list);
            list.add(node.val);
            readTree(node.right, list);
        }
    }

    //在递归中判断，记录中序遍历的前一个节点与当前节点的值比较
    TreeNode pre = null;
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        boolean left = isValidBST(root.left);

        if(pre == null || root.val > pre.val){
            pre = root;
        } else {
            return false;
        }

        boolean right = isValidBST(root.right);

        return left && right;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}