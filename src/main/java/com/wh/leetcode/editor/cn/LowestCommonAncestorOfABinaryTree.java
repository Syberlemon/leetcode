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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    //给一个节点，在二叉树找到其路径, 先序就行，需要递归；
    //递归结束条件：节点匹配到了，路径走到头了；但是递归每次要做的内容不清楚；
    private void getPath(TreeNode node, TreeNode p, List<TreeNode> path){
        if(node == p){
            path.add(node);
            return;
        }
        if(node.left != null){
            path.add(node);
            getPath(node.left, p, path);
        }
        if(node.right != null){
            path.add(node);
            getPath(node.right, p, path);
        }
        if(node.left == null && node.right == null){
            //说明这条路径走到头了，还是没找到目标节点，即上一个节点走错了
            //这里是用subList更好，还是remove更好
            path = path.subList(0, path.size()-1);
        }
    }
    private TreeNode ans = null;
    private boolean dfs(TreeNode node, TreeNode p, TreeNode q){
        if(node  == null){
            return false;
        }
        boolean lson = dfs(node.left, p, q);
        boolean rson = dfs(node.right, p, q);
        //若两个节点，一个在左子树，一个在右子树，则当前节点为最近公共祖先；
        //若两个节点，一个是当前节点，一个是当前节点的孩子，则当前节点也是最近公共祖先
        if((lson && rson) || (node.val == p.val || node.val == q.val)&&(lson || rson)){
            ans = node;
        }
        return lson || rson || (node.val == p.val || node.val == q.val);
    }
    public TreeNode lowestCommonAncestor_answer(TreeNode root, TreeNode p, TreeNode q){
        this.dfs(root, p, q);
        return this.ans;
    }

    //将每个节点的父亲存下来，从p走到根，然后从q往根走时判断是否访问过。因为都是从叶子往根走，故先匹配到的即离q最近的，即目标
    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    private void dfs(TreeNode node){
        if(node.left != null){
            parent.put(node.left.val, node);
            dfs(node.left);
        }
        if(node.right != null){
            parent.put(node.right.val, node);
            dfs(node.right);
        }
    }
    public TreeNode lowestCommonAncestor_answer2(TreeNode root, TreeNode p, TreeNode q){
        dfs(root);
        while(p != null){
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null){
            if(visited.contains(q.val)){
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}