package com.wh.leetcode.editor.cn;
//给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。 
//
// 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点
//。 
//
// 示例 1: 
//
// 
//输入: 
//	Tree 1                     Tree 2                  
//          1                         2                             
//         / \                       / \                            
//        3   2                     1   3                        
//       /                           \   \                      
//      5                             4   7                  
//输出: 
//合并后的树:
//	     3
//	    / \
//	   4   5
//	  / \   \ 
//	 5   4   7
// 
//
// 注意: 合并必须从两个树的根节点开始。 
// Related Topics 树 
// 👍 501 👎 0

import com.alibaba.fastjson.JSONObject;
import java.util.LinkedList;
import java.util.Queue;

public class MergeTwoBinaryTrees {
    public static void main(String[] args) {
        Solution solution = new MergeTwoBinaryTrees().new Solution();
//        System.out.println(JSONObject.toJSONString(solution.mergeTrees()));
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
public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode resTree = t1;
        if (t1 != null && t2 != null){
            resTree = new TreeNode(t1.val + t2.val);
            resTree.left = mergeTrees(t1.left, t2.left);
            resTree.right = mergeTrees(t1.right, t2.right);
        } else if (t1 != null && t2 == null){
            return t1;
        } else if(t1 == null && t2 != null){
            return t2;
        }
        return resTree;
    }

    //深度优先遍历
    public TreeNode mergeTrees_answer(TreeNode t1, TreeNode t2) {
        if(t1 == null){ // 包含两个都空，随便返回一个null即可
            return t2;
        }
        if(t2 == null){
            return t1;
        }
        TreeNode resTree = new TreeNode(t1.val + t2.val);
        resTree.left = mergeTrees(t1.left, t2.left);
        resTree.right = mergeTrees(t1.right, t2.right);
        return resTree;
    }

    //广度优先遍历
    public TreeNode mergeTrees_answer2(TreeNode t1, TreeNode t2) {
        if(t1 == null){
            return t2;
        }
        if(t2 == null){
            return t1;
        }
        TreeNode resTree = new TreeNode(t1.val + t2.val);
        Queue<TreeNode> qt1 = new LinkedList<>();
        Queue<TreeNode> qt2 = new LinkedList<>();
        Queue<TreeNode> resQueue = new LinkedList<>();
        resQueue.offer(resTree);
        qt1.offer(t1);
        qt2.offer(t2);
        while( !qt1.isEmpty() && !qt2.isEmpty()){
            TreeNode node = resQueue.poll(),n1 = qt1.poll(),n2 = qt2.poll();
            TreeNode left1 = n1.left, left2 = n2.left, right1 = n1.right, right2 = n2.right;
            if(left1 != null && left2 != null){
                TreeNode left = new TreeNode(left1.val + left2.val);
                node.left = left;
                resQueue.offer(left);
                qt1.offer(left1);
                qt2.offer(left2);
            } else if(left1 != null && left2 == null){
                node.left = left1;
            } else if (left1 == null && left2 != null){
                node.left = left2;
            }
            if(right1 != null && right2 != null){
                TreeNode right = new TreeNode(right1.val + right2.val);
                node.right = right;
                resQueue.offer(right);
                qt1.offer(right1);
                qt2.offer(right2);
            } else if(right1 != null && right2 == null){
                node.right = right1;
            } else if (right1 == null && right2 != null){
                node.right = right2;
            }
        }
        return resTree;
    }

    public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        if(t1 == null){
            return t2;//这里如果t2是null，直接返回与判断一下t2再返回null一样
        }
        if(t2 == null){
            return t1;
        }
        //能走到这里，说明t1和t2都不为空，所以直接取值和left right即可
        TreeNode root = new TreeNode(t1.val+t2.val);
        root.left = mergeTrees1(t1.left, t2.left);
        root.right = mergeTrees1(t1.right, t2.right);
        return root;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}