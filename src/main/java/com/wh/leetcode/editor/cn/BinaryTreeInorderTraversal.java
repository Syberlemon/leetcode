package com.wh.leetcode.editor.cn;
//给定一个二叉树，返回它的中序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 723 👎 0

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {
        Solution solution = new BinaryTreeInorderTraversal().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode
     * right; TreeNode() {} TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left,
     * TreeNode right) { this.val = val; this.left = left; this.right = right; } }
     */

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {

        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root != null) {
                traversal(root, list);
            }
            return list;
        }

        private void traversal(TreeNode node, List<Integer> list) {
            if (node != null) {
                traversal(node.left, list);
                list.add(node.val);
                traversal(node.right, list);
            }
        }

        //TODO 递归结束条件不对，还需要改
        public List<Integer> inorderTraversal1(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if(root == null){
                return res;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            TreeNode ancestor = null;
            while(!stack.isEmpty()){
                TreeNode node = stack.pop();
                while(node.left != null && node.left != ancestor){
                    stack.push(node);
                    ancestor = node;
                    node = node.left;
                }
                res.add(node.val);
                if(node.right != null){
                    stack.push(node.right);
                }
            }
            return res;
        }




        //迭代算法，可以用栈，中序遍历要左链入栈，弹出一个元素后，取其右节点后还需要左链入栈
        public List<Integer> inorderTraversal_answer(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> treeStack = new Stack<>();
            while (root != null && !treeStack.isEmpty()) {
                while (root != null) {
                    treeStack.push(root);
                    root = root.left;
                }
                root = treeStack.pop();
                res.add(root.val);
                root = root.right;
            }
            return res;
        }

        //Morris中序遍历，节省栈空间，这个解法难理解
        //root记录下一个要访问的节点 predecessor记录左子树最右节点，右节点指向root节点
        //当要往左深入的时候，需要把当前根节点记录下来，以便左边走完还能回来继续，那么当前根节点记到哪个节点后面呢？ 左子树的最右节点
        //左子树的最右节点怎么找？迭代找右节点，这个事情predecessor做
        //如果走到了最左节点，没有左孩子了，记录当前节点值，再走其右孩子
        //左子树走完后，往回退，predecessor.right为root，这个情况说明该走右子树了，由于可能没有左孩子，同时希望predecessor
        public List<Integer> inorderTraversal_answer2(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            TreeNode predecessor = null;
            while (root != null) {
                if (root.left != null) {
                    predecessor = root.left;
                    while (predecessor.right != null && predecessor.right != root) {
                        predecessor = predecessor.right;
                    }
                    if (predecessor.right == null) {
                        predecessor.right = root;//左子树的最右节点记录左子树完成后要回到的节点
                        root = root.left;
                    } else {
                        res.add(root.val);
                        root = root.right;
                        predecessor.right = null;
                        //最后这句话可以不写，因为只有root.left不为空才用到predecessor，则它必然会重新赋值，在这里赋值null对程序进展没有用
                        //写这句话的意义是把树结构还原，因为之前为了计算，将最右节点的右节点赋值了，所以需要清空
                    }
                } else {
                    res.add(root.val);
                    root = root.right;
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}