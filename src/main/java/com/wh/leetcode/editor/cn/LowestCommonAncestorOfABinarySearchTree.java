package com.wh.leetcode.editor.cn;
//给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
//输出: 6 
//解释: 节点 2 和节点 8 的最近公共祖先是 6。
// 
//
// 示例 2: 
//
// 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//输出: 2
//解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉搜索树中。 
// 
// Related Topics 树 
// 👍 455 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LowestCommonAncestorOfABinarySearchTree {

    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinarySearchTree().new Solution();
        Integer[] treeNode = {41, 37, 44, 24, 39, 42, 48, 1, 35, 38, 40, null, 43, 46};
        TreeNode tree = TreeNode.createBinaryTreeByArray(treeNode, 0);
        System.out.println(solution.lowestCommonAncestor(tree, tree.left.right.right,
                tree.right.right.left).val);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
     * right; TreeNode(int x) { val = x; } }
     */

    class Solution {

        /**
         * 先遍历一遍树，得到深度和父母map 首先将两个节点深度变成一致：更深的节点往上走，直到深度一致，然后两个节点一起往上走，直接父母相同
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            HashMap<TreeNode, Integer> depthMap = new HashMap<>();
            HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
            if (root == null) {
                return null;
            }
            depthMap.put(root, 0);
            parentMap.put(root, null);
            readTree(root, depthMap, parentMap);

            while (depthMap.get(p) > depthMap.get(q)) {
                p = parentMap.get(p);
            }

            while (depthMap.get(p) < depthMap.get(q)) {
                q = parentMap.get(q);
            }

            //如果其中一个是另一个父母，则深度一致，二者相等
            if (p == q) {
                return p;
            }

            //如果父母不同，继续共同向上走
            while (parentMap.get(p) != parentMap.get(q)) {
                p = parentMap.get(p);
                q = parentMap.get(q);
            }

            return parentMap.get(p);
        }

        private void readTree(TreeNode node, HashMap<TreeNode, Integer> depthMap,
                HashMap<TreeNode, TreeNode> parentMap) {

            if (node.left != null) {
                depthMap.put(node.left, depthMap.get(node) + 1);
                parentMap.put(node.left, node);
                readTree(node.left, depthMap, parentMap);
            }

            if (node.right != null) {
                depthMap.put(node.right, depthMap.get(node) + 1);
                parentMap.put(node.right, node);
                readTree(node.right, depthMap, parentMap);
            }
        }

        //这个真的很简洁，利用了二叉搜索树有序的特性，两个节点一起遍历
        public TreeNode lowestCommonAncestor_answer(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode ancestor = root;
            while (true) {
                if (p.val < ancestor.val && q.val < ancestor.val) {
                    ancestor = ancestor.left;
                } else if (p.val > ancestor.val && q.val > ancestor.val) {
                    ancestor = ancestor.left;
                } else {
                    break;
                }
            }
            return ancestor;
        }

        //两遍遍历，仅是两个路径，而不是整个树遍历
        public TreeNode lowestCommonAncestor_answer2(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode ancestor = root;
            List<TreeNode> pPath = getPath(root, p);
            List<TreeNode> qPath = getPath(root, q);
            for (int i = 0; i < pPath.size() && i < qPath.size(); i++) {
                if (pPath.get(i) == qPath.get(i)) {
                    ancestor = pPath.get(i);
                } else {
                    break;
                }
            }
            return ancestor;
        }

        private List<TreeNode> getPath(TreeNode root, TreeNode target) {
            List<TreeNode> path = new ArrayList<>();
            TreeNode node = root;
            while (node != target) {
                path.add(node);
                if (target.val < node.val) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            path.add(node);//抄答案少抄一行，得把当前节点加进去，否则会漏掉父子情况最近公共祖先是父亲
            return path;
        }


        //二叉搜索树，到达该节点路径唯一，故先找路径，然后找路径的公共元素，从根节点同时往下走两个路径，节点不同则上节点为最近公共祖先
        public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode res = null;
            List<TreeNode> p_path = getMyPath(root, p);
            List<TreeNode> q_path = getMyPath(root, q);
            for(int i = 0; i < p_path.size() && i < q_path.size(); i++){
                if(p_path.get(i) == q_path.get(i)){
                    res = p_path.get(i);
                } else {
                    break;
                }
            }
            return res;
        }

        private List<TreeNode> getMyPath(TreeNode node, TreeNode target) {
            List<TreeNode> path = new ArrayList<>();
            //这里node != target 足够
            while (node != null && node.val != target.val) {
                path.add(node);
                //除了if，就是else，因为不相等才能进来
                if (node.val < target.val) {
                    node = node.right;
                } else if (node.val > target.val) {
                    node = node.left;
                }
            }
            path.add(target);
            return path;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}