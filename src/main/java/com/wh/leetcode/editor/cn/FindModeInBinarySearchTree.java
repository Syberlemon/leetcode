package com.wh.leetcode.editor.cn;
//给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。 
//
// 假定 BST 有如下定义： 
//
// 
// 结点左子树中所含结点的值小于等于当前结点的值 
// 结点右子树中所含结点的值大于等于当前结点的值 
// 左子树和右子树都是二叉搜索树 
// 
//
// 例如： 
//给定 BST [1,null,2,2], 
//
//    1
//    \
//     2
//    /
//   2
// 
//
// 返回[2]. 
//
// 提示：如果众数超过1个，不需考虑输出顺序 
//
// 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内） 
// Related Topics 树 
// 👍 186 👎 0

import com.alibaba.fastjson.JSONObject;
import com.wh.leetcode.editor.util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindModeInBinarySearchTree {

    public static void main(String[] args) {
        Solution solution = new FindModeInBinarySearchTree().new Solution();
        Integer[] array = new Integer[]{1,null,2};
        TreeNode root = TreeNode.createBinaryTreeByArray(array, 0);
        System.out.println(JSONObject.toJSONString(solution.findMode2(root)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
//    public class TreeNode {
//
//        int val;
//        TreeNode left;
//        TreeNode right;
//
//        TreeNode(int x) {
//            val = x;
//        }
//    }

    class Solution {

        //中序遍历，计数，然后遍历计数结果输出众数
        public int[] findMode(TreeNode root) {
            Map<Integer, Integer> countMap = new HashMap<>();
            if(root == null){
                return new int[]{};
            } else {
                getVal(root, countMap);
            }
            int maxCount = 0;
            List<Integer> tempRes = new ArrayList<>();
            for (Integer key: countMap.keySet()) {
                if(countMap.get(key) > maxCount){
                    maxCount = countMap.get(key);
                    tempRes = new ArrayList<>();
                    tempRes.add(key);
                } else if(countMap.get(key) == maxCount){
                    tempRes.add(key);
                }
            }
            int[] res = new int[tempRes.size()];
            for(int i = 0; i < tempRes.size(); i++){
                res[i]= tempRes.get(i);
            }
            return res;
        }

        private void getVal(TreeNode node, Map<Integer, Integer> countMap) {

            if (node != null) {
                if(countMap.containsKey(node.val)){
                    countMap.put(node.val, countMap.get(node.val)+1);
                } else {
                    countMap.put(node.val, 1);
                }
            }

            if (node.left != null) {
                getVal(node.left, countMap);
            }

            if (node.right != null) {
                getVal(node.right, countMap);
            }
        }


        /**
         * 思路：
         * 1、所有众数，则未必只有一个数
         * 2、二叉搜索树是有序的，众数在中序遍历必然是挨着的
         */
        //众数的数量
        private int mostCount = 0;
        //当前元素的数量
        private int numCount = 0;
        //记录众数，因为可能有多个所以用一个list
        private List<Integer> modes = new ArrayList<>();
        //记录前一个节点，和当前节点比较
        private TreeNode pre = null;
        public int[] findMode2(TreeNode root) {
            if(root == null){
                return new int[]{};
            }
            dfs(root);
            int[] res = new int[modes.size()];
            for(int i = 0; i < modes.size(); i++){
                res[i] = modes.get(i);
            }
            return res;
        }

        private void dfs(TreeNode node){
            if(node == null){
                return ;
            }
            dfs(node.left);
            if(pre == null){//初始化
                pre = node;
                numCount = 1;
                modes.add(node.val);
            } else if(pre.val == node.val){//当前值和上个数相同，继续计数
                numCount += 1;
            } else{//当前值和上个值不同，重新计数
                pre = node;
                numCount = 1;
            }
            //判断当前值计数是否超过了众数计数
            if(numCount == mostCount){
                modes.add(node.val);
            }
            if(numCount > mostCount){
                mostCount = numCount;
                modes = new ArrayList<>();
                modes.add(node.val);
            }
            dfs(node.right);
        }

        /**
         * 答案收货：1、list转数组，是写for循环逐个赋值的，没有用工具方法
         * 2、不需要记录前一个节点，记录前一个值即可。我写代码因为函数外拿不到root，故pre有空指针问题，初始化搞得易出错
         * 3、对于当前值的处理，单独写一个方法，代码条理更清晰
         * 4、起名字，如answer，count，maxCount
         * Morris解法感觉复杂，不抄了
         */
        List<Integer> answer = new ArrayList<>();
        int base,count,maxCount;
        public int[] findMode_answer(TreeNode root){
            dfs_answer(root);
            int[] mode = new int[answer.size()];
            for (int i = 0; i < answer.size(); i++){
                mode[i] = answer.get(i);
            }
            return mode;
        }
        public void dfs_answer(TreeNode o){
            if(o == null){
                return;
            }
            dfs_answer(o.left);
            update(o.val);
            dfs_answer(o.right);
        }
        public void update(int x){
            if(x == base){
                ++count;
            } else {
                count = 1;
                base = x;
            }
            if(count == maxCount){
                answer.add(x);
            }
            if(count > maxCount){
                maxCount = count;
                answer.clear();
                answer.add(base);
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}