package com.wh.leetcode.editor.cn;

//给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//给定如下二叉树，以及目标和 sum = 22， 
//
//               5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \    / \
//        7    2  5   1
// 
//
// 返回: 
//
// [
//   [5,4,11,2],
//   [5,8,4,5]
//]
// 
// Related Topics 树 深度优先搜索 
// 👍 338 👎 0

import com.wh.leetcode.editor.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PathSumIi {
    public static void main(String[] args) {

        Solution solution = new PathSumIi().new Solution();
        Integer[] arr = new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null,null,null, 5, 1};
        TreeNode root = TreeNode.createBinaryTreeByArray(arr, 0);
//        System.out.println(JSONObject.toJSONString(TreeNode.BFSByQueue(root)));
        solution.pathSum(root, 22);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        //思路:递归遍历树，对于每个节点，都要分别计算其左右节点，到叶子节点后，判断路径和是否为sum
        //如何识别叶子节点：左右孩子均为null
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new LinkedList<>();

        public List<List<Integer>> pathSum(TreeNode root, int sum) {

            pathToCompute(root, sum);
            return res;
        }

        private void pathToCompute(TreeNode node,  int target) {

            if (node == null) {
                return;
            }
            target -= node.val;
            path.add(node.val);
            if (node.left == null && node.right == null) {
                //说明是叶子节点，如果达到目标就加到结果集，如果没达到就要舍弃该节点
                //因为path是公用的，内容在变，所以放入结果集的时候要新建一个list
                if (0 == target) {
                    List<Integer> temp = new ArrayList<>();
                    for (Integer t: path){
                        temp.add(t);
                    }
                    res.add(temp);
                }
                path = path.subList(0, path.size()-1);
            } else {
                pathToCompute(node.left,  target);
                pathToCompute(node.right, target);
                //该节点计算完毕，要从路径中移除，这样往回走才不包括算过的节点
                path = path.subList(0, path.size()-1);
            }
        }

//        private void dfs(TreeNode root, int sum){
//            if(root == null){
//                return ;
//            }
//            path.offerLast(root.val);
//            sum -= root.val;
//            if(root.left == null && root.right == null && sum == 0){
//                res.add(new LinkedList<>(path));
//            }
//            dfs(root.left, sum);
//            dfs(root.right, sum);
//            path.pollLast();
//        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

