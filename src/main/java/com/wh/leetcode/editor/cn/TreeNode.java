package com.wh.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author froid
 * Date:  2020/9/26
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public static TreeNode createBinaryTreeByArray(Integer []array, int index)
    {
        TreeNode tn = null;
        if (index<array.length) {
            Integer value = array[index];
            if (value == null) {
                return null;
            }
            tn = new TreeNode(value);
            tn.left = createBinaryTreeByArray(array, 2*index+1);
            tn.right = createBinaryTreeByArray(array, 2*index+2);
            return tn;
        }
        return tn;
    }

    public static List<Integer> BFSByQueue(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        //存放遍历结果，然后返回
        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();

            /*
            处理 TreeNode 节点 的逻辑
             */
            if(treeNode != null){
                result.add(treeNode.val);
                queue.add(treeNode.left);
                queue.add(treeNode.right);
            }else{
                result.add(null);
            }
        }
        return result;
    }
}
