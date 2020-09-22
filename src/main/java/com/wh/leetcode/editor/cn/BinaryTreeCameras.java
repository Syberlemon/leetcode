package com.wh.leetcode.editor.cn;
//给定一个二叉树，我们在树的节点上安装摄像头。 
//
// 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。 
//
// 计算监控树的所有节点所需的最小摄像头数量。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[0,0,null,0,0]
//输出：1
//解释：如图所示，一台摄像头足以监控所有节点。
// 
//
// 示例 2： 
//
// 
//
// 输入：[0,0,null,0,null,0,null,null,0]
//输出：2
//解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
// 
//
// 
//提示： 
//
// 
// 给定树的节点数的范围是 [1, 1000]。 
// 每个节点的值都是 0。 
// 
// Related Topics 树 深度优先搜索 动态规划 
// 👍 171 👎 0

import javax.swing.tree.TreeNode;

public class BinaryTreeCameras {

    public static void main(String[] args) {
        Solution solution = new BinaryTreeCameras().new Solution();
        //不会写树的初始化，根据输入数组构建一棵树，直接在leetcode拿用例测试的
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
     * right; TreeNode(int x) { val = x; } }
     */
    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {

        private int ans = 0;

        public int minCameraCover(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (dfs(root) == 1) {
                ans++;
            }
            return ans;
        }

        private int dfs(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int left = dfs(node.left);
            int right = dfs(node.right);
            if(left == 0 || right == 0){
                ans++;
                return 1;
            } else if(left == 1 || right == 1){
                return 2;
            } else {
                return 0;
            }
        }
        // 庆幸的是，题读明白了，解题关键是搞明白状态转移，递归实现
        /* 节点状态：0没被监控 1装监控 2被孩子监控
         * 左右有一个为0  则当前节点装监控 返回1
         * 左右有一个为1 则当前节点被孩子监控 返回2
         * 左右均为2 则当前节点不装监控 返回0
         *
         * 如果循环遇到节点为null，则该节点不需要监控，返回0
         *
         * 如果从下往上走到根节点，是0，则根节点需要装一个监控
         */
    }
//leetcode submit region end(Prohibit modification and deletion)

}