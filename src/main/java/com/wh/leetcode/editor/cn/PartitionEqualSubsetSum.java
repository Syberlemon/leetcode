package com.wh.leetcode.editor.cn;

//给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 注意: 
//
// 
// 每个数组中的元素不会超过 100 
// 数组的大小不会超过 200 
// 
//
// 示例 1: 
//
// 输入: [1, 5, 11, 5]
//
//输出: true
//
//解释: 数组可以分割成 [1, 5, 5] 和 [11].
// 
//
// 
//
// 示例 2: 
//
// 输入: [1, 2, 3, 5]
//
//输出: false
//
//解释: 数组不能分割成两个元素和相等的子集.
// 
//
// 
// Related Topics 动态规划 
// 👍 526 👎 0

import java.util.Stack;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        Solution solution = new PartitionEqualSubsetSum().new Solution();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        System.out.println(solution.canPartition(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canPartition(int[] nums) {
            //首先知道和为多少，分割两个子集，则没有元素大于和的一半 ，且一半不是小数
            //循环看能否找出几个数凑成指定和 动态规划题
            int sum = 0;
            int max = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                max = Math.max(max, nums[i]);
            }
            if (sum % 2 != 0) {
                return false;
            }
            int target = sum / 2;
            if (max > target) {
                return false;
            }
            int cnt = 0;
            Stack<Integer> stack = new Stack<>();
            int stackEnd = 0;
            int stackStart = 0;
            while (target != 0 && cnt < nums.length) {
                int temp = target - nums[cnt];
                if (temp == 0) {
                    return true;
                }
                if (stackStart == nums.length - 1) {
                    return false;
                }

                if (temp > 0) {
                    stack.push(nums[cnt]);
                    stackEnd = cnt;
                    target = temp;
                    cnt++;
                } else {
                    cnt++;
                }
                if (cnt == nums.length && !stack.isEmpty()) {
                    if (stackEnd == nums.length - 1) {
                        //将开始入栈的指针后移，所有参与循环计算的值都重新初始化
                        stackStart++;
                        stack = new Stack<>();
                        stack.push(nums[stackStart]);
                        stackEnd = stackStart;
                        cnt = stackStart + 1;
                        target = sum / 2;
                    } else {
                        int t = stack.pop();
                        target += t;
                        cnt = stackEnd + 1;
                    }

                }
            }

            return false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

