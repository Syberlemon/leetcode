package com.wh.leetcode.editor.cn;
//当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组： 
//
// 
// 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]； 
// 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。 
// 
//
// 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。 
//
// 返回 A 的最大湍流子数组的长度。 
//
// 
//
// 示例 1： 
//
// 输入：[9,4,2,10,7,8,8,1,9]
//输出：5
//解释：(A[1] > A[2] < A[3] > A[4] < A[5])
// 
//
// 示例 2： 
//
// 输入：[4,8,12,16]
//输出：2
// 
//
// 示例 3： 
//
// 输入：[100]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 40000 
// 0 <= A[i] <= 10^9 
// 
// Related Topics 数组 动态规划 Sliding Window 
// 👍 101 👎 0

public class LongestTurbulentSubarray {

    public static void main(String[] args) {
        Solution solution = new LongestTurbulentSubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 写不出来，是又要考虑挪动，又要考虑相等
         * 以为(arr[i] - arr[i - 1]) * (arr[i + 1] - arr[i])<0 是捷径，这一步涉及三个数，就把问题复杂化了
         * @param arr
         * @return
         */
        public int maxTurbulenceSize(int[] arr) {
            int max = 0;
            if (arr.length == 1) {
                return 1;
            }
            if (arr.length == 2) {
                if (arr[0] != arr[1]) {
                    return 2;
                } else {
                    return 1;
                }
            }
            //判断从第一个元素的起始计数值
            int count = 1;
            if (arr[0] != arr[1]) {
                count = 2;
            }
            for (int i = 1; i < arr.length - 1; i++) {
                int res = (arr[i] - arr[i - 1]) * (arr[i + 1] - arr[i]);
                if (res < 0) { //满足湍流
                    count += 1;
                    if (count > max) {
                        max = count;
                    }
                } else if (res > 0) { //不满足湍流，但是不相等
                    if (count > max) {
                        max = count;
                    }
                    count = 2;
                } else { //下一个元素和当前值相等
                    // 这里判断不明白了。。。
                    count = 1;
                }
            }
            return max;
        }

        public int maxTurbulenceSize_answer(int[] arr) {
            if (arr.length == 1) {
                return 1;
            }
            int max = 1;
            int[] increase = new int[arr.length];
            int[] decrease = new int[arr.length];
            increase[0] = 1;
            decrease[0] = 1;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] < arr[i]) {
                    increase[i] = decrease[i - 1] + 1;
                    decrease[i] = 1;
                } else if (arr[i - 1] > arr[i]) {
                    increase[i] = 1;
                    decrease[i] = increase[i - 1] + 1;
                } else {
                    increase[i] = 1;
                    decrease[i] = 1;
                }
                max = Math.max(max, Math.max(increase[i], decrease[i]));
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}