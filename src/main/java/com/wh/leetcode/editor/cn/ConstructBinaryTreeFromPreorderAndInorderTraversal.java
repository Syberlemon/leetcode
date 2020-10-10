package com.wh.leetcode.editor.cn;
//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 712 👎 0

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
     * right; TreeNode(int x) { val = x; } }
     */
    class Solution {

        //前序和中序构造二叉树 前序可以知道根，中序可以分开左右子树, 这个数组拷贝浪费空间时间
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder.length <= 0 || inorder.length <= 0) {
                return null;
            }
            TreeNode node = new TreeNode(preorder[0]);
            int leftTreeCount = 0;
            int rightTreeCount = 0;
            for (int i = 0; i < inorder.length; i++) {
                if (inorder[i] == preorder[0]) {
                    //注意左子树的个数不是i-1，而是i
                    leftTreeCount = i;
                    rightTreeCount = preorder.length - 1 - leftTreeCount;
                }
            }
            if (leftTreeCount > 0) {
                int[] preLeft = new int[leftTreeCount];
                int[] inLeft = new int[leftTreeCount];
                for (int p = 0; p < leftTreeCount; p++) {
                    preLeft[p] = preorder[p + 1];
                    inLeft[p] = inorder[p];
                }
                node.left = buildTree(preLeft, inLeft);
            }

            if (rightTreeCount > 0) {
                int[] preRight = new int[rightTreeCount];
                int[] inRight = new int[rightTreeCount];
                for (int q = 0; q < rightTreeCount; q++) {
                    preRight[q] = preorder[leftTreeCount + 1 + q];
                    inRight[q] = inorder[leftTreeCount + 1 + q];
                }
                node.right = buildTree(preRight, inRight);
            }
            return node;
        }

        private Map<Integer, Integer> indexMap;

        //利用map存储中序数组，从而快速定位分隔位置；由于是数组，不好分割，故在计算时分割计算，而不是将数组作为参数传递
        public TreeNode buildTree_answer(int[] preorder, int[] inorder) {
            int n = preorder.length;
            indexMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                indexMap.put(inorder[i], i);
            }
            return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
        }

        public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left,
                int preorder_right, int inorder_left, int inorder_right) {
            if (preorder_left > preorder_right) {
                return null;
            }
            int preorder_root = preorder_left;
            int inorder_root = indexMap.get(preorder[preorder_root]);
            int size_left_subtree = inorder_root - inorder_left;

            TreeNode root = new TreeNode(preorder[preorder_root]);
            root.left = myBuildTree(preorder, inorder, preorder_left + 1,
                    preorder_left + size_left_subtree, inorder_left,
                    inorder_root - 1);
            root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1,
                    preorder_right, inorder_root + 1, inorder_right);
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}