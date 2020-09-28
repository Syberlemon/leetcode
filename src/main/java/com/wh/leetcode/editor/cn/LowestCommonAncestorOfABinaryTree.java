package com.wh.leetcode.editor.cn;
//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
// 
//
// 示例 2: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉树中。 
// 
// Related Topics 树 
// 👍 763 👎 0

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
        Integer[] treeNode = {41,37,44,24,39,42,48,1,35,38,40,null,43,46};
        TreeNode tree = TreeNode.createBinaryTreeByArray(treeNode, 0);
        System.out.println(solution.lowestCommonAncestor(tree, tree.left.right.right, tree.right.right.left).val);
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
    //F1:遍历找路径
    //F2:左树包含一个，右树包含另一个，则当前节点为祖先； 当前节点为其中一个节点，另一个节点在左子树或右子树上，则当前节点为祖先
    //递归判断树上是否包含节点：包含即 左子树包含 || 右子树包含 || 是当前节点
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> p_path = new ArrayList<>();
        getPath(root, p, p_path);
        List<TreeNode> q_path = new ArrayList<>();
        getPath(root, q, q_path);
        TreeNode ancestor = null;
        for (int i = 0; i < p_path.size() && i <q_path.size(); i++){
            if(p_path.get(i) ==  q_path.get(i)){
                ancestor = p_path.get(i);
            } else {
                break;
            }
        }
        return ancestor;
    }

    //给一个节点，在二叉树找到其路径 ???? 不会写 找到对的path后不知道怎么停止迭代
    private void getPath(TreeNode node, TreeNode p, List<TreeNode> path){
//        path.add(node);
//        if(node == p){
//            return;
//        }
//        if(node.left != null){
//            getPath(node.left, p, path);
//        }
//        if(node.right != null){
//            getPath(node.right, p, path);
//        }
//        if(node.left == null && node.right == null){
//            return ;
//        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}