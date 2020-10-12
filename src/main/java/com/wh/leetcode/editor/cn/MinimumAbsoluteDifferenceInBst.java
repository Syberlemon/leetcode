package com.wh.leetcode.editor.cn;
//给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。 
//
// 
//
// 示例： 
//
// 输入：
//
//   1
//    \
//     3
//    /
//   2
//
//输出：
//1
//
//解释：
//最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
// 
//
// 
//
// 提示： 
//
// 
// 树中至少有 2 个节点。 
// 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 
//相同 
// 
// Related Topics 树 
// 👍 147 👎 0

import java.util.ArrayList;
import java.util.List;

public class MinimumAbsoluteDifferenceInBst {
    public static void main(String[] args) {
        Solution solution = new MinimumAbsoluteDifferenceInBst().new Solution();
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
    public int getMinimumDifference(TreeNode root) {
        //右孩子大，左孩子小，这个信息怎么利用上来减少遍历情况呢
        //暴力法就是遍历二叉树，两两做差，题意说明了最小绝对差为1，所以如果差值为1则结束
        //递归找根节点和其他所有节点的最小绝对差，相减正数则往右走更小，相减负数则往左走更小
        if(root == null){
            return 0;
        }
        //比较root的值，和其叶子节点 ??? 这个很复杂，写不出来了
        TreeNode node = root;
        while(node != null){
            if(node.left != null){
                int diff = node.val - node.left.val;
                if(diff == 1)
                    return 1;
                if(diff > 1 && node.right != null){
                    node = node.right;
                }
            }
        }

        if(root.left != null && root.right != null){
            return Math.min(getMinimumDifference(root.left), getMinimumDifference(root.right));
        } else if(root.left != null){
            return getMinimumDifference(root.left);
        } else if(root.right != null){
            return getMinimumDifference(root.right);
        }

        return 0;
    }

    public int getMinimumDifference2(TreeNode root) {
        //二叉搜索树按中序遍历则是有序数组，再依次计算相邻两个元素的差，取最小值即可
        List<Integer> list = new ArrayList<>();
        readTree(root, list);
        //题意至少有俩节点
        int min = list.get(1) - list.get(0);
        for (int i = 2; i < list.size(); i++) {
            int diff = list.get(i)- list.get(i-1);
            min = Math.min(diff, min);
        }
        return min;
    }

    private void readTree(TreeNode node, List<Integer> list){
        if(node != null){
            readTree(node.left, list);
            list.add(node.val);
            readTree(node.right, list);
        }
    }

    int pre;
    int ans;
    //中序遍历的过程中，比较出来最小值
    public int getMinimumDifference_answer(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root){
        if(root == null){
            return;
        }
        dfs(root.left);
        if(pre == -1){
            pre = root.val;
        } else {
            //因为是中序遍历，必然当前值比前一个值大，所以减法不会出负数，直接取min即可
            ans = Math.min(ans, root.val - pre);
        }
        dfs(root.right);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}