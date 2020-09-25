package com.wh.leetcode.editor.cn;
//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 深度优先搜索 数组 
// 👍 313 👎 0

import com.alibaba.fastjson.JSONObject;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode root = solution.buildTree(inorder,postorder);
        System.out.println(JSONObject.toJSONString(root));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder.length <= 0 || postorder.length <= 0){
            return null;
        }
        if(inorder.length == 1 && postorder.length == 1){
            TreeNode root = new TreeNode(inorder[0]);
            return root;
        }
        int leftTreeCount = 0;
        int rightTreeCount = 0;
        int rootNum = postorder[postorder.length-1];
        for (int i = 0; i < inorder.length; i++){
            if(inorder[i] == rootNum){
                leftTreeCount = i;
                rightTreeCount = inorder.length - leftTreeCount - 1;
            }
        }
        TreeNode root = new TreeNode(rootNum);
        if(leftTreeCount > 0){
            int[] leftInorder = new int[leftTreeCount];
            int[] leftPostorder = new int[leftTreeCount];
            for (int i = 0; i < leftTreeCount; i++){
                leftInorder[i] = inorder[i];
                leftPostorder[i] = postorder[i];
            }
            root.left = buildTree(leftInorder, leftPostorder);
        }
        if(rightTreeCount > 0){
            int[] rightInorder = new int[rightTreeCount];
            int[] rightPostorder = new int[rightTreeCount];
            for (int i = 0; i < rightTreeCount; i++){
                rightInorder[i] = inorder[leftTreeCount+1+i];
                rightPostorder[i] = postorder[leftTreeCount+i];
            }
            root.right = buildTree(rightInorder, rightPostorder);
        }

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}